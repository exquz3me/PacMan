package com.itp.pacman.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itp.pacman.PacMan;

public class GameScreen extends ScreenAdapter implements InputProcessor {
	//other screens will exitend this class, code in here will apply to every other screen
	//-> add button and sub menu support
	
	private PacMan game; //a reference to the shared variables
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	//private BitmapFont font;
	private Stage stage;
	
	//Debug
	Sprite img;
	
	public GameScreen(PacMan game) {
		this.game = game;
	}
	
	
	@Override
	public void show() {
		batch = game.getBatch();
		camera = game.getCamera();
		viewport = game.getViewport();
		//font = game.getFont();
        Gdx.input.setInputProcessor(this);
		//add camera movement for debug
	    //setScreen(new XScreen(game));
		//add button support
	    
        stage = new Stage(); //not sure if this is good, maybe it should be moved to the consturcutor?
        stage.setViewport(viewport);
        //create actors here
        //add actor to a stage
        
		//Debug
	    img = new Sprite(new Texture("res.png"));
	    img.setPosition(0,0);
	    img.setSize(100,100);	    
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
		
		batch.setProjectionMatrix(viewport.getCamera().combined);
	    camera.update();
	    batch.begin();
        img.draw(batch);
	    //font.draw(batch, "Title Screen!", 10, 10);
        //font.draw(batch, "Press Enter to play.", 12, 12);
        batch.end();
	    		
       
        //stage.draw(); // batch not needed, stage has own. draw is called for every actor contained
		//render layers depend on code (last thing on top)
		//stage.act(); //calls the overridden act method in each actor
		//Gdx.graphics.getDeltaTime()
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
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
		batch.dispose();
		font.dispose();
	    img.getTexture().dispose();
	}
    
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ENTER) {
            //game.setScreen(new MenuScreen(game));
        	Gdx.app.log("Enter", null);
        }
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
