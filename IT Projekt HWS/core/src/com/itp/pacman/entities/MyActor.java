package com.itp.pacman.entities;

import java.awt.Color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class MyActor extends Actor{
	//extends image is like extends actor, but image has a grpahical class attached
	
	//every actor has hit detection, animation (only 1 frame if static) and sound
	//specific actors can habe input listeners and specific acts

/*
 Animation<TextureRegion> myAnimation = new Animation<TextureRegion>(). 
Note that it would usually be inadvisable to use the Sprite class to represent frames of an animation, because the Sprite class contains positional data that would not carry from frame to frame.
 
libGDX’s TextureAtlas (code) class is typically used for combining many separate TextureRegions into a smaller set of Textures to reduce expensive draw calls. (details here).
TexturePacker and TextureAtlas provide a convenient way to generate animations.
 */
	

	//action pooling
	
	protected TextureRegion region;
	//add animation
	
	//idk feels stupid, we already have the actor class, why create an own?
	public MyActor(Batch batch) {			//has to be done for every actor		
		region = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")), 0, 0, 256, 256); //take region from texture atlas
        setBounds(region.getRegionX(), region.getRegionY(),
	    region.getRegionWidth(), region.getRegionHeight());
	}
	
	
	//The Actor hit method receives a point and returns the deepest actor at that point, or null if no actor was hit. Here is the default hit method:
	/*
	 public Actor hit (float x, float y, boolean touchable) {
	if (touchable && getTouchable() != Touchable.enabled) return null;
	return x >= 0 && x < width && y >= 0 && y < height ? this : null;
		}
	 * */
	
	//every actor has a box 2d collision
	
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
	public void act(float delta){	//not really neccesary
		super.act(delta); //group act?
		//own acts
	}
	/*
	Pool<Action> pool = new Pool<Action>() {
		protected MoveToAction newObject () {
			return new MoveToAction();
		}
	};
	*/
	//MoveToAction action = pool.obtain();
}
