package com.itp.pacman.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.ai.GhostManager;
import com.itp.pacman.ai.PathingMode;
import com.itp.pacman.entities.BlueGhost;
import com.itp.pacman.entities.OrangeGhost;
import com.itp.pacman.entities.PinkGhost;
import com.itp.pacman.entities.Player;
import com.itp.pacman.entities.RedGhost;
import com.itp.pacman.levels.ClassicLevel;

//TODO: 
//for some reason collisions with ghosts do not always get called (even on low speeds), hard to recreate
//rewrite the current timer code (create a new class)

public class MainStage extends GameStage {
	private Player player;
	private RedGhost redGhost;
	private BlueGhost blueGhost;
	private PinkGhost pinkGhost;
	private OrangeGhost orangeGhost;
	private float disableDuration;
	
	public MainStage(PacMan game, Viewport viewport) {
		super(game, viewport);
		
		ghostManager = new GhostManager(this);
		level = new ClassicLevel(game, this, 16, 16);
		addActor(level);
		
		player = new Player(this);
		setKeyboardFocus(player);
		addGameActor(1, player);
		
		redGhost = new RedGhost(this, player);	
		addGameActor(2, redGhost);	
		
		blueGhost = new BlueGhost(this, player, redGhost);
		addGameActor(3, blueGhost);	
		
		pinkGhost = new PinkGhost(this, player);
		addGameActor(4, pinkGhost);		
		
		orangeGhost = new OrangeGhost(this, player);
		addGameActor(5, orangeGhost);	
		
		spawnActors();
	}
	
	@Override
	public void draw() {
		super.draw();
			
		if(timerPaused) {
			timerDisabledDuration+= Gdx.graphics.getDeltaTime();
			enableTimer(timerDisabledDuration, disableDuration);			
		} else {
			ghostManager.timerActions(timer);
		}
		
		if(ghostManager.compareTile(player)) {
			level.reset();
		}	
		//ghostManager.debugGoalIndex(getBatch());
	}
	
	@Override
	public void disableTimer(float disableDuration) {
		timerPaused = true;	
		this.disableDuration = disableDuration;
	}
	
	@Override
	public void enableTimer(float timerDisabledDuration, float disableDuration) {
		if(timerDisabledDuration >= disableDuration) {
			timerPaused = false;
			timerDisabledDuration = 0;
			ghostManager.setPathingMode(PathingMode.SCATTER);
			ghostManager.setAnimations("GhostNormal");
		}
	}
}