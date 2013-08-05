package nl.grauw.glengine.testapp;

import java.nio.ByteBuffer;

import nl.grauw.glengine.model.Point;
import nl.grauw.glengine.model.Point2;
import nl.grauw.glengine.model.Vertex;

public class VertexPositionUV implements Vertex {
	
	Point position;
	Point2 uv;
	
	public static final VertexInfo vertexInfo = new VertexInfo(Point.attributeInfo, Point2.attributeInfo);
	
	public VertexPositionUV(Point position, Point2 uv) {
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
