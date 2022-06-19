package com.itp.pacman.ai;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.itp.pacman.entities.GameActor;
import com.itp.pacman.stages.GameStage;

//TODO:
//move setPathing logic to ghost class for easier managment

public class GhostManager {
	private GameStage stage;
	private HashMap<Integer, Ghost> ghosts = new HashMap<>();
	
	public GhostManager(GameStage stage) {
		this.stage = stage;
	}
	
	public void add(int ghostId, Ghost ghost) {
		ghosts.put(ghostId, ghost);
		stage.addActor(ghost);
	}
	
    public void remove(int ghostId) {
    	ghosts.remove(ghostId);
    }
	
	public void setPathingMode(PathingMode pathingMode) {
		for(Ghost ghost : ghosts.values()) {	
			if(ghost.getPathingMode() == PathingMode.BACKTRACK) {
				continue;
			}
			
			if(ghost.getPathingMode() == PathingMode.FRIGHTENED) {	
				ghost.setPathingMode(pathingMode);
			}
			
			if(pathingMode == PathingMode.FRIGHTENED) {
				ghost.setPathingMode(PathingMode.FRIGHTENED);	
				continue;
			}
			
			ghost.setPathingMode(pathingMode);
		}
	}
	
	public void debugGoalIndex(Batch batch) {
		for(Ghost ghost : ghosts.values()) {
			Texture texture = new Texture(Gdx.files.internal("White.png"));			
			Sprite sprite = new Sprite(texture);
			sprite.setColor(ghost.bodyColor);
			sprite.setPosition(stage.getLevel().getTileCenterPos(ghost.getGoalIndex()).x, 
					stage.getLevel().getTileCenterPos(ghost.getGoalIndex()).y);
			
			batch.begin();
			sprite.draw(batch);	
			batch.end();
		}
	}
	
	public boolean compareTile(GameActor actor) {
		for(Ghost ghost : ghosts.values()) {
			if(ghost.getPositionInLevel() == actor.getPositionInLevel()) {
				if(ghost.getPathingMode() == PathingMode.FRIGHTENED ) {
					ghost.setPathingMode(PathingMode.BACKTRACK);
				} else if (ghost.getPathingMode() != PathingMode.BACKTRACK) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void setAnimations(String regionName) {
		for(Ghost ghost : ghosts.values()) {
			if(ghost.getPathingMode() == PathingMode.FRIGHTENED) {
				continue;
			}	
			
			ghost.getAnimationManager().setCurrentAnim(regionName);
			ghost.getAnimationManager().play(true);
		}
	}
	
	public void timerActions(float timer) {
		if(timer >= 79) {
			setPathingMode(PathingMode.CHASE);
		} else if(timer >= 74) {
			setPathingMode(PathingMode.SCATTER);
		} else if(timer >= 54) {
			setPathingMode(PathingMode.CHASE);
		} else if(timer >= 41) {
			setPathingMode(PathingMode.SCATTER);
		} else if(timer >= 34) {
			setPathingMode(PathingMode.CHASE);
		} else if(timer >= 27) {
			setPathingMode(PathingMode.SCATTER);
		} else if(timer >= 7) {
			setPathingMode(PathingMode.CHASE);
		} else if(timer >= 0) {
			setPathingMode(PathingMode.SCATTER);
		}
	}
	
	public Ghost getGhostById(int ghostId) {
    	return ghosts.get(ghostId);
	}
}
