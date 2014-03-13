package com.ninjacloud.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.ninjacloud.NinjaCloudApp;

public class DesktopGame 
{
	public static void main (String[] args)
	{
		new LwjglApplication(new NinjaCloudApp(), "NinjaCloud", 680, 480, false);
	}

}
