package nl.grauw.glengine.testapp;

import android.os.SystemClock;
import nl.grauw.glengine.gl.GLContext;
import nl.grauw.glengine.gl.Program;
import nl.grauw.glengine.gl.GLContext.PrimitiveType;
import nl.grauw.glengine.model.Matrix;
import nl.grauw.glengine.model.Point;
import nl.grauw.glengine.model.Point2;
import nl.grauw.glengine.model.Vertex;
import nl.grauw.glengine.scene.Material;
import nl.grauw.glengine.scene.Shape;

public class TestShape extends Shape {
	
	private final Vertex[] vertices;
	private Material material;
	private final long startTime;
	
	public TestShape(Material material) {
		this.material = material;
		
		vertices = new Vertex[] {
			new VertexPositionUV(new Point( 0.0f,  0.5f, 0.0f), new Point2( 0.5f, -2.0f)),
			new VertexPositionUV(new Point(-0.5f, -0.5f, 0.0f), new Point2(-1.0f,  2.0f)),
			new VertexPositionUV(new Point( 0.5f, -0.5f, 0.0f), new Point2( 2.0f,  2.0f))
		};
		
		startTime = SystemClock.uptimeMillis();
	}
	
	@Override
	protected Vertex[] getVertices() {
		return vertices;
	}
	
	@Override
	public void draw(GLContext gl, Matrix transform) {
		Program program = material.getProgram();
		gl.useProgram(program);
		program.setUniformValue("color.color", 1, 0, 0, 1);
		program.setUniformValue("sinTime", (float)Math.sin((SystemClock.uptimeMillis() - startTime) / 1000f));
		program.setUniformValue("texture", gl.getActiveTextureUnit());
		
		drawArrays(gl, PrimitiveType.TRIANGLES, "position", "texCoord");
	}
	
}
