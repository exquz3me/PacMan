package com.itp.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.entities.MyActor;
import com.itp.pacman.stages.MainMenuStage;
import com.itp.pacman.stages.StartStage;

public class MenuScreen extends ScreenAdapter implements InputProcessor{
	final private PacMan game;
	private Stage stage;
	private Batch batch;
	private Camera camera;
	private Viewport viewport;
	
	private Skin skin;
	private Table table;
	private TextButton startButton;
	private TextButton quitButton;
	
	public MenuScreen(PacMan game) {
		this.game = game;
		viewport = game.getViewport();
	}
	
	public void show() {
		stage = new MainMenuStage(viewport);  
        camera = stage.getCamera();
        batch = stage.getBatch();
        
		skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        //table.setWidth(stage.getWidth());
        table.setFillParent(true);
        table.align(Align.center|Align.top);    
        //table.setPosition(0, Gdx.graphics.getHeight());

    	startButton = new TextButton("New Game", skin);	
    	startButton.addListener(new ClickListener(){
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
        		Gdx.app.log("Clicked", "start");
    		}
    	});
    	
    	quitButton = new TextButton("Quit Game", skin);
    	quitButton.addListener(new ClickListener(){
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
        		Gdx.app.log("Clicked", "quit");
    		}
    	});
    	
    	//table.setFillParent(true); //causing it to be sized to its parent (in this case, the stage) when validated
    	
    	//html table system
    	table.padTop(30);
    	table.add(startButton).padBottom(30); //this works because .add returns the table
    	table.row();
    	table.add(quitButton);
  
    	/*
        table.setX(100);
        table.setY(150);
    	table.setOrigin(0,0);
    	*/
    	
        table.debug();
        /*
    	final TextButton button = new TextButton("Click Me", skin, "default");
    	button.setWidth(200);
    	button.setHeight(50);
    	
    	final Dialog dialog = new Dialog("Click Message", skin);
    	button.addListener(new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			dialog.show(stage);
    			Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						dialog.hide();
					}
				},1.45f);
    			Gdx.app.log("Clicked", null);
    		}
    	});*/
    	
    	stage.addActor(table);
    	InputMultiplexer im = new InputMultiplexer(stage, this); //combines input processors, order is important first is priority
        Gdx.input.setInputProcessor(im);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.10f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		
		//called if you click anywhere on the screen
		Gdx.app.log("Clicked", "HAHA YOU MISSED");
		return true; //we set it to true because it has been handled, if we want to continue processing the event keep false
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
