package com.itp.pacman.entities;

import com.itp.pacman.PacMan;

public class RegularPellet extends GameActor{	//create class collectible
	public RegularPellet(PacMan game) {
		super(game);
		setRegion(atlas.findRegions("run").first());	//change from first to fitting index
		setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight()); 
	}
	
	public void interaction() {
		setVisible(false);
	}
}
