package com.itp.pacman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Pool;

public class GameActor extends Actor{
	//TODO: add action pooling, add sound, add box collision
	
	protected TextureAtlas atlas;
	protected TextureRegion region;
	
	public GameActor() {	
		atlas = new TextureAtlas(Gdx.files.internal("testing.atlas")); //load atlas
	}
	
	
	//The Actor hit method receives a point and returns the deepest actor at that point, or null if no actor was hit. Here is the default hit method:
	//every actor has a box 2d collision -> TODO
	
	@Override
	public void draw(Batch batch, float parentAlpha){ //The Batch passed to draw is configured to draw in the parent’s coordinates
		super.draw(batch, parentAlpha); //not sure if nessescary
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	

	//Note the color of the Batch my be changed by other actors and should be set by each actor before it draws.
	//If setVisible(false) is called on an actor, its draw method will not be called. It will also not receive input events.
	

	//MoveToAction action = pool.obtain();
	
	
	//TODO: all ghosts will be white, the color will be set in draw, the eyes are sperate child actors of the white ghost
	//the eyes are not really animation, they change states depending on the movement direction -> movedir == eyedir
}
