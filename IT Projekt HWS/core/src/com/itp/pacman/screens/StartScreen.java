package com.itp.pacman.screens;
import com.itp.pacman.PacMan;
import com.itp.pacman.stages.StartStage;

public class StartScreen extends GameScreen{
	public StartScreen(PacMan game) {
		super(game);
		stage = new StartStage(game);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		game.setScreen(new MenuScreen(game));
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		game.setScreen(new MenuScreen(game));
		return true;
	}
}
