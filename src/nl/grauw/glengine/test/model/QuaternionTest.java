package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix4x4;
import nl.grauw.glengine.model.Quaternion;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.model.Vector4;
import nl.grauw.glengine.test.Utils;

public class QuaternionTest extends TestCase {
	
	public void testMultiply_Vector3() {
		Quaternion quaternion = new Quaternion.Axis(0, 0, 1, Math.PI / 2);
		Vector3 vector = new Vector3(0, 1, 5);
		Vector3 product = quaternion.multiply(vector);
		
		Utils.assertFloatEquals(-1, product.getX());
		Utils.assertFloatEquals(0, product.getY());
		Utils.assertFloatEquals(5, product.getZ());
	}
	
	public void testMultiply_Vector4() {
		Quaternion quaternion = new Quaternion.Axis(0, 0, 1, Math.PI / 2);
		Vector4 vector = new Vector4(0, 1, 5, 6);
		Vector4 product = quaternion.multiply(vector);
		
		Utils.assertFloatEquals(-1, product.getX());
		Utils.assertFloatEquals(0, product.getY());
		Utils.assertFloatEquals(5, product.getZ());
		Utils.assertFloatEquals(6, product.getW());
	}
	
	public void testMultiply_RotationMatrix() {
		Quaternion quaternion = new Quaternion.Axis(0, 0, 1, Math.PI / 2);
		Matrix4x4 matrix = new Matrix4x4.Rotation(quaternion);
		Vector4 vector = new Vector4(0, 1, 5, 0);
		Vector4 product = matrix.multiply(vector);
		
		Utils.assertFloatEquals(-1, product.getX());
		assertEquals(true, product.getY() < 1e-7 && product.getY() > -1e-7);  // close enough to 0...
		Utils.assertFloatEquals(5, product.getZ());
		Utils.assertFloatEquals(0, product.getW());
	}
	
	public void testEquals() {
		Quaternion quaternion1 = new Quaternion.Axis(4, 5, 6, 7);
		Quaternion quaternion2 = new Quaternion.Axis(4, 5, 6, 7);
		assertEquals(true, quaternion1.equals(quaternion2));
	}
	
	public void testEquals_Different() {
		Quaternion quaternion = new Quaternion.Axis(4, 5, 6, 7);
		assertEquals(false, quaternion.equals(new Quaternion.Axis(9, 5, 6, 7)));
		assertEquals(false, quaternion.equals(new Quaternion.Axis(4, 9, 6, 7)));
		assertEquals(false, quaternion.equals(new Quaternion.Axis(4, 5, 9, 7)));
		assertEquals(false, quaternion.equals(new Quaternion.Axis(4, 5, 6, 9)));
	}
	
	public void testEquals_Objects() {
		Object quaternion1 = new Quaternion.Axis(4, 5, 6, 7);
		Object quaternion2 = new Quaternion.Axis(4, 5, 6, 7);
		assertEquals(true, quaternion1.equals(quaternion2));
	}
	
	public void testEquals_Self() {
		Quaternion quaternion = new Quaternion.Axis(4, 5, 6, 7);
		assertEquals(true, quaternion.equals(quaternion));
	}
	
}
