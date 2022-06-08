package com.itp.pacman.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;

public class GameScreen extends ScreenAdapter implements InputProcessor{ //contains how the screens should be handled
	protected final PacMan game;
	//in threory everything should be an list, to allow for multiple of each in one screen
	protected Stage stage;
	protected Batch batch;
	protected Camera camera;
	protected Viewport viewport;

	public GameScreen(PacMan game) {
		this.game = game;
	}

	@Override
	public void show() { //if you want to extend a method, do a super call then add changes
		viewport = game.getViewport();
		camera = stage.getCamera();
        batch = stage.getBatch();     
        InputMultiplexer im = new InputMultiplexer(stage, this); //combines input processors, order is important first is priority
        Gdx.input.setInputProcessor(im);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.0f, .0f, .0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
		//orthogonalTiledMapRenderer.render();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
	}
	
	@Override
	public void pause() {}
	
	@Override
	public void resume() {}
	
	@Override
	public void hide() {}
	
	@Override
	public void dispose () {	
		stage.dispose();
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
		// TODO Auto-generated method stub
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
	/*
	 Stage is an InputProcessor. When it receives input events, it fires them on the appropriate actors.
	 	If the stage is being used as a UI on top of other content (eg, a HUD), an InputMultiplexer can be used to first give the stage a chance to handle an event. 
	 The Actor class is a node in the graph which has a position, rectangular size, origin, scale, rotation, and color.
	 The Group class is an actor that may have child actors
	 The Stage class has a camera, SpriteBatch, and a root group and handles drawing the actors and distributing input events.
	 The stage’s viewport is determined by a Viewport instance.
	 The viewport manages a Camera and controls how the stage is displayed on the screen
	 The viewport also converts screen coordinates to and from stage coordinates.
	 * */
}
