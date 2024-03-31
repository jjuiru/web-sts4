package com.mohaji.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {
	@Before("execution(* com.mohaji.controller.*.*(..))") // execution 은 실행시 다음과 같이 진행하겠다는 뜻>>인터페이스를 이용해(bean확인) 프록시를
															// 생성한다.
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("-------------before----------------");
		System.out.println(joinPoint.getSignature().getName());
	}

	@After("execution(* com.mohaji.dao.*.*(..))") // execution 은 실행시 다음과 같이 진행하겠다는 뜻>>인터페이스를 이용해(bean확인) 프록시를 생성한다.
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("-------------after----------------");
		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		System.out.println("메소드명: " + joinPoint.getSignature().getName());
	}

	@Around("@within(org.springframework.stereotype.Controller)") // 컨트롤러 임포트문을 넣는다.
	public Object printCurrentTime(ProceedingJoinPoint pointcut) throws Throwable {
		System.out.println("==============around시작==================");
		System.out.println("처리전");
		System.out.println(System.nanoTime() + "나노초");
		Object tesult = pointcut.proceed();
		System.out.println("처리후");
		System.out.println(System.nanoTime() + "나노초");
		System.out.println("==============around끝===================");
		return pointcut.proceed();
	}
}
