package com.ninjacloud;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class SplashScreen extends BaseScreen
{
	private Texture splashTex;
	private TextureRegion texRegion;
	
	public SplashScreen(GameApp app, Color clearColor)
	{
		super(app, clearColor);	
	}
	// Maybe add in each method a debug log to inform in which screen we are
	@Override
	public void show()
	{
		super.show();
		try
		{
			splashTex = new Texture(Gdx.files.internal("libgdx.png"));
			splashTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		catch(com.badlogic.gdx.utils.GdxRuntimeException e)
		{
			Gdx.app.log(LogPath.getLogPath(), "Something went wrong during texture loading or batching!");
		}
	}
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		/*spriteBatch.begin();
		spriteBatch.setColor(clearColor);
		spriteBatch.draw(texRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBatch.end();*/
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		splashTex.dispose();
	}
	
	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		// Build coordinates according to width and height
		texRegion = new TextureRegion(splashTex, 0, 0, 512, 256);
		stage.clear();
		stage.setViewport(512, 256, true);
		stage.getCamera().position.set(512/2, 256/2, 0);
		
		TextureRegionDrawable drawableTexRegion = new TextureRegionDrawable(texRegion);
		Image splashImage = new Image(drawableTexRegion, Scaling.stretch, Align.center | Align.center);
		
		Color fadeColor = splashImage.getColor();
		fadeColor.a = 0f;
		splashImage.setColor(fadeColor);
		//sequence(fadeIn(2.75f), delay(1.75f), fadeOut(0.75f), 
		splashImage.addAction(sequence(fadeIn(2.75f), delay(1.75f), fadeOut(0.75f), run(new Runnable(){
			public void run(){
		    	//app.getScreen().dispose();
				app.setScreen(app.menuScreen);
				Gdx.app.log(LogPath.getLogPath(), "Splash screen cycle completed!");
			}
		}
		)));
		
		stage.addActor(splashImage);
	}
}
