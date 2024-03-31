package aop;

public class MainProxy {

	public static void main(String[] args) {
		ExeCalculator call = new ExeCalculator(new impeCalculator());
		System.out.println(call.factorial(20));
		
		ExeCalculator call2 = new ExeCalculator(new RecCalculator());
		System.out.println(call2.factorial(20));
		
		//성능 체크 코드를 프록시를 만들어 체크한다. 의존성 주입 메소드를 활용한다. 
		
//		impeCalculator f1 = new impeCalculator();
//		
//		RecCalculator f2 = new RecCalculator();
//		//------------------------------------------------
//		ExeCalculator f3 = new ExeCalculator(f1);
//		System.out.println(f3.factorial(5));
//		
//		ExeCalculator f4 = new ExeCalculator(f2);
//		System.out.println(f4.factorial(5));
	}
}
