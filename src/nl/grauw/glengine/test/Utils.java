package nl.grauw.glengine.test;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix;
import nl.grauw.glengine.model.Quaternion;

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
	public static void assertEqualsish(float expected, float actual, int maxUlps) {
		if (Float.isNaN(expected) || Float.isNaN(actual))
			TestCase.fail("Not a number, expected: <" + expected + ">, actual: <" + actual + ">");
		
		long difference = getULPDifference(expected, actual);
		if (difference > maxUlps)
			TestCase.assertEquals("ULP difference: " + difference + ",", expected, actual);
	}
	
	/**
	 * Asserts that two floating points are equal within a margin of 5 ULPs.
	 * 
	 * @param expected The expected value.
	 * @param actual The actual value.
	 * @see Utils#assertEqualsish(float, float, int)
	 */
	public static void assertEqualsish(float expected, float actual) {
		assertEqualsish(expected, actual, 5);
	}
	
	/**
	 * Asserts that two double precision floats are equal within a margin of error.
	 * 
	 * @param expected The expected value.
	 * @param actual The actual value.
	 * @param maxUlps The maximum difference in ULPs.
	 * @see Utils#assertEqualsish(float, float, int)
	 */
	public static void assertEqualsish(double expected, double actual, long maxUlps) {
		if (Double.isNaN(expected) || Double.isNaN(actual))
			TestCase.fail("Not a number, expected: <" + expected + ">, actual: <" + actual + ">");
		
		long difference = getULPDifference(expected, actual);
		if (difference > maxUlps)
			TestCase.assertEquals("ULP difference: " + difference + ",", expected, actual);
	}
	
	/**
	 * Asserts that two double precision floats are equal within a margin of 5 ULPs.
	 * 
	 * @param expected The expected value.
	 * @param actual The actual value.
	 * @see Utils#assertEqualsish(float, float, int)
	 */
	public static void assertEqualsish(double expected, double actual) {
		assertEqualsish(expected, actual, 5);
	}
	
	public static void assertEqualsish(Matrix expected, Matrix actual, int maxUlps) {
		if (actual.getColumns() != expected.getColumns() || actual.getRows() != expected.getRows())
			TestCase.assertEquals(expected, actual);
		
		float[] expectedElements = expected.getElements();
		float[] actualElements = actual.getElements();
		for (int i = 0; i < expectedElements.length; i++) {
			if (Float.isNaN(actualElements[i]) || Float.isNaN(actualElements[i]))
				TestCase.assertEquals(expected, actual);
			if (getULPDifference(expectedElements[i], actualElements[i]) > maxUlps)
				TestCase.assertEquals(expected, actual);
		}
	}
	
	public static void assertEqualsish(Matrix expected, Matrix actual) {
		assertEqualsish(expected, actual, 5);
	}
	
	public static void assertEqualsish(Quaternion expected, Quaternion actual, int maxUlps) {
		float[] expectedElements = new float[] { expected.getX(), expected.getY(), expected.getZ(), expected.getW() };
		float[] actualElements = new float[] { actual.getX(), actual.getY(), actual.getZ(), actual.getW() };
		for (int i = 0; i < expectedElements.length; i++) {
			if (Float.isNaN(actualElements[i]) || Float.isNaN(actualElements[i]))
				TestCase.assertEquals(expected, actual);
			if (getULPDifference(expectedElements[i], actualElements[i]) > maxUlps)
				TestCase.assertEquals(expected, actual);
		}
	}
	
	public static void assertEqualsish(Quaternion expected, Quaternion actual) {
		assertEqualsish(expected, actual, 5);
	}
	
	public static int getULPDifference(float expected, float actual) {
		int expectedBits = Float.floatToIntBits(expected) < 0 ? 0x80000000 - Float.floatToIntBits(expected) : Float.floatToIntBits(expected);
		int actualBits = Float.floatToIntBits(actual) < 0 ? 0x80000000 - Float.floatToIntBits(actual) : Float.floatToIntBits(actual);
		return expectedBits > actualBits ? expectedBits - actualBits : actualBits - expectedBits;
	}
	
	public static long getULPDifference(double expected, double actual) {
		long expectedBits = Double.doubleToLongBits(expected) < 0 ? 0x8000000000000000L - Double.doubleToLongBits(expected) : Double.doubleToLongBits(expected);
		long actualBits = Double.doubleToLongBits(actual) < 0 ? 0x8000000000000000L - Double.doubleToLongBits(actual) : Double.doubleToLongBits(actual);
		return expectedBits > actualBits ? expectedBits - actualBits : actualBits - expectedBits;
	}
	
}
