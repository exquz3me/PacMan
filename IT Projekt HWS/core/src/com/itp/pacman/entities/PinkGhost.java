package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.itp.pacman.PacMan;
import com.itp.pacman.ai.Ghost;

public class PinkGhost extends Ghost{
	public PinkGhost(PacMan game) {
		super(game);
		scatterIndex = 2;
		setColor(new Color(1f, 0.72f, 1f, 1f));
	}
	
	public void setChaseIndex(Player player) {
		if(player.getMovementManager().getMoveDir().y == 1) {
			chaseIndex = player.getPositionInLevel() - 4 * level.getFieldSizeX() - 3;
		} else {
			if(player.getMovementManager().getMoveDir().x == 1) {
				chaseIndex = player.getPositionInLevel() + 4;
			} else if(player.getMovementManager().getMoveDir().y == -1) {
				chaseIndex = player.getPositionInLevel() + level.getFieldSizeX() * 4 - 1;		
			} else if(player.getMovementManager().getMoveDir().x == -1) {
				chaseIndex = player.getPositionInLevel() - 4;
			}
		}
	}
}
