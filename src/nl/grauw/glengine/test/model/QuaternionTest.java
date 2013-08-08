package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Quaternion;

public class QuaternionTest extends TestCase {
	
	public void testEquals() {
		Quaternion quaternion1 = new Quaternion.Axis(4, 5, 6, 7);
		Quaternion quaternion2 = new Quaternion.Axis(4, 5, 6, 7);
		assertEquals(true, quaternion1.equals(quaternion2));
	}
	
	public void testEquals_Different() {
		Quaternion quaternion = new Quaternion.Axis(4, 5, 6, 7);
		assertEquals(false, quaternion.equals(new Quaternion.Axis(9, 5, 6, 7)));
		assertEquals(false, quaternion.equals(new Quaternion.Axis(4, 9, 6, 7)));
		assertEquals(false, quaternion.equals(new Quaternion.Axis(4, 5, 9, 7)));
		assertEquals(false, quaternion.equals(new Quaternion.Axis(4, 5, 6, 9)));
	}
	
	public void testEquals_Objects() {
		Object quaternion1 = new Quaternion.Axis(4, 5, 6, 7);
		Object quaternion2 = new Quaternion.Axis(4, 5, 6, 7);
		assertEquals(true, quaternion1.equals(quaternion2));
	}
	
	public void testEquals_Self() {
		Quaternion quaternion = new Quaternion.Axis(4, 5, 6, 7);
		assertEquals(true, quaternion.equals(quaternion));
	}
	
}
