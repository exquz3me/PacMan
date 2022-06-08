package com.itp.pacman;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.screens.MainScreen;
import com.itp.pacman.screens.StartScreen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class PacMan extends Game {
	private Viewport viewport;
	private Skin skin;
	public final float WORLD_WIDTH = 180;
	public final float WORLD_HEIGHT = 180;
	
	
	//PacMan Map is 28x31 tiles				optionally we can add 3 tiles on top and 2 on bottom so we dont have to align anything
	//1 tile is 16x16 -> 448 x 496 pixels
	
	@Override
	public void create () {   
	    setViewport(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT));
        setSkin(new Skin(Gdx.files.internal("uiskin.json")));
	    setScreen(new MainScreen(this)); //start screen
	}
	
	@Override
	public void dispose () {
		//TODO: findout how dispose works, and what has to be disposed
	}

	public Viewport getViewport() {
		return viewport;
	} 
	
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}
	
	//TODO: Add toString methods to all classes, move everything nesessary to desktop version
}
