package com.itp.pacman.movement;

import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.PacMan;
import com.itp.pacman.entities.GameActor;
import com.itp.pacman.levels.GameLevel;

public class MovementManager {
	private GameLevel level;
	private GameActor actor;
	private float moveSpeed;
	private float graceX;
	private float graceY;
	private Vector2 wishDir;
	private Vector2 moveDir;
	private int prevIndex;
	
	public MovementManager(PacMan game, GameActor actor, float moveSpeed) {
		level = game.getLevel();
		this.actor = actor;
		this.moveSpeed = moveSpeed;
		graceX = level.getTileSizeX() * 0.075f;
		graceY = level.getTileSizeY() * 0.075f;
		wishDir = new Vector2();
		moveDir = new Vector2();
		prevIndex = actor.getPositionInLevel();
	}
	
	public void move() {
		byte[] levelData = level.getLevelData();
		int index = actor.getPositionInLevel();
		
		if(actor.getCenterPos().x  >= level.getTotalSizeX()) {
			index -= level.getFieldSizeX();
			actor.setCenterPos(actor.getCenterPos().x  - level.getTotalSizeX(), actor.getCenterPos().y); 
		}
		
		if(actor.getCenterPos().x  <= 0) {
			index += level.getFieldSizeX() - 1;
			actor.setCenterPos(actor.getCenterPos().x  + level.getTotalSizeX(), actor.getCenterPos().y); 
		}
			
		if(actor.getCenterPos().y >= level.getTotalSizeY()) {
			index = levelData.length - level.getFieldSizeX();	
			actor.setCenterPos(actor.getCenterPos().x, actor.getCenterPos().y - level.getTotalSizeY()); 
		}
		
		if(actor.getCenterPos().y <= 0) {
			index = levelData.length - index;	
			actor.setCenterPos(actor.getCenterPos().x, actor.getCenterPos().y + level.getTotalSizeY()); 
		}
	
		Vector2 cellCenterPos = level.getTileCenterPos(index);
		
		if(Math.abs(actor.getCenterPos().x  - cellCenterPos.x) <= graceX && 
		   Math.abs(actor.getCenterPos().y - cellCenterPos.y) <= graceY) {	
			if(index != prevIndex) {
    			actor.setCenterPos(cellCenterPos);			
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
	        	actor.stopLogic(index);
	        }
	        
			prevIndex = index;
		}
		
		actor.setCenterPos(actor.getCenterPos().x + moveSpeed * moveDir.x, actor.getCenterPos().y + moveSpeed * moveDir.y); 
		actor.postMoveLogic(actor.getPositionInLevel());
		level.setLevelData(levelData);
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
