package com.ninjacloud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends BaseScreen
{
	private static final float BUTTON_WIDTH = 300f;
	private static final float BUTTON_HEIGHT = 60f;
	private static final float BUTTON_SPACING = 10f;
	
	private final Table table = new Table();
	private final TextButton startGameButton = new TextButton("Start game", skin);
	private final TextButton optionButton = new TextButton("Options", skin);

	public MenuScreen(GameApp app, Color clearColor) 
	{
		super(app, clearColor);
		
		stage.addActor(table);
	}
	
	public void initListener()
	{
		startGameButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
		    	//app.getScreen().dispose();
				app.setScreen(app.gameScreen);
				Gdx.app.log(LogPath.getLogPath(), "Start game !");
			}
		});
		
		optionButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.log(LogPath.getLogPath(), "Go to option menu");
			}
		});
	}
	
	@Override
	public void show()
	{
		initListener();
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		
		table.clear();
		//stage.clear();
		
		//stage.addActor(table);
		table.setWidth(width);
		table.setHeight(height);
		
		stage.setViewport(width, height);
		stage.getCamera().position.set(width/2, height/2, 0);
		//TableLayout layout = table.getTableLayout();
		
		table.add(startGameButton).width(width/2f).height(height/(height/50f)).padBottom(20);
		table.row();
		table.add(optionButton).width(width/2f).height(height/(height/50f));
		//Label welcomeLabel = new Label("NINJA CLOUD !", super.getSkin());
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
	}
	
}
