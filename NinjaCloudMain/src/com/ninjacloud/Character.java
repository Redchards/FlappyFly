package com.ninjacloud;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;



// Must dispose atlas at one point
public class Character extends Actor
{
	private Sprite fall;
	private Sprite fly;
	
	private Sprite currentSprite;
	private  Rectangle rectangle;
	
	boolean isAlive;
	
		
	boolean isFlapping = false;
		
	public Character()
	{
		TextureAtlas atlas; // Dispose this in a method
		
		atlas = new TextureAtlas("graphics/NinjaCloud.atlas");
		
		try {
		//AtlasRegion atlasReg = atlas.findRegion("flyBody");
		    fall = atlas.createSprite("flyFall");
		    fly = atlas.createSprite("flyBody");
		}
		
		catch(com.badlogic.gdx.utils.GdxRuntimeException e){
			Gdx.app.log(LogPath.getLogPath(), "Something went wrong during texture loading or batching!");
			fall = null;
			fly = null;
		}
		
		currentSprite = fall;
		
		rectangle = currentSprite.getBoundingRectangle();
		rectangle.setWidth(rectangle.getWidth()-1);
		rectangle.setHeight(rectangle.getHeight()-1);
		
	}
	
	public void animateFall()
	{
		currentSprite = fall;
	}
	
	public void animateFly()
	{
		currentSprite = fly;
	}
	
	public void reinit()
	{
		isAlive = true;
		setOrigin(getWidth()/2, getHeight()/2);
	}
	
	public void flap()
	{
		if(isAlive)
		{
			//Fix getY()+.. to match screen size
			isFlapping = true;
			addAction(sequence(moveTo(getX(), getY()+400, 1.7f), rotateTo(70f, 0.5f)));
			addAction(sequence(run(new Runnable()
			{ public void run(){
				animateFly();}}), delay(0.2f), run(new Runnable(){
					public void run(){
						animateFall();
					}
				}), run(new Runnable()
				{ public void run(){
					isFlapping = false;
				}})));
		}
		//velocity.y += 10f;
	}
	
	public void applyGravity()
	{
		if(isAlive)
		{
			//Same remark has in flap function
			if(getY() <= 2)
			{
				die();
			}
			if(!isFlapping)
			{
				addAction(sequence(moveTo(getX(), 0, 1+getY()/300), rotateTo(-70f, 0.5f)));
			}
		}
	}
	
	public void die()
	{
		isAlive = false;
		addAction(sequence(moveTo(getX(), 0f, 0.5f), run(new Runnable(){
			public void run(){
				fire(new GameOverEvent());
			}
		})));
	}
	
	public boolean isAlive()
	{
		return isAlive;
	}
	
	public Rectangle getRectangle()
	{
		return rectangle;
	}
	
	@Override
	public void draw(SpriteBatch batcher, float parentAlpha)
	{
		rectangle.setX(getX());
		rectangle.setY(getY());
		batcher.draw(currentSprite, getX(), getY());
	}
}
