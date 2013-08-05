package nl.grauw.glengine.testapp;

import javax.microedition.khronos.egl.EGLConfig;

import android.graphics.Bitmap;

import nl.grauw.glengine.GLEngineRenderer;
import nl.grauw.glengine.gl.GLContext;
import nl.grauw.glengine.gl.Texture.MagnificationFilter;
import nl.grauw.glengine.gl.Texture.MinificationFilter;
import nl.grauw.glengine.gl.Texture2D;
import nl.grauw.glengine.model.Matrix;
import nl.grauw.glengine.model.Quaternion;
import nl.grauw.glengine.scene.Material;
import nl.grauw.glengine.scene.Shape;
import nl.grauw.glengine.test.BenchmarkRunner;
import nl.grauw.glengine.test.BenchmarkRunner.Benchmark;

public class TestRenderer extends GLEngineRenderer implements Benchmark {
	
	private final Material material;
	private final Shape testShape;
	
	private final Bitmap textureBitmap;
	
	public TestRenderer(Material material, Bitmap textureImage) {
		this.material = material;
		this.textureBitmap = textureImage;
		
		this.testShape = new TestShape(material);
	}
	
	@Override
	public void onDrawFrame(GLContext gl) {
		gl.getColorBuffer().clear();
//		context.clear(true, false, false);  // use when clearing multiple buffers
		
		testShape.draw(gl, Matrix.IDENTITY);
	}
	
	@Override
	public void onSurfaceChanged(GLContext gl, int width, int height) {
		gl.setViewport(0, 0, width, height);
	}
	
	@Override
	public void onSurfaceCreated(GLContext gl, EGLConfig config) {
		material.compile(gl);
		
		gl.releaseShaderCompiler();
		
		Texture2D texture = gl.getActiveTextureUnit().getDefaultTexture2D();
		texture.setImage(0, textureBitmap);
		texture.generateMipmaps();
		texture.setMinificationFilter(MinificationFilter.LINEAR_MIPMAP_LINEAR);
		texture.setMagnificationFilter(MagnificationFilter.LINEAR);
		
		gl.getColorBuffer().setClearColor(0, 0, 0.5f, 1);
		
//		runBenchmark();
	}
	
	public void runBenchmark() {
		new BenchmarkRunner().run(5, this);
	}
	
	public Object benchmark() {
		Matrix projection = new Matrix.OrthographicProjection(-3, 2, -2, 5, 13, 151);
		Matrix rotation = new Matrix.Rotation(new Quaternion.EulerXYZ(1.3f, 2.2f, 3.1f));
		Matrix output = Matrix.IDENTITY;
		
		for (int i = 0; i < 100000; i++) {
			output = output.multiply(projection).multiply(rotation);
		}
		
		return output;
	}
	
}
