package com.itp.pacman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.InputAdapter;

public class PacMan extends ApplicationAdapter {
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
	//extends Game
	
	public SpriteBatch batch;
	BitmapFont font;
	ShapeRenderer shapeRenderer;
	Texture[] tilemap;
	float circleX = 200;
	float circleY = 100;
	
    float r = MathUtils.random();
    float g = MathUtils.random();
    float b = MathUtils.random();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img0 = new Texture("empty.jpg");
		img1 = new Texture("badlogic.jpg");
		tilemap = new Texture[] {img0,img1};
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
		//this.setScreen(new GameScreen(this));
		
		Gdx.input.setInputProcessor(new InputAdapter(){
			@Override
			public boolean keyTyped (char key) {
                r = MathUtils.random();
                g = MathUtils.random();
                b = MathUtils.random();
				return true;
			}
			
			@Override
			public boolean touchDown (int x, int y, int pointer, int button) {
                r = MathUtils.random();
                g = MathUtils.random();
                b = MathUtils.random();
				return true;
			}
		});
	}

	@Override
	public void render () {
		
		ScreenUtils.clear(1, 0, 0, 1);
		
		//polling type input
		if (Gdx.input.isTouched()) {
			circleX = Gdx.input.getX();
			circleY = Gdx.graphics.getHeight() - Gdx.input.getY();
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			circleY++;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			circleY--;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			circleX++;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			circleX--;
		}
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		//imput event handler for single action keys that would be cumbersome to poll
		
		//render layers depend on code (last thing on top)
			
		batch.begin();	
		for(int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[i].length; k++) {
				batch.draw(tilemap[map[i][k]], i*img0.getWidth(), k*img0.getHeight()); //layer 3
			}
		}	
		batch.end();
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);						//layer 2	
		shapeRenderer.setColor(r, g, b, 1);
		shapeRenderer.circle(circleX, circleY, circleX * .25f);
		shapeRenderer.end();
		
		batch.begin();	
		font.draw(batch, "PAC MAN", circleX, circleY);								//layer 1
		batch.end();
	}
	
	@Override
	public void dispose () {	
		shapeRenderer.dispose();
		batch.dispose();
		img1.dispose();
		font.dispose();
	}
}
