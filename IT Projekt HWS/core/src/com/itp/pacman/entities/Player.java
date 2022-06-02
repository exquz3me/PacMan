package com.itp.pacman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Player extends Actor{
	public Player () {
		setTouchable(Touchable.enabled); //If setTouchable(false) or setVisible(false) is called on an actor, it will not receive input events.
		
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
					Player.this.addAction(mba);
	    			Gdx.app.log("Moved", "right");
				}
				return true;
			}
		});	
	}
	
	@Override
	protected void positionChanged() { //fired when the position of the actor changes
		super.positionChanged();
	}
}
