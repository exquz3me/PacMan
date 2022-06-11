package com.itp.pacman.levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.itp.pacman.PacMan;
import com.itp.pacman.entities.GameActor;

public class GameLevel extends GameActor{
	protected Array<AtlasRegion> tileMap;
	protected byte[] tileData;
	protected byte[] entityData;	//idk maybe we will have to handle items differently, -> i dont like the limitation of 4 bits (16 enteties)
	protected byte[] collisionData;		//draw an item on top based on the first 4 bytes (same as tile data but drawn on top)
	protected byte[] backgroundData;	
	protected byte[] drawData;	
	protected int fieldSizeX;
	protected int fieldSizeY;
	protected int tileSizeX;
	protected int tileSizeY;
	protected int totalSizeX;
	protected int totalSizeY;
	
	private int colIndex = 0;	//eek
	private int rowIndex = 0;
	
	public GameLevel(PacMan game, String regionName, int fieldSizeX, int fieldSizeY, int tileSizeX, int tileSizeY) {	
		super(game);
		atlas = new TextureAtlas(Gdx.files.internal("map.atlas"));	//TODO: rmoeve, when atlases are merged
		tileMap = atlas.findRegions(regionName);
		this.fieldSizeX = fieldSizeX;
		this.fieldSizeY = fieldSizeY;
		this.tileSizeX = tileSizeX;
		this.tileSizeY = tileSizeY;
		totalSizeX = fieldSizeX * tileSizeX;
		totalSizeY = fieldSizeY * tileSizeY;
		initTileData();
		initEntityData();
		initBackgroundData();
		drawData = new byte[tileData.length];
		collisionData = new byte[tileData.length];
		initCollisionData();
	}
	
	protected void initTileData() {
		tileData = new byte[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0,
				0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0,
				0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0,
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
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};
	}
	
	protected void initBackgroundData() {
		backgroundData = new byte[] {
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
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		};		
	}
	
	protected void initCollisionData() {
		for(int i = 0; i < tileData.length; i++) {				
			byte data = 0;
			if(tileData[i] == 0) {
				data = (byte) (entityData[i] << 4);		//TODO: i dont like it
				drawData[i] = backgroundData[i];
				if(i > fieldSizeX - 1)					//oben check
					if(tileData[i - fieldSizeX] != 0)
						data = (byte) (data | 0b00000001);
				if((i + 1) % fieldSizeX != 0) 			//rechts check
					if(tileData[i + 1] != 0)
						data = (byte) (data | 0b00000010);
				if(i < tileData.length - fieldSizeX)	//unten check
					if(tileData[i + fieldSizeX] != 0)
						data = (byte) (data | 0b00000100);
				if(i % fieldSizeX != 0)					//links check
					if(tileData[i - 1] != 0)
						data = (byte) (data | 0b00001000);
			}
			else {
				drawData[i] = tileData[i];
			}
			collisionData[i] = data;	
		}
	}
	
	public int getArrayPos(float xPos, float yPos) {
        int row = (int) ((tileSizeY * fieldSizeY - yPos) / tileSizeY);
		int col = (int) (xPos / tileSizeX);
		int index = row * fieldSizeX + col;
        return index;
	}
	
	public float getTilePosX(int arrayPos) {
		int row = arrayPos / fieldSizeX;
		arrayPos -= row * fieldSizeX;
		float tilePosX = arrayPos * tileSizeX + tileSizeX/2;
		return tilePosX;
	}
	
	public float getTilePosY(int arrayPos) {
		int nrow = fieldSizeY - (arrayPos / fieldSizeX) - 1;
		float tilePosY = nrow * tileSizeY + tileSizeX/2;
		return tilePosY;
	}
	
	public void setTileBounds() {
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
		
	@Override
	public void draw(Batch batch, float parentAlpha){
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		for(int i = 0; i < drawData.length; i++) {	//TODO: not sure if this drawing method is a smart idea
			int tileSpriteIndex = drawData[i];
			region = tileMap.get(tileSpriteIndex);	
			setTileBounds();
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
	}
	
	public byte[] getTileData() {
		return tileData;
	}
	
	public void setTileData(byte[] tileData) {
		this.tileData = tileData;
	}
	
	public byte[] getEntityData() {
		return entityData;
	}
	
	public void setEntityData(byte[] entityData) {
		this.entityData = entityData;
	}
	
	public byte[] getCollisionData() {
		return collisionData;
	}
	
	public void setCollisionData(byte[] levelData) {
		this.collisionData = levelData;
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
}

//TODO: dispose!!!
//ToSting method ;)