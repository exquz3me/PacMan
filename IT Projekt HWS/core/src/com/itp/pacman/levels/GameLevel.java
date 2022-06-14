package com.itp.pacman.levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.itp.pacman.PacMan;
import com.itp.pacman.entities.GameActor;
import com.itp.pacman.screens.MainScreen;

public class GameLevel extends GameActor{		//TODO: get and set
	protected Array<AtlasRegion> tileMap;
	protected byte[] collisionData;
	protected byte[] entityData;	//TODO: maybe we will have to handle items differently, -> i dont like the limitation of 4 bits (16 enteties)
	protected byte[] actorData;
	protected byte[] tileData;
	protected byte[] levelData;
	protected int fieldSizeX;
	protected int fieldSizeY;
	protected int tileSizeX;
	protected int tileSizeY;
	protected int totalSizeX;
	protected int totalSizeY;
	
	protected int objectsTotal;
	protected int objectsLeft;
	protected int currentLevel = 0;
	private int colIndex = 0;
	private int rowIndex = 0;
	
	public GameLevel(PacMan game, String regionName, int fieldSizeX, int fieldSizeY, int tileSizeX, int tileSizeY) {	
		super(game);
		atlas = new TextureAtlas(Gdx.files.internal("map.atlas"));	//TODO: remove, when atlases are merged
		tileMap = atlas.findRegions(regionName);
		this.fieldSizeX = fieldSizeX;
		this.fieldSizeY = fieldSizeY;
		this.tileSizeX = tileSizeX;
		this.tileSizeY = tileSizeY;
		totalSizeX = fieldSizeX * tileSizeX;
		totalSizeY = fieldSizeY * tileSizeY;
		tileData = new byte[fieldSizeX * fieldSizeY];
		levelData = new byte[fieldSizeX * fieldSizeY];
		initCollisionData();
		initEntityData();
		initActorData();
		initTileData();
		initLevelData();
	}
	
	protected void load() {	
		//TODO:
	}
	
	//combine all to two dimensional array once we have a better way of getting the data
	protected void initCollisionData() {
		collisionData = new byte[] {
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1,
				0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1,
				0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0,
				0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0,
				0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};		
	}
	
	protected void initActorData() {
		actorData = new byte[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};		
	}
	
	protected void initTileData() {
		tileData = new byte[] {
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1,
				0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1,
				0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0,
				0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0,
				0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};
	}
	
