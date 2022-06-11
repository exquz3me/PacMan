package com.itp.pacman;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.screens.MainScreen;

public class PacMan extends Game {
	private Viewport viewport;
	private TextureAtlas atlas;
	private Skin skin;
	private GameLevel level;	//Change to levelHandler?
	
	private final float WORLD_WIDTH = 180;
	private final float WORLD_HEIGHT = 180;
	
	@Override
	public void create () {
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        atlas = new TextureAtlas(Gdx.files.internal("testing.atlas"));
        skin = new Skin(Gdx.files.internal("uiskin.json"));
	    setScreen(new MainScreen(this));	//TODO: change to StartScreen on build
	}
	
	@Override
	public void dispose () {
		//TODO: findout how dispose works, and what has to be disposed
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
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
}
//TODO: Add toString methods to all classes, move everything nesessary to desktop version
//TODO: exeption handleing	https://jonskeet.uk/java/passing.html
