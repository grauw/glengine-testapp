package nl.grauw.glengine.testapp;

import java.nio.ByteBuffer;

import nl.grauw.glengine.model.Vector2;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.model.Vertex;
import nl.grauw.glengine.model.VertexAttribute.AttributeInfo;
import nl.grauw.glengine.model.VertexAttribute.AttributeType;

public class VertexPositionUV extends Vertex {
	
	Vector3 position;
	Vector2 uv;
	
	public static final VertexInfo vertexInfo = new VertexInfo(
		new AttributeInfo(3, AttributeType.FLOAT),
		new AttributeInfo(2, AttributeType.FLOAT)
	);
	
	public VertexPositionUV(Vector3 position, Vector2 uv) {
		this.position = position;
		this.uv = uv;
	}
	
	@Override
	public void write(ByteBuffer buffer) {
		writeFloat3(buffer, position);
		writeFloat2(buffer, uv);
	}
	
	@Override
	public VertexInfo getVertexInfo() {
		return vertexInfo;
	}
	
}
