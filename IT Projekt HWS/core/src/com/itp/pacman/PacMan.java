package com.itp.pacman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PacMan extends ApplicationAdapter {
	
	Texture[] tilemap = new Texture[1];
	SpriteBatch batch;
	Texture img0, img1;
	
	int[][] map = new int[][] {
		{1, 0, 1, 1, 1, 0, 0},
		{1, 0, 0, 0, 0, 0, 1},
		{1, 0, 0, 0, 0, 0, 1},
		{1, 0, 0, 0, 0, 0, 1},
		{1, 0, 0, 0, 0, 0, 1},
		{1, 0, 0, 0, 0, 0, 1},
		{1, 0, 1, 1, 1, 0, 0}
	};
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img0 = new Texture("empty.jpg");
		img1 = new Texture("badlogic.jpg");
		tilemap = new Texture[] {img0,img1};
	}

	@Override
	public void render () {
		
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		for(int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[i].length; k++) {
				batch.draw(tilemap[map[i][k]], i*img0.getWidth(), k*img0.getHeight()); //img posx, posy
			}
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img1.dispose();
	}
}
