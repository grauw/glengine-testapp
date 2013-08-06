package nl.grauw.glengine.testapp;

import java.nio.ByteBuffer;

import nl.grauw.glengine.model.Vector2;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.model.Vertex;

public class VertexPositionUV implements Vertex {
	
	Vector3 position;
	Vector2 uv;
	
	public static final VertexInfo vertexInfo = new VertexInfo(Vector3.attributeInfo, Vector2.attributeInfo);
	
	public VertexPositionUV(Vector3 position, Vector2 uv) {
		this.position = position;
		this.uv = uv;
	}
	
	@Override
	public void write(ByteBuffer buffer) {
		position.write(buffer);
		uv.write(buffer);
	}
	
	@Override
	public VertexInfo getVertexInfo() {
		return vertexInfo;
	}
	
}
