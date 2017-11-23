package eu.jhnn.utils.math.functions.suppliers;

import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

import eu.jhnn.utils.math.InclusiveRandom;

public class RandomSuppliers {
	
	static final int DEFAULT_MAX = 100;
	static final int DEFAULT_MIN = 0;
	static final double DEFAULT_RANGE = 1.0;
	
	public static class IntegerRandomSupplier implements IntSupplier {
		
		private final Random random;
		private final int range;
		private final int min;
		
		public IntegerRandomSupplier() {
			this(DEFAULT_MIN, DEFAULT_MAX);
		}
		
		public IntegerRandomSupplier(int min, int max) {
			this(min, max, randomSeed());
		}
		
		/**
		 * 
		 * @param min Minimal value inclusive
		 * @param max Maximal value inclusive
		 * @param seed Random seed
		 */
		public IntegerRandomSupplier(int min, int max, long seed) {
			
			range = max - min + 1;
			if(range <= 0) {
				throw new IllegalArgumentException("Range must be postitive!");
			}
			
			if(min + range <= min) {
				throw new IllegalArgumentException("Potential overflow!");
			}
			
			this.min = min;
			random = new Random(seed);
		}

		@Override
		public int getAsInt() {
			return random.nextInt(range) + min;
		}
		
	}
	
	public static class LongRandomSupplier implements LongSupplier {

		private final long range;
		private final long min;
		private final Random random;
		
		public static LongRandomSupplier in(long min, long max) {
			long range = max - min + 1;
			return new LongRandomSupplier(range, min);
		}
		
		
		public LongRandomSupplier() {
			this(Long.MAX_VALUE, 0L);
		}
		
		public LongRandomSupplier(long range, long min) {
			this(range, min, randomSeed());
		}
		
		public LongRandomSupplier(long range, long min, long seed) {
			if(range <= 0) {
				throw new IllegalArgumentException("Range must be positive!");
			}
			
			if(min + range <= min) {
				throw new IllegalArgumentException("Potential overflow!");
			}
			
			this.range = range;
			this.min = min;
			random = new Random(seed);
		}

		@Override
		public long getAsLong() {
			if(range == Long.MAX_VALUE) {
				return random.nextLong();
			} else {
				// TODO Fix rounding issue
				double d = random.nextDouble();
				return (long) (d * range) + min;
			}
		}
		
	}
	
	public static class DoubleRandomSupplier implements DoubleSupplier {

		private final Random random;
		private final double range;
		private final double min;
		
		public DoubleRandomSupplier() {
			this(DEFAULT_RANGE, DEFAULT_MIN);
		}
		
		public DoubleRandomSupplier(double range, double min) {
			this(range, min, randomSeed(), false);
		}
		
		public DoubleRandomSupplier(double range, double min, long seed) {
			this(range, min, seed, false);
		}
		
		public DoubleRandomSupplier(double range, double min, boolean inclusive) {
			this(range, min, randomSeed(), inclusive);
		}
		
		public DoubleRandomSupplier(double range, double min, long seed, boolean inclusive) {
			this.range = range;
			this.min = min;
			if(inclusive) {
				random = new InclusiveRandom(seed);
			} else {
				random = new Random(seed);
			}
		}

		@Override
		public double getAsDouble() {
			return random.nextDouble() * range + min;
		}
		
	}
	
	static long randomSeed() {
		return new Random().nextLong();
	}

}
