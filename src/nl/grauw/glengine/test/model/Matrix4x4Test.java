package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix4x4;
import nl.grauw.glengine.model.Vector4;
import nl.grauw.glengine.test.Utils;

public class Matrix4x4Test extends TestCase {
	
	public void testGetColumn() {
		Matrix4x4 matrix = new Matrix4x4(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
		assertEquals(true, matrix.getColumn(0).equals(new Vector4(4, 5, 6, 7)));
		assertEquals(true, matrix.getColumn(1).equals(new Vector4(8, 9, 10, 11)));
		assertEquals(true, matrix.getColumn(2).equals(new Vector4(12, 13, 14, 15)));
		assertEquals(true, matrix.getColumn(3).equals(new Vector4(16, 17, 18, 19)));
	}
	
	public void testGetRow() {
		Matrix4x4 matrix = new Matrix4x4(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
		assertEquals(true, matrix.getRow(0).equals(new Vector4(4, 8, 12, 16)));
		assertEquals(true, matrix.getRow(1).equals(new Vector4(5, 9, 13, 17)));
		assertEquals(true, matrix.getRow(2).equals(new Vector4(6, 10, 14, 18)));
		assertEquals(true, matrix.getRow(3).equals(new Vector4(7, 11, 15, 19)));
	}
	
	public void testIdentity() {
		Matrix4x4 matrix = Matrix4x4.IDENTITY;
		Vector4 vector = new Vector4(4, 5, 6, 7);
		Vector4 product = matrix.multiply(vector);
		assertEquals(true, product.equals(vector));
	}
	
	public void testTranslation() {
		Matrix4x4 matrix = new Matrix4x4.Translation(2, 4, -3);
		Vector4 vector = new Vector4(4, 5, 6, 1);
		Vector4 product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Vector4(6, 9, 3, 1)));
	}
	
	public void testScale() {
		Matrix4x4 matrix = new Matrix4x4.Scale(3, 0.2f, -0.5f);
		Vector4 vector = new Vector4(4, 5, 6, 1);
		Vector4 product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Vector4(12, 1, -3, 1)));
	}
	
	public void testXRotation() {
		Matrix4x4 matrix1 = new Matrix4x4.XRotation(1);
		Matrix4x4 matrix2 = new Matrix4x4.Rotation(1, 0, 0, 1);
		Vector4 vector = new Vector4(4, 5, 6, 1);
		Vector4 product1 = matrix1.multiply(vector);
		Vector4 product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getX(), product2.getX());
		Utils.assertFloatEquals(product1.getY(), product2.getY());
		Utils.assertFloatEquals(product1.getZ(), product2.getZ());
		Utils.assertFloatEquals(product1.getW(), product2.getW());
	}
	
	public void testYRotation() {
		Matrix4x4 matrix1 = new Matrix4x4.YRotation(2);
		Matrix4x4 matrix2 = new Matrix4x4.Rotation(0, 1, 0, 2);
		Vector4 vector = new Vector4(4, 5, 6, 1);
		Vector4 product1 = matrix1.multiply(vector);
		Vector4 product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getX(), product2.getX());
		Utils.assertFloatEquals(product1.getY(), product2.getY());
		Utils.assertFloatEquals(product1.getZ(), product2.getZ());
		Utils.assertFloatEquals(product1.getW(), product2.getW());
	}
	
	public void testZRotation() {
		Matrix4x4 matrix1 = new Matrix4x4.ZRotation(3);
		Matrix4x4 matrix2 = new Matrix4x4.Rotation(0, 0, 1, 3);
		Vector4 vector = new Vector4(4, 5, 6, 1);
		Vector4 product1 = matrix1.multiply(vector);
		Vector4 product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getX(), product2.getX());
		Utils.assertFloatEquals(product1.getY(), product2.getY());
		Utils.assertFloatEquals(product1.getZ(), product2.getZ());
		Utils.assertFloatEquals(product1.getW(), product2.getW());
	}
	
	public void testPerspectiveProjection() {
		Matrix4x4 projection = new Matrix4x4.PerspectiveProjection(-3, 3, -2, 2, 15, 115);
		Vector4 vector = new Vector4(1.5f, -5, -75, 1);
		Vector4 product = projection.multiply(vector);
		
		float x = product.getX() / product.getW();
		float y = product.getY() / product.getW();
		float z = product.getZ() / product.getW();
		
		Utils.assertFloatEquals(0.1f, x);
		Utils.assertFloatEquals(-0.5f, y);
		Utils.assertFloatEquals(0.84f, z);
	}
	
	public void testPerspectiveProjection_Constructors() {
		Matrix4x4 projection1 = new Matrix4x4.PerspectiveProjection(-3, 3, -2, 2, 13, 150);
		Matrix4x4 projection2 = new Matrix4x4.PerspectiveProjection(6, 4, 13, 150);
		assertEquals(true, projection1.equals(projection2));
	}
	
	public void testOrthographicProjection() {
		Matrix4x4 projection = new Matrix4x4.OrthographicProjection(-3, 3, -2, 2, 15, 115);
		Vector4 vector = new Vector4(1.5f, -5, -75, 1);
		Vector4 product = projection.multiply(vector);
		Utils.assertFloatEquals(0.5f, product.getX());
		Utils.assertFloatEquals(-2.5f, product.getY());
		Utils.assertFloatEquals(0.2f, product.getZ());
		Utils.assertFloatEquals(1.0f, product.getW());
	}
	
	public void testOrthographicProjection_Constructors() {
		Matrix4x4 projection1 = new Matrix4x4.OrthographicProjection(-3, 3, -2, 2, 13, 150);
		Matrix4x4 projection2 = new Matrix4x4.OrthographicProjection(6, 4, 13, 150);
		assertEquals(true, projection1.equals(projection2));
	}
	
}
