package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix2x2;
import nl.grauw.glengine.model.Vector2;

public class Matrix2x2Test extends TestCase {
	
	public void testGetColumn() {
		Matrix2x2 matrix = new Matrix2x2(4, 5, 6, 7);
		assertEquals(new Vector2(4, 5), matrix.getColumn(0));
		assertEquals(new Vector2(6, 7), matrix.getColumn(1));
	}
	
	public void testGetRow() {
		Matrix2x2 matrix = new Matrix2x2(4, 5, 6, 7);
		assertEquals(new Vector2(4, 6), matrix.getRow(0));
		assertEquals(new Vector2(5, 7), matrix.getRow(1));
	}
	
	public void testIdentity() {
		Matrix2x2 matrix = Matrix2x2.IDENTITY;
		Vector2 vector = new Vector2(4, 5);
		Vector2 product = matrix.multiply(vector);
		assertEquals(vector, product);
	}
	
	public void testTranslation() {
		Matrix2x2 matrix = new Matrix2x2.Translation(2);
		Vector2 vector = new Vector2(4, 1);
		Vector2 product = matrix.multiply(vector);
		assertEquals(new Vector2(6, 1), product);
	}
	
	public void testTranslation_Multiply() {
		Matrix2x2 translation = new Matrix2x2.Translation(11);
		Matrix2x2 reference = new Matrix2x2(1, 0, 11, 1);
		Matrix2x2 operand = new Matrix2x2(4, 5, 6, 7);
		
		assertEquals(reference.multiply(operand), translation.multiply(operand));
	}
	
	public void testScale() {
		Matrix2x2 matrix = new Matrix2x2.Scale(3, 0.2f);
		Vector2 vector = new Vector2(4, 5);
		Vector2 product = matrix.multiply(vector);
		assertEquals(new Vector2(12, 1), product);
	}
	
	public void testScale_Multiply() {
		Matrix2x2 scale = new Matrix2x2.Scale(11, 23);
		Matrix2x2 reference = new Matrix2x2(11, 0, 0, 23);
		Matrix2x2 operand = new Matrix2x2(4, 5, 6, 7);
		
		assertEquals(reference.multiply(operand), scale.multiply(operand));
	}
	
	public void testRotation() {
		Matrix2x2 matrix = new Matrix2x2.Rotation(Math.PI / 2);
		Vector2 vector = new Vector2(0, 1);
		Vector2 product = matrix.multiply(vector);
		float inpreciseZero = (float)Math.cos(Math.PI / 2);
		assertEquals(new Vector2(-1, inpreciseZero), product);
	}
	
}
