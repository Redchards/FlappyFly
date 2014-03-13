package com.ninjacloud;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PairObstacle 
{
	Obstacle up;
	Obstacle down;
	
	public PairObstacle(String path, Vector2 pos, float distance)
	{
		up = new Obstacle(path, pos);
		
		pos.y += 1500 + distance;
		down = new Obstacle(path, pos);
	}
	
	public void addPairToStage(Stage stage)
	{
		stage.addActor(up);
		stage.addActor(down);
	}
}
