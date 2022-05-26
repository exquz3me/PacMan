package com.itp.pacman.entities;

import java.awt.Color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class MyActor extends Actor{
	//extends image is like extends actor, but image has a grpahical class attached
	TextureRegion region;
	
	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg"))); //a sprite has positional information, a stage has it too so its overkill
	public boolean started = false;
	
	public MyActor(Batch batch) {	
		setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());	//has to be done for every actor	
		setTouchable(Touchable.enabled);
		
		region = new TextureRegion(sprite);
        setBounds(region.getRegionX(), region.getRegionY(),
	    region.getRegionWidth(), region.getRegionHeight());
		
		
		addListener(new InputListener() {
			/*
			public boolean touchDown (InputEvent event, float x, float y, int pointer,int button) {
				 ((MyActor)event.getTarget()).started = true;
				 return true;
			}*/
			
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Input.Keys.RIGHT) {
					MoveByAction mba = new MoveByAction();
					mba.setAmount(100f, 0f);
					mba.setDuration(5f);
					MyActor.this.addAction(mba);
				}
				return true;
			}
		});	
	}
	
	@Override
	protected void positionChanged() { //fired when the position of the actor changes
		sprite.setPosition(getX(), getY());
		super.positionChanged();
	}
	
	
	//an actors draw mehtod can be overriden (the overriden version will be called when we use stage.draw)
	//Each actor draws in its own un-rotated and unscaled coordinate system where 0,0 is the bottom left corner of the actor.
	//The Batch passed to draw is configured to draw in the parent’s coordinates
	@Override
	public void draw(Batch batch, float alpha){ //we want to calculate transparency based on parent
		super.draw(batch, alpha);
		com.badlogic.gdx.graphics.Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * alpha);
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	//Note the color of the Batch may be changed by other actors and should be set by each actor before it draws.
	//If setVisible(false) is called on an actor, its draw method will not be called. It will also not receive input events.
	
	@Override
	public void act(float delta){
		super.act(delta);
		//own acts
	}
}
