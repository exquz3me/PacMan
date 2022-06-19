package com.itp.pacman.animation;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationManager {
	private HashMap<String, Animation<AtlasRegion>> animations = new HashMap<>();
	private String currentAnim;
	private float timer;
    private boolean looping;
    private boolean play = false;

    public Animation<AtlasRegion> add(TextureAtlas atlas, String regionName, float frameDuration) {
		Array<AtlasRegion> atlasRegion = atlas.findRegions(regionName);
		Animation<AtlasRegion> animation = new Animation<>(frameDuration, atlasRegion);;
    	animations.put(regionName, animation);
    	return animation;
    }
    
    public void remove(String animationName) {
    	animations.remove(animationName);
    }
      
	public TextureRegion getCurrentFrame() {
		if(play) {
			timer += Gdx.graphics.getDeltaTime();	
		}

		return animations.get(currentAnim).getKeyFrame(timer, looping);
	}
    
    public int getCurrentFrameIndex(){
        return animations.get(currentAnim).getKeyFrameIndex(timer);
    } 
	
	public float getCurrentFrameDuration() {
		return animations.get(currentAnim).getFrameDuration();
	}
	
	public void setCurrentFrameDuration(float frameDuration) {
		animations.get(currentAnim).setFrameDuration(frameDuration);
	}
	
	public float getCurrentAnimationDuration() {
		return animations.get(currentAnim).getAnimationDuration();
	}
	
    public boolean isCurrentFinished(){
        return animations.get(currentAnim).isAnimationFinished(timer);
    }
	
	public void setCurrentPlayMode(PlayMode playMode) {
		animations.get(currentAnim).setPlayMode(playMode);
	}
	
	public PlayMode getCurrentPlayMode() {
		return animations.get(currentAnim).getPlayMode();
	}
    
    public Animation<AtlasRegion> getCurrentAnim() {
    	return animations.get(currentAnim);
	}
    
	public void setCurrentAnim(String currentAnim) {
		this.currentAnim = currentAnim;
    	timer = 0;
	}
    
    public int getFrameIndexByName(String regionName){
        return animations.get(regionName).getKeyFrameIndex(timer);
    } 
	
	public float getFrameDurationByName(String regionName) {
		return animations.get(regionName).getFrameDuration();
	}
	
	public void setFrameDurationByName(String regionName, float frameDuration) {
		animations.get(regionName).setFrameDuration(frameDuration);
	}
	
	public float getAnimationDurationByName(String regionName) {
		return animations.get(regionName).getAnimationDuration();
	}
	
    public boolean isFinishedByName(String regionName){
        return animations.get(regionName).isAnimationFinished(timer);
    }
	
	public void setPlayModeByName(String regionName, PlayMode playMode) {
		animations.get(regionName).setPlayMode(playMode);
	}
	
	public PlayMode getPlayModeByName(String regionName) {
		return animations.get(regionName).getPlayMode();
	}
    
    public Animation<AtlasRegion> getAnimationByName(String regionName) {
    	return animations.get(regionName);
    }

	public float getTimer() {
		return timer;
	}

	public void setTimer(float timer) {
		this.timer = timer;
	}

	public boolean getLooping() {
		return looping;
	}

	public void setLooping(boolean looping) {
		this.looping = looping;
	}

	public boolean getPlay() {
		return play;
	}

	public Animation<AtlasRegion> play(boolean play) {
		this.play = play;
		return animations.get(currentAnim);
	}
}
