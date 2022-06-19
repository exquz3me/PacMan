package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.itp.pacman.animation.AnimationManager;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.movement.IMoveable;
import com.itp.pacman.movement.MovementManager;
import com.itp.pacman.sound.SoundManager;
import com.itp.pacman.stages.GameStage;

public abstract class GameActor extends Actor implements IMoveable {
	protected GameStage stage;
	protected GameLevel level;
	protected TextureRegion region;
	protected AnimationManager animationManager;
	protected SoundManager soundManager;
	protected MovementManager movementManager;
	
	public GameActor(GameStage stage) {
		super();
		this.stage = stage;
		level = stage.getLevel();
		soundManager = new SoundManager();
		animationManager = new AnimationManager();
		movementManager = new MovementManager(stage, this);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public int getPositionInLevel() {
		return level.getArrayPos(getCenterPos()); 
	}

	public void setPositionInLevel(int index) {
		setCenterPos(level.getTileCenterPos(index));
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
	
	@Override
	public void enteredNewFieldLogic(int index) {
		
	}

	@Override
	public void moveLogic(int index) {
		
	}

	@Override
	public void postMoveLogic(int index) {
		
	}

	@Override
	public void stopMoveLogic(int index) {
		
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
	
	public SoundManager getSoundManager() {
		return soundManager;
	}
	
	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}
	
	public MovementManager getMovementManager() {
		return movementManager;
	}

	public void setMovementManager(MovementManager movementManager) {
		this.movementManager = movementManager;
	}
}
