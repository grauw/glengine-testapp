package nl.grauw.glengine.test.model;

import junit.framework.TestCase;

import nl.grauw.glengine.model.Matrix4x4;
import nl.grauw.glengine.model.Quaternion;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.model.Vector4;
import nl.grauw.glengine.test.Utils;

public class QuaternionTest extends TestCase {
	
	public void testGetAxis() {
		Quaternion quaternion = new Quaternion.Axis(1, 2, 4, Math.PI / 3);
		
		assertEquals(new Vector3(1, 2, 4), quaternion.getAxis());
	}
	
	public void testGetAngle() {
		Quaternion quaternion = new Quaternion.Axis(1, 2, 4, Math.PI / 3);
		
		assertEquals((float)(Math.PI / 3), (float)quaternion.getAngle());
	}
	
	public void testMultiply_Identity() {
		Quaternion quaternion = new Quaternion.Axis(1, 2, 3, 4);
		Quaternion product1 = quaternion.multiply(Quaternion.IDENTITY);
		Quaternion product2 = Quaternion.IDENTITY.multiply(quaternion);
		
		assertEquals(quaternion, product1);
		assertEquals(quaternion, product2);
	}
	
	public void testMultiply_Vector3() {
		Quaternion quaternion = new Quaternion.Axis(0, 0, 1, Math.PI / 2);
		Vector3 vector = new Vector3(3, 4, 5);
		Vector3 product = quaternion.multiply(vector);
		
		Utils.assertEqualsish(new Vector3(-4, 3, 5), product);
	}
	
	public void testMultiply_Vector4() {
		Quaternion quaternion = new Quaternion.Axis(0, 0, 1, Math.PI / 2);
		Vector4 vector = new Vector4(3, 4, 5, 6);
		Vector4 product = quaternion.multiply(vector);
		
		Utils.assertEqualsish(new Vector4(-4, 3, 5, 6), product);
	}
	
	public void testMultiply_RotationMatrix() {
		Quaternion quaternion = new Quaternion.Axis(0, 0, 1, Math.PI / 2);
		Matrix4x4 matrix = new Matrix4x4.Rotation(quaternion);
		Vector4 vector = new Vector4(3, 1, 5, 0);
		Vector4 product = matrix.multiply(vector);
		
		Utils.assertEqualsish(new Vector4(-1, 3, 5, 0), product);
	}
	
	public void testMultiply_Reciprocal() {
		Quaternion quaternion = new Quaternion.Axis(1, 2, 3, 4);
		Quaternion product = quaternion.multiply(quaternion.reciprocal());
		
		assertEquals(Quaternion.IDENTITY, product);
	}
	
	public void testMultiply_Conjugate() {
		Quaternion quaternion = new Quaternion.Axis(1, 2, 3, 4);
		Quaternion product = quaternion.multiply(quaternion.conjugate());
		
		assertEquals(new Quaternion(0, 0, 0, quaternion.getMagnitudeSquared()), product);
	}
	
	public void testMultiply_ConjugateVector() {
		Quaternion quaternion = new Quaternion.Axis(1, 2, 3, 4).normalize();
		Vector3 vector = new Vector3(1, 3, 5);
		Vector3 product = quaternion.multiply(vector);
		Vector3 product2 = quaternion.conjugate().multiply(product);
		
		Utils.assertEqualsish(vector, product2);
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
	
	public void testEulerXYZ() {
		Quaternion quaternion = new Quaternion.EulerXYZ(1, 2, 3);
		Quaternion expected = new Quaternion.Axis(1, 0, 0, 1).multiply(new Quaternion.Axis(0, 1, 0, 2).multiply(new Quaternion.Axis(0, 0, 1, 3)));
		
		Utils.assertEqualsish(expected, quaternion);
	}
	
	public void testEulerXZY() {
		Quaternion quaternion = new Quaternion.EulerXZY(1, 2, 3);
		Quaternion expected = new Quaternion.Axis(1, 0, 0, 1).multiply(new Quaternion.Axis(0, 0, 1, 3).multiply(new Quaternion.Axis(0, 1, 0, 2)));
		
		Utils.assertEqualsish(expected, quaternion);
	}
	
	public void testEulerYXZ() {
		Quaternion quaternion = new Quaternion.EulerYXZ(1, 2, 3);
		Quaternion expected = new Quaternion.Axis(0, 1, 0, 2).multiply(new Quaternion.Axis(1, 0, 0, 1).multiply(new Quaternion.Axis(0, 0, 1, 3)));
		
		Utils.assertEqualsish(expected, quaternion);
	}
	
	public void testEulerYZX() {
		Quaternion quaternion = new Quaternion.EulerYZX(1, 2, 3);
		Quaternion expected = new Quaternion.Axis(0, 1, 0, 2).multiply(new Quaternion.Axis(0, 0, 1, 3).multiply(new Quaternion.Axis(1, 0, 0, 1)));
		
		Utils.assertEqualsish(expected, quaternion);
	}
	
	public void testEulerZXY() {
		Quaternion quaternion = new Quaternion.EulerZXY(1, 2, 3);
		Quaternion expected = new Quaternion.Axis(0, 0, 1, 3).multiply(new Quaternion.Axis(1, 0, 0, 1).multiply(new Quaternion.Axis(0, 1, 0, 2)));
		
		Utils.assertEqualsish(expected, quaternion);
	}
	
	public void testEulerZYX() {
		Quaternion quaternion = new Quaternion.EulerZYX(1, 2, 3);
		Quaternion expected = new Quaternion.Axis(0, 0, 1, 3).multiply(new Quaternion.Axis(0, 1, 0, 2).multiply(new Quaternion.Axis(1, 0, 0, 1)));
		
		Utils.assertEqualsish(expected, quaternion);
	}
	
}
