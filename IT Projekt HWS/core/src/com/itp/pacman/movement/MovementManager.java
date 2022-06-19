package com.itp.pacman.movement;

import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.entities.GameActor;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.stages.GameStage;

//TODO: 
//avoid clipping at high speeds
//create a check area, it checks all tiles between the player and the target position
	//if there is a full field between the player and target position
	//or if the player wants to move to a full field -> stop at the closest index


public class MovementManager {
	private GameLevel level;
	private GameActor actor;
	private float moveSpeed;
	private float graceX;
	private float graceY;
	private Vector2 wishDir;
	private Vector2 moveDir;
	private int prevIndex;
	
	public MovementManager(GameStage stage, GameActor actor) {
		level = stage.getLevel();
		this.actor = actor;
		graceX = level.getTileSizeX() * 0.075f;
		graceY = level.getTileSizeY() * 0.075f;
		wishDir = new Vector2();
		moveDir = new Vector2();
		prevIndex = actor.getPositionInLevel();
	}
	
	public void move() {
		byte[] levelData = level.getLevelData();
		int index = actor.getPositionInLevel();
		Vector2 targetPos = actor.getCenterPos();
		Vector2 currentCellPos = level.getTileCenterPos(index);	
		
		if(targetPos.x  >= level.getTotalSizeX()) {
			targetPos.sub(level.getTotalSizeX(), 0);
		}
		
		if(targetPos.x  <= 0) {
			targetPos.add(level.getTotalSizeX(), 0);
		}
			
		if(targetPos.y >= level.getTotalSizeY()) {
			targetPos.sub(0, level.getTotalSizeY());
		}
		
		if(targetPos.y <= 0) {
			targetPos.add(0, level.getTotalSizeY());
		}

		if(Math.abs(targetPos.x  - currentCellPos.x) <= graceX && 
		   Math.abs(targetPos.y - currentCellPos.y) <= graceY) {	
			if(index != prevIndex) {
				targetPos = currentCellPos;
    			actor.enteredNewFieldLogic(index);
    			prevIndex = index;
    		}
			
			int cd = levelData[index];
	
	        if (wishDir.x != 0 || wishDir.y != 0) {
	        	if ( ( (wishDir.x ==  0 && wishDir.y ==  1 && (cd & 1) == 0)
	                || (wishDir.x ==  1 && wishDir.y ==  0 && (cd & 2) == 0)
	                || (wishDir.x ==  0 && wishDir.y == -1 && (cd & 4) == 0)
	                || (wishDir.x == -1 && wishDir.y ==  0 && (cd & 8) == 0)) ) {
	        		moveDir.x = wishDir.x;
	                moveDir.y = wishDir.y;
	        		actor.moveLogic(index);
	            }
	        }
	            
	        if (   (moveDir.x ==  0 && moveDir.y ==  1 && (cd & 1) != 0)
	            || (moveDir.x ==  1 && moveDir.y ==  0 && (cd & 2) != 0)
	            || (moveDir.x ==  0 && moveDir.y == -1 && (cd & 4) != 0)
	            || (moveDir.x == -1 && moveDir.y ==  0 && (cd & 8) != 0)) {
	        	moveDir.x = 0;
	        	moveDir.y = 0;
	        	actor.stopMoveLogic(index);
	        }
	        
			prevIndex = index;
		}
		
		actor.setCenterPos(targetPos.add(moveDir.x * moveSpeed, moveDir.y * moveSpeed));			
		actor.postMoveLogic(actor.getPositionInLevel());
	}

	public GameActor getActor() {
		return actor;
	}

	public void setActor(GameActor actor) {
		this.actor = actor;
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
	
	public Vector2 getWishDir() {
		return wishDir;
	}
	
	public void setWishDir(float wishDir_x, float wishDir_y) {
		this.wishDir = new Vector2(wishDir_x ,wishDir_y);
	}
	
	public void setWishDir(Vector2 wishDir) {
		this.wishDir = wishDir;
	}
	
	public Vector2 getMoveDir() {
		return moveDir;
	}
	
	public void setMoveDir(float moveDir_x, float moveDir_y) {
		this.moveDir = new Vector2(moveDir_x, moveDir_y);
	}
	
	public void setMoveDir(Vector2 moveDir) {
		this.moveDir = moveDir;
	}
}
