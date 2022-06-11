package com.itp.pacman.screens;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.stages.GameStage;
import com.itp.pacman.stages.MainStage;

public class MainScreen extends GameScreen{
	public MainScreen(PacMan game) {
		super(game);
		stage = new MainStage(game);
		viewport = new FitViewport(180, 180);
	}		
}