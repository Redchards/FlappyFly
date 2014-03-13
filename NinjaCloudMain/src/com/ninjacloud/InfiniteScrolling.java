package com.ninjacloud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Scaling;

public class InfiniteScrolling extends Actor
{
	//SpriteBatch batcher = new SpriteBatch();
	Sprite backgroundSprite;
	Texture background;
	
	float scrollTimer = 0f;
	
	boolean isScrolling = true;
	
	public InfiniteScrolling(int width, int height)
	{
		Texture.setEnforcePotImages(false);
		background = new Texture("graphics/background.png");
		
		background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		
		backgroundSprite = new Sprite(background, 0, 0, width, height);
	}
	
	public void stop()
	{
		isScrolling = false;
	}
	
	public void start()
	{
		isScrolling = true;
	}
	
	@Override
	public void draw(SpriteBatch batcher, float parentAlpha)
	{
		if(isScrolling)
		{
			scrollTimer += Gdx.graphics.getDeltaTime()/10; // Here, /5 is for speed reducing
			if(scrollTimer>1.0f)
			{
				scrollTimer = 0f;
			}
			
			backgroundSprite.setU(scrollTimer);
			backgroundSprite.setU2(scrollTimer+1);
		}
		
		backgroundSprite.draw(batcher);
	}
	
	public void dispose()
	{
		background.dispose();
	}
}
