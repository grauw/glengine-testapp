package nl.grauw.glengine.testapp;

import nl.grauw.glengine.gl.GLContext;
import nl.grauw.glengine.model.Matrix4x4;
import nl.grauw.glengine.scene.Material;

public class TestMaterial extends Material {
	
	private long time = 0;
	
	public TestMaterial(String vertexSource, String fragmentSource) {
		super(vertexSource, fragmentSource);
	}
	
	@Override
	public void initialize(GLContext gl) {
		super.initialize(gl);
		
		gl.useProgram(program);
		program.setUniformValue("color.color", 1, 0, 0, 1);
	}
	
	@Override
	public void update(int elapsed) {
		super.update(elapsed);
		
		time += elapsed;
	}
	
	@Override
	public void prepareDraw(GLContext gl, Matrix4x4 transform) {
		super.prepareDraw(gl, transform);
		
		program.setUniformValue("sinTime", (float)Math.sin(time / 1000f));
		program.setUniformValue("texture", gl.getActiveTextureUnit());
	}
	
}
