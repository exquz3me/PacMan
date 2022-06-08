package com.itp.pacman.animator;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationHandler { //Pack frames into one texture along with other sprites to optimize rendering. This is easily done with TexturePacker.
	//BUG: animation stops while in window resize, same with B2D
	
	private HashMap<String, Animation<AtlasRegion>> animations = new HashMap<>();
    private String currentAnim;
	private float timer;
    private boolean looping;

    public void add(TextureAtlas atlas, String regionName, float frameDuration){
		Array<AtlasRegion> atlasRegion = atlas.findRegions(regionName);
		Animation<AtlasRegion> frames = new Animation<>(frameDuration, atlasRegion);
    	animations.put(regionName, frames);
    }
    
    public void set(String anim, boolean looping) {
    	this.currentAnim = anim;
    	this.looping = looping;
    	timer = 0;
    } 
    
	public TextureRegion getFrame() {	
        timer += Gdx.graphics.getDeltaTime();
		return animations.get(currentAnim).getKeyFrame(timer, looping);
	}
	
    public boolean isFinished(){
        return animations.get(currentAnim).isAnimationFinished(timer);
    }
    
    public int frameIndex(){
        return animations.get(currentAnim).getKeyFrameIndex(timer);
    } 
    
    @Override
    public String toString() {
        return "AnimationHandler{" +
                "timer=" + timer +
                ", looping=" + looping +
                ", current='" + currentAnim +
                ", frame=" + animations.get(currentAnim).getKeyFrameIndex(timer) +
                '}';
    }
    
	//TODO: dispose?
}
