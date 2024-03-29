package com.itp.pacman.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.screens.MainScreen;

public class MainMenuStage extends GameStage { //contains what is actually beeing displayed
	private Table table;
	private TextButton startButton;
	
	private TextButton quitButton;
	
	public MainMenuStage(final PacMan game, Viewport viewport) {	//TODO: pool the screens, TODO: set Input focus
        super(game, viewport);

        table = new Table();
        table.setFillParent(true);
        table.center();
        
        startButton = new TextButton("New Game", skin);	
    	startButton.addListener(new ClickListener(){
    		@Override
    		public void clicked(InputEvent event, float x, float y) {		//TODO: final is needed becuase screen change is in an encloseing loop
    			game.setScreen(new MainScreen(game, game.getVirtualHeight(), game.getVirtualHeight())); //todo change
    			Gdx.app.log("Clicked", "start");
        		//hide current table, set visible another table
    		}
    	});
    	
    	quitButton = new TextButton("Quit", skin);	//window is an inependent table,	Window window = ..., window.add(...)  	
    	quitButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) { //depends on actor, slider = slide, button = click
				Gdx.app.exit();
				Gdx.app.log("Changed", "quit");
			}
    	});
    	
    	//table.setFillParent(true); //causing it to be sized to its parent (in this case, the stage) when validated
    	
    	//html table system
    	table.padTop(30);
    	table.add(startButton).padBottom(30).width(100); //this works because .add returns the table
    	table.row();
    	table.add(quitButton).width(100);
  
    	/*
        table.setX(100);
        table.setY(150);
    	table.setOrigin(0,0);
    	*/
    	
        table.debug();
        /*
    	final TextButton button = new TextButton("Click Me", skin, "default");
    	button.setWidth(200);
    	button.setHeight(50);
    	
    	final Dialog dialog = new Dialog("Click Message", skin);
    	button.addListener(new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			dialog.show(stage);
    			Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						dialog.hide();
					}
				},1.45f);
    			Gdx.app.log("Clicked", null);
    		}
    	});*/
        
    	addActor(table);
	}
}
