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
import com.itp.pacman.screens.MenuScreen;
import com.itp.pacman.stages.StartStage;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class PacMan extends Game {
	//this code applies to everything (shared properties)
	
	//passed on vars
	private Viewport viewport;
	public final float WORLD_WIDTH = 300;		//virtuall cooridniate system
	public final float WORLD_HEIGHT = 300;		//https://sodocumentation.net/libgdx/topic/4219/supporting-multiple-resolutions
	
	@Override
	public void create () {   
	    setViewport(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT)); //default viewport
	    setScreen(new MenuScreen(this)); //passes a references of this class to the gamescreen
	   
	    //change gamescreen to main menu  
        //TODO: window should be the fit viewport, you should only be able to resize the screen in the same apscet ratio
	}
	
	@Override
	public void dispose () {	//TODO: findout how dispose works, and what has to be disposed
	}

	public Viewport getViewport() {
		return viewport;
	}
	
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
}
