package com.itp.pacman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.screens.GameScreen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class PacMan extends Game {
	//this code applies to everything (shared properties)
	
	//passed on vars
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private BitmapFont font;
	
	public final float WORLD_WIDTH = 100;		//virtuall cooridniate system
	public final float WORLD_HEIGHT = 100;		//https://sodocumentation.net/libgdx/topic/4219/supporting-multiple-resolutions
	
	@Override
	public void create () {
		setBatch(new SpriteBatch());    
	    setCamera(new OrthographicCamera());
	    setViewport(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT,getCamera())); //default viewport
	    setFont(new BitmapFont());
	    setScreen(new GameScreen(this)); //passes a references of this class to the gamescreen
	    //change gamescreen to main menu
	    
	    //tilemap object
	   
        
        //TODO: window should be the fit viewport, you should only be able to resize the screen in the same apscet ratio
	}
	
	@Override
	public void dispose () {	//TODO: findout how dispose works, and what has to be disposed
		getBatch().dispose();
		getFont().dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public Viewport getViewport() {
		return viewport;
	}
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}
}
