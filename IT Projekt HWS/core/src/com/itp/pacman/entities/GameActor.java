package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.itp.pacman.PacMan;
import com.itp.pacman.animation.AnimationManager;
import com.itp.pacman.sound.SoundManager;

public abstract class GameActor extends Actor { //should implement movement manager
	protected final PacMan game;
	protected TextureRegion region;
	protected AnimationManager animationManager;
	protected float animationSpeed;
	protected SoundManager soundManager;

	public GameActor(PacMan game) {
		super();
		this.game = game;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	public abstract void enteredNewFieldLogic(int index);
	
	public abstract void moveLogic(int index);
	
	public abstract void postMoveLogic(int index);
	
	public abstract void stopLogic(int index);
	
	public int getPositionInLevel() {
		return game.getLevel().getArrayPos(getCenterPos()); 
	}

	public void setPositionInLevel(int index) {
		setCenterPos(game.getLevel().getTileCenterPos(index));
	}
	
	public Vector2 getCenterPos() {
		return new Vector2(getX() + getWidth()/2, getY() + getHeight()/2);
	}
	
	public void setCenterPos(Vector2 position) {
		setX(position.x - getWidth()/2);
		setY(position.y - getHeight()/2);
	}
	
	public void setCenterPos(float positionX, float positionY) {
		setX(positionX - getWidth()/2);
		setY(positionY - getHeight()/2);
	}
	
	public TextureRegion getRegion() {
		return region;
	}
	
	public void setRegion(TextureRegion region) {
		this.region = region;
	}
	
	public AnimationManager getAnimationManager() {
		return animationManager;
	}

	public void setAnimationManager(AnimationManager animationManager) {
		this.animationManager = animationManager;
	}
	
	public float getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(float animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	
	public SoundManager getSoundManager() {
		return soundManager;
	}
	
	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}
}
