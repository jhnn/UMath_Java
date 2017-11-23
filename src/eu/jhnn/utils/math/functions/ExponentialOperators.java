package eu.jhnn.utils.math.functions;

import java.util.function.DoubleUnaryOperator;

/**
 * f(x) = exp(x)
 * 
 * @author jhnn
 *
 */
public class ExponentialOperators {
	
	public static class DoubleExponentialOperator implements DoubleUnaryOperator {

		@Override
		public double applyAsDouble(double arg0) {
			return Math.exp(arg0);
		}
		
	}

}
