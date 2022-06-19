package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.ai.Ghost;
import com.itp.pacman.stages.GameStage;

public class PinkGhost extends Ghost{
	public PinkGhost(GameStage stage, Player player) {
		super(stage, player);
		scatterIndex = 2;
		bodyColor = new Color(1f, 0.72f, 1f, 1f);
		setColor(bodyColor);
	}
	
	@Override
	public void setGoalIndex() {
		Vector2 playerMoveDir = player.getMovementManager().getMoveDir();
		int playerArrayPos = player.getPositionInLevel();
		
		if(playerMoveDir.y == 1) {
			goalIndex = playerArrayPos - 4 * level.getFieldSizeX() - 4;
		} else if(playerMoveDir.x == 1) {
			goalIndex = playerArrayPos + 4;
		} else if(playerMoveDir.y == -1) {
			goalIndex = playerArrayPos + 4 * level.getFieldSizeX();		
		} else if(playerMoveDir.x == -1) {
			goalIndex = playerArrayPos - 4;
		}
	}
}
