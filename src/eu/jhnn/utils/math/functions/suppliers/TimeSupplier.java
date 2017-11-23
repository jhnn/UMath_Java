package eu.jhnn.utils.math.functions.suppliers;

import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;

public class TimeSupplier {

	
	public static class LongTimeSupplier implements LongSupplier {
		
		private long offset;
		
		public static LongTimeSupplier elapsedLocal() {
			return new LongTimeSupplier(System.currentTimeMillis());
		}
		
		public static LongTimeSupplier elapsedSystem() {
			return new LongTimeSupplier(0L);
		}
		
		public LongTimeSupplier(long offset) {
			this.offset = offset;
		}

		@Override
		public long getAsLong() {
			return System.currentTimeMillis() - offset;
		}
		
	}
	
	public static class DoubleTimeSupplier implements DoubleSupplier {
		
		private long offset;
		private double scale;
		
		/**
		 * 
		 * @param offset
		 * @param scale Use 1.0 for Milliseconds, 1000.0 for seconds or similar factors.
		 */
		public DoubleTimeSupplier(long offset, double scale) {
			this.offset = offset;
			this.scale = scale;
		}

		@Override
		public double getAsDouble() {
			long t = System.currentTimeMillis() - offset;
			return scale * t;
		}
		
	}
}
