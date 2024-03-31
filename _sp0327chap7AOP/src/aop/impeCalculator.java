package aop;

public class impeCalculator implements Calculator{

	@Override
	public long factorial(long num) { // 팩토리얼 만드는 코드
		long result=1;
		for(long i=1; i<=num; i++) {
			result*=i;
		}
		return result;
	}
}
