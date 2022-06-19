package com.itp.pacman.levels;

import com.itp.pacman.PacMan;
import com.itp.pacman.stages.GameStage;

public class ExampleLevel extends GameLevel {
	public ExampleLevel(PacMan game, GameStage stage, float tileSizeX, float tileSizeY) {
		super(game, stage, 9, 9, tileSizeX, tileSizeY);
	}

	@Override
	protected void initCollisionData() {
		collisionData = new byte[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 0, 1, 0, 0, 0,
				0, 0, 0, 1, 0, 1, 0, 1, 0,
				0, 0, 0, 1, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0
		};		
	}

	@Override
	protected void initActorData() {
		actorData = new byte[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 5, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 2, 0, 3, 0, 4, 0, 0, 0
		};	
	}

	@Override
	protected void initTileData() {
		tileData = new byte[] {
				1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 0, 1, 0, 1, 1, 1,
				1, 1, 1, 0, 1, 0, 1, 0, 1,
				1, 1, 1, 0, 1, 0, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1,
		};
	}

	@Override
	protected void initEntityData() {
		entityData = new byte[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 1, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 2, 1, 0, 2, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0
		};
	}

}
