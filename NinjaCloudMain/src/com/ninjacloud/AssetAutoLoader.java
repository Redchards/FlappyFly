package com.ninjacloud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
//Shoudl read xml file to find objects to load
public class AssetAutoLoader extends AssetManager
{
	public AssetAutoLoader()
	{
		Init();
	}
	
	public void Init()
	{
		load("libgdx.png", Texture.class);
	}
}
