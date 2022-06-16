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
    private boolean tiledMode = false;

    public void add(TextureAtlas atlas, String regionName, float frameDuration){
		Array<AtlasRegion> atlasRegion = atlas.findRegions(regionName);
		Animation<AtlasRegion> animation = new Animation<>(frameDuration, atlasRegion);;
    	animations.put(regionName, animation);
    }
      
	public TextureRegion getFrame() {
		if(play) {
			if(tiledMode) {
				timer += animations.get(currentAnim).getFrameDuration();	
			} else {
				timer += Gdx.graphics.getDeltaTime();	
			}
		}

		return animations.get(currentAnim).getKeyFrame(timer, looping);
	}
	
    public int getFrameIndex(){
        return animations.get(currentAnim).getKeyFrameIndex(timer);
    } 
	
	public float getFrameDuration() {
		return animations.get(currentAnim).getFrameDuration();
	}
	
	public void setFrameDuration(float frameDuration) {
		animations.get(currentAnim).setFrameDuration(frameDuration);
	}
	
	public float getAnimationDuration() {
		return animations.get(currentAnim).getAnimationDuration();
	}
	
    public boolean isFinished(){
        return animations.get(currentAnim).isAnimationFinished(timer);
    }
	
	public void setPlayMode(PlayMode playMode) {
		animations.get(currentAnim).setPlayMode(playMode);
	}
	
	public PlayMode getPlayMode() {
		return animations.get(currentAnim).getPlayMode();
	}
    
    public String getCurrentAnim() {
		return currentAnim;
	}

	public void setCurrentAnim(String currentAnim) {
		this.currentAnim = currentAnim;
    	timer = 0;
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

	public void setPlay(boolean play) {
		this.play = play;
	}
	
	public boolean getTiledMode() {
		return tiledMode;
	}

	public void setTiledMode(boolean tiledMode) {
		this.tiledMode = tiledMode;
	}
	
    public Animation<AtlasRegion> getAnimationByName(String regionName) {
    	return animations.get(regionName);
    }
}
