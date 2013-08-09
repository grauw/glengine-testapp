package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix3x3;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.test.Utils;

public class Matrix3x3Test extends TestCase {
	
	public void testGetColumn() {
		Matrix3x3 matrix = new Matrix3x3(4, 5, 6, 7, 8, 9, 10, 11, 12);
		assertEquals(new Vector3(4, 5, 6), matrix.getColumn(0));
		assertEquals(new Vector3(7, 8, 9), matrix.getColumn(1));
		assertEquals(new Vector3(10, 11, 12), matrix.getColumn(2));
	}
	
	public void testGetRow() {
		Matrix3x3 matrix = new Matrix3x3(4, 5, 6, 7, 8, 9, 10, 11, 12);
		assertEquals(new Vector3(4, 7, 10), matrix.getRow(0));
		assertEquals(new Vector3(5, 8, 11), matrix.getRow(1));
		assertEquals(new Vector3(6, 9, 12), matrix.getRow(2));
	}
	
	public void testIdentity() {
		Matrix3x3 matrix = Matrix3x3.IDENTITY;
		Vector3 vector = new Vector3(4, 5, 6);
		Vector3 product = matrix.multiply(vector);
		assertEquals(vector, product);
	}
	
	public void testTranslation() {
		Matrix3x3 matrix = new Matrix3x3.Translation(2, 4);
		Vector3 vector = new Vector3(4, 5, 1);
		Vector3 product = matrix.multiply(vector);
		assertEquals(new Vector3(6, 9, 1), product);
	}
	
	public void testTranslation_Multiply() {
		Matrix3x3 translation = new Matrix3x3.Translation(11, 23);
		Matrix3x3 reference = new Matrix3x3(1, 0, 0, 0, 1, 0, 11, 23, 1);
		Matrix3x3 operand = new Matrix3x3(4, 5, 6, 7, 8, 9, 10, 11, 12);
		
		assertEquals(reference.multiply(operand), translation.multiply(operand));
	}
	
	public void testScale() {
		Matrix3x3 matrix = new Matrix3x3.Scale(3, 0.2f, -0.5f);
		Vector3 vector = new Vector3(4, 5, 6);
		Vector3 product = matrix.multiply(vector);
		assertEquals(new Vector3(12, 1, -3), product);
	}
	
	public void testScale_Multiply() {
		Matrix3x3 scale = new Matrix3x3.Scale(11, 23, 47);
		Matrix3x3 reference = new Matrix3x3(11, 0, 0, 0, 23, 0, 0, 0, 47);
		Matrix3x3 operand = new Matrix3x3(4, 5, 6, 7, 8, 9, 10, 11, 12);
		
		assertEquals(reference.multiply(operand), scale.multiply(operand));
	}
	
	public void testXRotation() {
		Matrix3x3 matrix1 = new Matrix3x3.XRotation(1);
		Matrix3x3 matrix2 = new Matrix3x3.Rotation(1, 0, 0, 1);
		Vector3 vector = new Vector3(4, 5, 6);
		Vector3 product1 = matrix1.multiply(vector);
		Vector3 product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getX(), product2.getX());
		Utils.assertFloatEquals(product1.getY(), product2.getY());
		Utils.assertFloatEquals(product1.getZ(), product2.getZ());
	}
	
	public void testYRotation() {
		Matrix3x3 matrix1 = new Matrix3x3.YRotation(2);
		Matrix3x3 matrix2 = new Matrix3x3.Rotation(0, 1, 0, 2);
		Vector3 vector = new Vector3(4, 5, 6);
		Vector3 product1 = matrix1.multiply(vector);
		Vector3 product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getX(), product2.getX());
		Utils.assertFloatEquals(product1.getY(), product2.getY());
		Utils.assertFloatEquals(product1.getZ(), product2.getZ());
	}
	
	public void testZRotation() {
		Matrix3x3 matrix1 = new Matrix3x3.ZRotation(3);
		Matrix3x3 matrix2 = new Matrix3x3.Rotation(0, 0, 1, 3);
		Vector3 vector = new Vector3(4, 5, 6);
		Vector3 product1 = matrix1.multiply(vector);
		Vector3 product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getX(), product2.getX());
		Utils.assertFloatEquals(product1.getY(), product2.getY());
		Utils.assertFloatEquals(product1.getZ(), product2.getZ());
	}
	
}
