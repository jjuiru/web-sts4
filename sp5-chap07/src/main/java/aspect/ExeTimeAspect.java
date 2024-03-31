package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
// @Order(1)
public class ExeTimeAspect { // 프록시를 사용하지 않고 aop를 사용하는 코드

	@Pointcut("execution(public * chap07..*(..))") // execution 은 실행시 다음과 같이 진행하겠다는 뜻>>인터페이스를 이용해(bean확인) 프록시를 생성한다. 
	private void publicTarget() {
	}

	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()),
					(finish - start));
		}
	}

}
