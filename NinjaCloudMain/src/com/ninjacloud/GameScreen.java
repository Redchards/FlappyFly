package com.ninjacloud;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GameScreen extends BaseScreen
{
	private Character character;
	public boolean fixed = true;
	public boolean isInit = false;
	private float obstacleTimer;
	BufferedReader br;
	String currentPos;
	int currentWidth;
	int currentHeight;
	
	InfiniteScrolling scroll;
	
	private Array<Obstacle> obstacles;
	
	public GameScreen(GameApp app, Color color)
	{
		super(app, color);
		br = Gdx.files.internal("obstacles.txt").reader(100);
		//Enable this when changing art, but not
		/*try {
			TexturePacker.pack("NinjaCloud");
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}
	
	@Override
	public void show()
	{
		super.show();
		System.out.println("show");
		if(isInit)
		{
			init();
		}
		
		stage.getCamera().position.set(0, 0, 0);

		stage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				character.flap();
			}
		});
		
		Gdx.input.setInputProcessor(stage);
		
		fixed = true;
		
		//if(!Gdx.input.isClicked())
		//character.addAction(moveTo(character.getX(), character.getY()-20*Gdx.graphics.getDeltaTime(), 0));
	}
	
	@Override
	public void render(float alpha)
	{
		super.render(alpha);
		if(character.isAlive())
		{
			obstacleTimer += Gdx.graphics.getDeltaTime(); // Here, /5 is for speed reducing
			if(obstacleTimer >= 3.0f)
			{
				try {
					if((currentPos = br.readLine()) != null)
					{
						System.out.println("pass");
						PairObstacle tmp = new PairObstacle("graphics/obstacle.png", new Vector2(currentWidth, -1500f + Float.parseFloat(currentPos)), 120f);
						//PairObstacle tmp = new PairObstacle("graphics/obstacle.png", new Vector2(currentWidth, -1420f), 120f);
						tmp.addPairToStage(stage);
						updateObstacles();
					}
					else
					{
						// No, we need to pass last obstacles
						System.out.println("you won");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				obstacleTimer = 0f;
			}
			character.applyGravity();
			
			for(Obstacle obs : obstacles)
			{
				obs.addAction(moveBy(-2f, 0f, 1f));
				if(Intersector.overlaps(character.getRectangle(), obs.getRectangle()))
				{
					character.die();
					//System.out.println("You die by hitting obstacle !");
				}
			}
		}
		else
		{
			scroll.stop();
			Timer.schedule(new Task(){
			    @Override
			    public void run() {
			    	//app.getScreen().dispose();
			    	disposeActors();
			        app.setScreen(app.menuScreen);
			    }
			}, 2);
		}
	}
	
	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		
		currentWidth = width;
		currentHeight = height;
		
		if(!isInit)
		{
			character = new Character();
			scroll = new InfiniteScrolling(currentWidth, currentHeight);
			isInit = true;
			
			init();
		}
	}
	
	@Override
	public void hide()
	{
		super.hide();
		//disposeActors();
		stage.clear();
	}
	
	public void init()
	{
		character.setX(currentWidth * 0.5f);
		character.setY(currentHeight * 0.5f);
		
		stage.addActor(scroll);
		scroll.start();
		
		stage.addActor(character);
		character.reinit();
		//PairObstacle tmp = new PairObstacle("graphics/obstacle.png", new Vector2(currentWidth, -1420f), 120f);
		//tmp.addPairToStage(stage);
		//stage.addActor(new Obstacle("graphics/obstacle.png", new Vector2(width/2, height/2)));
		updateObstacles();
	}
	
	private void updateObstacles()
	{
		Array<Actor> tmp = stage.getActors();
		Array<Obstacle> newArray = new Array<Obstacle>();
		for(Actor actor : tmp)
		{
			if(actor.getClass() == Obstacle.class )
			{
				newArray.add((Obstacle)actor);
			}
		}
		obstacles = newArray;
	}
	
	private void disposeActors()
	{
		Array<Actor> tmp = stage.getActors();
		for(Actor actor : tmp)
		{
			if(actor.getClass() == Obstacle.class)
			{
				Obstacle tmp1 = (Obstacle)actor;
				tmp1.dispose();
			}
		}
	}
	
	@Override
	public void dispose()
	{
		stage.dispose();
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
