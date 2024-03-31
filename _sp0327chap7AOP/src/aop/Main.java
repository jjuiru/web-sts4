package aop;
//152p
public class Main {

	public static void main(String[] args) {
		impeCalculator f1 = new impeCalculator();
//		System.out.println(f1.factorial(5));
		
		RecCalculator f2 = new RecCalculator();
//		System.out.println(f2.factorial(5));
		//------------------------------------------------
		ExeCalculator f3 = new ExeCalculator(f1);
		System.out.println(f3.factorial(5));
		
		ExeCalculator f4 = new ExeCalculator(f2);
		System.out.println(f4.factorial(5));

	}

}
