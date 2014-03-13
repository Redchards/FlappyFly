package com.ninjacloud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SoftBody 
{
	private final int SEGMENTS = 16;
	private final float SPRING = 10f;
	private final World physicWorld = new World(new Vector2(0, 2f),false );
	
	private final Array<Body> circle = new Array<Body>();
	private final Array<DistanceJoint> joints = new Array<DistanceJoint>();
	
	
	private final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	private final SpriteBatch batch = new SpriteBatch();
	public SoftBody()
	{
		//DEBUG !! ONLY FOR TESTING !!!
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 10));
		Body groundBody = physicWorld.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(10f, 10f);
		groundBody.createFixture(groundBox, 0.0f);
		
		
		BodyDef circleDef = new BodyDef();
		circleDef.type = BodyType.DynamicBody;
		
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.setRadius(1f);
		
		FixtureDef fixture = new FixtureDef();
		fixture.shape = dynamicCircle;
		fixture.density = 0.1f;
		fixture.friction = 1f;
		fixture.restitution = 0.05f;
		
		float x, y;
		
		for(int i = 0; i < SEGMENTS; i++)
		{
			x = MathUtils.cosDeg(360 / SEGMENTS * i) * 50;
			y = MathUtils.sinDeg(360 / SEGMENTS * i) * 50;
			circleDef.position.set(x, y);
			
			Body newCircularBody = physicWorld.createBody(circleDef);
			circle.add(newCircularBody);
			newCircularBody.createFixture(fixture);
		}
	}
	
	public void render()
	{
		batch.begin();
		//debugRenderer.render(physicWorld, camera.combined);
		batch.end();
	}
	
	
}
