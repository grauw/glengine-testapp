package nl.grauw.glengine.testapp;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;

public class TestSurfaceView extends GLSurfaceView {
	
	private final TestRenderer renderer;
	
	public TestSurfaceView(Context context) {
		super(context);
		
		Materials materials = new Materials(context.getAssets());
		Bitmap texture = loadBitmapAsset("texture.png");
		
		renderer = new TestRenderer(materials, texture);
		
		setEGLContextClientVersion(2);
		
		setRenderer(renderer);
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
