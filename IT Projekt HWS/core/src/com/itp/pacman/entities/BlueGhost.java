package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.ai.Ghost;
import com.itp.pacman.stages.GameStage;

public class BlueGhost extends Ghost{
	private Ghost ghost;
	private int targetTileIndex;	
	
	
	public BlueGhost(GameStage stage, Player player, Ghost ghost) {
		super(stage, player);
		this.ghost = ghost;
		scatterIndex = level.getLevelData().length - 1;
		bodyColor = new Color(0f, 1f, 1f, 1f);
		setColor(bodyColor);
	}
	
	@Override
	public void setGoalIndex() {
		Vector2 playerMoveDir = player.getMovementManager().getMoveDir();
		Vector2 redGhostPosition = level.getTileCenterPos(ghost.getPositionInLevel());
		int playerArrayPos = player.getPositionInLevel();

		if(playerMoveDir.y == 1) {
			targetTileIndex = playerArrayPos - 2 * level.getFieldSizeX() - 2;
		} else if(playerMoveDir.x == 1) {
			targetTileIndex = playerArrayPos + 2;
		} else if(playerMoveDir.y == -1) {
			targetTileIndex = playerArrayPos + 2 * level.getFieldSizeX();		
		} else if(playerMoveDir.x == -1) {
			targetTileIndex = playerArrayPos - 2;
		}	
		
		Vector2 targetTilePosition = level.getTileCenterPos(targetTileIndex);
		float xDistance = targetTilePosition.x - redGhostPosition.x;
		float yDistance = targetTilePosition.y - redGhostPosition.y;
		targetTilePosition = new Vector2(targetTilePosition.x + xDistance, targetTilePosition.y + yDistance);
		goalIndex = level.getArrayPos(targetTilePosition);
	}
}
