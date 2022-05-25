package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Button extends Actor{
	Texture texture = new Texture("badlogic.jpg");
	float actorX = 0, actorY = 0;
	public boolean started = false;
	
	public Button(Batch batch) {
		batch.draw(texture, 0, 0);
		setBounds(actorX, actorY,texture.getWidth(), texture.getHeight());	
		addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer,int button) {
				 ((Button)event.getTarget()).started = true;
				 return true;
			}
		});	
	}

	@Override
	public void draw(Batch batch, float alpha){
		batch.draw(texture,actorX,actorY);
	}

	@Override
	public void act(float delta){}
}
