package com.itp.pacman.movement;

public interface IMoveable {
	public abstract void enteredNewFieldLogic(int index);
	
	public abstract void moveLogic(int index);
	
	public abstract void postMoveLogic(int index);
	
	public abstract void stopMoveLogic(int index);
}
