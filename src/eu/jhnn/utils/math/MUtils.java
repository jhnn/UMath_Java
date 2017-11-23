package eu.jhnn.utils.math;

import java.io.ByteArrayOutputStream;
import java.security.InvalidParameterException;
import java.util.List;

public class MUtils {

	/**
	 * Integer implementation for power function.
	 * @param a
	 * @param b
	 * @return a^b
	 */
	public static int pow(int a, int b) {
		int r = (b % 2 == 0) ? 0 : a;
		while (b > 0) {
			a = a * a;
			b = b >> 1;
			r = (b % 2 == 0) ? r : r + a;
		}
		return r;
	}

	/**
	 * @param a
	 * @param b
	 * @return The arithmetic mean of a and b.
	 */
	public static float avg(float a, float b) {
		return (a + b) / 2;
	}

	public static float[] avg(final float[] a, final float b[]) {
		final float[] result = new float[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = avg(a[i], b[i]);
		}
		return result;
	}

	/**
	 * Calculates a randomized linear average
	 * {@code (0.5 + random1) * a/2 + (0.5 + random2) * b/2}, with random1 and
	 * random2 element [0,1). <br>
	 * This is useful for cases where the result should be larger than the
	 * individual values from time to time.
	 * 
	 * @param a
	 * @param b
	 * @return A number between 0.25 and 0.75 times the sum of a and b.
	 */
	public static float rndAvg2(float a, float b) {
		return 0.5f * (float) ((0.5f + Math.random()) * a + (0.5f + Math.random()) * b);
	}

	public static float[] rndAvg2(final float[] a, final float b[]) {
		final float[] result = new float[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = avg(a[i], b[i]);
		}
		return result;
	}

	/**
	 * Calculates (0.5 + random) * value, with random element [0,1).
	 * 
	 * @param value
	 *            The input value
	 * @return A number between 0.5 and 1.5 times the value.
	 */
	public static float rndAvg(float value) {
		float rnd = (float) Math.random();
		return (0.5f + rnd) * value;
	}

	/**
	 * Calculates (0.5 + random^2) * value, with random element [0,1).
	 * 
	 * @param value
	 *            The input value
	 * @return A number between 0.5 and 1.5 times the value.
	 */
	public static float rndAvgSqr(float value) {
		float rnd = (float) Math.random();
		return (0.5f + rnd * rnd) * value;
	}

	public static <E> E getRandomElement(E[] array) {
		int rnd = (int) (Math.random() * array.length);
		return array[rnd];
	}

	public static <E> E getRandomElement(List<E> list) {
		int rnd = (int) (Math.random() * list.size());
		return list.get(rnd);
	}

	public static <E> E chooseRandom(E e1, E e2) {
		if (Math.random() < 0.5) {
			return e1;
		} else {
			return e2;
		}
	}

	public static char[] deBruijnSequence(char[] alphabet, int length) {
		byte[] sequence = MUtils.deBruijnSequence((byte) alphabet.length, length);
		char[] result = new char[sequence.length];
		for (int i = 0; i < sequence.length; i++) {
			result[i] = alphabet[sequence[i]];
		}
		return result;
	}

	/**
	 * Computes the De Bruijn sequence of order n, based on Frank Ruskey's
	 * Combinatorial Generation.
	 * 
	 * @param symbols
	 *            Number of symbols in the alphabet.
	 * @param order
	 *            The order or length of the sequences.
	 * @return An array indicating the distribution of the symbols in the sequence.
	 */
	public static byte[] deBruijnSequence(byte symbols, int order) {
		ByteArrayOutputStream sequence = new ByteArrayOutputStream();

		_dB(1, 1, order, symbols, new byte[symbols * order], sequence);

		return sequence.toByteArray();
	}

	private static void _dB(final int t, final int p, final int n, final int k, byte[] a, ByteArrayOutputStream s) {
		if (t > n) {
			if (n % p == 0) {
				for (int i = 1; i < p + 1; i++) {
					s.write(a[i]);
				}
			}
		} else {
			a[t] = a[t - p];
			_dB(t + 1, p, n, k, a, s);
			for (byte j = (byte) (a[t - p] + 1); j < k; j++) {
				a[t] = j;
				_dB(t + 1, t, n, k, a, s);
			}
		}
	}

