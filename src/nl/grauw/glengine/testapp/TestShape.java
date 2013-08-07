package nl.grauw.glengine.testapp;

import nl.grauw.glengine.gl.GLContext;
import nl.grauw.glengine.gl.GLContext.PrimitiveType;
import nl.grauw.glengine.model.Matrix4x4;
import nl.grauw.glengine.model.Vector2;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.scene.Material;
import nl.grauw.glengine.scene.Shape;

public class TestShape extends Shape {
	
	private final Material material;
	
	public TestShape(Material material) {
		super(PrimitiveType.TRIANGLES);
		
		this.material = material;
		
		setVertices(
			new VertexPositionUV(new Vector3( 0.0f,  0.5f, 0.0f), new Vector2( 0.5f, -2.0f)),
			new VertexPositionUV(new Vector3(-0.5f, -0.5f, 0.0f), new Vector2(-1.0f,  2.0f)),
			new VertexPositionUV(new Vector3( 0.5f, -0.5f, 0.0f), new Vector2( 2.0f,  2.0f))
		);
	}
	
	@Override
	public void draw(GLContext gl, Matrix4x4 transform) {
		material.prepareDraw(gl);
		
		draw(gl);
	}
	
}
