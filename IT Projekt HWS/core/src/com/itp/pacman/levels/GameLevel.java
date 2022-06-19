package com.itp.pacman.levels;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.itp.pacman.PacMan;
import com.itp.pacman.screens.MainScreen;
import com.itp.pacman.stages.GameStage;

//TODO:
//combine all data arrays into one twodimensional array, once there is a better way of creating this array (e.g. map editor)
//make level an group and add all the stage actors to it for easier managment
//Fruit spawn

public abstract class GameLevel extends Actor{
	protected PacMan game;
	protected Array<AtlasRegion> tileMap;
	protected Array<AtlasRegion> entitySprites;
	protected TextureRegion region;
	protected byte[] collisionData;
	protected byte[] entityData;
	protected byte[] tileData;
	protected byte[] levelData;
	protected byte[] actorData;
	protected int fieldSizeX;
	protected int fieldSizeY;
	protected float tileSizeX;
	protected float tileSizeY;
	protected int totalSizeX;
	protected int totalSizeY;
	protected int objectsTotal;
	protected int objectsLeft;
	private int colIndex = 0;
	private int rowIndex = 0;
	
	public GameLevel(PacMan game, GameStage stage, int fieldSizeX, int fieldSizeY, float tileSizeX, float tileSizeY) {	
		this.game = game;
		tileMap = stage.getAtlas().findRegions("tile");
		entitySprites = stage.getAtlas().findRegions("Entity");
		this.fieldSizeX = fieldSizeX;
		this.fieldSizeY = fieldSizeY;
		this.tileSizeX = tileSizeX;
		this.tileSizeY = tileSizeY;
		totalSizeX = (int)(fieldSizeX * tileSizeX);
		totalSizeY = (int)(fieldSizeY * tileSizeY);
		tileData = new byte[fieldSizeX * fieldSizeY];
		levelData = new byte[fieldSizeX * fieldSizeY];
		initCollisionData();
		initTileData();
		initEntityData();
		initActorData();
		countObjects();
		initLevelData();
	}

	protected abstract void initCollisionData();
	
	protected abstract void initTileData();
	
	protected abstract void initEntityData();
	
	protected abstract void initActorData();
	
	protected void countObjects() {
		for(int i = 0; i < entityData.length; i++) {
			if(entityData[i] != 0) {
				objectsTotal++;
			}
		}
		
		objectsLeft = objectsTotal;
	}
	
