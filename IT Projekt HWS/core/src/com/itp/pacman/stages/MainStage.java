package com.itp.pacman.stages;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.levels.LevelManager;

public class MainStage extends GameStage{	//stage already has a list of actors
	public MainStage(PacMan game, Viewport viewport) {
		super(game, viewport);	
		LevelManager levelHandler = new LevelManager(game, this); 
		GameLevel level = new GameLevel(game, "map", 16, 19, 8, 8);
		levelHandler.add("Example Level", level);	//move this to custom level class
		levelHandler.setLevel("Example Level");
	}
}