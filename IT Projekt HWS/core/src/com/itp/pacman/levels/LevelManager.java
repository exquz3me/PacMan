package com.itp.pacman.levels;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.itp.pacman.PacMan;
import com.itp.pacman.entities.Ghost;
import com.itp.pacman.entities.Player;
import com.itp.pacman.stages.GameStage;

public class LevelManager {		//TODO: get and set
	private HashMap<String, GameLevel> levels = new HashMap<>();
	private PacMan game;
	private GameStage stage;
	
	public LevelManager(PacMan game, GameStage stage) {	//this object is in stage
		this.game = game;
		this.stage = stage;
	}
	
	public void add(String levelName, GameLevel level) {
		levels.put(levelName, level);
	}
		
	public void spawnActors(String levelName) {
		GameLevel level = levels.get(levelName);
		for(int i = 0; i < level.getActorData().length; i++) {		
			if(level.getActorData()[i] == 1) {
				Player player = new Player(game);
				player.setMiddlePosX(level.getTileCenterPosX(i));
				player.setMiddlePosY(level.getTileCenterPosY(i));
				stage.addActor(player);
				stage.setKeyboardFocus(player);
			}
			
			if(level.getActorData()[i] == 2) {	//TODO: need a ghost manager to set the colors?
				Ghost ghost = new Ghost(game, new Color(0, 50, 100, 255)); //
				ghost.setMiddlePosX(level.getTileCenterPosX(i));
				ghost.setMiddlePosY(level.getTileCenterPosY(i));
				stage.addActor(ghost);
			}
			//TODO: set ghost position... etc
		}
	}
	
	public GameLevel getLevel(String levelName) {
		return levels.get(levelName);
	}
	
	public void setLevel(String levelName) {
		GameLevel level = levels.get(levelName);
		game.setLevel(level);
		stage.addActor(level);
		spawnActors(levelName); //despwan/dispose old actors?
	}
}

//TODO: i dont like the -tileSizeY in totalSizeY-tileSizeY, cant we just multiply it one less time?
//TODO: getX() and getY() give the world position, but we need position relative to parent!
//TODO: player movementcode is messy, add a way for arrayPos to know if a position is inbounds or not
//TODO: dispose?
//TODO: the player is an enity, it gets spawned on the cord of the index it was set to
//		the ghosts are entitys too -> the ghosts, aswell as the player, dont share place with a pellet
//TODO: other level classes contain only the dataArrays and the draw method
//TODO: how about setting all the variables in this class and creating a level in the construcor
//-> we add a levelHanlder to the stage that is capabable of displaying diffetent levels -> has the draw function
//TODO: we need a way to set tileSizeX and Y to the region size to avoid having to know the tile size

