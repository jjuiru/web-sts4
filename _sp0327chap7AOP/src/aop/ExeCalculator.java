package aop;

public class ExeCalculator implements Calculator{
	private Calculator delegate;
	
	public ExeCalculator(Calculator delegate) { // 생성자에 의존성 주입
		this.delegate=delegate;
	}

	@Override
	public long factorial(long num) {
		long start= System.nanoTime(); // 시간계산하는 코드를 넣는다.
		long result=delegate.factorial(num); // 매개변수 주입값에 따라 impe와 rec의 결과가 나타난다. 
		long end=System.nanoTime();
		System.out.printf("%s.fac(%d) 실행시간=%d\n", delegate.getClass().getSimpleName(),num,(end-start));
		return result;
	}
}
