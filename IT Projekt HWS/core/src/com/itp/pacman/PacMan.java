package com.itp.pacman;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.screens.MainScreen;

public class PacMan extends Game {	//TODO: make nessarcy use of abstract
	private TextureAtlas atlas;
	private Skin skin;
	
	private GameLevel level;	//current level
	
	private final int VIRTUAL_WIDTH = 8*28;	//how many world units we see when looking trough camera
	private final int VIRTUAL_HEIGHT = 8*31;
	//TODO: set width and height based on level
	
	@Override
	public void create () { //load the needed sounds and animations into the handlers *loading* ?
        atlas = new TextureAtlas(Gdx.files.internal("testing.atlas"));
        skin = new Skin(Gdx.files.internal("uiskin.json"));
	    setScreen(new MainScreen(this, VIRTUAL_WIDTH, VIRTUAL_HEIGHT));		//default viewport TODO: set size to device screen size
	}
	
	@Override
	public void dispose () {
		//TODO: findout how dispose works, and what has to be disposed
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