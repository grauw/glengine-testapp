package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix.AnyMatrix;
import nl.grauw.glengine.model.Vector4;

public class Vector4Test extends TestCase {
	
	public void testEquals() {
		Vector4 vector1 = new Vector4(4, 5, 6, 7);
		Vector4 vector2 = new Vector4(4, 5, 6, 7);
		assertEquals(true, vector1.equals(vector2));
	}
	
	public void testEquals_Different() {
		Vector4 vector = new Vector4(4, 5, 6, 7);
		assertEquals(false, vector.equals(new Vector4(9, 5, 6, 7)));
		assertEquals(false, vector.equals(new Vector4(4, 9, 6, 7)));
		assertEquals(false, vector.equals(new Vector4(4, 5, 9, 7)));
		assertEquals(false, vector.equals(new Vector4(4, 5, 6, 9)));
	}
	
	public void testEquals_Matrix() {
		Vector4 vector = new Vector4(4, 5, 6, 7);
		Object matrix = new AnyMatrix(1, 4, 4, 5, 6, 7);
		assertEquals(true, vector.equals(matrix));
	}
	
	public void testEquals_Objects() {
		Object vector1 = new Vector4(4, 5, 6, 7);
		Object vector2 = new Vector4(4, 5, 6, 7);
		assertEquals(true, vector1.equals(vector2));
	}
	
	public void testEquals_Self() {
		Vector4 vector = new Vector4(4, 5, 6, 7);
		assertEquals(true, vector.equals(vector));
	}
	
}
