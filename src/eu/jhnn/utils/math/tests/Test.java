package eu.jhnn.utils.math.tests;

import static org.junit.Assert.*;

import java.util.function.DoubleUnaryOperator;

import eu.jhnn.utils.math.MUtils;
import eu.jhnn.utils.math.functions.suppliers.IdentifierSupplier.LongIdentifierSupplier;

public class Test {
	
	@org.junit.Test
	public void testSuppliers() {
		LongIdentifierSupplier longIdentifierSupplier = new LongIdentifierSupplier();
		assertEquals(0L, longIdentifierSupplier.getAsLong());
		assertEquals(1L, longIdentifierSupplier.getAsLong());
		assertEquals(2L, longIdentifierSupplier.getAsLong());
		
		longIdentifierSupplier = new LongIdentifierSupplier(5L);
		assertEquals(5L, longIdentifierSupplier.getAsLong());
		assertEquals(6L, longIdentifierSupplier.getAsLong());
	}

	@org.junit.Test
	public void test() {
		DoubleUnaryOperator o1 = a -> a + 1;
		DoubleUnaryOperator o2 = a -> a + 1;

		DoubleUnaryOperator o3 = o1.andThen(o2);

		assertTrue(o3.applyAsDouble(1) == 3);
		
		for(int i = 0; i < 32; i++) {
			assertEquals(MUtils.floorLog2(1 << i), i);
			assertEquals(MUtils.ceilLog2(1 << i), i);
		}
		
		for(int i = 0; i < 64; i++) {
			assertEquals(MUtils.floorLog2(1L << i), i);
			assertEquals(MUtils.ceilLog2(1L << i), i);
		}
		
		assertEquals(MUtils.floorLog2(55), 5);
		assertEquals(MUtils.ceilLog2(55), 6);
	}

	@org.junit.Test
	public void deBruijneTest() {
		char[] seq1 = MUtils.deBruijnSequence("abcd".toCharArray(), 2);
		assertEquals("aabacadbbcbdccdd", new String(seq1));

		char[] seq2 = MUtils.deBruijnSequence("01".toCharArray(), 3);
		assertEquals("00010111", new String(seq2));

		assertEquals(0b01, MUtils.deBruijnSequence((byte) 1));
		assertEquals(0b0011, MUtils.deBruijnSequence((byte) 2));
		assertEquals(0b00010111, MUtils.deBruijnSequence((byte) 3));
		assertEquals(0b0000101001101111, MUtils.deBruijnSequence((byte) 4));
		assertEquals(0b00000101011010010001100111011111, MUtils.deBruijnSequence((byte) 5));
	}

	@org.junit.Test
	public void powerOfTwoTest() {
		assertFalse(MUtils.isPowerOfTwo(0));
		assertTrue(MUtils.isPowerOfTwo(1));
		assertTrue(MUtils.isPowerOfTwo(2));
		assertFalse(MUtils.isPowerOfTwo(3));
		assertTrue(MUtils.isPowerOfTwo(8));

		assertTrue(MUtils.isPowerOfTwo(1L << 42));
		assertFalse(MUtils.isPowerOfTwo(42L));
	}

	@org.junit.Test
	public void miscTest() {
		long sequence = 0b010010;
		long extended = MUtils.extend(sequence, 6, 2);
		assertEquals(0b1001_0010, extended);

		extended = MUtils.extend(0b001, 3, 2);
		assertEquals(0b01001, extended);

		assertFalse(MUtils.containsPattern(0b0, (byte) 1, 0b1, (byte) 1));
		assertTrue(MUtils.containsPattern(0b0, (byte) 1, 0b0, (byte) 1));
		assertFalse(MUtils.containsPattern(0b1, (byte) 1, 0b0, (byte) 1));
		assertTrue(MUtils.containsPattern(0b1, (byte) 1, 0b1, (byte) 1));

		assertFalse(MUtils.containsPattern(0b00, (byte) 2, 0b1, (byte) 1));
		assertTrue(MUtils.containsPattern(0b01, (byte) 2, 0b1, (byte) 1));
		assertTrue(MUtils.containsPattern(0b10, (byte) 2, 0b1, (byte) 1));
		assertTrue(MUtils.containsPattern(0b11, (byte) 2, 0b1, (byte) 1));

		assertFalse(MUtils.containsPattern(0b0101_0100, (byte) 8, 0b001, (byte) 3));
		assertTrue(MUtils.containsPattern(0b1111_0000, (byte) 8, 0b111000, (byte) 6));
		assertTrue(MUtils.containsPattern(0b1101_1111, (byte) 8, 0b01, (byte) 2));
		assertTrue(MUtils.containsPattern(0b0110_1001, (byte) 8, 0b1010, (byte) 4));

		assertTrue(MUtils._containsPatternWrapped(0b0101_0100, (byte) 8, 0b001, (byte) 3));
	}

}
