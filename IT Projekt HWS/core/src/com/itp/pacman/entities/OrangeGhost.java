package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.ai.Ghost;
import com.itp.pacman.stages.GameStage;

public class OrangeGhost extends Ghost{
	
	public OrangeGhost(GameStage stage, Player player) {
		super(stage, player);
		scatterIndex = level.getLevelData().length - level.getFieldSizeX() + 1;
		bodyColor = new Color(1f, 0.72f, 0.32f, 1f);
		setColor(bodyColor);
	}
	
	@Override
	public void setGoalIndex() {
		Vector2 ghostTilePosition = level.getTileCenterPos(getPositionInLevel());
		Vector2 playerTilePosition = level.getTileCenterPos(player.getPositionInLevel());
		float distance = ghostTilePosition.dst(playerTilePosition);
		
		if(distance / level.getTileSizeX() <= 8) {
			goalIndex = scatterIndex;
		} else if (distance / level.getTileSizeY() <= 8) {
			goalIndex = scatterIndex;
		} else {
			goalIndex = player.getPositionInLevel();
		}
	}
}
