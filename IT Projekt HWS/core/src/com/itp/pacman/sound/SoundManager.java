package com.itp.pacman.sound;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.itp.pacman.PacMan;
import com.itp.pacman.entities.GameActor;

public class SoundManager{	//TODO: move to screen		TODO: its not a handler ista manager
	//play every sound in loading screen to get the ids?
	//ogg is not supported on ios, WAV files is the way to go
	
	//maniuplating the sound object, manipulates all played instances, id manipulates specific instance

	private HashMap<String, Sound> sounds = new HashMap<>();
	private HashMap<Long, Sound> instances = new HashMap<>();
	private long id;
	
	
	//changaes and method calls will only apply to the newest sound
	
	//playing a sound returns a long for this sound instance, using this instance will allow to modify the playback
	
	//maybe get rid of this method entierely
	
    public void add(String soundName, Sound sound){
    	sounds.put(soundName, sound);
    }
    
    public void play(String soundName, float volume, boolean looping, float pitch, float pan) {
    	Sound sound = sounds.get(soundName);
    	id = sound.play(volume);
    	sound.setLooping(id, looping);
    	sound.setPitch(id, pitch);
    	sound.setPan(id, pan, volume);
    	instances.put(id, sound);
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
    	sound.setPan(id, pan, volume);
    }
    
    public Sound getSound(String soundName) {
    	return sounds.get(soundName);
    }
    
    public Sound getSoundInstance(long id) {
    	return instances.get(id);
    }
    //TODO: setters
    
	//TODO: dispose?
    //everything that implements dispoable, has to be disposed
}
