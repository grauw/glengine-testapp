package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix.AnyMatrix;
import nl.grauw.glengine.model.Vector3;

public class Vector3Test extends TestCase {
	
	public void testEquals() {
		Vector3 vector1 = new Vector3(4, 5, 6);
		Vector3 vector2 = new Vector3(4, 5, 6);
		assertEquals(true, vector1.equals(vector2));
	}
	
	public void testEquals_Different() {
		Vector3 vector = new Vector3(4, 5, 6);
		assertEquals(false, vector.equals(new Vector3(9, 5, 6)));
		assertEquals(false, vector.equals(new Vector3(4, 9, 6)));
		assertEquals(false, vector.equals(new Vector3(4, 5, 9)));
	}
	
	public void testEquals_Matrix() {
		Vector3 vector = new Vector3(4, 5, 6);
		Object matrix = new AnyMatrix(1, 3, 4, 5, 6);
		assertEquals(true, vector.equals(matrix));
	}
	
	public void testEquals_Objects() {
		Object vector1 = new Vector3(4, 5, 6);
		Object vector2 = new Vector3(4, 5, 6);
		assertEquals(true, vector1.equals(vector2));
	}
	
	public void testEquals_Self() {
		Vector3 vector = new Vector3(4, 5, 6);
		assertEquals(true, vector.equals(vector));
	}
	
}
