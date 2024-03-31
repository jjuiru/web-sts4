package aop;

public class RecCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		long start= System.nanoTime();
		if(num==0)
			return 1;
		else {
		return num*factorial(num-1); //재귀호출 동일한 팩토리얼 코드 (조건값이 점점 작아진다.) 
		}
	}
}
