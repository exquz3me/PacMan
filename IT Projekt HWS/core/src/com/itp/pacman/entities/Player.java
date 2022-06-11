package com.itp.pacman.entities;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.Input;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.itp.pacman.PacMan;
import com.itp.pacman.animator.AnimationHandler;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.sound.SoundHandler;

public class Player extends GameActor{
	private GameLevel level;
	
	private float PLAYER_SPEED = 8f;
	
	private int wishDir_x = 0;	//TODO: convert to vector2?
	private int wishDir_y = 0;
	private int moveDir_x = 0;
	private int moveDir_y = 0;
	
	public Player (PacMan game) { //passing down the atlas seems unnessecary	
		super(game);
		this.level = game.getLevel(); //i dont like passing the data like that TODO: put level to Game
		animationHandler.add(atlas, "run", 1/3f); //player move anim speed is based on movespeed!!
		animationHandler.set("run", true);
		region = animationHandler.getFrame(); //possible null reference error
		
        setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());  
        
        addListener(new InputListener() {	//TODO: InputListener or InputProcessor?
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down");		
				return false;	//The touchDragged and touchUp events will only be received if touchDown returns true
			}
			
			public boolean keyDown (InputEvent event, int keycode) {
				if (keycode == Input.Keys.UP) {
					wishDir_y = 1;
					wishDir_x = 0;
					movePlayer();
				}
				
				if (keycode == Input.Keys.RIGHT) {
					wishDir_y = 0;
					wishDir_x = 1;
					movePlayer();
				}
				
				if (keycode == Input.Keys.DOWN) {
					wishDir_y = -1;
					wishDir_x = 0;
					movePlayer();
				}
				
				if (keycode == Input.Keys.LEFT) {
					wishDir_y = 0;
					wishDir_x = -1;
					movePlayer();
				}
			    return true;
			}
		});	
		
		//To run code when an action is complete, a sequence with a RunnableAction can be used:
		addAction(forever(run(new Runnable() {
			public void run () {
				region = animationHandler.getFrame(); //atlas.getRegion for non animated actors
			}
		})));
		//addAction(Actions.sequence(Actions.moveTo(10, 4, 5), Actions.color(Color.RED, 6), Actions.delay(0.5f), Actions.rotateTo(90, 2))); //pooled action from the actions class
		//addAction(Actions.parallel(moveTo(0, 0, 2, bounceOut), Actions.color(Color.RED, 6), Actions.delay(0.5f), rotateTo(180, 5, swing)));
		addAction(moveTo(0, 0, 2));
		//addAction(Actions.forever(Actions.sequence(Actions.scaleTo(2, 2, 0.5f), Actions.scaleTo(1, 1, 0.5f), Actions.delay(0.5f))));
		addAction(pool.obtain());
		//parralel runs them at the same time, squence one after another
	}

	Pool<MoveToAction> pool = new Pool<MoveToAction>() {
		protected MoveToAction newObject () {
			return new MoveToAction();
		}
	};
	
	@Override
	public void act(float delta) {
		super.act(delta); //not needed
	}

	
	private void movePlayer() {	//TODO: this method looks fugly (there must be a better way)
		byte[] data = level.getCollisionData();
		int tileSizeX = level.getTileSizeX();
		int tileSizeY = level.getTileSizeY();
		//TODO: fuzzzy equal grace should be based on ms
		
		float posX = getX() + getWidth()/2;
		float posY = getY() + getHeight()/2;
		
		int levelSizeX = level.getTotalSizeX();
		int levelSizeY = level.getTotalSizeY();
		
		boolean inBounds = false;
		if(!(posX > levelSizeX || posY > levelSizeY || posX < 0 || posY < 0)) {
			inBounds = true;
		}	
		
		//setX(pos/level.getFieldSizeX() * tileSizeX); //gets the col
		//setY(pos/level.getFieldSizeY() * tileSizeY);
		
		//TODO: the logs appar from the previous tile and after reentering map trough wall the player "ignores one input", possibly connected
		if (posX % (tileSizeX/2) == 0 && posY % (tileSizeY/2) == 0) {		//TODO: fuzzy equal, set position to tile			
			if(inBounds) {
				int pos = level.getArrayPos(posX, posY);	//TODO: make the mehtod return null if were out of bounds
				
				float tilePosX = level.getTilePosX(pos);
				float tilePosY = level.getTilePosY(pos);
				
				int ch = data[pos];	
				Gdx.app.log("Player", "PosX: " + posX + " PosY: " + posY + " tilePosX: " + tilePosX + " tilePosY: " + tilePosY + " pos: " + pos + " ch: " + ch);
				
				/*
				if ((ch & 0b11110000) != 0) {
					level.getItemData()[pos] = 0;
		        }
				*/
				
	            if (wishDir_x != 0 || wishDir_y != 0) {
	                if ( ( (wishDir_x ==  0 && wishDir_y ==  1 && (ch & 1) == 0)
	                    || (wishDir_x ==  1 && wishDir_y ==  0 && (ch & 2) == 0)
	                    || (wishDir_x ==  0 && wishDir_y == -1 && (ch & 4) == 0)
	                    || (wishDir_x == -1 && wishDir_y ==  0 && (ch & 8) == 0)) ) {
	                	moveDir_x = wishDir_x;
	                	moveDir_y = wishDir_y;
	                }
	            }
	            
	            if (   (moveDir_x ==  0 && moveDir_y ==  1 && (ch & 1) != 0)
	                || (moveDir_x ==  1 && moveDir_y ==  0 && (ch & 2) != 0)
	                || (moveDir_x ==  0 && moveDir_y == -1 && (ch & 4) != 0)
	                || (moveDir_x == -1 && moveDir_y ==  0 && (ch & 8) != 0)) {
	            	moveDir_x = 0;
	            	moveDir_y = 0;
	            }
			}			
			else {
				moveDir_x = wishDir_x;
				moveDir_y = wishDir_y;
			}
		}
	    setX(getX() + PLAYER_SPEED * moveDir_x);
	    setY(getY() + PLAYER_SPEED * moveDir_y);
	}
		
	public AnimationHandler getAnimationHandler() {
		return animationHandler;
	}
	
	public SoundHandler getSoundHandler() {
		return soundHandler;
	}
}

/* Info on Input
Depending on the input device, one can either poll
the state of a device periodically, or register a listener 
that will receive input events in chronological order.
*/

//TODO: make nessecary variables final, dont forget to rename to all caps!
//TODO: should ghosts use a factory pattern?
//TODO: fix pixel stretching (probably a camera setting)
//TODO: dont forget to offset the drawed region of the player otherwise it wont be centered
