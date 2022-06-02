package com.itp.pacman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.itp.pacman.animator.AnimationHandler;

public class Player extends Actor{
	private AnimationHandler animationHanlder;
	private TextureAtlas atlas;
	TextureRegion region;
	
	float elapsedTime;
	
	public Player (TextureAtlas atlas) { //passing down the atlas seems unnessecary
		this.atlas = atlas;
		animationHanlder = new AnimationHandler(atlas, "run"); //currently only supports one anim
		region = animationHanlder.getFrame(0); //possible null reference error
        setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());  
        setTouchable(Touchable.enabled); //If setTouchable(false) or setVisible(false) is called on an actor, it will not receive input events.
		addListener(new InputListener() {});	
	}
	
	//create an animation hanlder pool, so we dont have to create new ones every time
	
	public void setAnimationHanlder(String animation) {
		switch(animation) {
			case "move":
				animationHanlder = new AnimationHandler(atlas, "move");
				break;
				
			case "die":
				animationHanlder = new AnimationHandler(atlas, "die");
				break;
		}
	}
	
	public AnimationHandler getAnimationHanlder() {
		return animationHanlder;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) { //called by stage
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		elapsedTime += delta;
		region = animationHanlder.getFrame(elapsedTime);
		animationHanlder.update(delta); //call the update to change the region we want to draw
		//own acts
	}
}
