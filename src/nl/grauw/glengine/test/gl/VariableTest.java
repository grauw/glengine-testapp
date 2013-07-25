package nl.grauw.glengine.test.gl;

import android.opengl.GLES20;
import nl.grauw.glengine.gl.Variable.Uniform;
import nl.grauw.glengine.gl.Variable.VariableType;
import junit.framework.TestCase;

public class VariableTest extends TestCase {
	
	VariableType type;
	Uniform uniform;
	
	@Override
	protected void setUp() throws Exception {
		type = VariableType.getById(GLES20.GL_FLOAT_VEC4);
		uniform = new Uniform(10, "testUniform", 2, type);
	}
	
	public void testGetLocation() {
		assertEquals(10, uniform.getLocation());
	}
	
	public void testGetName() {
		assertEquals("testUniform", uniform.getName());
	}
	
	public void testGetSize() {
		assertEquals(2, uniform.getSize());
	}
	
	public void testGetType() {
		assertEquals(type, uniform.getType());
	}
	
}
