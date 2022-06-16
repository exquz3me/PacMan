package com.itp.pacman.entities;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.itp.pacman.PacMan;
import com.itp.pacman.animation.AnimationManager;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.movement.MovementManager;
import com.itp.pacman.sound.SoundManager;

public class Player extends GameActor{
	private GameLevel level;
	private SoundManager soundManager;
	private MovementManager movementManager;
	private float moveSpeed = 0.8f;
	
	//private int lives = 3;
	private boolean eatSound = false;
	
	public Player (PacMan game) {
		super(game);
		level = game.getLevel();
		
		soundManager = new SoundManager();
		soundManager.add("OM", Gdx.audio.newSound(Gdx.files.internal("pickupCoin1.wav")));
		soundManager.add("NOM", Gdx.audio.newSound(Gdx.files.internal("pickupCoin2.wav")));		

		animationManager = new AnimationManager();
		animationSpeed = 0.5f;	//TODO: anim speed based on player movespeed
		animationManager.add(game.getAtlas(), "PlayerMove", animationSpeed);
		animationManager.setCurrentAnim("PlayerMove");	
		animationManager.setLooping(true);
		
		movementManager = new MovementManager(game, this, moveSpeed);
		
		setRegion(game.getAtlas().getRegions().first()); //TODO: change to a proper region
		setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight());
		setScaleX(getScaleX() * 2);
		setScaleY(getScaleY() * 2);
		setOrigin(getWidth()/2, getHeight()/2);
		
        addListener(new InputListener() {		
			public boolean keyDown (InputEvent event, int keycode) {
				if (keycode == Input.Keys.UP) {
					movementManager.setWishDir(0, 1);				
				}
				
				if (keycode == Input.Keys.RIGHT) {			
					movementManager.setWishDir(1, 0);	
				}
				
				if (keycode == Input.Keys.DOWN) {
					movementManager.setWishDir(0, -1);			
				}
				
				if (keycode == Input.Keys.LEFT) {			
					movementManager.setWishDir(-1, 0);		
				}
			    return false;
			}
		});	     
	}

	@Override
	public void act(float delta) {
		movementManager.move();
		setRegion(animationManager.getFrame());
	}
	
	@Override 
	public void enteredNewFieldLogic(int index){	//TODO: check if he shares index with anyone else
		if(level.getEntityData()[index] != 0) {		//TODO: find a different way to check for same index
			eat(index);
		}
		
		//TODO: if level.getGhostposition == index -> lose one life
	}
	
	@Override
	public void moveLogic(int index) {	//NOTE: sprite can be flipped if the region is negative 
		animationManager.setPlay(true);
		if(movementManager.getMoveDir().y == 1) {
        	setRotation(90); 
		} else if(movementManager.getMoveDir().x == 1) {
        	setRotation(0);    
		} else if(movementManager.getMoveDir().y == -1) {
        	setRotation(270);    
		} else {
        	setRotation(180);
		}
	}
	
	@Override
	public void postMoveLogic(int index) {

	}
	
	@Override
	public void stopLogic(int index) {
		animationManager.setPlay(false);
	}
		
	private void eat(int index) {
		level.interact(index);
		
		if(eatSound == false) {
			soundManager.play("OM", 0.5f, false, 0.4f, 0f);
			eatSound = true;
		} else {
			soundManager.play("NOM", 0.5f, false, 0.3f, 0f);
			eatSound = false;
		}
	}
	
	public MovementManager getMovementManager() {
		return movementManager;
	}

	public void setMovementManager(MovementManager movementManager) {
		this.movementManager = movementManager;
	}

	public float getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(float animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
}