package nl.grauw.glengine.testapp;

import android.content.res.AssetManager;

import nl.grauw.glengine.scene.MaterialRegistry;

public class Materials extends MaterialRegistry {
	
	private final TestMaterial testMaterial;
	
	public Materials(AssetManager assets, Textures textures) {
		super(assets);
		
		testMaterial = register(new TestMaterial(loadTextAsset("test.vert"), loadTextAsset("test.frag"), textures));
	}
	
	public TestMaterial getTestMaterial() {
		return testMaterial;
	}
	
}
