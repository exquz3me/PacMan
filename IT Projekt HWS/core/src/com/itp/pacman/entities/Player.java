package com.itp.pacman.entities;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.itp.pacman.PacMan;
import com.itp.pacman.animator.AnimationManager;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.levels.LevelManager;
import com.itp.pacman.movement.MovementManager;
import com.itp.pacman.sound.SoundManager;

public class Player extends GameActor{
	private SoundManager soundManager;
	private MovementManager movementManager;
	
	private GameLevel level;
	//TODO: AssetManager to handle assets properly
	
	private boolean eatSound = false;
	private float animationSpeed = 0.04f;	//TODO: player move anim speed is based on movespeed!!
	private float moveSpeed = 0.53f;
	private float graceX = 0.6f;
	private float graceY = 0.6f;
	private int lives = 3;
	
	public Player (PacMan game) {	
		super(game);
		level = game.getLevel();
	

		soundManager = new SoundManager();
		soundManager.add("OM", Gdx.audio.newSound(Gdx.files.internal("pickupCoin (1).wav")));
		soundManager.add("NOM", Gdx.audio.newSound(Gdx.files.internal("pickupCoin (2).wav")));
		
		movementManager = new MovementManager(game, this, "run", moveSpeed, animationSpeed, graceX, graceY);
		

		/*
		region = animationHandler.getFrame(); //possible null reference error?
		*/
		
		
		//pacman_chomp.wav is not useable because it has 8 bits per sampe?
		
		
        addListener(new InputListener() {		
			public boolean keyDown (InputEvent event, int keycode) {
				if (keycode == Input.Keys.UP) {
					movementManager.setWishDirX(0);
					movementManager.setWishDirY(1);
				}
				
				if (keycode == Input.Keys.RIGHT) {			
					movementManager.setWishDirX(1);
					movementManager.setWishDirY(0);
				}
				
				if (keycode == Input.Keys.DOWN) {
					movementManager.setWishDirX(0);
					movementManager.setWishDirY(-1);
				}
				
				if (keycode == Input.Keys.LEFT) {			
					movementManager.setWishDirX(-1);
					movementManager.setWishDirY(0);
				}
			    return false;
			}
		});	
        
		//To run code when an action is complete, a sequence with a RunnableAction can be used:
        /*
		addAction(forever(run(new Runnable() {
			public void run () {
				region = animationHandler.getFrame();
			}
		})));
		*/
        
		//addAction(Actions.sequence(Actions.moveTo(10, 4, 5), Actions.color(Color.RED, 6), Actions.delay(0.5f), Actions.rotateTo(90, 2))); //pooled action from the actions class
		//addAction(Actions.parallel(moveTo(0, 0, 2, bounceOut), Actions.color(Color.RED, 6), Actions.delay(0.5f), rotateTo(180, 5, swing)));		addAction(moveTo(0, 0, 2));
		//addAction(Actions.forever(Actions.sequence(Actions.scaleTo(2, 2, 0.5f), Actions.scaleTo(1, 1, 0.5f), Actions.delay(0.5f))));
		//addAction(pool.obtain());
		//parralel runs them at the same time, squence one after another
 
        

		setRegion(atlas.getRegions().first()); //TODO: change to a proper region
		setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight());  //dont forget, pacman is bigger than 1 tile
		setOrigin(getWidth()/2, getHeight()/2);
	}

	
	/*
	Pool<MoveToAction> pool = new Pool<MoveToAction>() {
		protected MoveToAction newObject () {
			return new MoveToAction();
		}
	};
	*/
	
	@Override
	public void act(float delta) {
		super.act(delta); //not needed		

		movementManager.move();
		//movePlayer();
		
		if(lives == 0) {	 //a ghost is considered to have caught Pac-Man when it occupies the same tile as him.
			//loose condition
		}
	}
	
	@Override 
	public void enteredNewFieldLogic(int index){
		if(level.getEntityData()[index] != 0) {		//TODO: find a different way to check for same index
			eat(index);
		}
	}
	
	@Override
	public void moveLogic(int index) {	//NOTE: sprite can be flipped if the region is negative 
        if(movementManager.getMoveDirY() == 1)
        	setRotation(90); 
        else if(movementManager.getMoveDirX() == 1)
        	setRotation(0);    
        else if(movementManager.getMoveDirY() == -1) 
        	setRotation(270);     
        else if(movementManager.getMoveDirX() == -1) 
        	setRotation(180);
	}
		
	private void eat(int index) {
		level.interact(index);
		
		if(eatSound == false) {
			soundManager.play("OM", 1, false, 0.4f, 0);
			eatSound = true;
		} else {
			soundManager.play("NOM", 1, false, 0.3f, 0);
			eatSound = false;
		}
	}

	public SoundManager getSoundHandler() {
		return soundManager;
	}
	
	public void setSoundHanlder(SoundManager soundHandler) {
		this.soundManager = soundHandler;
	}
}

//TODO: make nessecary variables final, dont forget to rename to all caps!
//TODO: should ghosts use a factory pattern?
//TODO: rotate player sprite based on moevement direction



/*
 In UIstage create to tables one is align top other one i salign bot
 ghosts move like player but wishdir is set differently
 
 * */