package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix3x3;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.test.Utils;

public class Matrix3x3Test extends TestCase {
	
	public void testIdentity() {
		Matrix3x3 matrix = Matrix3x3.IDENTITY;
		Vector3 vector = new Vector3(4, 5, 6);
		Vector3 product = matrix.multiply(vector);
		assertEquals(true, product.equals(vector));
	}
	
	public void testTranslation() {
		Matrix3x3 matrix = new Matrix3x3.Translation(2, 4);
		Vector3 vector = new Vector3(4, 5, 1);
		Vector3 product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Vector3(6, 9, 1)));
	}
	
	public void testScale() {
		Matrix3x3 matrix = new Matrix3x3.Scale(3, 0.2f, -0.5f);
		Vector3 vector = new Vector3(4, 5, 6);
		Vector3 product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Vector3(12, 1, -3)));
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
