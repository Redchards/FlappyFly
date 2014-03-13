package com.ninjacloud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.Game;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.physics.box2d.*;
public class GameApp extends Game
{
	public SplashScreen splashScreen;
	public MenuScreen menuScreen;
	public GameScreen gameScreen;// = new GameScreen(this, new Color(255f, 255f, 255f, 255f));
	
	private FPSLogger fpsLog;
	
	@Override
	public void create()
	{
		splashScreen = new SplashScreen(this, new Color(255f, 255f, 255f, 255f));;
		menuScreen = new MenuScreen(this, new Color(255f, 255f, 255f, 255f));
		gameScreen = new GameScreen(this, new Color(255f, 255f, 255f, 255f));
		Gdx.app.log(LogPath.getLogPath(), "Starting application");
		fpsLog = new FPSLogger();
	}
	
	@Override
	public void render()
	{
		super.render();
		fpsLog.log();
	}
	
	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		Gdx.app.log(LogPath.getLogPath(), "Resize application to " + Integer.toString(width) + " / " + Integer.toString(height));
	}
	
	@Override
	public void pause()
	{
		super.pause();
		Gdx.app.log(LogPath.getLogPath(), "Game pending");
	}
	
	@Override
	public void resume()
	{
		super.resume();
		Gdx.app.log(LogPath.getLogPath(), "Resuming game");
	}
	
	@Override
	public void dispose()
	{
		//super.dispose();
		Gdx.app.log(LogPath.getLogPath(), "Quit game");
	}
}
