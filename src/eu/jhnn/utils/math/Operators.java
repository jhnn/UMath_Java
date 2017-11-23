package eu.jhnn.utils.math;

public interface Operators {

	public static interface Summand<A> {
		A add(A a);
		A get();
		Summand<A> add(Summand<A> a);
	}
	
	public static interface DoubleMultiplicator<A> {
		A mul(double d);
	}
	
	public static interface Multiplicator<A,B> {
		B mul(A a);
	}
}
