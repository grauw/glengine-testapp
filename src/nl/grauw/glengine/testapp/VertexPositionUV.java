package nl.grauw.glengine.testapp;

import nl.grauw.glengine.model.Vector2;
import nl.grauw.glengine.model.Vector3;
import nl.grauw.glengine.model.Vertex;
import nl.grauw.glengine.model.VertexArray;

public class VertexPositionUV extends Vertex {
	
	private final Vector3 position;
	private final Vector2 uv;
	
	public static final VertexInfo info = new VertexInfo(
		new AttributeInfo("position", 3, AttributeType.FLOAT),
		new AttributeInfo("uv", 2, AttributeType.FLOAT)
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
	public VertexInfo getInfo() {
		return info;
	}
	
}
