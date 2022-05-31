package com.itp.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.itp.pacman.PacMan;
import com.itp.pacman.stages.MainMenuStage;

public class MenuScreen extends GameScreen{ //contains what the individual screen has
	public MenuScreen(PacMan game) {
		super(game);
		stage = new MainMenuStage(game);
	}
	
	//input outside of buttons
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("Clicked", "HAHA YOU MISSED");			//called if you click anywhere on the screen
		return true; //we set it to true because it has been handled, if we want to continue processing the event keep false
	}
}
