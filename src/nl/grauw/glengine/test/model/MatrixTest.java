package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix;
import nl.grauw.glengine.model.Matrix.AnyMatrix;

public class MatrixTest extends TestCase {
	
	public void testCopyConstructor() {
		Matrix other = new AnyMatrix(2, 2, 4, 5, 6, 7);
		Matrix matrix = new AnyMatrix(2, 2, other);
		assertEquals(other, matrix);
	}
	
	public void testCopyConstructor_Grow() {
		Matrix other = new AnyMatrix(2, 2, 4, 5, 6, 7);
		Matrix matrix = new AnyMatrix(3, 3, other);
		assertEquals(new AnyMatrix(3, 3, 4, 5, 0, 6, 7, 0, 0, 0, 1), matrix);
	}
	
	public void testCopyConstructor_Shrink() {
		Matrix other = new AnyMatrix(3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		Matrix matrix = new AnyMatrix(2, 2, other);
		assertEquals(new AnyMatrix(2, 2, 4, 5, 7, 8), matrix);
	}
	
	public void testCopyConstructor_DiagonalVertical() {
		Matrix other = new AnyMatrix(1, 3, 4, 5, 6);
		Matrix matrix = new AnyMatrix(3, 3, other);
		assertEquals(new AnyMatrix(3, 3, 4, 5, 6, 0, 1, 0, 0, 0, 1), matrix);
	}
	
	public void testCopyConstructor_DiagonalHorizontal() {
		Matrix other = new AnyMatrix(3, 1, 4, 5, 6);
		Matrix matrix = new AnyMatrix(4, 2, other);
		assertEquals(new AnyMatrix(4, 2, 4, 0, 5, 1, 6, 0, 0, 0), matrix);
	}
	
	public void testCopyConstructor_Flipped() {
		Matrix other = new AnyMatrix(3, 1, 4, 5, 6);
		Matrix matrix = new AnyMatrix(1, 3, other);
		assertEquals(new AnyMatrix(1, 3, 4, 0, 0), matrix);
	}
	
	public void testGetColumns() {
		Matrix matrix = new AnyMatrix(3, 1, 0, 0, 0);
		assertEquals(3, matrix.getColumns());
	}
	
	public void testGetRows() {
		Matrix matrix = new AnyMatrix(1, 3, 0, 0, 0);
		assertEquals(3, matrix.getRows());
	}
	
	public void testGetElement() {
		Matrix matrix = new AnyMatrix(2, 3, 6, 5, 4, 3, 2, 1);
		assertEquals(6.0f, matrix.getElement(0, 0));
		assertEquals(5.0f, matrix.getElement(0, 1));
		assertEquals(4.0f, matrix.getElement(0, 2));
		assertEquals(3.0f, matrix.getElement(1, 0));
		assertEquals(2.0f, matrix.getElement(1, 1));
		assertEquals(1.0f, matrix.getElement(1, 2));
	}
	
	public void testGetElements() {
		Matrix matrix = new AnyMatrix(2, 3, 6, 5, 4, 3, 2, 1);
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
		Object matrix1 = new AnyMatrix(2, 3, 6, 5, 4, 3, 2, 1);
		Matrix matrix2 = new AnyMatrix(2, 3, 6, 5, 4, 3, 2, 1);
		assertEquals(true, matrix1.equals(matrix2));
	}
	
	public void testEquals_DifferentData() {
		Matrix matrix1 = new AnyMatrix(2, 3, 6, 5, 4, 3, 2, 1);
		Object matrix2 = new AnyMatrix(2, 3, 6, 5, 4, 2, 2, 1);
		assertEquals(false, matrix1.equals(matrix2));
	}
	
	public void testEquals_DifferentDimensions() {
		Object matrix1 = new AnyMatrix(2, 3, 6, 5, 4, 3, 2, 1);
		Object matrix2 = new AnyMatrix(3, 2, 6, 5, 4, 3, 2, 1);
		assertEquals(false, matrix1.equals(matrix2));
	}
	
	public void testEquals_Self() {
		Matrix matrix = new AnyMatrix(2, 3, 6, 5, 4, 3, 2, 1);
		assertEquals(true, matrix.equals(matrix));
	}
	
	public void testTranspose() {
		Matrix matrix = new AnyMatrix(2, 3, 6, 5, 4, 3, 2, 1);
		Matrix transposed = matrix.transpose();
		assertNotSame(matrix, transposed);
		assertEquals(new AnyMatrix(3, 2, 6, 3, 5, 2, 4, 1), transposed);
	}
	
	public void testMultiply_Identity() {
		Matrix matrix1 = new AnyMatrix(2, 2, 6, 5, 4, 3);
		Matrix matrix2 = new AnyMatrix(2, 2, 1, 0, 0, 1);
		Matrix product = matrix1.multiply(matrix2);
		assertEquals(new AnyMatrix(2, 2, 6, 5, 4, 3), product);
	}
	
	public void testMultiply_ScaleTranslate() {
		Matrix scaleMatrix = new AnyMatrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix translateMatrix = new AnyMatrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix product = scaleMatrix.multiply(translateMatrix);
		assertEquals(new AnyMatrix(3, 3, 2, 0, 0, 0, 0.5f, 0, -6, 1, 1), product);
	}
	
	public void testMultiply_IdentityVector() {
		Matrix matrix = new AnyMatrix(3, 3, 1, 0, 0, 0, 1, 0, 0, 0, 1);
		Matrix vector = new AnyMatrix(1, 3, 5, 4, 3);
		Matrix product = matrix.multiply(vector);
		assertEquals(new AnyMatrix(1, 3, 5, 4, 3), product);
	}
	
	public void testMultiply_VectorIdentity() {
		Matrix matrix = new AnyMatrix(3, 3, 1, 0, 0, 0, 1, 0, 0, 0, 1);
		Matrix vector = new AnyMatrix(3, 1, 5, 4, 3);
		Matrix product = vector.multiply(matrix);
		assertEquals(new AnyMatrix(3, 1, 5, 4, 3), product);
	}
	
	public void testMultiply_TranslateVector() {
		Matrix matrix = new AnyMatrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix vector = new AnyMatrix(1, 3, 5, 4, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(new AnyMatrix(1, 3, 2, 6, 1), product);
	}
	
	public void testMultiply_ScaleVector() {
		Matrix matrix = new AnyMatrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix vector = new AnyMatrix(1, 3, 2, 6, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(new AnyMatrix(1, 3, 4, 3, 1), product);
	}
	
	public void testMultiply_RotateVector() {
		Matrix matrix = new AnyMatrix(3, 3, (float)Math.cos(Math.PI/2), (float)Math.sin(Math.PI/2), 0, (float)-Math.sin(Math.PI/2), (float)Math.cos(Math.PI/2), 0, 0, 0, 1);
		Matrix vector = new AnyMatrix(1, 3, 4, 3, 1);
		Matrix product = matrix.multiply(vector);
		assertEquals(new AnyMatrix(1, 3, -3, 4, 1), product);
	}
	
	public void testMultiply_TranslateScaleRotateVector() {
		Matrix translateMatrix = new AnyMatrix(3, 3, 1, 0, 0, 0, 1, 0, -3, 2, 1);
		Matrix scaleMatrix = new AnyMatrix(3, 3, 2, 0, 0, 0, 0.5f, 0, 0, 0, 1);
		Matrix rotateMatrix = new AnyMatrix(3, 3, (float)Math.cos(Math.PI/2), (float)Math.sin(Math.PI/2), 0, (float)-Math.sin(Math.PI/2), (float)Math.cos(Math.PI/2), 0, 0, 0, 1);
		Matrix vector = new AnyMatrix(1, 3, 5, 4, 1);
		Matrix product = rotateMatrix.multiply(scaleMatrix).multiply(translateMatrix).multiply(vector);
		assertEquals(new AnyMatrix(1, 3, -3, 4, 1), product);
	}
	
}
