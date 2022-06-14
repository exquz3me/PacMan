package com.itp.pacman.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.screens.MainScreen;

public class UIStage extends GameStage{
	private Table table;	
	private Label pointCounter;
	private Label highscore;
	private Label highscoreLabel;
	private Label live1;
	private Label live2;
	private Label live3;
	private Label oneUpLabel;
	private Label fruit;	//no idea what the fruits are for
	private Label soundButton;
	//when you score 10000
	private TextButton quitButton;
	private TextButton quitButtonYes;
	private TextButton quitButtonNo;
	
	private String score = "000000";
	private String highScore = "000000";
	
	private float botPad = 325;
	private float distance = 20;
	
	public UIStage(final PacMan game, Viewport viewport) {
		super(game, viewport);
		table = new Table();
    	table.setTransform(true);
    	table.setFillParent(true);
    	table.setPosition(viewport.getWorldWidth()/2 - 35, viewport.getWorldHeight()/2 - 80);
    	table.setScale(0.55f, 0.55f);


		
        pointCounter = new Label(score, skin);
        highscoreLabel = new Label("Highscore", skin);
        highscore = new Label(highScore, skin);
        
        live1 = new Label("live1", skin);
        live2 = new Label("live2", skin);
        live3 = new Label("live3", skin);
        
        fruit = new Label("fruit", skin);
        oneUpLabel = new Label("1 UP", skin);
        soundButton = new Label("sound", skin);
        
    	pointCounter.addListener(new ClickListener(){
    		
    	@Override
    	public void clicked(InputEvent event, float x, float y) {		//TODO: final is needed becuase screen change is in an encloseing loop
    			game.setScreen(new MainScreen(game, 12, 12)); //todo change
    			Gdx.app.log("Clicked", "start");
        		//hide current table, set visible another table
    		}
    	});
    	
    	quitButton = new TextButton("Quit", skin);	//window is an inependent table,	Window window = ..., window.add(...)  	
    	quitButtonYes = new TextButton("Yes", skin);
    	quitButtonNo = new TextButton("No", skin); 
    	quitButtonYes.setVisible(false);			//set children visible?
    	quitButtonNo.setVisible(false);
    	quitButton.add(quitButtonYes);
    	quitButton.add(quitButtonNo);
    	
    	quitButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) { //depends on actor, slider = slide, button = click
				quitButtonNo.setVisible(true);
				quitButtonYes.setVisible(true);
				Gdx.app.log("Changed", "quit");
			}
    	});
    	
    	quitButtonYes.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) { //depends on actor, slider = slide, button = click
				quitButtonNo.setVisible(false);
				quitButtonYes.setVisible(false);
				quitButton.setVisible(true);
				Gdx.app.log("Changed", "quit yes");
			}
    	});
    	
    	quitButtonNo.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) { //depends on actor, slider = slide, button = click
				quitButtonNo.setVisible(false);
				quitButtonYes.setVisible(false);
				quitButton.setVisible(true);
    			Gdx.app.log("Changed", "quit no");
			}
    	});
    	 	
		Table liveTable = new Table();
		liveTable.add(live1);	//TODO: replace with image width
		liveTable.add(live2);
		liveTable.add(live3);
		
		Table fruitTable = new Table();
		fruitTable.add(fruit).align(Align.center);
		
		Table scoreTable = new Table();
		scoreTable.add(oneUpLabel).align(Align.center);
		scoreTable.row();
		scoreTable.add(pointCounter).align(Align.center);
		
		Table highscoreTable = new Table();
		highscoreTable.add(highscoreLabel).align(Align.center);
		highscoreTable.row();
		highscoreTable.add(highscore).align(Align.center);
		
    	table.align(Align.center);	//posssibly have to align center
    	table.add(scoreTable).padBottom(botPad);
    	table.add(highscoreTable).padBottom(botPad);
    	table.add(soundButton).padBottom(botPad).align(Align.center);											//sound button
    	table.row();
    	table.add(liveTable);
    	table.add();
    	table.add(fruitTable);
 
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
