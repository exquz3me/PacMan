package com.itp.pacman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.itp.pacman.PacMan;
import com.itp.pacman.animator.AnimationManager;
import com.itp.pacman.levels.GameLevel;
public class Ghost extends GameActor{
	private AnimationManager animationHandler;	//Todo: these are not handlers, they are Managers

	private GameLevel level;
	//TODO: AssetManager to handle assets properly
	
	private float playerSpeed = 0.53f;
	private float animationSpeed = 0.04f;	//TODO: player move anim speed is based on movespeed!!
	
	private int wishDir_x = 0;	//TODO: convert to vector2?
	private int wishDir_y = 0;
	private int moveDir_x = 0;
	private int moveDir_y = 0;

	private int eyeDir = 0;
	
	private int prevIndex;
	private float animationTimer;		//TODO: set/get
	private float graceX = 0.6f;
	private float graceY = 0.6f;
	private Color bodyColor;
	
	private boolean isEatable = false;
	
	public Ghost(PacMan game, Color color) {	//multiple ghost classes? or a ghostmanager? both? ;)
		super(game);
		this.bodyColor = color;
		level = game.getLevel();
			
		animationHandler = new AnimationManager();
		animationHandler.add(atlas, "run", animationSpeed);	//TODO: load correct animations
        animationHandler.set("run", true);
        
		setRegion(atlas.getRegions().first());
		setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight());  //dont forget, ghosts are bigger than 1 tile
		setOrigin(getWidth()/2, getHeight()/2);
		setColor(color);
	}
	
	@Override
	public void act(float delta) {

	}
	
	@Override
	public void moveLogic(int index) {
		if(moveDir_y == 1)
			eyeDir = 0;
		else if(moveDir_x == 1)
	        eyeDir = 1;       
		else if(moveDir_y == -1)
	        eyeDir = 2;      
		else if(moveDir_x == -1)
	        eyeDir = 3;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		batch.setColor(255, 255, 255, 255 * parentAlpha);
		
		if(isEatable == false) {
			region = atlas.findRegions("run").get(eyeDir);
			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX()/2, getScaleY()/2, getRotation());
		}
	}
}

//individual ghosts will extend this class
//TODO: all ghosts will be white, the color will be set in draw, the eyes are sperate child actors of the white ghost
//the eyes are not really animation, they change states depending on the movement direction -> movedir == eyedir
