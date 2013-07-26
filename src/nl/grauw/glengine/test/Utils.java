package nl.grauw.glengine.test;

import junit.framework.TestCase;

public class Utils {
	
	/**
	 * Asserts that two floating points are equal within a margin of error.
	 * 
	 * This can be used to compensate for inequality caused by accumulated
	 * floating point math errors.
	 * 
	 * The error margin is specified in ULPs (units of least precision).
	 * A one-ULP difference means there are no representable floats in between.
	 * E.g. 0f and 1.4e-45f are one ULP apart. So are -6.1340704f and -6.13407f.
	 * Depending on the number of calculations involved, typically a margin of
	 * 1-5 ULPs should be enough.
	 * 
	 * @param expected The expected value.
	 * @param actual The actual value.
	 * @param maxUlps The maximum difference in ULPs.
	 */
	public static void assertFloatEquals(float expected, float actual, int maxUlps) {
		int expectedBits = Float.floatToIntBits(expected) < 0 ? 0x80000000 - Float.floatToIntBits(expected) : Float.floatToIntBits(expected);
		int actualBits = Float.floatToIntBits(actual) < 0 ? 0x80000000 - Float.floatToIntBits(actual) : Float.floatToIntBits(actual);
		int difference = expectedBits > actualBits ? expectedBits - actualBits : actualBits - expectedBits;
		
		if (Float.isNaN(expected) || Float.isNaN(actual))
			TestCase.fail("Not a number, expected: <" + expected + ">, actual: <" + actual + ">");
		if (difference > maxUlps)
			TestCase.assertEquals(expected, actual);
	}
	
	/**
	 * Asserts that two floating points are equal within a margin of 5 ULPs.
	 * 
	 * @param expected The expected value.
	 * @param actual The actual value.
	 * @see Utils#assertFloatEquals(float, float, int)
	 */
	public static void assertFloatEquals(float expected, float actual) {
		assertFloatEquals(expected, actual, 5);
	}
	
	/**
	 * Asserts that two double precision floats are equal within a margin of error.
	 * 
	 * @param expected The expected value.
	 * @param actual The actual value.
	 * @param maxUlps The maximum difference in ULPs.
	 * @see Utils#assertFloatEquals(float, float, int)
	 */
	public static void assertDoubleEquals(double expected, double actual, long maxUlps) {
		long expectedBits = Double.doubleToLongBits(expected) < 0 ? 0x8000000000000000L - Double.doubleToLongBits(expected) : Double.doubleToLongBits(expected);
		long actualBits = Double.doubleToLongBits(actual) < 0 ? 0x8000000000000000L - Double.doubleToLongBits(actual) : Double.doubleToLongBits(actual);
		long difference = expectedBits > actualBits ? expectedBits - actualBits : actualBits - expectedBits;
		
		if (Double.isNaN(expected) || Double.isNaN(actual))
			TestCase.fail("Not a number, expected: <" + expected + ">, actual: <" + actual + ">");
		if (difference > maxUlps)
			TestCase.assertEquals(expected, actual);
	}
	
	/**
	 * Asserts that two double precision floats are equal within a margin of 5 ULPs.
	 * 
	 * @param expected The expected value.
	 * @param actual The actual value.
	 * @see Utils#assertFloatEquals(float, float, int)
	 */
	public static void assertDoubleEquals(double expected, double actual) {
		assertDoubleEquals(expected, actual, 5);
	}
	
}
