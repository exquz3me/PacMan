package com.itp.pacman.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.ai.PathingMode;
import com.itp.pacman.entities.BlueGhost;
import com.itp.pacman.entities.OrangeGhost;
import com.itp.pacman.entities.PinkGhost;
import com.itp.pacman.entities.Player;
import com.itp.pacman.entities.RedGhost;
import com.itp.pacman.levels.GameLevel;

public class MainStage extends GameStage{	//stage already has a list of actors
	Player player;
	RedGhost redGhost;	//TODO: handle multiple ghosts: probably just change the level actor array
	BlueGhost blueGhost;	//TODO: handle multiple ghosts: probably just change the level actor array
	OrangeGhost orangeGhost;
	PinkGhost pinkGhost;
	
	private float timer;
	private boolean timerPaused = false;
	
	public MainStage(PacMan game, Viewport viewport) {
		super(game, viewport);	
		GameLevel level = new GameLevel(game, 28, 31, 16, 16);
		addActor(level);
		game.setLevel(level);
		
		for(int i = 0; i < level.getActorData().length; i++) {		
			if(level.getActorData()[i] == 1) {
				player = new Player(game);
				player.setPositionInLevel(i);
				addActor(player);
				setKeyboardFocus(player);
			}
			
			if(level.getActorData()[i] == 2) {	//TODO: need a ghost manager
				redGhost = new RedGhost(game);
				redGhost.setPathingMode(PathingMode.SCATTER);
				redGhost.setPositionInLevel(i);
				addActor(redGhost);
			}
			
			if(level.getActorData()[i] == 3) {
				blueGhost = new BlueGhost(game);
				blueGhost.setPathingMode(PathingMode.SCATTER);
				blueGhost.setPositionInLevel(i);
				addActor(blueGhost);
			}
			
			if(level.getActorData()[i] == 4) {
				orangeGhost = new OrangeGhost(game);
				orangeGhost.setPathingMode(PathingMode.SCATTER);
				orangeGhost.setPositionInLevel(i);
				addActor(orangeGhost);
			}
			
			if(level.getActorData()[i] == 5) {
				pinkGhost = new PinkGhost(game);
				pinkGhost.setPathingMode(PathingMode.SCATTER);
				pinkGhost.setPositionInLevel(i);
				addActor(pinkGhost);
			}
		}
	}
	
	@Override
	public void draw() {
		super.draw();
		redGhost.setChaseIndex(player);
		blueGhost.setChaseIndex(player, redGhost);
		orangeGhost.setChaseIndex(player);
		pinkGhost.setChaseIndex(player);
		
		if(!timerPaused && redGhost.getPathingMode() != PathingMode.FRIGHTENED) {	//TODO: if any ghost is in frightened mode stop timer
			timer += Gdx.graphics.getDeltaTime();
			timerActions(timer);
		}

		if(redGhost.getPathingMode() != PathingMode.FRIGHTENED) {
			if(player.getPositionInLevel() == redGhost.getPositionInLevel() ) {
				Gdx.app.log("Main screen", "caught");
			}
			if(player.getPositionInLevel() == blueGhost.getPositionInLevel()) {
				Gdx.app.log("Main screen", "caught");
			}
			if(player.getPositionInLevel() == orangeGhost.getPositionInLevel()) {
				Gdx.app.log("Main screen", "caught");
			}
			if(player.getPositionInLevel() == pinkGhost.getPositionInLevel()) {
				Gdx.app.log("Main screen", "caught");
			}
		}
		
		if(redGhost.getPathingMode() == PathingMode.FRIGHTENED || 
		   blueGhost.getPathingMode() == PathingMode.FRIGHTENED ||
		   orangeGhost.getPathingMode() == PathingMode.FRIGHTENED ||
		   pinkGhost.getPathingMode() == PathingMode.FRIGHTENED) {
			if(player.getPositionInLevel() == redGhost.getPositionInLevel()) {
				redGhost.setIsEaten(true);
				blueGhost.getMovementManager().setMoveSpeed(2f);
				redGhost.setPathingMode(PathingMode.BACKTRACK);
			}
			
			if(player.getPositionInLevel() == blueGhost.getPositionInLevel()) {
				blueGhost.setIsEaten(true);
				blueGhost.getMovementManager().setMoveSpeed(2f);
				blueGhost.setPathingMode(PathingMode.BACKTRACK);
			}
			
			if(player.getPositionInLevel() == orangeGhost.getPositionInLevel()) {
				orangeGhost.setIsEaten(true);
				blueGhost.getMovementManager().setMoveSpeed(2f);
				orangeGhost.setPathingMode(PathingMode.BACKTRACK);
			}
			
			if(player.getPositionInLevel() == pinkGhost.getPositionInLevel()) {
				pinkGhost.setIsEaten(true);
				blueGhost.getMovementManager().setMoveSpeed(2f);
				pinkGhost.setPathingMode(PathingMode.BACKTRACK);
			}
		}
		
		
		if(game.getLevel().getEntityData()[player.getPositionInLevel()] == 2) {
			timerPaused = true;
			
			if(redGhost.getPathingMode() != PathingMode.BACKTRACK) {
				redGhost.getMovementManager().setMoveSpeed(0.4f);
				redGhost.getAnimationManager().setCurrentAnim("GhostEatable");
				redGhost.setPathingMode(PathingMode.FRIGHTENED);
			}
			
			if(blueGhost.getPathingMode() != PathingMode.BACKTRACK) {
				blueGhost.getMovementManager().setMoveSpeed(0.4f);
				blueGhost.getAnimationManager().setCurrentAnim("GhostEatable");
				blueGhost.setPathingMode(PathingMode.FRIGHTENED);
			}
			
			if(orangeGhost.getPathingMode() != PathingMode.BACKTRACK) {
				orangeGhost.getMovementManager().setMoveSpeed(0.4f);
				orangeGhost.getAnimationManager().setCurrentAnim("GhostEatable");
				orangeGhost.setPathingMode(PathingMode.FRIGHTENED);
			}
			
			if(pinkGhost.getPathingMode() != PathingMode.BACKTRACK) {
				pinkGhost.getMovementManager().setMoveSpeed(0.4f);
				pinkGhost.getAnimationManager().setCurrentAnim("GhostEatable");
				pinkGhost.setPathingMode(PathingMode.FRIGHTENED);
			}

		}
	}
	
