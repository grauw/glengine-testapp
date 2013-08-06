package nl.grauw.glengine.testapp;

import nl.grauw.glengine.model.Vector2;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.model.Vertex;
import nl.grauw.glengine.model.VertexArray;

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
	public void write(VertexArray array) {
		array.writeFloat3(position);
		array.writeFloat2(uv);
	}
	
	@Override
	public VertexInfo getVertexInfo() {
		return vertexInfo;
	}
	
}
