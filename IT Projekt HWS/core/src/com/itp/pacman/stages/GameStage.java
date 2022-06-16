package com.itp.pacman.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;

public abstract class GameStage extends Stage{
	protected final PacMan game;
	protected TextureAtlas atlas;
	protected Skin skin;
	protected boolean active = true;
	
	public GameStage(PacMan game, Viewport viewport) {
		super(viewport);
		this.game = game;
		atlas = game.getAtlas();
		skin = game.getSkin();	
	}
	
	@Override
	public void draw() {
		if (active) {
			getViewport().apply();
			act(Gdx.graphics.getDeltaTime());
			super.draw();
		} else {
			return;
		}
	}
	
	@Override
	public void dispose() {
		atlas.dispose();
		skin.dispose();
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
