package com.itp.pacman.screens;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.stages.MainStage;

public class MainScreen extends GameScreen{
	public MainScreen(PacMan game, int screenWidth, int screenHeight) {	
		super(screenWidth, screenHeight);
		viewport = new ExtendViewport(screenWidth, screenHeight, camera);
		stage = new MainStage(game, viewport);
	}
}