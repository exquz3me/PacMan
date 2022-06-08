package com.itp.pacman.sound;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundHandler {
	//play every sound in loading screen to get the ids?
	//ogg is not supported on ios, WAV files is the way to go
	
	//maniuplating the sound object, manipulates all played instances, id manipulates specific instance
	
	private HashMap<String, Sound> sounds = new HashMap<>();
	private HashMap<Long, Sound> instances = new HashMap<>();
	private String soundName;
	private boolean looping;
	private long id;
	
	//changaes and method calls will only apply to the newest sound
	
	//playing a sound returns a long for this sound instance, using this instance will allow to modify the playback
	
    public void add(String soundName, Sound sound){
    	sounds.put(soundName, sound);
    }
    
    public void play(String soundName, float volume, boolean looping) {
    	Sound sound = sounds.get(soundName);
     	id = sound.play(volume); //multiple ids are usefull if we play more than one of the same sound
    	instances.put(id, sound);
    	sound.setLooping(id, looping);
    	this.looping = looping;
    	this.soundName = soundName;
    	Gdx.app.log("SoundHandler", this.toString());
    } 
    
    public void stop(long id) {
    	Sound sound = instances.get(id);
    	sound.stop();
    }
    
    public void pause(long id) {
    	Sound sound = instances.get(id);
    	sound.pause(id);
    }
    
    public void pitch(long id, float pitch) {
    	Sound sound = instances.get(id);
    	sound.setPitch(id, pitch); 
    }
    
    public void pan(long id, float pan, float volume) {
    	Sound sound = instances.get(id);
    	sound.setPan(id, pan, volume);    //sets the pan of the sound to the left side at full volume
    }
    
    @Override
    public String toString() {
        return "SoundHandler{" +
        		"soundName=" + soundName +
                ", id=" + id +
                ", looping=" + looping + 
                '}';
    }
    
	//Sound sound = Gdx.audio.newSound(Gdx.files.internal("mixkit-player-jumping-in-a-video-game-2043.wav"));
	//TODO: dispose?, to String
    //TODO: this code is weird
    //everything that implements dispoable, has to be disposed
}
