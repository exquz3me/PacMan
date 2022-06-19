package com.itp.pacman.ai;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.entities.GameActor;
import com.itp.pacman.entities.Player;
import com.itp.pacman.stages.GameStage;

//TODO:
//frigthened mode is not implemented correctly -> should be random decision, 
//ghosts have to flip direction on mode change
//if goalIndex is out of bounds (left or right) it gets looped arround -> getArray position code
//ghosts are not blinking (they flash exactly nine times before exiting frightened mode)
//ghosts sould be able to turn freely in backtrackmode
//ghosts are bigger than 1 tile but we need the original atlas for that
//ghosts should be able to choose direction freely in backtrack mode
//there are special tiles on the level - ghosts can not choose to turn upwards from these tiles, 
	//in such tiles if coming from left or right, they will proceed out the other side
//up left down right (decision priority)
//one ghost got stuck (must have something to do with distance calculation)
//ghost cage

public abstract class Ghost extends GameActor {
	protected Player player;
	protected float chaseSpeed = 0.6f;	
	protected float frightenedSpeed = 0.3f;
	protected float backtrackSpeed = 1.8f;
	
	protected int goalIndex;
	protected int scatterIndex;
	protected int backtrackIndex = 320;
	
	protected PathingMode pathingMode = PathingMode.SCATTER;
	protected PathingMode previousPathingMode = pathingMode;
	
	protected Color bodyColor;
	private int eyeDir;

