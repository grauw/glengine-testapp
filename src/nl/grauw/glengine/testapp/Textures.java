package nl.grauw.glengine.testapp;

import android.content.res.AssetManager;

import nl.grauw.glengine.gl.Texture2D;
import nl.grauw.glengine.scene.TextureLoader.TextureLoader2D;
import nl.grauw.glengine.scene.TextureRegistry;

public class Textures extends TextureRegistry {
	
	private final TextureLoader2D testTexture;
	
	public Textures(AssetManager assets) {
		testTexture = register(new TextureLoader2D(assets, "texture.png"));
	}
	
	public Texture2D getTestTexture() {
		return testTexture.getTexture();
	}
	
}
