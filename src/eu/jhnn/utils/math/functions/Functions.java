package eu.jhnn.utils.math.functions;

/**
 * Semi-generic interface for functions.
 * 
 * Usage notes.
 * 
 * Use applyAfter to generate the necessary non-trivial functions. E.g.
 * 
 * f(x) = a * exp(b * x + c) + d
 * 
 * UnaryOperator o1 = LinearOperator(b,c);
 * UnaryOperator o2 = ExponentialOperator();
 * UnaryOperator o3 = LinearOperator(a,d);
 * 
 * UnaryOperator f = o1.thenApply(o2).thenApply(o3);
 */
public interface Functions {
	
	public static interface Function<V,R> {
		public R calculate(V v);
	}
}
