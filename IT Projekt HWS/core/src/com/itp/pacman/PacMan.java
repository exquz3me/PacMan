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
	public final float WORLD_WIDTH = 10;
	public final float WORLD_HEIGHT = 10;
	
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
}
