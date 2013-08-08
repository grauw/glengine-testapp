package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix.AnyMatrix;
import nl.grauw.glengine.model.Vector2;

public class Vector2Test extends TestCase {
	
	public void testEquals() {
		Vector2 vector1 = new Vector2(4, 5);
		Vector2 vector2 = new Vector2(4, 5);
		assertEquals(true, vector1.equals(vector2));
	}
	
	public void testEquals_Different() {
		Vector2 vector = new Vector2(4, 5);
		assertEquals(false, vector.equals(new Vector2(9, 5)));
		assertEquals(false, vector.equals(new Vector2(4, 9)));
	}
	
	public void testEquals_Matrix() {
		Vector2 vector = new Vector2(4, 5);
		Object matrix = new AnyMatrix(1, 2, 4, 5);
		assertEquals(true, vector.equals(matrix));
	}
	
	public void testEquals_Objects() {
		Object vector1 = new Vector2(4, 5);
		Object vector2 = new Vector2(4, 5);
		assertEquals(true, vector1.equals(vector2));
	}
	
	public void testEquals_Self() {
		Vector2 vector = new Vector2(4, 5);
		assertEquals(true, vector.equals(vector));
	}
	
}
