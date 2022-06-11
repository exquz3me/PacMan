package com.itp.pacman.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.itp.pacman.PacMan;

public class StartStage extends GameStage{	//TODO: make it pretty
	private Table table;
	private Table table1;
	private Table table2;
	
	public StartStage(PacMan game) {
		super(game);
		
		table = new Table();
        table.setFillParent(true);
        table.center();
        
        Texture title = new Texture("badlogic.jpg");
        Image titleImg = new Image(title); //title image
        table.add(titleImg).align(Align.top).width(100).height(50); //use screen size instead of full values
        table.row();
			table1 = new Table();
			table1.center(); 
			
			Label ghost1 = new Label("1", skin);
			Label ghost1text = new Label("text", skin);
			Label ghost2 = new Label("2",skin);
			Label ghost2text = new Label("text", skin);
			Label ghost3 = new Label("3", skin);
			Label ghost3text = new Label("text", skin);
			Label ghost4 = new Label("4",skin);
			Label ghost4text = new Label("text", skin);
			
			table1.add(ghost1);
			table1.add(ghost1text);
			table1.row();
			table1.add(ghost2);
			table1.add(ghost2text);
			table1.row();
			table1.add(ghost3);
			table1.add(ghost3text);
			table1.row();
			table1.add(ghost4);
			table1.add(ghost4text);  
        table.add(table1);
        table.row();
        
        Label anim = new Label("anim", skin);
        table.add(anim);
        table.row();
        
			table2 = new Table();
			table2.center();
			Label pellet = new Label("1", skin);
			Label pelletText = new Label("text", skin);
			Label powerPellet = new Label("2",skin);
			Label powerPelletText = new Label("text", skin);
			
			table2.add(pellet);
			table2.add(pelletText);
			table2.row();
			table2.add(powerPellet);
			table2.add(powerPelletText);
		table.add(table2);
		table.row();
		
        Label text = new Label("- Press Any Key To Start -", skin);
		table.add(text);
		
        table.debug(); 
        table1.debug(); 
        table2.debug(); 
    	addActor(table);
	}
}

