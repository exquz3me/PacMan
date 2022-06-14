package com.itp.pacman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.itp.pacman.PacMan;

//TODO: not sure if this class is really nessecary
public class GameActor extends Actor{		//TODO: action pooling?
	protected final PacMan game;
	protected TextureAtlas atlas;
	protected TextureRegion region; //not really needed, actor clayss has setRegion
	
	protected float centerX;		//TODO: find a way to make it accec
	protected float centerY;
	
	
	public GameActor(PacMan game) {	
		super();
		this.game = game;
		atlas = game.getAtlas();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public void enteredNewFieldLogic(int index) {
		
	}
	
	public void moveLogic(int index) {
		//TODO: make abstract, -> make GameLevel not a Game Actor
	}
	
	public void stopLogic(int index) {
		//TODO: make abstract, -> make GameLevel not a Game Actor so it doesnt have to overwrite this
	}
	
	public void setMiddlePosX(float posX) {	//not needed, can rewrite to set Origin
		setX(posX - getWidth()/2);
	}
	
	public float getMiddlePosX() {			//TODO: we dont need to calculate it everytime
		return getX() + getWidth()/2;
	}
	
	public void setMiddlePosY(float posY) {
		setY(posY - getHeight()/2);
	}
	
	public float getMiddlePosY() {
		return getY() + getHeight()/2;
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}
	
	public TextureRegion getRegion() {
		return region;
	}
	
	public void setRegion(TextureRegion region) {
		this.region = region;
	}
}
