package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix;
import nl.grauw.glengine.model.Matrix4x4;
import nl.grauw.glengine.model.Vector4;
import nl.grauw.glengine.test.Utils;

public class MatrixTest extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
	}
	
	public void testGetColumns() {
		Matrix matrix = new Matrix(3, 1, 0, 0, 0);
		assertEquals(3, matrix.getColumns());
	}
	
	public void testGetRows() {
		Matrix matrix = new Matrix(1, 3, 0, 0, 0);
		assertEquals(3, matrix.getRows());
	}
	
	public void testGetElement() {
		Matrix matrix = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		assertEquals(6.0f, matrix.getElement(0, 0));
		assertEquals(5.0f, matrix.getElement(0, 1));
		assertEquals(4.0f, matrix.getElement(0, 2));
		assertEquals(3.0f, matrix.getElement(1, 0));
		assertEquals(2.0f, matrix.getElement(1, 1));
		assertEquals(1.0f, matrix.getElement(1, 2));
	}
	
	public void testGetElements() {
		Matrix matrix = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		float[] elements = matrix.getElements();
		assertEquals(6, elements.length);
		assertEquals(6.0f, elements[0]);
		assertEquals(5.0f, elements[1]);
		assertEquals(4.0f, elements[2]);
		assertEquals(3.0f, elements[3]);
		assertEquals(2.0f, elements[4]);
		assertEquals(1.0f, elements[5]);
	}
	
	public void testEquals() {
		Matrix matrix1 = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		Matrix matrix2 = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		assertEquals(true, matrix1.equals(matrix2));
	}
	
	public void testEquals_DifferentData() {
		Matrix matrix1 = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		Matrix matrix2 = new Matrix(2, 3, 6, 5, 4, 2, 2, 1);
		assertEquals(false, matrix1.equals(matrix2));
	}
	
	public void testEquals_DifferentDimensions() {
		Matrix matrix1 = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		Matrix matrix2 = new Matrix(3, 2, 6, 5, 4, 3, 2, 1);
		assertEquals(false, matrix1.equals(matrix2));
	}
	
	public void testEquals_Self() {
		Matrix matrix = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		assertEquals(true, matrix.equals(matrix));
	}
	
	public void testTranspose() {
		Matrix matrix = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		Matrix transposed = matrix.transpose();
		assertNotSame(matrix, transposed);
		assertEquals(3, transposed.getColumns());
		assertEquals(2, transposed.getRows());
		assertEquals(6.0f, transposed.getElement(0, 0));
		assertEquals(3.0f, transposed.getElement(0, 1));
		assertEquals(5.0f, transposed.getElement(1, 0));
		assertEquals(2.0f, transposed.getElement(1, 1));
		assertEquals(4.0f, transposed.getElement(2, 0));
		assertEquals(1.0f, transposed.getElement(2, 1));
	}
	
	public void testMultiply_Identity() {
		Matrix matrix1 = new Matrix(2, 2, 6, 5, 4, 3);
		Matrix matrix2 = new Matrix(2, 2, 1, 0, 0, 1);
		Matrix product = matrix1.multiply(matrix2);
		assertEquals(2, product.getColumns());
		assertEquals(2, product.getRows());
		assertEquals(6.0f, product.getElement(0, 0));
		assertEquals(5.0f, product.getElement(0, 1));
		assertEquals(4.0f, product.getElement(1, 0));
		assertEquals(3.0f, product.getElement(1, 1));
	}
	
	public void testMultiply_ScaleTranslate() {
		Matrix scaleMatrix = new Matrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix translateMatrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix product = scaleMatrix.multiply(translateMatrix);
		assertEquals(3, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(2.0f, product.getElement(0, 0));
		assertEquals(0.0f, product.getElement(0, 1));
		assertEquals(0.0f, product.getElement(0, 2));
		assertEquals(0.0f, product.getElement(1, 0));
		assertEquals(0.5f, product.getElement(1, 1));
		assertEquals(0.0f, product.getElement(1, 2));
		assertEquals(-6.0f, product.getElement(2, 0));
		assertEquals(1.0f, product.getElement(2, 1));
		assertEquals(1.0f, product.getElement(2, 2));
	}
	
	public void testMultiply_IdentityVector() {
		Matrix matrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, 0, 0, 1);
		Matrix vector = new Matrix(1, 3, 5, 4, 3);
		Matrix product = matrix.multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(5.0f, product.getElement(0, 0));
		assertEquals(4.0f, product.getElement(0, 1));
		assertEquals(3.0f, product.getElement(0, 2));
	}
	
	public void testMultiply_VectorIdentity() {
		Matrix matrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, 0, 0, 1);
		Matrix vector = new Matrix(3, 1, 5, 4, 3);
		Matrix product = vector.multiply(matrix);
		assertEquals(3, product.getColumns());
		assertEquals(1, product.getRows());
		assertEquals(5.0f, product.getElement(0, 0));
		assertEquals(4.0f, product.getElement(1, 0));
		assertEquals(3.0f, product.getElement(2, 0));
	}
	
	public void testMultiply_TranslateVector() {
		Matrix matrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix vector = new Matrix(1, 3, 5, 4, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(2.0f, product.getElement(0, 0));
		assertEquals(6.0f, product.getElement(0, 1));
		assertEquals(1.0f, product.getElement(0, 2));
	}
	
	public void testMultiply_ScaleVector() {
		Matrix matrix = new Matrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix vector = new Matrix(1, 3, 2, 6, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(4.0f, product.getElement(0, 0));
		assertEquals(3.0f, product.getElement(0, 1));
		assertEquals(1.0f, product.getElement(0, 2));
	}
	
	public void testMultiply_RotateVector() {
		Matrix matrix = new Matrix(3, 3, (float)Math.cos(Math.PI/2), (float)Math.sin(Math.PI/2), 0, (float)-Math.sin(Math.PI/2), (float)Math.cos(Math.PI/2), 0, 0, 0, 1);
		Matrix vector = new Matrix(1, 3, 4, 3, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(-3.0f, product.getElement(0, 0));
		assertEquals(4.0f, product.getElement(0, 1));
		assertEquals(1.0f, product.getElement(0, 2));
	}
	
	public void testMultiply_TranslateScaleRotateVector() {
		Matrix translateMatrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix scaleMatrix = new Matrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix rotateMatrix = new Matrix(3, 3, (float)Math.cos(Math.PI/2), (float)Math.sin(Math.PI/2), 0, (float)-Math.sin(Math.PI/2), (float)Math.cos(Math.PI/2), 0, 0, 0, 1);
		Matrix vector = new Matrix(1, 3, 5, 4, 1);
		Matrix product = rotateMatrix.multiply(scaleMatrix).multiply(translateMatrix).multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(-3.0f, product.getElement(0, 0));
		assertEquals(4.0f, product.getElement(0, 1));
		assertEquals(1.0f, product.getElement(0, 2));
	}
	
	public void testIdentityMatrix() {
		Matrix4x4 matrix = Matrix4x4.IDENTITY;
		Vector4 vector = new Vector4(4, 5, 6, 7);
		Vector4 product = matrix.multiply(vector);
		assertEquals(true, product.equals(vector));
	}
	
	public void testTranslationMatrix() {
		Matrix4x4 matrix = new Matrix4x4.Translation(2, 4, -3);
		Vector4 vector = new Vector4(4, 5, 6, 1);
		Vector4 product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Vector4(6, 9, 3, 1)));
	}
	
	public void testScaleMatrix() {
		Matrix4x4 matrix = new Matrix4x4.Scale(3, 0.2f, -0.5f);
		Vector4 vector = new Vector4(4, 5, 6, 1);
		Vector4 product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Vector4(12, 1, -3, 1)));
	}
	
	public void testXRotationMatrix() {
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
	
	public void testYRotationMatrix() {
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
	
	public void testZRotationMatrix() {
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
