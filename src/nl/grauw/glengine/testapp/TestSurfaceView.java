package nl.grauw.glengine.testapp;

import java.io.File;
import java.io.IOException;

import nl.grauw.glengine.scene.Material;
import nl.grauw.glengine.scene.MaterialManager;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;

public class TestSurfaceView extends GLSurfaceView {
	
	private TestRenderer renderer;
	
	public TestSurfaceView(Context context) {
		super(context);
		
		MaterialManager materialManager = new MaterialManager(getContext().getAssets());
		
		Material material = materialManager.createMaterial(new File("test.vert"), new File("test.frag"));
		Bitmap texture = getTextureBitmap();
		
		renderer = new TestRenderer(material, texture);
		
		setEGLContextClientVersion(2);
		
		setRenderer(renderer);
	}
	
	public Bitmap getTextureBitmap() {
		AssetManager assets = getContext().getAssets();
		try {
			return BitmapFactory.decodeStream(assets.open("texture.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
