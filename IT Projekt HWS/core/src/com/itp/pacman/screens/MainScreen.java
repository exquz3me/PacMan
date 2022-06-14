package com.itp.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.stages.GameStage;
import com.itp.pacman.stages.MainStage;
import com.itp.pacman.stages.UIStage;

public class MainScreen extends GameScreen{
	private GameStage uiStage;
	private Viewport uiViewport;
	
	//TODO: set screenwidth and height to int
	public MainScreen(PacMan game, int screenWidth, int screenHeight) {	
		super(game, screenWidth, screenHeight);
		viewport = new ExtendViewport(screenWidth, screenHeight, camera);	//the main viewport	
		stage = new MainStage(game, viewport); //the stage
		
		//ui has own viewport
		uiViewport = new FitViewport(screenWidth, screenHeight, camera); //the ui viewport (same size)
		uiStage = new UIStage(game, uiViewport);
		
		uiStage.setActive(false);
		//TODO: both stages should use the same viewport in theory
	}
	
	@Override
	public void show() {
		super.show();
        uiStage.setViewport(uiViewport);
        inputMultiplexer.addProcessor(uiStage);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		uiStage.getBatch().setProjectionMatrix(camera.combined);
		uiStage.getViewport().apply();
		uiStage.draw();	
	}	//uiStage1.getViewport().setScreenBounds(500, 0, 120, 120);	//TODO: find out where to put this thing so that it reacts, currntly this is the only place
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		uiStage.getViewport().update(width, height, false);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE) {
			game.setScreen(new MainMenuScreen(game, game.getVirtualWidth(), game.getVirtualHeight()));
		}
		return true; //TODO: what does this boolean mean?
	}
}