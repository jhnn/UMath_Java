package eu.jhnn.utils.math;

public class Alphabet<T> {
	
	private final T[] symbols;
	
	public Alphabet (T[] symbols) {
		this.symbols = symbols;
	}
	
	public T[] getDeBruijnSequence(byte length) {
		byte[] sequence = MUtils.deBruijnSequence((byte) symbols.length, length);
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[sequence.length];
		for(int i = 0; i < sequence.length; i++) {
			result[i] = symbols[sequence[i]];
		}
		return result;
	}

}
