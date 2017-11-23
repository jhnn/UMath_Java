package eu.jhnn.utils.math.functions;

import java.util.Comparator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

public class KroneckerFunctions {
	
	protected static final int LOW = 0, HIGH = 1;
	
	public static class KroneckerFunction<V,R> implements Function<V,R> {
		
		protected R l, h;
		protected V t;
		protected Comparator<V> c;
		
		public KroneckerFunction(V threshold, R low, R high, Comparator<V> comparator) {
			this.t = threshold;
			this.l = low;
			this.h = high;
			this.c = comparator;
		}

		@Override
		public R apply(V v) {
			return c.compare(t, v) == 0 ? h : l;
		}
		
	}
	
	public static class KroneckerIntFunction implements IntUnaryOperator {
		
		protected int t, l, h;
		
		public KroneckerIntFunction() {
			this(0, LOW, HIGH);
		}
		
		public KroneckerIntFunction(int threshold) {
			this(threshold, LOW, HIGH);
		}
		
		public KroneckerIntFunction(int low, int high) {
			this(0, LOW, HIGH);
		}
		
		public KroneckerIntFunction(int threshold, int low, int high) {
			this.t = threshold;
			this.l = low;
			this.h = high;
		}
		
		@Override
		public int applyAsInt(int operand) {
			return (t == operand) ? h : l;
		}
	}
	
	public static class KroneckerLongFunction implements LongUnaryOperator {
		
		protected long t, l, h;
		
		public KroneckerLongFunction() {
			this(0, LOW, HIGH);
		}
		
		public KroneckerLongFunction(long threshold) {
			this(threshold, LOW, HIGH);
		}
		
		public KroneckerLongFunction(long low, long high) {
			this(0, LOW, HIGH);
		}
		
		public KroneckerLongFunction(long threshold, long low, long high) {
			this.t = threshold;
			this.l = low;
			this.h = high;
		}
		
		@Override
		public long applyAsLong(long operand) {
			return (t == operand) ? h : l;
		}
	}
	
	public static class KroneckerDoubleFunction implements DoubleUnaryOperator {
		
		protected double t, l, h;
		
		public KroneckerDoubleFunction() {
			this(0, LOW, HIGH);
		}
		
		public KroneckerDoubleFunction(double threshold) {
			this(threshold, LOW, HIGH);
		}
		
		public KroneckerDoubleFunction(double low, double high) {
			this(0, LOW, HIGH);
		}
		
		public KroneckerDoubleFunction(double threshold, double low, double high) {
			this.t = threshold;
			this.l = low;
			this.h = high;
		}
		
		@Override
		public double applyAsDouble(double arg0) {
			return (t == arg0) ? h : l;
		}
	}

}
