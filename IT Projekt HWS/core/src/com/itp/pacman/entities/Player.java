package com.itp.pacman.entities;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Pool;
import com.itp.pacman.animator.AnimationHandler;
import com.itp.pacman.sound.SoundHandler;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.math.Interpolation.*;

public class Player extends GameActor{
	private AnimationHandler animationHanlder;
	private SoundHandler soundHandler; //will apply changes to the last played sound
	
	public Player () { //passing down the atlas seems unnessecary	
		super();
		animationHanlder = new AnimationHandler(); //currently only supports one anim
		animationHanlder.add(atlas, "run", 1/3f); //player move anim speed is based on movespeed!!
		animationHanlder.set("run", true);
		region = animationHanlder.getFrame(); //possible null reference error
		
		//setScrollFocus / setKeyboardFocus on the stage for actors to be able to recieve scroll / keyboard inputs
        setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());  
		addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down");
				
				
				//call action from pool
				 
				
				
				return true;	//The touchDragged and touchUp events will only be received if touchDown returns true
			}
		});	
		
		//To run code when an action is complete, a sequence with a RunnableAction can be used:
		addAction(Actions.forever(Actions.run(new Runnable() {
			public void run () {
				region = animationHanlder.getFrame(); //atlas.getRegion for non animated actors
			}
		})));
		//addAction(Actions.sequence(Actions.moveTo(10, 4, 5), Actions.color(Color.RED, 6), Actions.delay(0.5f), Actions.rotateTo(90, 2))); //pooled action from the actions class
		addAction(Actions.parallel(moveTo(16, 16, 2, bounceOut), Actions.color(Color.RED, 6), Actions.delay(0.5f), rotateTo(180, 5, swing)));
		//addAction(Actions.forever(Actions.sequence(Actions.scaleTo(2, 2, 0.5f), Actions.scaleTo(1, 1, 0.5f), Actions.delay(0.5f))));
		addAction(pool.obtain());
		//parralel runs them at the same time, squence one after another
		
	}
	 //If setTouchable(false) or setVisible(false) is called on an actor, it will not receive input events.

	
	Pool<MoveToAction> pool = new Pool<MoveToAction>() {
		protected MoveToAction newObject () {
			return new MoveToAction();
		}
	};
	
	//when setting keyboard focus add stage.getActors()... as parameter
	
	private void movePlayer() {
		int pos;
		int ch;
		
		//gets the x position of the left edge, TODO: calculate the player middle once, not always
		
		float posX = getX() + getWidth()/2;
		float posY = getY() + getHeight()/2;
		
		if (posX % 8 == 0 && posY % 8 == 0) {	//if the player is in the middle of a block
			
		}
	}
	
	
	@Override
	protected void positionChanged() {
		super.positionChanged();
	}
	
	public AnimationHandler getAnimationHanlder() {
		return animationHanlder;
	}
	
	public SoundHandler getSoundHandler() {
		return soundHandler;
	}
	
	/* ideas
	 	scince we cant get the tile cords from the object, we might aswell create an 2d array and use the tiledmap only to display the world
	 * */
	
	
	/*  Collision research
		For this we'll keep track of the 4 immediate tiles surrounding the PLAYER
	 		-> scan for specific tiles
	 		-> convert player coords to tilemap cords
	 		-> turn point is exactly in the middle of the tile
	 		-> but with any smoothing or acceleration this will never happen so we need fuzzyEqual
	 			-> It compares the values and if they are within the threshold difference of each other, they are considered as being equal:
	 			if they are equal, set the player cords equal to the tile middle and send it off
	 		-> The other import part is that Pacman is actually much larger than the grid, while the physics body is 16x16 pacman is 32x32
	 		
	 	the dots are not objects, when a dot is eaten -> pacman is in the middle of the tile, and the tile has a dot, the tile gets changed to a empty one
	 	-> dots are 4x4 tile is 16x16
	 	
	 	input queue
	 */
	
	//TODO: make nessecary variables final
}
