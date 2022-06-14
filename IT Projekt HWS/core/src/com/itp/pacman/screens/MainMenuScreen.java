package com.itp.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.itp.pacman.PacMan;
import com.itp.pacman.stages.MainMenuStage;

public class MainMenuScreen extends GameScreen{
	public MainMenuScreen(PacMan game, int screenWidth, int screenHeight) {
		super(game, screenWidth, screenHeight);
		stage = new MainMenuStage(game, viewport);
	}			//pixelart games should use frame buttons?
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("Clicked", "HAHA YOU MISSED");	//called if you click anywhere on the screen
		return true; //we set it to true because it has been handled, if we want to continue processing the event keep false TODO: what does this mean?
	}
}
