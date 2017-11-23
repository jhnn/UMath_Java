package eu.jhnn.utils.math.functions;

import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

public class MultiplicationOperators {

	public static class IntegerMultiplicatorOperator implements IntBinaryOperator {

		@Override
		public int applyAsInt(int arg0, int arg1) {
			return Math.multiplyExact(arg0, arg1);
		}

	}

	public static class LongMultiplicatorOperator implements LongBinaryOperator {

		@Override
		public long applyAsLong(long arg0, long arg1) {
			return Math.multiplyExact(arg0, arg1);
		}
	}

	public static class DoubleMultiplicatorOperator implements DoubleBinaryOperator {

		@Override
		public double applyAsDouble(double arg0, double arg1) {
			return arg0 * arg1;
		}
	}
}
