package com.itp.pacman.animator;

import java.util.HashMap;
import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationHandler { //Pack frames into one texture along with other sprites to optimize rendering. This is easily done with TexturePacker.
	//pass the sprite sheet to this class, break it down into textures, reurn a texture reagion
	//thos regions can be called via the name
	//use lib gdx texture packer to auto generate this atlas
	
	private Animation<AtlasRegion> frames;
	private float frameDuration;
	private float currentFrameTime;
	private float frameCount;
	
	private float timer;
	
	private int frame;
    private boolean looping;
    
	//new TextureReion(img, x, y, width, height) //with and height to cut out
	
	//a texture atlas is usefull if yor spritesheet has differntly sized sprites
	//a texture atlas contains named regions with individual offset and so on
	
	public AnimationHandler(TextureAtlas atlas, String regionName) {
		Array<AtlasRegion> atlasRegion = atlas.findRegions(regionName);
		frames = new Animation<>(frameDuration, atlasRegion);
		frameCount = atlasRegion.size;
		frameDuration = 1/3f; //3frames per second
		frames.setFrameDuration(frameDuration);
		looping = true;
		frame = 0;
	}
	
	public void update(float delta) {
		currentFrameTime += delta; //how long the current frame has been in view
		if(currentFrameTime > frameDuration){
			frame++;
   			currentFrameTime = 0;		
		}
		
		if(frame >= frameCount) {
			frame = 0;
		}
	}
	
	public TextureRegion getFrame(float elapsedTime) {	//returns the frame we are supposed to draw	
		return frames.getKeyFrame(elapsedTime, looping); //not sure with this return
		
	}
	
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	
	//---- different approach
	/*
	private float timer = 0;
    private boolean looping = true;
    private String current;
    private final HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();
 
    public void add(String name, Animation<TextureRegion> animation){
        animations.put(name, animation);
    }
 
    public void setCurrent(String name){
        if (Objects.equals(current, name)) return;
        assert (animations.containsKey(name)) : "No such animation " + name;
        current = name;
        timer = 0;
        looping = true;
    }
 
    public void setCurrent(String name, boolean looping){
        setCurrent(name);
        this.looping = looping;
    }
    public void setAnimationDuration(long duration){
        animations.get(current).setFrameDuration(duration / ((float) animations.get(current).getKeyFrames().length * 1000));
    }
 
    public boolean isCurrent(String name){
        return current.equals(name);
    }
    public boolean isFinished(){
        return animations.get(current).isAnimationFinished(timer);
    }
    public int frameIndex(){
        return animations.get(current).getKeyFrameIndex(timer);
    } 
    public TextureRegion getFrame2(){
        timer += Gdx.graphics.getDeltaTime();
        return animations.get(current).getKeyFrame(timer, looping);
    }
 
 
    @Override
    public String toString() {
        return "AnimationHandler{" +
                "timer=" + timer +
                ", looping=" + looping +
                ", current='" + current + '\'' +
                ", frame=" + animations.get(current).getKeyFrameIndex(timer) +
                '}';
    }
    */
}
