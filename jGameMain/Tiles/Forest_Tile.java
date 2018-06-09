package jGameMain.Tiles;
import java.awt.Color;

import jGameMain.Tile;
import jGameMain.Unit;

public class Forest_Tile extends Tile {
	
	public Forest_Tile() {
		TileID = 1;
		TileSymbol = "F";
		TileName = "Forest";
		TileDesc = "Heavily wooded forest region.\n Provides modest defense.\nYields high production.";
		Defense = 2.0;
		c = new Color(50, 155, 25);
		//RGB
		imgURL1 = getClass().getResource("\\Forest01.png");
		imgURL2 = getClass().getResource("\\Forest02.png");
		imgURL3 = getClass().getResource("\\Forest03.png");
	}
}
