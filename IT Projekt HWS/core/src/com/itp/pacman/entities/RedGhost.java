package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.itp.pacman.PacMan;
import com.itp.pacman.ai.Ghost;

public class RedGhost extends Ghost{
	public RedGhost(PacMan game) {
		super(game);
		scatterIndex = level.getFieldSizeX() - 4;
		setColor(new Color(1f, 0f, 0f, 1f));
	}

	public void setChaseIndex(Player player) {
		chaseIndex = player.getPositionInLevel();
	}
}
