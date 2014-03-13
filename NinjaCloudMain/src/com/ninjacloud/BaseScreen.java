package com.ninjacloud;

import java.awt.Font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//import com.badlogic.gdx.graphics.Screen;

public class BaseScreen implements Screen
{
	protected final Color clearColor;
	protected Stage stage;
	protected final BitmapFont font;
	protected final Skin skin;
	
	protected final GameApp app;
	
	public BaseScreen(GameApp app, Color clearColor)
	{
		this.clearColor = clearColor;
		this.stage = new Stage(0, 0, true);
		this.app = app;
		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);

		//Warning, texture atlas must be disposed
		TextureAtlas tmp = new TextureAtlas(Gdx.files.internal("UI/uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("UI/uiskin.json"), tmp);
		font = new BitmapFont();
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void pause()
	{
		//Actually, just a placeholder
	}
	
	@Override
	public void resume()
	{
		//Actually, just a placeholder
	}
	
	@Override
	public void resize(int width, int height)
	{
		// May cause trouble !!
		stage.setViewport(width,  height, true);
	}
	
	@Override
	public void hide()
	{
		//Actually, just a placeholder
	}
	
	@Override
	public void show()
	{
		//Actually, just a placeholder
	}
	
	@Override
	public void dispose()
	{
		font.dispose();
		skin.dispose();
		stage.dispose();
		//app.dispose();
	}
}
