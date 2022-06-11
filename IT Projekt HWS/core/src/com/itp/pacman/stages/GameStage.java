package com.itp.pacman.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.itp.pacman.PacMan;

public class GameStage extends Stage{
	protected final PacMan game;
	protected TextureAtlas atlas;
	protected Skin skin;
	protected boolean active = true;
	
	public GameStage(PacMan game) {
		this.game = game;
		atlas = game.getAtlas();
		skin = game.getSkin();
	}
	
	@Override
	public void draw() {
		if (active) {
			act(Gdx.graphics.getDeltaTime());
			super.draw();
		}
	}
	
	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}

/*
Stage is an InputProcessor. When it receives input events, it fires them on the appropriate actors.
	If the stage is being used as a UI on top of other content (eg, a HUD), an InputMultiplexer can be used to first give the stage a chance to handle an event. 
The Actor class is a node in the graph which has a position, rectangular size, origin, scale, rotation, and color.
The Group class is an actor that may have child actors
The Stage class has a camera, SpriteBatch, and a root group and handles drawing the actors and distributing input events.
The stage’s viewport is determined by a Viewport instance.
The viewport manages a Camera and controls how the stage is displayed on the screen
The viewport also converts screen coordinates to and from stage coordinates.
* */
