package com.itp.pacman.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.itp.pacman.PacMan;

public class GameScreen implements Screen{

	Texture img;
	float xpos;
	float ypos;
	float speed = 40;
	
	PacMan game;
	
	public GameScreen(PacMan game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		
		//delta time is the time between frames
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			ypos += speed * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			ypos -= speed * Gdx.graphics.getDeltaTime();
		}
	
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			xpos += speed * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			xpos -= speed * Gdx.graphics.getDeltaTime();
		}	

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(0);
		game.batch.begin();
		game.batch.draw(img, xpos, ypos); //image, x cord, y cord
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
