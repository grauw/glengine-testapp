package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix4x4;
import nl.grauw.glengine.model.Transform;
import nl.grauw.glengine.model.Vector4;
import nl.grauw.glengine.test.Utils;

public class TransformTest extends TestCase {
	
	public void testGetMatrix() throws Exception {
		Transform transform = new Transform();
		transform.setScale(10);
		transform.setTranslation(10, 20, 30);
		
		Vector4 position = new Vector4(4, 5, 6, 1);
		Vector4 newPosition = transform.getMatrix().multiply(position);
		
		assertEquals(new Vector4(50, 70, 90, 1), newPosition);
	}
	
	public void testGetInverseMatrix_MultiplyVector() throws Exception {
		Transform transform = new Transform();
		transform.setTranslation(10, 20, 30);
		transform.setRotationXYZ(1, 2, 3);
		transform.setScale(10);
		
		Vector4 position = new Vector4(4, 5, 6, 1);
		Vector4 newPosition = transform.getMatrix().multiply(position);
		Vector4 oldPosition = transform.getInverseMatrix().multiply(newPosition);
		
		Utils.assertEqualsish(position, oldPosition);
	}
	
	public void testGetInverseMatrix_MultiplyInverse() throws Exception {
		Transform transform = new Transform();
		transform.setTranslation(10, 20, 30);
		transform.setRotationXYZ(1, 2, 3);
		transform.setScale(10);

		assertEquals(true, isIdentityMatrixish(transform.getInverseMatrix().multiply(transform.getMatrix())));
		assertEquals(true, isIdentityMatrixish(transform.getMatrix().multiply(transform.getInverseMatrix())));
	}
	
	private static boolean isIdentityMatrixish(Matrix4x4 matrix) {
		for (int column = 0; column < 4; column++) {
			for (int row = 0; row < 4; row++) {
				float element = matrix.getElement(column, row);
				if (row == column ? element < 0.99999f || element > 1.00001f : element < -0.00001f || element > 0.00001f)
					return false;
			}
		}
		return true;
	}
	
}
