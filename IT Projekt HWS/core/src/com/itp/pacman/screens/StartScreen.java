package com.itp.pacman.screens;

import com.itp.pacman.PacMan;
import com.itp.pacman.stages.StartStage;

public class StartScreen extends GameScreen{
	public StartScreen(PacMan game, int screenWidth, int screenHeight) {
		super(game, screenWidth, screenHeight);
		stage = new StartStage(game, viewport);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		game.setScreen(new MainMenuScreen(game, screenWidth, screenHeight));
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		game.setScreen(new MainMenuScreen(game, screenWidth, screenHeight));
		return true;
	}
}
