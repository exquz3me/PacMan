package com.itp.pacman.ai;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.PacMan;
import com.itp.pacman.animation.AnimationManager;
import com.itp.pacman.entities.GameActor;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.movement.MovementManager;

//TODO: forgot about the ? operator, maybe it could be handy somewhere

public abstract class Ghost extends GameActor{
	protected GameLevel level;	
	protected MovementManager movementManager;
	protected float ghostSpeed = 0.7f;	
	protected int chaseIndex;
	protected int scatterIndex;
	protected int backtrackIndex = 320;
	protected PathingMode pathingMode;
	protected PathingMode previousPathingMode;	
	private boolean isEaten;
	private int eyeDir;

	public Ghost(PacMan game) {
		super(game);
		level = game.getLevel();
		animationManager = new AnimationManager();
		animationSpeed = 0.5f;
		animationManager.add(game.getAtlas(), "GhostNormal", animationSpeed);  
		animationManager.add(game.getAtlas(), "GhostEatable", animationSpeed);    
		animationManager.add(game.getAtlas(), "Eyes", animationSpeed);  
		animationManager.setCurrentAnim("GhostNormal");
		animationManager.setLooping(true);			
		movementManager = new MovementManager(game, this, ghostSpeed);
		setRegion(game.getAtlas().getRegions().first());
		setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight());  //dont forget, ghosts are bigger than 1 tile
		setOrigin(getWidth()/2, getHeight()/2);
	}
	
	@Override
	public void act(float delta) {
		movementManager.move();
		setRegion(animationManager.getFrame());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(pathingMode == PathingMode.CHASE || pathingMode == PathingMode.SCATTER) {
			batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			batch.setColor(255, 255, 255, 255 * parentAlpha);
			region = game.getAtlas().findRegions("Eyes").get(eyeDir);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		} else if (pathingMode == PathingMode.FRIGHTENED) {
			batch.setColor(255, 255, 255, 255 * parentAlpha);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		} else if (pathingMode == PathingMode.BACKTRACK) {
			batch.setColor(255, 255, 255, 255 * parentAlpha);
			region = game.getAtlas().findRegions("Eyes").get(eyeDir);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
	}
	
	@Override
	public void moveLogic(int index) {
		setEyeDir();
		animationManager.setPlay(true);
	}
	
	@Override
	public void enteredNewFieldLogic(int index) {				//TODO: up left down (decision prio) never right
		//frightent -> random direction on crossection (eatable)
		//when they change modes they are forced to reverse direction + overwrites the made decicion
		//exeption is frightend mode
		//there are special yellow tiles on the level -  ghosts can not choose to turn upwards from these tiles, 
		//in such tiles if coming from left or right, they will proceed out the other side

		switch(pathingMode) {			//TODO: create a different method for every chaseMode
			case CHASE:
				getDistance(index, chaseIndex);
				break;
			case SCATTER:
				getDistance(index, scatterIndex);
				break;
			case FRIGHTENED:
				getDistance(index, 0);
				break;
			case BACKTRACK:
				getDistance(index, backtrackIndex);
				if(getPositionInLevel() == backtrackIndex) {
					pathingMode = PathingMode.CHASE;
					movementManager.setMoveSpeed(2f);
				}
				break;
		}
		
		previousPathingMode = pathingMode;				
	}
	
	@Override
	public void postMoveLogic(int index) {
		
	}

	@Override
	public void stopLogic(int index) {
		animationManager.setPlay(false);
	}
	
	private void getDistance(int index, int goalIndex) {	
		Vector2 goalIndexPos = level.getTileCenterPos(goalIndex);		
		int cd = level.getLevelData()[index];
		float distance= level.getTotalSizeX() + level.getTotalSizeY();
		float distanceUp = 0;
		float distanceRight = 0;
		float distanceDown = 0;
		float distanceLeft = 0;
				
		if(pathingMode != previousPathingMode && pathingMode != PathingMode.FRIGHTENED) {
			movementManager.setWishDir(movementManager.getMoveDir().x * -1, movementManager.getMoveDir().y * -1);
		}
			
		if((cd & (cd - 1)) == 0) {	//is in crossection		
			if(pathingMode == PathingMode.FRIGHTENED) {
				int random = (int)Math.floor(Math.random()*(3+1));
				if((cd & 0b00000001) == 0 && random == 0) {			//Up
					movementManager.setWishDir(0, 1);
					return;
				} else if((cd & 0b00000010) == 0 && random == 1) {	//Right
					movementManager.setWishDir(1, 0);
					return;
				} else if((cd & 0b00000100) == 0 && random == 2) {	//Down
					movementManager.setWishDir(0, -1);
					return;
				} else if((cd & 0b00001000) == 0 && random == 3) {	//Left
					movementManager.setWishDir(-1, 0);
					return;
				}
			}
			
			//the first ifs are optional
			if(index > level.getFieldSizeX() - 1) {									//Up
				if((cd & 0b00000001) == 0 && movementManager.getMoveDir().y != -1) {
					distanceUp = level.getTileCenterPos(index - level.getFieldSizeX()).dst(goalIndexPos);	
					distance = distanceUp;
				}
			}	
			
			if((index + 1) % level.getFieldSizeX() != 0) {							//Right
				if((cd & 0b00000010) == 0 && movementManager.getMoveDir().x != -1) {
					distanceRight = level.getTileCenterPos(index + 1).dst(goalIndexPos);
					if (distanceRight < distance) {
						distance = distanceRight;
					}
				}
			}
			
			if(index < level.getCollisionData().length - level.getFieldSizeX()) {	//Down
				if((cd & 0b00000100) == 0 && movementManager.getMoveDir().y != 1) {
					distanceDown = level.getTileCenterPos(index + level.getFieldSizeX()).dst(goalIndexPos);
					if (distanceDown < distance) {
						distance = distanceDown;
					}
				}
			}
			
			if(index % level.getFieldSizeX() != 0)	{								//Left
				if((cd & 0b00001000) == 0 && movementManager.getMoveDir().x != 1) {
					distanceLeft = level.getTileCenterPos(index - 1).dst(goalIndexPos);
					if (distanceLeft < distance) {
						distance = distanceLeft;
					}
				}
			}	
			
			//ghost cant call the same direction twice, but if many distacnes are equal it will start going in circles
			//for original movement set != opposide and change the priority order
			//TODO: == 0 fixes the problem but creates a new one of ghosts moving in circles
	
			if(distanceUp == distance && movementManager.getMoveDir().y == 0) {				//Up
				movementManager.setWishDir(0, 1);
				return;
			} else if(distanceRight == distance && movementManager.getMoveDir().x == 0) {	//Right
				movementManager.setWishDir(1, 0);			
				return;
			} else if(distanceDown == distance && movementManager.getMoveDir().y == 0) {	//Down
				movementManager.setWishDir(0, -1);	
				return;
			} else if(distanceLeft == distance && movementManager.getMoveDir().x == 0) {	//Left
				movementManager.setWishDir(-1, 0);
				return;
			}
		}
		
		else {
			if((cd & 0b00000001) == 0 && movementManager.getMoveDir().y == 0) {				//Up
				movementManager.setWishDir(0, 1);
				return;
			} else if((cd & 0b00000010) == 0 && movementManager.getMoveDir().x == 0) {		//Right
				movementManager.setWishDir(1, 0);
				return;
			} else if((cd & 0b00000100) == 0 && movementManager.getMoveDir().y == 0) {		//Down
				movementManager.setWishDir(0, -1);
				return;
			} else if((cd & 0b00001000) == 0 && movementManager.getMoveDir().x == 0) {		//Left
				movementManager.setWishDir(-1, 0);
				return;
			} else if(((~cd & 0x0f) & ((~cd & 0x0f)-1)) == 0) {								//is stuck
				movementManager.setWishDir(movementManager.getMoveDir().x * -1, movementManager.getMoveDir().y * -1);
			}
		}
	}
	
	public void setEyeDir() {
		if(movementManager.getMoveDir().y == 1) {
			eyeDir = 0;
		} else if(movementManager.getMoveDir().x == 1) {
			eyeDir = 1;
		} else if(movementManager.getMoveDir().y == -1) {
			eyeDir = 2;
		} else if(movementManager.getMoveDir().x == -1) {
			eyeDir = 3;
		}
	}
	
	public MovementManager getMovementManager() {
		return movementManager;
	}

	public void setMovementManager(MovementManager movementManager) {
		this.movementManager = movementManager;
	}

	public float getGhostSpeed() {
		return ghostSpeed;
	}

	public void setGhostSpeed(float ghostSpeed) {
		this.ghostSpeed = ghostSpeed;
	}

	public int getChaseIndex() {
		return chaseIndex;
	}

	public void setChaseIndex(int chaseIndex) {
		this.chaseIndex = chaseIndex;
	}
	
	public int getScatterIndex() {
		return scatterIndex;
	}

	public void setScatterIndex(int scatterIndex) {
		this.scatterIndex = scatterIndex;
	}

	public int getBacktrackIndex() {
		return backtrackIndex;
	}

	public void setBacktrackIndex(int backtrackIndex) {
		this.backtrackIndex = backtrackIndex;
	}
	
	public void setIsEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}
	
	public boolean getIsEaten() {
		return isEaten;
	}
	
	public void setPathingMode(PathingMode pathingMode) {
		this.pathingMode = pathingMode;
		previousPathingMode = pathingMode;
	}
	
	public PathingMode getPathingMode() {
		return pathingMode;
	}
}

//----	different pathing version (with calculation of map border)
/*
					//Right
if((cd & 0b00000010) == 0 && movementManager.getMoveDir().x != -1) {		//if no wall to right and we are current not moving left
	if((index + 1) % level.getFieldSizeX() == 0) {	//if we are on the right side of the field
		distanceRight = level.getTileCenterPos(index - level.getFieldSizeX() - 1).dst(goalIndexPos);	//get the left most index
	} else {										//if we are not on the right side of the field
		distanceRight = level.getTileCenterPos(index + 1).dst(goalIndexPos);
	}
	
	if (distanceRight < distance) {
		distance = distanceRight;
	}
}
*/
//----
