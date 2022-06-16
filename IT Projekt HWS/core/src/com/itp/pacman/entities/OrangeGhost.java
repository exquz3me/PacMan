package com.itp.pacman.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.itp.pacman.PacMan;
import com.itp.pacman.ai.Ghost;

public class OrangeGhost extends Ghost{
	public OrangeGhost(PacMan game) {
		super(game);
		scatterIndex = level.getLevelData().length - level.getFieldSizeX() + 1;
		setColor(new Color(1f, 0.72f, 0.32f, 1f));
	}

	public void setChaseIndex(Player player) {
		Vector2 ghostTilePosition = level.getTileCenterPos(getPositionInLevel());			//Note, can be replaced with only getPosition in level
		Vector2 playerTilePosition = level.getTileCenterPos(player.getPositionInLevel());
		float distance = ghostTilePosition.dst(playerTilePosition);
		
		if(distance / level.getTileSizeX() <= 8) {	//TODO: change to a vector 2 comparison?
			chaseIndex = scatterIndex;
		} else if (distance / level.getTileSizeY() <= 8) {
			chaseIndex = scatterIndex;
		} else {
			chaseIndex = player.getPositionInLevel();
		}
	}
}
