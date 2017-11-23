package eu.jhnn.utils.math.functions.suppliers;

import java.util.function.Supplier;

public class NullSupplier<T> implements Supplier<T> {

	@Override
	public T get() {
		return null;
	}

}
