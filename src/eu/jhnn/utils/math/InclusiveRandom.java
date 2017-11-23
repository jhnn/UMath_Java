package eu.jhnn.utils.math;

import java.util.Random;

public class InclusiveRandom extends Random {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 32983565429505649L;
	
	private final double D = 1 / (double) ((1L << 53) - 1L);
	
	public InclusiveRandom() {
		super();
	}
	
	public InclusiveRandom(long seed) {
		super(seed);
	}
	
	@Override
	public double nextDouble() {
		long l = ((long) next(26)) << 27 + next(27);
		return l * D;
	}
	
	/**
	 * Not uniformly distributed if range is greater than Integer.MAX_VALUE.
	 * @param range
	 * @return
	 */
	public long nextLong(long range) {
		if(range > Integer.MAX_VALUE) {
			return nextInt((int) range);
		} else {
			// TODO Fix if necessary
			return (long) (nextDouble() * range);
		}
	}

}
