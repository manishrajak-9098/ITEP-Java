package MainPackage;

public class SimpleCalculator implements Calculator {

	
	@Override
	public double add(double a, double b) {
		return a+b;
	}
	
	@Override
	public double add(double a, double b, double c ) {
		return a+b+c;
	}
	
	@Override
	public double subtract(double a, double b) {
		return a-b;
	}
	
	@Override
	public double multiply(double a, double b) {
		return a*b;
	}
	
	@Override
	public double divide(double a, double b) {
		
		if(b==0) {
			throw new ArithmeticException("divide bu zero no allowed");
		}
		return a/b;
	}
	
}
