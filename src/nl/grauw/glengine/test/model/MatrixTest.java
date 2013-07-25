package nl.grauw.glengine.test.model;

import nl.grauw.glengine.model.Matrix;
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
	
	public void testGetValue() {
		Matrix matrix = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		assertEquals(6.0f, matrix.getValue(0, 0));
		assertEquals(5.0f, matrix.getValue(0, 1));
		assertEquals(4.0f, matrix.getValue(0, 2));
		assertEquals(3.0f, matrix.getValue(1, 0));
		assertEquals(2.0f, matrix.getValue(1, 1));
		assertEquals(1.0f, matrix.getValue(1, 2));
	}
	
	public void testGetArray() {
		Matrix matrix = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		float[] returned = matrix.toArray();
		assertEquals(6, returned.length);
		assertEquals(6.0f, returned[0]);
		assertEquals(5.0f, returned[1]);
		assertEquals(4.0f, returned[2]);
		assertEquals(3.0f, returned[3]);
		assertEquals(2.0f, returned[4]);
		assertEquals(1.0f, returned[5]);
	}
	
	public void testGetArray_NotTheSame() {
		float[] values = new float[] { 6, 5, 4, 3, 2, 1 };
		float[] returned = new Matrix(2, 3, values).toArray();
		assertNotSame(values, returned);
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
	
	public void testTranspose() {
		Matrix matrix = new Matrix(2, 3, 6, 5, 4, 3, 2, 1);
		Matrix transposed = matrix.transpose();
		assertNotSame(matrix, transposed);
		assertEquals(3, transposed.getColumns());
		assertEquals(2, transposed.getRows());
		assertEquals(6.0f, transposed.getValue(0, 0));
		assertEquals(3.0f, transposed.getValue(0, 1));
		assertEquals(5.0f, transposed.getValue(1, 0));
		assertEquals(2.0f, transposed.getValue(1, 1));
		assertEquals(4.0f, transposed.getValue(2, 0));
		assertEquals(1.0f, transposed.getValue(2, 1));
	}
	
	public void testMultiply_Identity() {
		Matrix matrix1 = new Matrix(2, 2, 6, 5, 4, 3);
		Matrix matrix2 = new Matrix(2, 2, 1, 0, 0, 1);
		Matrix product = matrix1.multiply(matrix2);
		assertEquals(2, product.getColumns());
		assertEquals(2, product.getRows());
		assertEquals(6.0f, product.getValue(0, 0));
		assertEquals(5.0f, product.getValue(0, 1));
		assertEquals(4.0f, product.getValue(1, 0));
		assertEquals(3.0f, product.getValue(1, 1));
	}
	
	public void testMultiply_ScaleTranslate() {
		Matrix scaleMatrix = new Matrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix translateMatrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix product = scaleMatrix.multiply(translateMatrix);
		assertEquals(3, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(2.0f, product.getValue(0, 0));
		assertEquals(0.0f, product.getValue(0, 1));
		assertEquals(0.0f, product.getValue(0, 2));
		assertEquals(0.0f, product.getValue(1, 0));
		assertEquals(0.5f, product.getValue(1, 1));
		assertEquals(0.0f, product.getValue(1, 2));
		assertEquals(-6.0f, product.getValue(2, 0));
		assertEquals(1.0f, product.getValue(2, 1));
		assertEquals(1.0f, product.getValue(2, 2));
	}
	
	public void testMultiply_IdentityVector() {
		Matrix matrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, 0, 0, 1);
		Matrix vector = new Matrix(1, 3, 5, 4, 3);
		Matrix product = matrix.multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(5.0f, product.getValue(0, 0));
		assertEquals(4.0f, product.getValue(0, 1));
		assertEquals(3.0f, product.getValue(0, 2));
	}
	
	public void testMultiply_VectorIdentity() {
		Matrix matrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, 0, 0, 1);
		Matrix vector = new Matrix(3, 1, 5, 4, 3);
		Matrix product = vector.multiply(matrix);
		assertEquals(3, product.getColumns());
		assertEquals(1, product.getRows());
		assertEquals(5.0f, product.getValue(0, 0));
		assertEquals(4.0f, product.getValue(1, 0));
		assertEquals(3.0f, product.getValue(2, 0));
	}
	
	public void testMultiply_TranslateVector() {
		Matrix matrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix vector = new Matrix(1, 3, 5, 4, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(2.0f, product.getValue(0, 0));
		assertEquals(6.0f, product.getValue(0, 1));
		assertEquals(1.0f, product.getValue(0, 2));
	}
	
	public void testMultiply_ScaleVector() {
		Matrix matrix = new Matrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix vector = new Matrix(1, 3, 2, 6, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(4.0f, product.getValue(0, 0));
		assertEquals(3.0f, product.getValue(0, 1));
		assertEquals(1.0f, product.getValue(0, 2));
	}
	
	public void testMultiply_RotateVector() {
		Matrix matrix = new Matrix(3, 3, (float)Math.cos(Math.PI/2), (float)Math.sin(Math.PI/2), 0, (float)-Math.sin(Math.PI/2), (float)Math.cos(Math.PI/2), 0, 0, 0, 1);
		Matrix vector = new Matrix(1, 3, 4, 3, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(-3.0f, product.getValue(0, 0));
		assertEquals(4.0f, product.getValue(0, 1));
		assertEquals(1.0f, product.getValue(0, 2));
	}
	
	public void testMultiply_TranslateScaleRotateVector() {
		Matrix translateMatrix = new Matrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix scaleMatrix = new Matrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix rotateMatrix = new Matrix(3, 3, (float)Math.cos(Math.PI/2), (float)Math.sin(Math.PI/2), 0, (float)-Math.sin(Math.PI/2), (float)Math.cos(Math.PI/2), 0, 0, 0, 1);
		Matrix vector = new Matrix(1, 3, 5, 4, 1);
		Matrix product = rotateMatrix.multiply(scaleMatrix).multiply(translateMatrix).multiply(vector);
		assertEquals(1, product.getColumns());
		assertEquals(3, product.getRows());
		assertEquals(-3.0f, product.getValue(0, 0));
		assertEquals(4.0f, product.getValue(0, 1));
		assertEquals(1.0f, product.getValue(0, 2));
	}
	
}
