package com.itp.pacman.levels;

import com.badlogic.gdx.Gdx;

public class LevelHandler {
	private GameLevel level;
	private byte[] tileData;
	private byte[] itemData;				//idk maybe we will have to handle items differently
	private byte[] levelData;
	private int fieldSizeX;
	private int fieldSizeY;					//maybe just make the variables of level public?
	private int tileSizeX;
	private int tileSizeY;
	private int colIndex = 0;
	private int rowIndex = 0;
	
	public LevelHandler (GameLevel level) {
		this.level = level;
		this.tileData = level.getTileData();
		this.itemData = level.getItemData();
		this.levelData = level.getLevelData();
		fieldSizeX = level.getSizeX();
		fieldSizeY = level.getSizeY();
		tileSizeX = level.getTileSizeX();
		tileSizeY = level.getTileSizeY();
	}
	
	public void initData() {	
		for(int i = 0; i < tileData.length; i++) {				
			byte data = 0;
			if(tileData[i] == 0) {
				data = (byte) (itemData[i] << 4);
				//set background draw index to this
				if(i > fieldSizeY - 1)					//oben check
					if(tileData[i - fieldSizeY] != 0)
						data = (byte) (data | 1);
				if((i + 1) % fieldSizeX != 0) 			//rechts check
					if(tileData[i + 1] != 0)
						data = (byte) (data | 2);
				if(i < tileData.length - fieldSizeX) 	//unten check	
					if(tileData[i + fieldSizeY] != 0)
						data = (byte) (data | 4);
				if(i % fieldSizeX != 0)					//links check
					if(tileData[i - 1] != 0)
						data = (byte) (data | 8);
			}
			levelData[i] = data;
		}
	}
	
	public void setTileBounds(float scale) {
		int xPos = (int) (colIndex * tileSizeX);	//eek rowIndex and colIndex wtf
		int yPos = (int) (rowIndex * tileSizeY);
		level.setBounds(xPos, yPos, tileSizeX, tileSizeY);
		
		if(colIndex == fieldSizeX) {
			colIndex = 0;
			rowIndex++;
		}	
		if(rowIndex == fieldSizeY) {
			rowIndex = 0;
		}	
		colIndex++;	
	}
	
	public int getArrayPos(float posX, float posY) {
		int totalSizeY = tileSizeY * fieldSizeY;
        int row = (int) ((totalSizeY - posY) / tileSizeY);
		int col = (int) (posX / tileSizeX);
		int index = row * fieldSizeY + col;
		Gdx.app.log("row", " " + row);
		Gdx.app.log("col", " " + col);
        return index;
	}
}

//TODO: instead of having a walkable tiles list, create a third array of background, to which the tileData serves as a mask -> 0th index displays nothing,
//		the 0s get the sprite from the backgorund index to draw
//TODO: getX() and getY() give the world position, but we need position relative to parent!
//TODO: dispose?

