package nl.grauw.glengine.testapp;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;

import android.graphics.Bitmap;
import android.os.SystemClock;

import nl.grauw.glengine.GLEngineRenderer;
import nl.grauw.glengine.gl.GLContext;
import nl.grauw.glengine.gl.GLContext.PrimitiveType;
import nl.grauw.glengine.gl.Program;
import nl.grauw.glengine.gl.Texture.MagnificationFilter;
import nl.grauw.glengine.gl.Texture.MinificationFilter;
import nl.grauw.glengine.gl.Texture2D;
import nl.grauw.glengine.gl.Utils;
import nl.grauw.glengine.scene.Material;

public class TestRenderer extends GLEngineRenderer {
	
	private Material material;
	private final Bitmap textureBitmap;
	private final long startTime;
	
	public TestRenderer(Material material, Bitmap textureImage) {
		this.material = material;
		this.textureBitmap = textureImage;
		startTime = SystemClock.uptimeMillis();
	}
	
	@Override
	public void onDrawFrame(GLContext gl) {
		gl.getColorBuffer().clear();
//		context.clear(true, false, false);  // use when clearing multiple buffers
		
		FloatBuffer vertices = Utils.createFloatBuffer(new float[] {
			 0.0f,  0.5f, 0.0f,
			-0.5f, -0.5f, 0.0f,
			 0.5f, -0.5f, 0.0f
		});
		
		FloatBuffer texCoords = Utils.createFloatBuffer(new float[] {
			 0.5f, -2.0f,
			-1.0f,  2.0f,
			 2.0f,  2.0f
		});
		
		Program program = material.getProgram();
		gl.useProgram(program);
		gl.setVertexAttribPointer("position", 3, 0, vertices);
		gl.enableVertexAttribArray("position");
		gl.setVertexAttribPointer("texCoord", 2, 0, texCoords);
		gl.enableVertexAttribArray("texCoord");
		program.setUniformValue("color.color", 1, 0, 0, 1);
		program.setUniformValue("sinTime", (float)Math.sin((SystemClock.uptimeMillis() - startTime) / 1000f));
		program.setUniformValue("texture", gl.getActiveTextureUnit());
		
		gl.drawArrays(PrimitiveType.TRIANGLES, 0, 3);
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
	}
	
}
