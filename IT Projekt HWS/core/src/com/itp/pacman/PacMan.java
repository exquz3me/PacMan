package com.itp.pacman;

import com.badlogic.gdx.Game;
import com.itp.pacman.screens.MainScreen;

//TODO: 
//assetManager, dispose, finish texture atlas, add to String methods for easier debuging
//set the viewport size to the level size (set after the creation of a stage)

public class PacMan extends Game {	
	private final int VIRTUAL_WIDTH = 16*28;
	private final int VIRTUAL_HEIGHT = 16*36;
	
	@Override
	public void create () {
	    setScreen(new MainScreen(this, VIRTUAL_WIDTH, VIRTUAL_HEIGHT));
	}
	
	@Override
	public void dispose () {
		getScreen().dispose();
	}
	
	public int getVirtualWidth() {
		return VIRTUAL_WIDTH;
	}
	
	public int getVirtualHeight() {
		return VIRTUAL_HEIGHT;
	}
}