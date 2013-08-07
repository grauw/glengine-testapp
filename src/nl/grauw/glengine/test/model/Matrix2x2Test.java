package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix2x2;
import nl.grauw.glengine.model.Vector2;

public class Matrix2x2Test extends TestCase {
	
	public void testGetColumn() {
		Matrix2x2 matrix = new Matrix2x2(4, 5, 6, 7);
		assertEquals(true, matrix.getColumn(0).equals(new Vector2(4, 5)));
		assertEquals(true, matrix.getColumn(1).equals(new Vector2(6, 7)));
	}
	
	public void testGetRow() {
		Matrix2x2 matrix = new Matrix2x2(4, 5, 6, 7);
		assertEquals(true, matrix.getRow(0).equals(new Vector2(4, 6)));
		assertEquals(true, matrix.getRow(1).equals(new Vector2(5, 7)));
	}
	
	public void testIdentity() {
		Matrix2x2 matrix = Matrix2x2.IDENTITY;
		Vector2 vector = new Vector2(4, 5);
		Vector2 product = matrix.multiply(vector);
		assertEquals(true, product.equals(vector));
	}
	
	public void testTranslation() {
		Matrix2x2 matrix = new Matrix2x2.Translation(2);
		Vector2 vector = new Vector2(4, 1);
		Vector2 product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Vector2(6, 1)));
	}
	
	public void testScale() {
		Matrix2x2 matrix = new Matrix2x2.Scale(3, 0.2f);
		Vector2 vector = new Vector2(4, 5);
		Vector2 product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Vector2(12, 1)));
	}
	
	public void testRotation() {
		Matrix2x2 matrix = new Matrix2x2.Rotation((float)(Math.PI / 2));
		Vector2 vector = new Vector2(0, 1);
		Vector2 product = matrix.multiply(vector);
		float inpreciseZero = (float)Math.cos((float)(Math.PI / 2));
		assertEquals(true, product.equals(new Vector2(-1, inpreciseZero)));
	}
	
}
