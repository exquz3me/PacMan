package com.itp.pacman.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

//box 2d feels overkill
public class B2dModel {	//works in addition to screens
	public World world;	//will hold all the physical objects in game
	private Body bodyd;
	private Body bodys;
	private Body bodyk;
	
	public B2dModel(){
		world = new World(new Vector2(0,-10f), true);
		createFloor();
		createObject();
		createMovingObject();
	}
	
	public void logicStep(float delta){ //tell box2d when tomove forward in time
		world.step(delta , 3, 3);
	}
	
	private void createFloor() {	 
		// create a new body definition (type and location)
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(0, 0);
 
		// add it to the world
		bodyd = world.createBody(bodyDef);
 
		// set the shape (here we use a box 50 meters wide, 1 meter tall )
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50, 1);
 
		// create the physical object in our body)
		// without this our body would just be data in the world
		bodyd.createFixture(shape, 0.0f);
 
		// we no longer use the shape object here so dispose of it.
		shape.dispose();
	}
	
    private void createObject(){
    	//create a new body definition (type and location)
    	BodyDef bodyDef = new BodyDef();
    	bodyDef.type = BodyDef.BodyType.DynamicBody;
    	bodyDef.position.set(4,10);

    	

    	// add it to the world
    	bodys = world.createBody(bodyDef);
    	//bodys.setGravityScale(0);
    	// set the shape (here we use a box 50 meters wide, 1 meter tall )
    	PolygonShape shape = new PolygonShape();
    	shape.setAsBox(1,1);

    	// set the properties of the object ( shape, weight, restitution(bouncyness)
    	FixtureDef fixtureDef = new FixtureDef();
    	fixtureDef.shape = shape;
    	fixtureDef.density = 1f;

    	// create the physical object in our body)
    	// without this our body would just be data in the world
    	bodys.createFixture(shape, 0.0f);

    	// we no longer use the shape object here so dispose of it.
    	shape.dispose();
    }


    private void createMovingObject(){
    	
    	//create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(2,8);
        
        // add it to the world
        bodyk = world.createBody(bodyDef);
     
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);
     
        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
     
        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyk.createFixture(shape, 0.0f);
     
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
        
        bodyk.setLinearVelocity(0.5f, -0.75f);
    }
}
