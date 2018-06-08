package jGameMain.Tiles;
import java.awt.Color;

import jGameMain.Tile;
import jGameMain.Unit;

public class Plains_Tile extends Tile {
	
	public Plains_Tile() {
		TileID = 2;
		TileSymbol = "P";
		TileName = "Plains";
		TileDesc = "Sparsely vegetated plains.\nOffers little defensive value.\nDusty and barren.";
		Defense = 0.0;
		c = new Color(200, 255, 0);
		//RGB
		imgURL1 = getClass().getResource("\\Plains01.png");
		imgURL2 = getClass().getResource("\\Plains01.png");
		imgURL3 = getClass().getResource("\\Plains01.png");
	}
}
