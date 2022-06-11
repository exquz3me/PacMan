package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.itp.pacman.PacMan;
import com.itp.pacman.animator.AnimationHandler;
import com.itp.pacman.sound.SoundHandler;

public class GameActor extends Actor{		//TODO: action pooling?
	protected final PacMan game;
	protected TextureAtlas atlas;
	protected TextureRegion region;
	protected AnimationHandler animationHandler;
	protected SoundHandler soundHandler;
	
	public GameActor(PacMan game) {	
		this.game = game;
		atlas = game.getAtlas();
		animationHandler = new AnimationHandler();
		soundHandler = new SoundHandler();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public AnimationHandler getAnimationHandler() {
		return animationHandler;
	}
	
	public void setAnimtationHandler(AnimationHandler animationHandler) {
		this.animationHandler = animationHandler;
	}
	
	public SoundHandler getSoundHandler() {
		return soundHandler;
	}
	
	public void setSoundHanlder(SoundHandler soundHandler) {
		this.soundHandler = soundHandler;
	}
}
