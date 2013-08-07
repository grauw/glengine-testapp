package nl.grauw.glengine.testapp;

import javax.microedition.khronos.egl.EGLConfig;

import android.graphics.Bitmap;
import android.os.SystemClock;

import nl.grauw.glengine.GLEngineRenderer;
import nl.grauw.glengine.gl.GLContext;
import nl.grauw.glengine.gl.Texture.MagnificationFilter;
import nl.grauw.glengine.gl.Texture.MinificationFilter;
import nl.grauw.glengine.gl.Texture2D;
import nl.grauw.glengine.model.Matrix;
import nl.grauw.glengine.model.Matrix4x4;
import nl.grauw.glengine.model.Quaternion;
import nl.grauw.glengine.scene.Shape;
import nl.grauw.glengine.test.BenchmarkRunner;
import nl.grauw.glengine.test.BenchmarkRunner.Benchmark;

public class TestRenderer extends GLEngineRenderer implements Benchmark {
	
	private final Materials materials;
	private final Shape testShape;
	
	private final Bitmap textureBitmap;
	
	private long lastUpdateTime = 0;
	
	public TestRenderer(Materials materials, Bitmap textureImage) {
		this.materials = materials;
		this.textureBitmap = textureImage;
		
		testShape = new TestShape(materials.getTestMaterial());
	}
	
	@Override
	public void onDrawFrame(GLContext gl) {
		gl.getColorBuffer().clear();
//		context.clear(true, false, false);  // use when clearing multiple buffers
		
		long time = SystemClock.uptimeMillis();
		if (lastUpdateTime != 0)
			materials.update((int)(time - lastUpdateTime));
		lastUpdateTime = time;
		
		testShape.draw(gl, Matrix4x4.IDENTITY);
	}
	
	@Override
	public void onSurfaceChanged(GLContext gl, int width, int height) {
		gl.setViewport(0, 0, width, height);
	}
	
	@Override
	public void onSurfaceCreated(GLContext gl, EGLConfig config) {
		materials.initialize(gl);
		testShape.initialize(gl);
		
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
		Matrix projection = new Matrix4x4.OrthographicProjection(-3, 2, -2, 5, 13, 151);
		Matrix rotation = new Matrix4x4.Rotation(new Quaternion.EulerXYZ(1.3f, 2.2f, 3.1f));
		Matrix output = Matrix4x4.IDENTITY;
		
		for (int i = 0; i < 100000; i++) {
			output = output.multiply(projection).multiply(rotation);
		}
		
		return output;
	}
	
}
