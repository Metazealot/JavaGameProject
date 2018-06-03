package jGameMain.Tiles;
import java.awt.Color;

import jGameMain.Tile;
import jGameMain.Unit;

public class Mountain_Tile extends Tile {
	
	public Mountain_Tile() {
		TileID = 4;
		TileSymbol = "M";
		TileName = "Mountain";
		TileDesc = "A tall mountain\nImpassible on foot.\n.";
		Defense = 0;
		c = new Color(100, 100, 40);
		//RGB
	}
}
