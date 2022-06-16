package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.PacMan;
import com.itp.pacman.ai.Ghost;

public class BlueGhost extends Ghost{
	public BlueGhost(PacMan game) {
		super(game);
		scatterIndex = level.getLevelData().length - 1;
		setColor(new Color(0f, 1f, 1f, 1f));
	}
	
	public void setChaseIndex(Player player, Ghost ghost) {
		Vector2 redGhostPosition;
		Vector2 targetTilePosition;
		int targetTileIndex = 0;

		if(player.getMovementManager().getMoveDir().y == 1) {
			 targetTileIndex = player.getPositionInLevel() - 2 * level.getFieldSizeX() - 1;		 
		} else {
			if(player.getMovementManager().getMoveDir().x == 1) {
				targetTileIndex = player.getPositionInLevel() + 2;
			} else if(player.getMovementManager().getMoveDir().y == -1) {
				targetTileIndex = player.getPositionInLevel() + level.getFieldSizeX() * 2 - 1;		
			} else if(player.getMovementManager().getMoveDir().x == -1) {
				targetTileIndex = player.getPositionInLevel() - 2;
			}
		}
		
		targetTilePosition = level.getTileCenterPos(targetTileIndex);
		redGhostPosition = level.getTileCenterPos(ghost.getPositionInLevel());
		targetTilePosition.add(targetTilePosition.sub(redGhostPosition));
		chaseIndex = level.getArrayPos(targetTilePosition);
	}
	
	//TODO: use dst2. everywhere you used dst on?
	//TODO: first create a vector of the target tile, and only then set the target index
}