	/**
	 * Computes the De Bruijn sequence for a n-bits binary number, based on the
	 * Prefer Opposites algorithm presented in: Alhakim, Abbas M. "A simple
	 * combinatorial algorithm for de Bruijn sequences." American Mathematical
	 * Monthly 117.8 (2010): 728-732.
	 * 
	 * @param order
	 *            The order of the sequence.
	 * @return The De Bruijn sequences as a number, left-padded with zeros.
	 */
	public static long deBruijnSequence(byte order) {
		if (order < 1) {
			throw new InvalidParameterException("Order of de Bruijn sequence must be greater than zero!");
		}

		long seq = 0;
		byte lenSeq = (byte) (order - 1);
		long maskSeq = (1L << lenSeq) - 1;

		long pat;
		byte lenPat = order;
		long maskPat = (1L << lenPat) - 1;

		while (true) {
			pat = seq & maskSeq;
			if (pat == maskSeq) {
				return (seq << 1) + 1;
			} else {
				pat = pat << 1;
			}

			long a = seq & 1L;
			pat = pat + (1 - a);

			if (_matchesPattern(seq, lenSeq, pat, lenPat, maskPat)) {
				seq = (seq << 1) + a;
			} else {
				seq = (seq << 1) + (1 - a);
			}

			lenSeq++;
		}
	}

	/**
	 * Internal method, do not use.
	 * 
	 * @param x
	 *            Sequence
	 * @param l_x
	 *            Length of sequence
	 * @param p
	 *            Pattern
	 * @param l_p
	 *            Length of pattern
	 * @param m
	 *            Mask, should be (1L << l_p) - 1
	 * @return Whether parts of the sequence are equal to the pattern.
	 */
	private static boolean _matchesPattern(long x, byte l_x, long p, byte l_p, long m) {
		for (byte i = 0; i <= (l_x - l_p); i++) {
			if (((x >> i) & m) == p) {
				return true;
			}
		}
		return false;
	}

	public static boolean _containsPatternWrapped(long s, byte l_s, long p, byte l_p) {
		s = extend(s, l_s, l_p - 1);
		l_s = (byte) (l_s + l_p - 1);
		return containsPattern(s, l_s, p, l_p);
	}

	/**
	 * 
	 * @param sequence
	 *            Must be a positive long.
	 * @param lengthSequence
	 * @param lengthExtend
	 * @return
	 */
	public static long extend(long sequence, int lengthSequence, int lengthExtend) {
		long mask = (1L << lengthExtend) - 1;
		long extension = (sequence & mask) << lengthSequence;
		return extension + sequence;
	}

	public static boolean containsPattern(long x, byte l_x, long p, byte n) {
		long mask = (1L << n) - 1;
		for (byte i = 0; i <= (l_x - n); i++) {
			if (((x >> i) & mask) == p) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the bit at the n-th position of number i.
	 * 
	 * @param i
	 * @param n
	 * @return
	 */
	public static byte getBit(int i, byte n) {
		return (byte) ((i >>> n) & 1);
	}

	/**
	 * Returns the bit at the n-th position of number l.
	 * 
	 * @param l
	 * @param n
	 * @return
	 */
	public static byte getBit(long l, byte n) {
		return (byte) ((l >>> n) & 1);
	}

	/**
	 * Checks whether a number is a power of two. Based on Sean Eron Anderson's
	 * algorithm from
	 * http://graphics.stanford.edu/~seander/bithacks.html#DetermineIfPowerOf2
	 * 
	 * @param i
	 *            The number
	 * @return False if the number is not a power of two or not positive.
	 */
	public static boolean isPowerOfTwo(int i) {
		return (i > 0) && (i & (i - 1)) == 0;
	}

	public static boolean isPowerOfTwo(long l) {
		return (l > 0L) && (l & (l - 1)) == 0;
	}
	         
	private static final int[] MultiplyDeBruijnBitPosition = {
	  0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 
	  31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9
	};
	
	/**
	 * From https://graphics.stanford.edu/~seander/bithacks.html#IntegerLogDeBruijn
	 * Based on work by Leiserson http://supertech.csail.mit.edu/papers/debruijn.pdf
	 * @param v A positive number, must be a power of two.
	 * @return The logarithm of base two.
	 */
	public static int log2DeBruijn(int v) {
		v = (int) ((v & -v) * 0x077CB531L);
		return MultiplyDeBruijnBitPosition[v >>> 27];
	}
	
	public static int floorLog2(int i) {
		return 31 - Integer.numberOfLeadingZeros(i);
	}
	
	public static int ceilLog2(int i) {
		return 32 - Integer.numberOfLeadingZeros(i - 1);
	}
	
	public static int floorLog2(long l) {
		return 63 - Long.numberOfLeadingZeros(l);
	}
	
	public static int ceilLog2(long l) {
		return 64 - Long.numberOfLeadingZeros(l - 1);
	}
}
