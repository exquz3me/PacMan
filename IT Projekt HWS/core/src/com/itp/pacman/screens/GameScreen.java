package com.itp.pacman.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;
import com.itp.pacman.entities.MyActor;
import com.itp.pacman.stages.StartStage;

public class GameScreen extends ScreenAdapter{
	final private PacMan game; //a reference to the shared variables
	private Stage stage;
	private Batch batch;
	private Camera camera;
	private Viewport viewport;
	
	public GameScreen(PacMan game) {
		this.game = game;
		viewport = game.getViewport();
	}
	
	
	@Override
	public void show() {
		//batch = game.getBatch();
		//camera = game.getCamera(); 		//add camera movement for debug
		
		//font = game.getFont();
	    //setScreen(new XScreen(game));
		//add button support
	    
		stage = new StartStage(viewport);
        Gdx.input.setInputProcessor(stage); //the stage is an input processor on its own
        camera = stage.getCamera();
        batch = stage.getBatch();
        MyActor actor = new MyActor(batch);
        stage.addActor(actor); //stage is a reference container
        stage.setKeyboardFocus(actor); //actor recieves keyboard events
        //NOTE: if you change stages, you have to set the inputprocessor accrodingly
        
        //create actors here
        //add actor to a stage
        
		/*
        //Debug
	    img = new Sprite(new Texture("res.png"));
	    img.setPosition(0,0);
	    img.setSize(100,100);	
	    */    
	}
	
	//float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    //it should be the fit viewport, you should only be able to resize the screen in the same apscet ratio
	
	//viewport = new FitViewport(game.WORLD_WIDTH, game.WORLD_HEIGHT,camera);  //this screens individual viewport
    //game.setViewport(viewport);
    //viewport.apply();

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
		
		/*
		batch.setProjectionMatrix(viewport.getCamera().combined);
	    camera.update();
	    batch.begin();
        img.draw(batch);
	    //font.draw(batch, "Title Screen!", 10, 10);
        //font.draw(batch, "Press Enter to play.", 12, 12);
        batch.end();
	    */
		
		//normal batch call or other stage here -- next one will be on top "menu"
		
        stage.act(Gdx.graphics.getDeltaTime());
		stage.draw(); // batch not needed, stage has own. draw is called for every actor contained
        //when changing screen make a dispose call
		
        //render layers depend on code (last thing on top)
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true); //Passing true when updating the viewport changes the camera position so it is centered on the stage
	    //When managing the camera position yourself, pass false or omit the boolean. If the stage position is not set, by default 0,0 will be in the center of the screen.
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
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
	public void dispose () {	
		stage.dispose();
	    //img.getTexture().dispose();
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
