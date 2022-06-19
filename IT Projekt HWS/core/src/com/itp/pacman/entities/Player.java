package com.itp.pacman.entities;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.itp.pacman.ai.PathingMode;
import com.itp.pacman.stages.GameStage;

//TODO:
//need original player sprites (player is bigger than one tile)
//make code more managbale for player / ghost interaction
//add original sounds

public class Player extends GameActor {
	private boolean eatSound = false;
	private float powerUpTime = 5f;
	
	public Player (GameStage stage) {
		super(stage);
		
		movementManager.setMoveSpeed(0.6f);
		
		soundManager.add("OM", Gdx.audio.newSound(Gdx.files.internal("pickupCoin1.wav")));
		soundManager.add("NOM", Gdx.audio.newSound(Gdx.files.internal("pickupCoin2.wav")));		
		
		animationManager.add(stage.getAtlas(), "PlayerMove", 0.5f);
		animationManager.setCurrentAnim("PlayerMove");	
		animationManager.setLooping(true);

		setRegion(stage.getAtlas().findRegion("PlayerMove"));
		setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight());
		setScaleX(getScaleX() * 4);
		setScaleY(getScaleY() * 4);
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
		setRegion(animationManager.getCurrentFrame());
	}
	
	@Override
	public void enteredNewFieldLogic(int index){
		if(level.getEntityData()[index] == 1) {
			eat(index);
		}
		
		if(level.getEntityData()[index] == 2) {
			eat(index);
			stage.disableTimer(powerUpTime);
			stage.setTimerDisabledDuration(0f);
			stage.getGhostManager().setAnimations("GhostEatable");
			stage.getGhostManager().setPathingMode(PathingMode.FRIGHTENED);
		}
	}
	
	@Override
	public void moveLogic(int index) {	//NOTE: sprite can be flipped if the region is negative 
		animationManager.play(true);
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
	public void stopMoveLogic(int index) {
		animationManager.play(false);
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
}