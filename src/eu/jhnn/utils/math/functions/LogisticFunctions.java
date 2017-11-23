package eu.jhnn.utils.math.functions;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

import eu.jhnn.utils.math.Operators.Multiplicator;
import eu.jhnn.utils.math.Operators.Summand;

public class LogisticFunctions {
	
	public static class LogisticFunction<V, R> implements Function<V,R> {

		protected Multiplicator<Number, R> a;
		protected Multiplicator<V, Number> b;
		protected Summand<V> c;
		protected Summand<R> d;
		
		public LogisticFunction(Multiplicator<Number, R> a, Multiplicator<V, Number> b, Summand<V> c, Summand<R> d) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}
		
		@Override
		public R apply(V v) {
			Number n = b.mul(c.add(v));
			double et = Math.exp(n.doubleValue());
			double k = et / (1 + et);
			R r = a.mul(k);
			return d.add(r);
		}
		
		public Multiplicator<Number, R> getA() {
			return a;
		}
		
		public void setA(Multiplicator<Number, R> a) {
			this.a = a;
		}
		
		public Multiplicator<V, Number> getB() {
			return b;
		}
		
		public void setB(Multiplicator<V, Number> b) {
			this.b = b;
		}
		
		public Summand<V> getC() {
			return c;
		}
		
		public void setC(Summand<V> c) {
			this.c = c;
		}
		
		public Summand<R> getD() {
			return d;
		}
		
		public void setD(Summand<R> d) {
			this.d = d;
		}
		
		public void set(Multiplicator<Number, R> a, Multiplicator<V, Number> b, Summand<V> c, Summand<R> d) {
			setA(a);
			setB(b);
			setC(c);
			setD(d);
		}
		
	}

	static final long A = 0, B = 0, C = 0, D = 0;
	
	public static class LogisticDoubleFunction implements DoubleUnaryOperator {
		
		protected double a, b, c, d;

		/**
		 * f(x) = a / (1 + exp(b * (x - c))) + d
		 * @param peakValue The global maxima for this function.
		 * @param minValue The minima for this function.
		 * @param steepness A value of 10 yields roughly f(0) = 0 and f(1) = peakValue.
		 * @param xShift Should be 0.5 for Math.random().
		 */
		public LogisticDoubleFunction(double peakValue, double minValue, double steepness, double xShift) {
			this.a = peakValue - minValue;
			this.b = steepness;
			this.c = xShift;
			this.d = minValue;
		}
		
		@Override
		public double applyAsDouble(double operand) {
			double et = Math.exp(b * (operand - c));
			return a * et / (1 + et) + d;
		}
	}
}