	protected void initEntityData() {
		entityData = new byte[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};
		
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
				if(i > fieldSizeX - 1) {					//oben check
					if(collisionData[i - fieldSizeX] != 0)
						data = (byte) (data | 0b00000001);
				} else if(collisionData[collisionData.length - fieldSizeX + i] != 0) {
						data = (byte) (data | 0b00000001);
				}
				
				if((i + 1) % fieldSizeX != 0) {				//rechts check
					if(collisionData[i + 1] != 0)
						data = (byte) (data | 0b00000010);
				} else if(collisionData[i - fieldSizeX + 1] != 0) {
						data = (byte) (data | 0b00000010);
				}
				
				if(i < collisionData.length - fieldSizeX) {	//unten check
					if(collisionData[i + fieldSizeX] != 0)
						data = (byte) (data | 0b00000100);
				} else if(collisionData[i - collisionData.length + fieldSizeX] != 0){
					data = (byte) (data | 0b00000100);
				}
				
				if(i % fieldSizeX != 0)	{					//links check
					if(collisionData[i - 1] != 0)
						data = (byte) (data | 0b00001000);
				} else if(collisionData[i + fieldSizeX - 1] != 0) {
					data = (byte) (data | 0b00001000);
				}
			}
			levelData[i] = data;
		}
	}		//TODO: two same assignments logic can be simplyfied
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		for(int i = 0; i < tileData.length; i++) {	//TODO: not sure if this drawing method is a smart idea
			setTilePosition();
			
			int tileSpriteIndex = tileData[i];
			region = tileMap.get(tileSpriteIndex);	
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			
			int entitySpriteIndex = entityData[i];
			region = tileMap.get(entitySpriteIndex);		
			if(entitySpriteIndex != 0) {
				batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
						getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			}
		}
	}
	
	public void next() {	//TODO: difficulty
		//increase difficulty variables in level class
		reset();
	}
	
	public void reset() {
		/*
		initCollisionData();
		initEntityData();
		initTileData();
		initLevelData();
		initDrawData();
		*/
		
		//set screen is weird sometimes, maybe its because im not calling dispoe?
		game.setScreen(new MainScreen(game, game.getVirtualWidth(), game.getVirtualHeight()));
	}
	
	public void interact(int index) {	
		switch(entityData[index]){			//TODO: score based on item picked up
			case 1:
				objectsLeft--;
				break;
			case 2:
				objectsLeft--;
				break;
		}
		
		entityData[index] = 0;
		
		if(objectsLeft == objectsTotal/2) {
			Gdx.app.log(getName(),"FRUIT SPAWNED!");
		}
		
		if(objectsLeft == objectsTotal/4) {
			Gdx.app.log(getName(),"FRUIT SPAWNED!");
		}

		if(objectsLeft == 0) {	//Win condition
			Gdx.app.log(getName(), "YOU WIN!");
			currentLevel++;
			reset();
		}
	}
	
	
	public int getArrayPos(float xPos, float yPos) {
        int row = (int) ((totalSizeY - yPos) / tileSizeY);
		int col = (int) (xPos / tileSizeX);
		int index = row * fieldSizeX + col;		
		return index;
	}
	
	public float getTileCenterPosX(int arrayPos) {
		int row = arrayPos / fieldSizeX;
		arrayPos -= row * fieldSizeX;
		float tilePosX = arrayPos * tileSizeX + tileSizeX/2;
		return tilePosX;
	}
	
	public float getTileCenterPosY(int arrayPos) {
		int nrow = fieldSizeY - (arrayPos / fieldSizeX) - 1;
		float tilePosY = nrow * tileSizeY + tileSizeY/2;
		return tilePosY;
	}
	
	public void setTilePosition() {
		if(colIndex == fieldSizeX) {
			colIndex = 0;
			rowIndex++;
		}	
		
		if(rowIndex == fieldSizeY) {
			rowIndex = 0;
		}	
		
		int xPos = (int) (colIndex * tileSizeX);
		int yPos = totalSizeY - tileSizeY - (int) (rowIndex * tileSizeY);
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
		this.entityData = actorData;
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
	
	public void setLevelData(byte[] levelData) {	//TODO: do i have to recalculate everything?
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
	
	public int getTileSizeX() {
		return tileSizeX;
	}
	
	public void setTileSizeX(int tileSizeX) {
		this.tileSizeX = tileSizeX;
	}
	
	public int getTileSizeY() {
		return tileSizeY;
	}
	
	public void setTileSizeY(int tileSizeY) {
		this.tileSizeY = tileSizeY;
	}
	
	public int getTotalSizeX() {
		return totalSizeX;
	}
	
	public void setTotalSizeX(int totalSizeX) {		//TODO: not sure if i have to recalculate the thing in set
		this.totalSizeX = totalSizeX;
	}
	
	public int getTotalSizeY() {
		return totalSizeY;
	}
	
	public void setTotalSizeY(int totalSizeY) {		//TODO: not sure if i have to recalculate the thing in set
		this.totalSizeY = totalSizeY;
	}
	
	public int getObjectsToCollect() {				//TODO: maybe only leave setters?
		return objectsTotal;
	}
	
	public void setObjectsToCollect(int objectsToCollect) {
		this.objectsTotal = objectsToCollect;
	}
}

//TODO: dispose!!! -> everything that implements disposable
//ToSting method ;)