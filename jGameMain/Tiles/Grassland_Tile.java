package jGameMain.Tiles;
import java.awt.Color;

import jGameMain.Tile;
import jGameMain.Unit;

public class Grassland_Tile extends Tile {
	
	public Grassland_Tile() {
		TileID = 0;
		TileSymbol = "G";
		TileName = "Grassland";
		TileDesc = "Gently rolling grasslands.\nOffers little defensive value.\nExcellent for farmland.";
		Defense = 0.0;
		c = new Color(0, 220, 0);
		//RGB
		imgURL1 = getClass().getResource("\\Grassland01.png");
		imgURL2 = getClass().getResource("\\Grassland01.png");
		imgURL3 = getClass().getResource("\\Grassland01.png");
	}
}
