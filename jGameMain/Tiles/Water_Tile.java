package jGameMain.Tiles;
import java.awt.Color;

import jGameMain.Tile;
import jGameMain.Unit;

public class Water_Tile extends Tile {
	
	public Water_Tile() {
		TileID = 3;
		TileSymbol = "W";
		TileName = "Water";
		TileDesc = "A body of water.\nImpassible on foot.\nUse boats to cross.";
		Defense = 0.0;
		c = new Color(25, 50, 200);
		//RGB
	}
}