	public void timerActions(float timer) {		//TODO: i need a ghost handler that will call every ghost up
		if(timer >= 79) {
			redGhost.setPathingMode(PathingMode.CHASE);
			blueGhost.setPathingMode(PathingMode.CHASE);
			orangeGhost.setPathingMode(PathingMode.CHASE);
			pinkGhost.setPathingMode(PathingMode.CHASE);
		} else if(timer >= 74) {
			redGhost.setPathingMode(PathingMode.SCATTER);
			blueGhost.setPathingMode(PathingMode.SCATTER);
			orangeGhost.setPathingMode(PathingMode.SCATTER);
			pinkGhost.setPathingMode(PathingMode.SCATTER);
		} else if(timer >= 54) {
			redGhost.setPathingMode(PathingMode.CHASE);
			blueGhost.setPathingMode(PathingMode.CHASE);
			orangeGhost.setPathingMode(PathingMode.SCATTER);
			pinkGhost.setPathingMode(PathingMode.SCATTER);
		} else if(timer >= 41) {
			redGhost.setPathingMode(PathingMode.SCATTER);
			blueGhost.setPathingMode(PathingMode.SCATTER);
			orangeGhost.setPathingMode(PathingMode.SCATTER);
			pinkGhost.setPathingMode(PathingMode.SCATTER);
		} else if(timer >= 34) {
			redGhost.setPathingMode(PathingMode.CHASE);
			blueGhost.setPathingMode(PathingMode.CHASE);
			orangeGhost.setPathingMode(PathingMode.CHASE);
			pinkGhost.setPathingMode(PathingMode.CHASE);
		} else if(timer >= 27) {
			redGhost.setPathingMode(PathingMode.SCATTER);
			blueGhost.setPathingMode(PathingMode.SCATTER);
			orangeGhost.setPathingMode(PathingMode.SCATTER);
			pinkGhost.setPathingMode(PathingMode.SCATTER);
		} else if(timer >= 7) {
			redGhost.setPathingMode(PathingMode.CHASE);
			blueGhost.setPathingMode(PathingMode.CHASE);
			orangeGhost.setPathingMode(PathingMode.CHASE);
			pinkGhost.setPathingMode(PathingMode.CHASE);
		} else if(timer >= 0) {
			redGhost.setPathingMode(PathingMode.SCATTER);
			blueGhost.setPathingMode(PathingMode.SCATTER);
			orangeGhost.setPathingMode(PathingMode.SCATTER);
			pinkGhost.setPathingMode(PathingMode.SCATTER);
		}
	}
}