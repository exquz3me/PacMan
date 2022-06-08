package com.itp.pacman.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.itp.pacman.PacMan;
import com.itp.pacman.box2d.B2dModel;
import com.itp.pacman.entities.Player;
import com.itp.pacman.entities.TiledMapHandler;
import com.itp.pacman.levels.ClassicLevel;
import com.itp.pacman.levels.GameLevel;
import com.itp.pacman.stages.MainStage;

public class MainScreen extends GameScreen{
	B2dModel model;
	Box2DDebugRenderer debugRenderer;
	
	TextureAtlas atlas; //there is only one atlas, it is loaded on nessecary screens
						//remove b2d -> too much for our use
	
	
	
	protected OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
	protected TiledMapHandler tiledMapHandler;
	
	public MainScreen(PacMan game) {
		super(game);
		stage = new MainStage(game);
		atlas = new TextureAtlas(Gdx.files.internal("testing.atlas")); //load atlas
		model = new B2dModel();
		debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
		Player player = new Player();
		stage.addActor(player);
		
		GameLevel level = new GameLevel(16, 16, 8, 8, 1);
		stage.addActor(level);
		
        tiledMapHandler = new TiledMapHandler();
        orthogonalTiledMapRenderer = tiledMapHandler.setupMap();
        
        MapObjects objects = tiledMapHandler.getTiledMap().getLayers().get("walls").getObjects();
        MapProperties prop = tiledMapHandler.getTiledMap().getProperties();
        /*
        for(MapObject object : objects) {
        	RectangleMapObject tmpObject = (RectangleMapObject)object;
        	Rectangle rectangle = tmpObject.getRectangle();
        	PolygonShape shape = new PolygonShape();
        	float objectWidth = rectangle.x/16 + rectangle.width/16 * 0.5f; //center point
        	float objectHeight = rectangle.y/16 + rectangle.height/16 * 0.5f; //center point
        	Vector2 size = new Vector2(objectWidth, objectHeight);
        	float hx = rectangle.width/16 * 0.5f;
        	float hy = rectangle.height/16 * 0.5f;
        	shape.setAsBox(hx, hy, size, 0);
        	
        	BodyDef bodyDef = new BodyDef();
        	bodyDef.type = BodyType.StaticBody;
        	Body body = world.createBody(bodyDef);
        	body.createFixture(shape, 1);
        	
        	shape.dispose();
        }*/
	}
	
	@Override
	public void render(float delta) {
		model.logicStep(delta); //add if statement, to pause game
		super.render(delta);
		
		//orthogonalTiledMapRenderer.setView((OrthographicCamera) camera);	//add the tiled map as an actor to a stage
		//orthogonalTiledMapRenderer.render();
		debugRenderer.render(model.world, camera.combined);
	}
}
