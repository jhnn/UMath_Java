package eu.jhnn.utils.math.functions;

import java.util.Comparator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

public class HeavisideFunctions {

	protected static int THRESHOLD = 0, LOW = 0, HIGH = 1;

	public static class HeavisideFunction<V, R> implements Function<V, R> {

		protected R l, h;
		protected V t;
		protected Comparator<V> c;

		public HeavisideFunction(V threshold, R low, R high, Comparator<V> comparator) {
			l = low;
			h = high;
			t = threshold;
			c = comparator;
		}

		@Override
		public R apply(V v) {
			return (c.compare(t, v) < 0) ? l : h;
		}

	}

	public static class HeavisideIntOperator implements IntUnaryOperator {
		protected int t, l, h;

		public HeavisideIntOperator() {
			this(THRESHOLD, LOW, HIGH);
		}

		public HeavisideIntOperator(int threshold) {
			this(threshold, LOW, HIGH);
		}

		public HeavisideIntOperator(int low, int high) {
			this(THRESHOLD, low, high);
		}

		public HeavisideIntOperator(int threshold, int low, int high) {
			t = threshold;
			l = low;
			h = high;
		}

		@Override
		public int applyAsInt(int operand) {
			return (t < operand) ? l : h;
		}
	}

	public static class HeavisideLongOperator implements LongUnaryOperator {
		protected long t, l, h;

		public HeavisideLongOperator() {
			this(THRESHOLD, LOW, HIGH);
		}

		public HeavisideLongOperator(long threshold) {
			this(threshold, LOW, HIGH);
		}

		public HeavisideLongOperator(long low, long high) {
			this(THRESHOLD, low, high);
		}

		public HeavisideLongOperator(long threshold, long low, long high) {
			t = threshold;
			l = low;
			h = high;
		}

		@Override
		public long applyAsLong(long operand) {
			return (t < operand) ? l : h;
		}
	}

	/**
	 * Heaviside function
	 */
	public static class HeavisideDoubleOperator implements DoubleUnaryOperator {

		protected double t, l, h;

		public HeavisideDoubleOperator() {
			this(THRESHOLD, LOW, HIGH);
		}

		public HeavisideDoubleOperator(double threshold) {
			this(threshold, LOW, HIGH);
		}

		public HeavisideDoubleOperator(double low, double high) {
			this(THRESHOLD, low, high);
		}

		/**
		 * Compares the input to a threshold.
		 * 
		 * @param threshold
		 * @param low
		 *            Value returned if input is less than threshold.
		 * @param high
		 *            Value returned if input is greater or equal to threshold.
		 */
		public HeavisideDoubleOperator(double threshold, double low, double high) {
			this.t = threshold;
			this.l = low;
			this.h = high;
		}

		@Override
		public double applyAsDouble(double operand) {
			return (t < operand) ? l : h;
		}
	}
}
