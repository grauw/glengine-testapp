package nl.grauw.glengine.testapp;

import android.content.res.AssetManager;

import nl.grauw.glengine.scene.MaterialRegistry;

public class Materials extends MaterialRegistry {
	
	private final TestMaterial testMaterial;
	
	public Materials(AssetManager assets, Textures textures) {
		testMaterial = register(new TestMaterial(assets, textures));
	}
	
	public TestMaterial getTestMaterial() {
		return testMaterial;
	}
	
}
