package com.itp.pacman.stages;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.ai.Ghost;
import com.itp.pacman.ai.GhostManager;
import com.itp.pacman.ai.PathingMode;
import com.itp.pacman.entities.GameActor;
import com.itp.pacman.levels.GameLevel;

//TODO: 
//create a Timer class for easier manipulation
//create a levelManager class for multiple level support

public abstract class GameStage extends Stage{
	protected PacMan game;
	protected HashMap<Integer, GameActor> gameActors = new HashMap<>();
	protected TextureAtlas atlas;
	protected Skin skin;
	protected GameLevel level;
	protected GhostManager ghostManager;
	
	protected float timer;
	protected boolean timerPaused = false;
	protected float timerDisableDuration;
	protected float timerDisabledDuration;
	protected float disableDuration;

	public GameStage(PacMan game, Viewport viewport) {
		super(viewport);
		this.game = game;
	    atlas = new TextureAtlas(Gdx.files.internal("Sprites.atlas"));
	    skin = new Skin(Gdx.files.internal("uiskin.json"));
		ghostManager = new GhostManager(this);
	}
	
	@Override
	public void draw() {
		if(!timerPaused) {
			timer += Gdx.graphics.getDeltaTime();
		} 
		
		getViewport().apply();
		act(Gdx.graphics.getDeltaTime());
		super.draw();
	}
	
	public void spawnActors() {
		for(int i = 0; i < level.getActorData().length; i++) {
			for(int id : gameActors.keySet()) {
				if(level.getActorData()[i] == id) {
					gameActors.get(id).setPositionInLevel(i);
					if(gameActors.get(id) instanceof Ghost) {
						ghostManager.add(id, (Ghost) gameActors.get(id));
					}
				}
			}
		}
	}

	
	public void disableTimer(float disableDuration) {
		timerPaused = true;
		timerDisabledDuration += Gdx.graphics.getDeltaTime();	
		if(timerDisabledDuration >= disableDuration) {
			timerPaused = false;
			timerDisabledDuration = 0;
		}
	}
	
	public void enableTimer(float timerDisabledDuration, float disableDuration) {
		if(timerDisabledDuration >= disableDuration) {
			Gdx.app.log("", "unpaused");
			timerPaused = false;
			timerDisabledDuration = 0;
			ghostManager.setPathingMode(PathingMode.SCATTER);
			ghostManager.setAnimations("GhostNormal");
		}
	}
	
	public void addGameActor(int actorId, GameActor gameActor) {
		gameActors.put(actorId, gameActor);
		addActor(gameActor);
	}
	
	public GameActor getGameActorById(int actorId) {
		return gameActors.get(actorId);
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}
	
	public Skin getSkin() {
		return skin;
	}
	
	public void setSkin(Skin skin) {
		this.skin = skin;
	}
	
	public GameLevel getLevel() {
		return level;
	}

	public void setLevel(GameLevel level) {
		this.level = level;
	}

	public GhostManager getGhostManager() {
		return ghostManager;
	}

	public void setGhostManager(GhostManager ghostManager) {
		this.ghostManager = ghostManager;
	}
	
	public boolean getTimerPaused() {
		return timerPaused;
	}

	public void setTimerPaused(boolean timerPaused) {
		this.timerPaused = timerPaused;
	}

	public float getTimerDisableDuration() {
		return timerDisableDuration;
	}

	public void setTimerDisableDuration(float timerDisabledDuration) {
		this.timerDisabledDuration = timerDisabledDuration;
	}
	
	public float getTimerDisabledDuration() {
		return timerDisableDuration;
	}

	public void setTimerDisabledDuration(float timerDisabledDuration) {
		this.timerDisabledDuration = timerDisabledDuration;
	}
	
	
	public float getDisableDuration() {
		return disableDuration;
	}

	public void setDisableDuration(float disableDuration) {
		this.disableDuration = disableDuration;
	}
}
