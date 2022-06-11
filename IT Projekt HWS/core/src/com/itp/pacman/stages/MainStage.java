package com.itp.pacman.stages;

import com.itp.pacman.PacMan;
import com.itp.pacman.entities.Player;
import com.itp.pacman.levels.CustomLevel;
import com.itp.pacman.levels.GameLevel;

public class MainStage extends GameStage{
	public MainStage(PacMan game) {
		super(game);
		
		GameLevel level = new CustomLevel(game, "map", 16, 19, 8, 8);
		addActor(level);
		
		game.setLevel(level); //TODO: move to levelHandler
		
		Player player = new Player(game);
		addActor(player);
		setKeyboardFocus(player);
	}
}