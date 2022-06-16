package com.itp.pacman.sound;

import java.util.HashMap;
import com.badlogic.gdx.audio.Sound;

public class SoundManager{
	private HashMap<String, Sound> sounds = new HashMap<>();
	private HashMap<Long, Sound> instances = new HashMap<>();
	
	private float volume;
	private boolean looping;
	private float pitch;
	private float pan;
	
	private long soundId;
	private Sound sound = instances.get(soundId);
	
    public void add(String soundName, Sound sound){
    	sounds.put(soundName, sound);
    }
    
    public void play(String soundName, float volume, boolean looping, float pitch, float pan) {
    	Sound sound = sounds.get(soundName);
    	this.volume = volume;
    	this.looping = looping;
    	this.pitch = pitch;
    	this.pan = pan;   	
    	soundId = sound.play(volume);
    	sound.setLooping(soundId, looping);
    	sound.setPitch(soundId, pitch);
    	sound.setPan(soundId, pan, volume);
    	instances.put(soundId, sound);
    } 
    
    public void play(String soundName) {
    	this.play(soundName, volume, looping, pitch, pan);
    }
    
    public void stop() {
    	sound.stop(soundId);
    }
    
    public void pause() {
    	sound.pause(soundId);
    }
    
    public void resume() {
    	sound.resume(soundId);
    }
    
    public void loop() {
    	sound.loop(soundId);
    }
    
    public float getVolume() {
    	return volume;
    }
    
    public void setVolume(float volume) {
    	sound.setVolume(soundId, volume);
    	this.volume = volume;
    }
    
    public float getPitch() {
    	return pitch;
    }
    
    public void setPitch(float pitch) {
    	sound.setPitch(soundId, pitch);
    	this.pitch = pitch;
    }
    
    public float getPan() {
    	return pan;
    }
    
    public void setPan(float pan) {
    	sound.setPan(soundId, pan, volume);
    	this.pan = pan;
    }
    
    public long getSoundId() {
    	return soundId;
    }

    public long setSoundId() {
    	return soundId;
    }  
    
    public Sound getSoundByName(String soundName) {
    	return sounds.get(soundName);
    }
    
    public Sound getSoundBySoundId(long soundId) {
    	return instances.get(soundId);
    }
}
