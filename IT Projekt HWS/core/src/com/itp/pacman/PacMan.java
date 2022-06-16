package com.itp.pacman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.screens.MainScreen;

public class PacMan extends Game {	//TODO: assetManager, dispose, finish texture atlas, use vector claculations in ghost classes
	private TextureAtlas atlas;
	private Skin skin;
	private GameLevel level;
	
	private final int VIRTUAL_WIDTH = 16*28;
	private final int VIRTUAL_HEIGHT = 16*36;	//TODO: set width and height based on level
	
	@Override
	public void create () {
        atlas = new TextureAtlas(Gdx.files.internal("GameSprites.atlas"));
        skin = new Skin(Gdx.files.internal("uiskin.json"));
	    setScreen(new MainScreen(this, VIRTUAL_WIDTH, VIRTUAL_HEIGHT));
	}
	
	@Override
	public void dispose () {
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
	
	public GameLevel getLevel() {
		return level;
	}
	
	public void setLevel(GameLevel level) {
		this.level = level;
	}
	
	public int getVirtualWidth() {
		return VIRTUAL_WIDTH;
	}
	
	public int getVirtualHeight() {
		return VIRTUAL_HEIGHT;
	}
}
//TODO: Add toString methods to all classes, move everything nesessary to desktop version
//https://stackoverflow.com/questions/54198655/how-camera-works-in-libgdx-and-together-with-viewport
//https://stackoverflow.com/questions/28431423/libgdx-hud-with-two-stages