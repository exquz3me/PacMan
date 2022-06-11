package com.itp.pacman.levels;

public class LevelHandler {
	private GameLevel level;
	
	//static variables are initialized only once, at the start of the execution, all intsatnces of the class have the same value for this var
	//maybe just make the variables of level public?
	//TODO: the player is an enity, it gets spawned on the cord of the index it was set to
	//		the ghosts are entitys too -> the ghosts, aswell as the player, dont share place with a pellet
	//TODO: other level classes contain only the dataArrays and the draw method
	//TODO: how about setting all the variables in this class and creating a level in the construcor
	//-> we add a levelHanlder to the stage that is capabable of displaying diffetent levels -> has the draw function
	//TODO: we need a way to set tileSizeX and Y to the region size to avoid having to know the tile size
	
	public LevelHandler (GameLevel level) { //level contains the Data arrays only
		this.level = level;
	}
		
	//THIS CLASS SHOULD HANDLE THE RESET OF THE LEVEL?, this class will have a hashmap of levels to wich you can add cutsom ones
	public GameLevel getLevel(){	//returns current level
		return level;
	}
	
	public void setLevel(GameLevel level) {	//TODO: set the variables too, size etc, maybe even call the whole process again?
		this.level = level;
	}
}

//TODO: i dont like the -tileSizeY in totalSizeY-tileSizeY, cant we just multiply it one less time?
//TODO: instead of having a walkable tiles list, create a third array of background, to which the tileData serves as a mask -> 0th index displays nothing,
//		the 0s get the sprite from the backgorund index to draw
//TODO: getX() and getY() give the world position, but we need position relative to parent!
//TODO: player movementcode is messy, add a way for arrayPos to know if a position is inbounds or not
//TODO: dispose?
//TODO: look into if returning the given parameter, but modified is a good idea or not, -> int& value "passing by reference" -> changes apply to the parameter, in java objects are passed by ref by default
//NOTE: level.getHeight() gives the region Ysize / tileSizeY same with width, is it possible to save mem by solving some logic issue?