	public Ghost(GameStage stage, Player player) {
		super(stage);
		this.player = player;
		
		animationManager.add(stage.getAtlas(), "GhostNormal", 0.5f);
		animationManager.add(stage.getAtlas(), "GhostEatable", 0.5f);
		animationManager.setCurrentAnim("GhostNormal");
		animationManager.setLooping(true);	
		
		movementManager.setMoveSpeed(chaseSpeed);
		
		setRegion(stage.getAtlas().getRegions().first());
		setScaleX(getScaleX() * 2);
		setScaleY(getScaleY() * 2);
		setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight());
		setOrigin(getWidth()/2, getHeight()/2);
	}
	
	@Override
	public void act(float delta) {
		movementManager.move();
		setRegion(animationManager.getCurrentFrame());
		setGoalIndex();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(pathingMode == PathingMode.CHASE || pathingMode == PathingMode.SCATTER) {
			super.draw(batch, parentAlpha);
			
			batch.setColor(new Color(1f, 1f, 1f, 1f));
			region = stage.getAtlas().findRegions("Eyes").get(eyeDir);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
		
		else if(pathingMode == PathingMode.FRIGHTENED) {
			batch.setColor(new Color(1f, 1f, 1f, 1f));
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
		
		else if(pathingMode == PathingMode.BACKTRACK) {
			batch.setColor(new Color(1f, 1f, 1f, 1f));
			region = stage.getAtlas().findRegions("Eyes").get(eyeDir);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
	}
	
	@Override
	public void moveLogic(int index) {
		setEyeDir();
		animationManager.play(true);
	}
	
	@Override
	public void enteredNewFieldLogic(int index) {
		switch(pathingMode) {
			case CHASE:
				chase(index, goalIndex);
				break;
			case SCATTER:
				scatter(index);
				break;
			case FRIGHTENED:
				frightened(index);
				break;
			case BACKTRACK:
				backtrack(index);
				break;
		}
	}
	
	@Override
	public void stopMoveLogic(int index) {
		animationManager.play(false);
	}
	
	public void chase(int index, int goalIndex) {
		movementManager.setMoveSpeed(chaseSpeed);
		chooseDirection(index, goalIndex);
	}
	
	public void scatter(int index) {
		movementManager.setMoveSpeed(chaseSpeed);
		chooseDirection(index, scatterIndex);
	}
	
	public void frightened(int index) {
		int cd = level.getLevelData()[index];
		Vector2 moveDir = movementManager.getMoveDir();
		movementManager.setMoveSpeed(frightenedSpeed);
		
		if((cd & 0b00000001) == 0 && moveDir.y != -1) {				//Up
			movementManager.setWishDir(0, 1);
			return;
		} else if((cd & 0b00000010) == 0 && moveDir.x != -1) {		//Right
			movementManager.setWishDir(1, 0);
			return;
		} else if((cd & 0b00000100) == 0 && moveDir.y != 1) {		//Down
			movementManager.setWishDir(0, -1);
			return;
		} else if((cd & 0b00001000) == 0 && moveDir.x != 1) {		//Left
			movementManager.setWishDir(-1, 0);
			return;
		} else if(((~cd & 0x0f) & ((~cd & 0x0f)-1)) == 0) {			//is stuck
			movementManager.setWishDir(moveDir.scl(-1));
		}
		
		//not every direction is suitable find a way to choose between the suitable ones (using wall collsion data)
		//int random = (int)Math.floor(Math.random()*(3+1));
		//not sure if move dir check is required
	}
	
	public void backtrack(int index) {
		movementManager.setMoveSpeed(backtrackSpeed);
		
		if(pathingMode == PathingMode.BACKTRACK && index == backtrackIndex) {
			animationManager.setCurrentAnim("GhostNormal");
			pathingMode = PathingMode.SCATTER;
		}
		
		chooseDirection(index, backtrackIndex);
	}
	
	private void chooseDirection(int index, int goalIndex) {
		Vector2 goalIndexPos = level.getTileCenterPos(goalIndex);		
		Vector2 moveDir = movementManager.getMoveDir();
		int cd = level.getLevelData()[index];
		float distance= 100000;
		float distanceUp = 0;
		float distanceRight = 0;
		float distanceDown = 0;
		float distanceLeft = 0;
		
		if((cd & (cd - 1)) == 0) {	//is in crossection	
			if((cd & 0b00000001) == 0 && moveDir.y != -1) {		//Up
				distanceUp = level.getTileCenterPos(index - level.getFieldSizeX()).dst(goalIndexPos);	
				distance = distanceUp;
			}

			if((cd & 0b00000010) == 0 && moveDir.x != -1) {		//Right
				distanceRight = level.getTileCenterPos(index + 1).dst(goalIndexPos);
				if (distanceRight < distance) {
					distance = distanceRight;
				}
			}
			
			if((cd & 0b00000100) == 0 && moveDir.y != 1) {		//Down
				distanceDown = level.getTileCenterPos(index + level.getFieldSizeX()).dst(goalIndexPos);
				if (distanceDown < distance) {
					distance = distanceDown;
				}
			}
			
			if((cd & 0b00001000) == 0 && moveDir.x != 1) {		//Left
				distanceLeft = level.getTileCenterPos(index - 1).dst(goalIndexPos);
				if (distanceLeft < distance) {
					distance = distanceLeft;
				}
			}
			
			if(distanceUp == distance && moveDir.y != -1) {				//Up
				movementManager.setWishDir(0, 1);
				return;
			} else if(distanceRight == distance && moveDir.x != -1) {	//Right
				movementManager.setWishDir(1, 0);	
				return;
			} else if(distanceDown == distance && moveDir.y != 1) {		//Down
				movementManager.setWishDir(0, -1);	
				return;
			} else if(distanceLeft == distance && moveDir.x != 1) {		//Left
				movementManager.setWishDir(-1, 0);
				return;
			}
		}
		
		if((cd & 0b00000001) == 0 && moveDir.y != -1) {				//Up
			movementManager.setWishDir(0, 1);
			return;
		} else if((cd & 0b00000010) == 0 && moveDir.x != -1) {		//Right
			movementManager.setWishDir(1, 0);
			return;
		} else if((cd & 0b00000100) == 0 && moveDir.y != 1) {		//Down
			movementManager.setWishDir(0, -1);
			return;
		} else if((cd & 0b00001000) == 0 && moveDir.x != 1) {		//Left
			movementManager.setWishDir(-1, 0);
			return;
		} else if(((~cd & 0x0f) & ((~cd & 0x0f)-1)) == 0) {			//is stuck
			movementManager.setWishDir(moveDir.scl(-1));
		}
	}
	
	public void setEyeDir() {
		Vector2 moveDir = movementManager.getMoveDir();
		
		if(moveDir.y == 1) {
			eyeDir = 0;
		} else if(moveDir.x == 1) {
			eyeDir = 1;
		} else if(moveDir.y == -1) {
			eyeDir = 2;
		} else if(moveDir.x == -1) {
			eyeDir = 3;
		}
	}

	public float getGhostSpeed() {
		return chaseSpeed;
	}

	public void setGhostSpeed(float ghostSpeed) {
		this.chaseSpeed = ghostSpeed;
	}

	public int getGoalIndex() {
		return goalIndex;
	}

	public abstract void setGoalIndex();

	public void setPathingMode(PathingMode pathingMode) {
		this.pathingMode = pathingMode;
		previousPathingMode = pathingMode;
	}
	
	public PathingMode getPathingMode() {
		return pathingMode;
	}
}
