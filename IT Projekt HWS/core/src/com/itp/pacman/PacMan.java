package com.itp.pacman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class PacMan extends ApplicationAdapter implements InputProcessor {
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
	Sprite img;
	OrthographicCamera camera;
	Viewport viewport;
	
	
	
	final float WORLD_WIDTH = 100;		//virtuall cooridniate system
	final float WORLD_HEIGHT = 100;		//https://sodocumentation.net/libgdx/topic/4219/supporting-multiple-resolutions
	
	BitmapFont font;
	ShapeRenderer shapeRenderer;
	Texture[] tilemap;
	float circleX = 200;
	float circleY = 100;
	
	Texture img0, img1;
	
    float r = MathUtils.random();
    float g = MathUtils.random();
    float b = MathUtils.random();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
	    img = new Sprite(new Texture("res.png"));
	    img.setPosition(0,0);
	    img.setSize(100,100);
	    
	    //float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
	    
	    camera = new OrthographicCamera();
	    viewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT,camera); //FitViewport seems reasonably good for our use
	    viewport.apply();
	    
		img0 = new Texture("empty.jpg");
		img1 = new Texture("badlogic.jpg");
		tilemap = new Texture[] {img0,img1};
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
		//this.setScreen(new GameScreen(this));
		
		
		/*Gdx.input.setInputProcessor(new InputAdapter(){
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
		});*/
		
	    Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		
	      Gdx.gl.glClearColor(1, 0, 0, 1);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	      camera.update();
	      batch.setProjectionMatrix(viewport.getCamera().combined);
	      batch.begin();
	      img.draw(batch);
	      
	      //have two viewportsm one for the game, one for mouse input
	      
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);						//layer 2	
			shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
			shapeRenderer.setColor(r, g, b, 1);
			shapeRenderer.circle(circleX, circleY, 25);
			
	      batch.end();
	      
	     
			shapeRenderer.end();
	    
		/*
		ScreenUtils.clear(1, 0, 0, 1);
		//polling type input

		
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
		
		batch.begin();	
		font.draw(batch, "PAC MAN", circleX, circleY);								//layer 1
		batch.end();
		*/
	}
	
	@Override
	public void dispose () {	
		shapeRenderer.dispose();
		batch.dispose();
		img1.dispose();
		font.dispose();
	    img.getTexture().dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	    camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	    Gdx.app.log("resized", null);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	      Gdx.app.log("Mouse Event","Click at " + screenX + "," + screenY);
	      Vector3 worldCoordinates = camera.unproject(new Vector3(screenX,screenY,0));
	      Gdx.app.log("Mouse Event","Projected at " + worldCoordinates.x + "," + worldCoordinates.y);
	      
          r = MathUtils.random();
          g = MathUtils.random();
          b = MathUtils.random();
          
          circleX = worldCoordinates.x;
          circleY = worldCoordinates.y;
          
	      Gdx.app.log("circleX", ""+ circleX);
	      Gdx.app.log("circleY", ""+ circleY);
          
	      return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
