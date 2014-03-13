package com.ninjacloud;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class TexturePacker 
{
	static final String assetPath = "../NinjaCloudAndroid/assets/";
	
	public static void pack(String arg) throws IOException
	{
		Settings settings = new Settings();
		settings.maxWidth = 512;
		settings.maxHeight = 512;
		
		TexturePacker2.process(settings, assetPath + "images", assetPath + "graphics", arg);
	}
}
