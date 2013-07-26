package nl.grauw.glengine.test.model;

import nl.grauw.glengine.model.Matrix;
import nl.grauw.glengine.test.Utils;
import junit.framework.TestCase;

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
		Matrix matrix = new Matrix.Identity();
		Matrix vector = new Matrix(1, 4, 4, 5, 6, 7);
		Matrix product = matrix.multiply(vector);
		assertEquals(true, product.equals(vector));
	}
	
	public void testTranslationMatrix() {
		Matrix matrix = new Matrix.Translation(2, 4, -3);
		Matrix vector = new Matrix(1, 4, 4, 5, 6, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Matrix(1, 4, 6, 9, 3, 1)));
	}
	
	public void testScaleMatrix() {
		Matrix matrix = new Matrix.Scale(3, 0.2f, -0.5f);
		Matrix vector = new Matrix(1, 4, 4, 5, 6, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(true, product.equals(new Matrix(1, 4, 12, 1, -3, 1)));
	}
	
	public void testXRotationMatrix() {
		Matrix matrix1 = new Matrix.XRotation(1);
		Matrix matrix2 = new Matrix.Rotation(1, 0, 0, 1);
		Matrix vector = new Matrix(1, 4, 4, 5, 6, 1);
		Matrix product1 = matrix1.multiply(vector);
		Matrix product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getElement(0, 0), product2.getElement(0, 0));
		Utils.assertFloatEquals(product1.getElement(0, 1), product2.getElement(0, 1));
		Utils.assertFloatEquals(product1.getElement(0, 2), product2.getElement(0, 2));
		Utils.assertFloatEquals(product1.getElement(0, 3), product2.getElement(0, 3));
	}
	
	public void testYRotationMatrix() {
		Matrix matrix1 = new Matrix.YRotation(2);
		Matrix matrix2 = new Matrix.Rotation(0, 1, 0, 2);
		Matrix vector = new Matrix(1, 4, 4, 5, 6, 1);
		Matrix product1 = matrix1.multiply(vector);
		Matrix product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getElement(0, 0), product2.getElement(0, 0));
		Utils.assertFloatEquals(product1.getElement(0, 1), product2.getElement(0, 1));
		Utils.assertFloatEquals(product1.getElement(0, 2), product2.getElement(0, 2));
		Utils.assertFloatEquals(product1.getElement(0, 3), product2.getElement(0, 3));
	}
	
	public void testZRotationMatrix() {
		Matrix matrix1 = new Matrix.ZRotation(3);
		Matrix matrix2 = new Matrix.Rotation(0, 0, 1, 3);
		Matrix vector = new Matrix(1, 4, 4, 5, 6, 1);
		Matrix product1 = matrix1.multiply(vector);
		Matrix product2 = matrix2.multiply(vector);
		Utils.assertFloatEquals(product1.getElement(0, 0), product2.getElement(0, 0));
		Utils.assertFloatEquals(product1.getElement(0, 1), product2.getElement(0, 1));
		Utils.assertFloatEquals(product1.getElement(0, 2), product2.getElement(0, 2));
		Utils.assertFloatEquals(product1.getElement(0, 3), product2.getElement(0, 3));
	}
	
	// XXX: this test is a bit weak, it does not test the actual projection.
	public void testProjection() {
		Matrix projection1 = new Matrix.Projection(-3, 3, -2, 2, 13, 150);
		Matrix projection2 = new Matrix.Projection(6, 4, 13, 150);
		assertEquals(true, projection1.equals(projection2));
	}
	
}
