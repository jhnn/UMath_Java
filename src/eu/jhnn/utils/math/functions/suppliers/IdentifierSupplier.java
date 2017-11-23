package eu.jhnn.utils.math.functions.suppliers;

import java.util.function.LongSupplier;

public class IdentifierSupplier {
	
	public static class LongIdentifierSupplier implements LongSupplier {
		
		private long nextID;
		
		public LongIdentifierSupplier() {
			this(0L);
		}

		public LongIdentifierSupplier(long nextID) {
			this.nextID = nextID;
		}
		
		private synchronized long getNextID() {
			return nextID++;
		}

		@Override
		public long getAsLong() {
			return getNextID();
		}
		
	}

}
