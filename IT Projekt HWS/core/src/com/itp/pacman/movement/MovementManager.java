package com.itp.pacman.movement;

import com.badlogic.gdx.Gdx;
import com.itp.pacman.PacMan;
import com.itp.pacman.animator.AnimationManager;
import com.itp.pacman.entities.GameActor;
import com.itp.pacman.levels.GameLevel;

public class MovementManager { //TODO: dispose?
	private GameLevel level;
	private GameActor actor;
	private float animationTimer;
	private float animationSpeed;
	private float moveSpeed;
	private float graceX;
	private float graceY;
	private AnimationManager animationManager;
	private int prevIndex;
	
	private int wishDir_x;	//TODO: convert to vector2?
	private int wishDir_y;
	private int moveDir_x;
	private int moveDir_y;
	
	public MovementManager(PacMan game, GameActor actor, String regionName, float moveSpeed, float animationSpeed, float graceX, float graceY) {
		level = game.getLevel();
		this.actor = actor;
		this.moveSpeed = moveSpeed;
		this.animationSpeed = animationSpeed;
		this.graceX = graceX;
		this.graceY = graceY;
		animationManager = new AnimationManager();
		animationManager.add(game.getAtlas(), regionName, animationSpeed);
		animationManager.set(regionName, true);	
		prevIndex = level.getArrayPos(actor.getMiddlePosX(), actor.getMiddlePosY()); 
	}
	
	public void move() {
		byte[] data = level.getLevelData(); //TODO: maybe do different levelData for ghoststs beacuse of ghost cage ->
		//instead of level.getData do actor.getData -> each moving object calculates its own collisions that default to level data if unchanged?
		
		int index = level.getArrayPos(actor.getMiddlePosX(), actor.getMiddlePosY());
		
		/*	TODO: try to replace with
		boolean inBounds = getMiddlePosX() >= level.getTotalSizeX() && getMiddlePosX() <= 0 ||
						   getMiddlePosY() >= level.getTotalSizeY() && getMiddlePosY() <= 0;
		*/
		
		if(actor.getMiddlePosX() >= level.getTotalSizeX()) {
			index -= level.getFieldSizeX();
			actor.setMiddlePosX(level.getTileCenterPosX(index) - level.getTileSizeX()/2 + 0.1f);
		}
		
		if(actor.getMiddlePosX() <= 0) {
			index += level.getFieldSizeX() - 1;
			actor.setMiddlePosX(level.getTileCenterPosX(index) + level.getTileSizeX()/2 - 0.1f);
		}
			
		if(actor.getMiddlePosY() >= level.getTotalSizeY()) {
			index = data.length - level.getFieldSizeX();	
			actor.setMiddlePosY(level.getTileCenterPosY(index) - level.getTileSizeY()/2 + 0.1f); 
		}
		
		if(actor.getMiddlePosY() <= 0) {
			index = data.length - index;	
			actor.setMiddlePosY(level.getTileCenterPosY(index) + level.getTileSizeY()/2 - 0.1f); 
		}
	
		float cellMiddlePosX = level.getTileCenterPosX(index);
		float cellMiddlePosY = level.getTileCenterPosY(index);
		
		if(Math.abs(actor.getMiddlePosX() - cellMiddlePosX) <= graceX && 
		   Math.abs(actor.getMiddlePosY() - cellMiddlePosY) <= graceY) {	
			if(index != prevIndex) {
    			actor.setMiddlePosX(cellMiddlePosX);
    			actor.setMiddlePosY(cellMiddlePosY);
    			prevIndex = index;
    			return;
    		}
			
			actor.enteredNewFieldLogic(index);
			int cd = data[index];

	        if (wishDir_x != 0 || wishDir_y != 0) {
	        	if ( ( (wishDir_x ==  0 && wishDir_y ==  1 && (cd & 1) == 0)
	                || (wishDir_x ==  1 && wishDir_y ==  0 && (cd & 2) == 0)
	                || (wishDir_x ==  0 && wishDir_y == -1 && (cd & 4) == 0)
	                || (wishDir_x == -1 && wishDir_y ==  0 && (cd & 8) == 0)) ) {
	        		moveDir_x = wishDir_x;
	                moveDir_y = wishDir_y;
	        		actor.moveLogic(index);
	                animationTimer += Gdx.graphics.getDeltaTime();
	            }
	        }
	            
	        if (   (moveDir_x ==  0 && moveDir_y ==  1 && (cd & 1) != 0)
	            || (moveDir_x ==  1 && moveDir_y ==  0 && (cd & 2) != 0)
	            || (moveDir_x ==  0 && moveDir_y == -1 && (cd & 4) != 0)
	            || (moveDir_x == -1 && moveDir_y ==  0 && (cd & 8) != 0)) {
	        	moveDir_x = 0;
	        	moveDir_y = 0;
	        	actor.stopLogic(index);
	        } 
	        
			prevIndex = index;
		}
		
		actor.setMiddlePosX(actor.getMiddlePosX() + moveSpeed * moveDir_x);
		actor.setMiddlePosY(actor.getMiddlePosY() + moveSpeed * moveDir_y); 
		actor.setRegion(animationManager.getFrame(animationTimer));
		level.setLevelData(data);
	}
	
	public GameLevel getLevel() {
		return level;
	}

	public void setLevel(GameLevel level) {
		this.level = level;
	}

	public GameActor getActor() {
		return actor;
	}

	public void setActor(GameActor actor) {
		this.actor = actor;
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

	public float getGraceX() {
		return graceX;
	}

	public void setGraceX(float graceX) {
		this.graceX = graceX;
	}

	public float getGraceY() {
		return graceY;
	}

	public void setGraceY(float graceY) {
		this.graceY = graceY;
	}

	public AnimationManager getAnimationManager() {
		return animationManager;
	}

	public void setAnimationManager(AnimationManager animationManager) {
		this.animationManager = animationManager;
	}
	
	public int getWishDirX() {
		return wishDir_x;
	}
	
	public void setWishDirX(int wishDir_x) {
		this.wishDir_x = wishDir_x;
	}
	
	public int getWishDirY() {
		return wishDir_y;
	}
	
	public void setWishDirY(int wishDir_y) {
		this.wishDir_y = wishDir_y;
	}
	
	public int getMoveDirX() {
		return moveDir_x;
	}
	
	public void setMoveDirX(int moveDir_x) {
		this.moveDir_x = moveDir_x;
	}
	
	public int getMoveDirY() {
		return moveDir_y;
	}
	
	public void setMoveDirY(int moveDir_y) {
		this.moveDir_y = moveDir_y;
	}
}
