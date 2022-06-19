package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.itp.pacman.ai.Ghost;
import com.itp.pacman.stages.GameStage;

public class RedGhost extends Ghost{
	public RedGhost(GameStage stage, Player player) {
		super(stage, player);
		scatterIndex = level.getFieldSizeX() - 4;
		bodyColor = new Color(1f, 0f, 0f, 1f);
		setColor(bodyColor);
	}

	@Override
	public void setGoalIndex() {
		goalIndex = player.getPositionInLevel();
	}
}
