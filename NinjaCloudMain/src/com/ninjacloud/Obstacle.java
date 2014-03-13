package com.ninjacloud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Obstacle extends Actor
{
	Texture spriteTex;
	Sprite sprite;
	Rectangle rectangle;
	
	public Obstacle(String path, Vector2 pos)
	{
		Texture.setEnforcePotImages(false);
		spriteTex = new Texture(path);
		sprite = new Sprite(spriteTex);
		rectangle = sprite.getBoundingRectangle();
		setX(pos.x);
		setY(pos.y);
		
		rectangle.setX(getX());
		rectangle.setY(getY());
	}
	
	public Rectangle getRectangle()
	{
		return rectangle;
	}
	
	@Override
	public void draw(SpriteBatch batcher, float delta)
	{
		rectangle.setX(getX());
		rectangle.setY(getY());
		batcher.draw(sprite, getX(), getY());
	}
	
	public void dispose()
	{
		sprite = null;
		spriteTex.dispose();
	}

}
