package com.itp.pacman.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.itp.pacman.entities.GameActor;

//i dont think extending GameActor is worth it tbh
public class GameLevel extends GameActor{	//TODO: we could pass down the tileData and itemData from a different place
	protected Array<AtlasRegion> tileMap;
	protected LevelHandler levelHandler;
	protected byte[] tileData;
	protected byte[] itemData;				//idk maybe we will have to handle items differently
	protected byte[] levelData;
	protected int fieldSizeX;
	protected int fieldSizeY;
	protected int tileSizeX;
	protected int tileSizeY;
	protected float scale;
	
	
	public int index;
	
	public GameLevel(int filedSizeX, int fieldSizeY, int tileSizeX, int tileSizeY,float scale) {		
		this.fieldSizeX = filedSizeX;
		this.fieldSizeY = fieldSizeY;
		this.tileSizeX = tileSizeX;
		this.tileSizeY = tileSizeY;
		this.scale = scale;
		levelData = new byte[filedSizeX * fieldSizeY];	
		initLevel();
		levelHandler = new LevelHandler(this);
		levelHandler.initData();
		tileMap = atlas.findRegions("run");	//TODO: set the atlas outside of this class (in stage or screen)
		index = levelHandler.getArrayPos(16 + tileSizeX/2, 16 + tileSizeY/2); 
		Gdx.app.log("test", "index:" + index);

	}

	public void initLevel() {	//TODO: remove the initialisation, its is only done by the responsible classes
		tileData = new byte[]{
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7,
				0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7
				
		};

		itemData = new byte[] {
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
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		for(int tileSpriteIndex : tileData) {
			region = tileMap.get(tileSpriteIndex);
			levelHandler.setTileBounds(scale);	
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
	}
	
	public LevelHandler getLevelHandler() {
		return levelHandler;
	}
	
	public TextureRegion getRegion() {
		return region;
	}
	
	public void setRegion(TextureRegion region) {
		this.region = region;
	}
	
	public int getTileSizeX() {
		return tileSizeX;
	}
	
	public int getTileSizeY() {
		return tileSizeY;
	}
	
	public byte[] getTileData() {
		return tileData;
	}
	
	public byte[] getItemData() {
		return itemData;
	}
	
	public byte[] getLevelData() {
		return levelData;
	}
	
	public int getSizeX() {
		return fieldSizeX;
	}
	
	public int getSizeY() {
		return fieldSizeY;
	}
}
//TODO: dispose!!!
