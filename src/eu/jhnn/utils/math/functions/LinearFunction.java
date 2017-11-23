package eu.jhnn.utils.math.functions;

import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;

public class LinearFunctions {
	
	static final int A = 1, B = 0;
	
	public static class LinearIntFunction implements IntUnaryOperator {
		
		protected int a;
		protected int b;
		
		public LinearIntFunction() {
			this(A, B);
		}
		
		public LinearIntFunction(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int applyAsInt(int arg0) {
			return a * arg0 + b;
		}
		
	}
	
	public static class LinearDoubleFunction implements DoubleUnaryOperator {
		
		protected double a, b;
		
		public LinearDoubleFunction() {
			this(A, B);
		}
		
		public LinearDoubleFunction(double a, double b) {
			this.a = a;
			this.b = b;
		}
		
		public LinearDoubleFunction(double a, double b, double c) {
			this(a, a*  b + c);
		}

		@Override
		public double applyAsDouble(double arg0) {
			return a * arg0 + b;
		}
	}

	
	public static LinearDoubleFunction points(double x1, double y1, double x2, double y2) {
		double a = (y2 - y1) / (x2 - x1);
		double b = y1 - a * x1;
		return new LinearDoubleFunction(a,b);
	}

}