	protected void initLevelData() {
		for(int i = 0; i < collisionData.length; i++) {
			byte data = 0;
			if(collisionData[i] == 0) {
				if(i > fieldSizeX - 1) {					//Up
					if(collisionData[i - fieldSizeX] != 0)
						data = (byte) (data | 0b0000001);
				} else if(collisionData[collisionData.length - fieldSizeX + i] != 0) {
						data = (byte) (data | 0b00000001);
				}
				
				if((i + 1) % fieldSizeX != 0) {				//Right
					if(collisionData[i + 1] != 0)
						data = (byte) (data | 0b00000010);
				} else if(collisionData[i - fieldSizeX + 1] != 0) {
						data = (byte) (data | 0b00000010);
				}
				
				if(i < collisionData.length - fieldSizeX) {	//Down
					if(collisionData[i + fieldSizeX] != 0)
						data = (byte) (data | 0b00000100);
				} else if(collisionData[i - collisionData.length + fieldSizeX] != 0){
					data = (byte) (data | 0b00000100);
				}
				
				if(i % fieldSizeX != 0)	{					//Left
					if(collisionData[i - 1] != 0)
						data = (byte) (data | 0b00001000);
				} else if(collisionData[i + fieldSizeX - 1] != 0) {
					data = (byte) (data | 0b00001000);
				}
			}
			levelData[i] = data;
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		for(int i = 0; i < tileData.length; i++) {
			setTilePosition();
			
			int tileSpriteIndex = tileData[i];
			region = tileMap.get(tileSpriteIndex);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			int entitySpriteIndex = entityData[i];
			region = entitySprites.get(entitySpriteIndex);		
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
	}
	
	public void reset() {
		game.setScreen(new MainScreen(game, (int)getStage().getViewport().getWorldWidth(), (int)getStage().getViewport().getWorldHeight()));
	}
	
	public void interact(int index) {	
		switch(entityData[index]){
			case 1:
				objectsLeft--;
				break;
			case 2:
				objectsLeft--;
				break;
		}
		
		entityData[index] = 0;
		
		/*
		if(objectsLeft == objectsTotal/2) {
			Gdx.app.log(getName(),"FRUIT SPAWNED!");
		}
		
		if(objectsLeft == objectsTotal/4) {
			Gdx.app.log(getName(),"FRUIT SPAWNED!");
		}
		 */
		
		if(objectsLeft == 0) {
			reset();
		}
	}
	
	public int getArrayPos(float xPos, float yPos) {
        int row = (int) ((totalSizeY - yPos) / tileSizeY);
		int col = (int) (xPos / tileSizeX);
		int index = row * fieldSizeX + col;		
		return index;
	}
		
	public int getArrayPos(Vector2 position) {
		int row = (int) ((totalSizeY - position.y) / tileSizeY);
		int col = (int) (position.x / tileSizeX);
		int index = row * fieldSizeX + col;		
		return index;
	}
	
	public Vector2 getTileCenterPos(int arrayPos) {
		int row = arrayPos / fieldSizeX;
		float tilePosX = (arrayPos - row * fieldSizeX) * tileSizeX + tileSizeX/2;	
		row = fieldSizeY - (arrayPos / fieldSizeX) - 1;
		float tilePosY = row * tileSizeY + tileSizeY/2;
		return new Vector2(tilePosX, tilePosY);
	}
	
	public void setTilePosition() {
		if(colIndex == fieldSizeX) {
			colIndex = 0;
			rowIndex++;
		}	
		
		if(rowIndex == fieldSizeY) {
			rowIndex = 0;
		}	
		
		float xPos = colIndex * tileSizeX;
		float yPos = totalSizeY - tileSizeY - rowIndex * tileSizeY;	
		setBounds(xPos, yPos, tileSizeX, tileSizeY);
		colIndex++;
	}
	
	public byte[] getCollisionData() {
		return collisionData;
	}
	
	public void setCollisionData(byte[] tileData) {
		this.collisionData = tileData;
	}
	
	public byte[] getEntityData() {
		return entityData;
	}
	
	public void setEntityData(byte[] entityData) {
		this.entityData = entityData;
	}
	
	public byte[] getActorData() {
		return actorData;
	}
	
	public void setActorData(byte[] actorData) {
		this.actorData = actorData;
	}
	
	public byte[] getTileData() {
		return tileData;
	}
	
	public void setTileData(byte[] drawData) {
		this.tileData = drawData;
	}
	
	public byte[] getLevelData() {
		return levelData;
	}
	
	public void setLevelData(byte[] levelData) {
		this.levelData = levelData;
	}
	
	public int getFieldSizeX() {
		return fieldSizeX;
	}
	
	public void setFieldSizeX(int fieldSizeX) {
		this.fieldSizeX = fieldSizeX;
	}
	
	public int getFieldSizeY() {
		return fieldSizeY;
	}
	
	public void setFieldSizeY(int fieldSizeY) {
		this.fieldSizeY = fieldSizeY;
	}
	
	public float getTileSizeX() {
		return tileSizeX;
	}
	
	public void setTileSizeX(int tileSizeX) {
		this.tileSizeX = tileSizeX;
	}
	
	public float getTileSizeY() {
		return tileSizeY;
	}
	
	public void setTileSizeY(int tileSizeY) {
		this.tileSizeY = tileSizeY;
	}
	
	public int getTotalSizeX() {
		return totalSizeX;
	}
	
	public void setTotalSizeX(int totalSizeX) {
		this.totalSizeX = totalSizeX;
	}
	
	public int getTotalSizeY() {
		return totalSizeY;
	}
	
	public void setTotalSizeY(int totalSizeY) {
		this.totalSizeY = totalSizeY;
	}
	
	public int getObjectsToCollect() {
		return objectsTotal;
	}
	
	public void setObjectsToCollect(int objectsToCollect) {
		this.objectsTotal = objectsToCollect;
	}
}