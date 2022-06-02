package com.itp.pacman.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.itp.pacman.PacMan;
import com.itp.pacman.box2d.B2dModel;
import com.itp.pacman.entities.Player;
import com.itp.pacman.stages.MainStage;

public class MainScreen extends GameScreen{
	B2dModel model;
	Box2DDebugRenderer debugRenderer;
	
	TextureAtlas atlas; //there is only one atlas, it is loaded on nessecary screens
	
	
	public MainScreen(PacMan game) {
		super(game);
		stage = new MainStage(game);
		atlas = new TextureAtlas(Gdx.files.internal("testing.atlas")); //load atlas
		model = new B2dModel();
		debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
		Player player = new Player(atlas);
		stage.addActor(player);
	}
	
	@Override
	public void render(float delta) {
		model.logicStep(delta); //add if statement, to pause game
		super.render(delta);
		debugRenderer.render(model.world, camera.combined);
	}
}
