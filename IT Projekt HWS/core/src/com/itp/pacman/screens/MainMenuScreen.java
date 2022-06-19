package com.itp.pacman.screens;

import com.itp.pacman.PacMan;
import com.itp.pacman.stages.MainMenuStage;

public class MainMenuScreen extends GameScreen{
	public MainMenuScreen(PacMan game, int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		stage = new MainMenuStage(game, viewport);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}
}
