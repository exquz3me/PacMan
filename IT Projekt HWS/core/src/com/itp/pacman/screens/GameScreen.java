package com.itp.pacman.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.stages.GameStage;

public class GameScreen extends ScreenAdapter implements InputProcessor{	//TODO: multiple stage / viewport / camera support
	protected final PacMan game;
	protected TextureAtlas atlas;
	protected Skin skin;
	protected int screenWidth;
	protected int screenHeight;
	protected Viewport viewport;
	protected OrthographicCamera camera;
	protected GameStage stage;
	protected InputMultiplexer inputMultiplexer;

	public GameScreen(PacMan game, int screenWidth, int screenHeight) {
		this.game = game;
		this.atlas = game.getAtlas();
		this.skin = game.getSkin();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		camera = new OrthographicCamera(screenWidth, screenHeight);
		viewport = new ScreenViewport(camera);
	}
	@Override
	public void show() {
        stage.setViewport(viewport);
        inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		camera.update();
		stage.getBatch().setProjectionMatrix(camera.combined);	
		Gdx.gl.glClearColor(.0f, .0f, .0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
		stage.act(delta);
		stage.getViewport().apply();
		stage.draw();
	}			//when having multiple stages, apply the viewport first before drawing the sevcond one

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
		stage.getViewport().getCamera().position.set(game.getLevel().getTotalSizeX()/2, game.getLevel().getTotalSizeY()/2, 0);
		stage.getCamera().update();
	}
	
	@Override
	public void dispose () {	
		stage.dispose();
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
	
	public float getScreenWidth() {
		return screenWidth;
	}
	
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	public float getScreenHeight() {
		return screenHeight;
	}
	
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	public GameStage getStage() {
		return stage;
	}
	
	public void setStage(GameStage stage) {
		this.stage = stage;
	}
}
