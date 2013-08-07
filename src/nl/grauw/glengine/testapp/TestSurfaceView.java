package nl.grauw.glengine.testapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nl.grauw.glengine.gl.GLEngineException;
import nl.grauw.glengine.scene.Material;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;

public class TestSurfaceView extends GLSurfaceView {
	
	private final TestRenderer renderer;
	
	public TestSurfaceView(Context context) {
		super(context);
		
		Material material = new Material(loadTextAsset("test.vert"), loadTextAsset("test.frag"));
		Bitmap texture = loadBitmapAsset("texture.png");
		
		renderer = new TestRenderer(material, texture);
		
		setEGLContextClientVersion(2);
		
		setRenderer(renderer);
	}
	
	private String loadTextAsset(String path) {
		AssetManager assets = getContext().getAssets();
		try {
			InputStream input = assets.open(path);
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			StringBuilder text = new StringBuilder();
			
			int length;
			char[] buffer = new char[1024];
			while ((length = reader.read(buffer)) != -1)
				text.append(buffer, 0, length);
			
			return text.toString();
		} catch (IOException e) {
			throw new GLEngineException("Error reading file", e);
		}
	}
	
	public Bitmap loadBitmapAsset(String path) {
		AssetManager assets = getContext().getAssets();
		try {
			return BitmapFactory.decodeStream(assets.open(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
