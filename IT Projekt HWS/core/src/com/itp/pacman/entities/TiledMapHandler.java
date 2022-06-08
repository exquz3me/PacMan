package com.itp.pacman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledMapHandler extends GameActor{
	//the player srpite is slighly bigger than one cell
	
	private TiledMap tiledMap;
	
	//object layers in tiled are polygon objects than can be referenced for collision
	
	public TiledMapHandler() {
		
	}
	
	public OrthogonalTiledMapRenderer setupMap() {
		tiledMap = new TmxMapLoader().load(Gdx.files.internal("testmap.tmx").file().getAbsolutePath());
		return new OrthogonalTiledMapRenderer(tiledMap);
	}
	
	public TiledMap getTiledMap() {
		return tiledMap;
	}
}
