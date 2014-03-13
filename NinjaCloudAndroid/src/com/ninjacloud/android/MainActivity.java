package com.ninjacloud.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ninjacloud.GameApp;
import com.ninjacloud.NinjaCloudApp;

import android.app.Activity;
import android.os.Bundle;

import android.view.Menu;
import android.view.WindowManager;

public class MainActivity extends AndroidApplication{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//setContentView(R.layout.activity_main);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		initialize(new NinjaCloudApp(), cfg);
	}

}
