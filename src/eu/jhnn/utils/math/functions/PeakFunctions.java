package eu.jhnn.utils.math.functions;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

import eu.jhnn.utils.math.Operators.Multiplicator;

public class PeakFunctions {

	public static final double A = 1;
	public static final double B = 1;

	public static class PeakFunction<V, R> implements Function<V, R> {

		Multiplicator<V, Number> a;
		Multiplicator<Number, R> b;

		@Override
		public R apply(V t) {
			double n = a.mul(t).doubleValue();
			Number e = n * Math.exp(n);
			return b.mul(e);
		}
	}

	public static class PeakDoubleFunction implements DoubleUnaryOperator {

		protected double a, b;

		/**
		 * Function that reaches a maximal (or minimal) value.
		 * 
		 * f(x) = a * exp(1) * x / b * exp(-x / b);
		 * 
		 * @param peak
		 *            The maximum of this function
		 * @param peakValue
		 *            The position of the maximum
		 */
		public PeakDoubleFunction(double peak, double peakValue) {
			a = peak * Math.E;
			b = 1 / peakValue;
		}

		@Override
		public double applyAsDouble(double operand) {
			double x = operand * b;
			return a * x * Math.exp(-x);
		}
	}

}
