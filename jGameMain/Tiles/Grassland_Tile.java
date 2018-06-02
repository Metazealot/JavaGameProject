package jGameMain.Tiles;
import java.awt.Color;

import jGameMain.Tile;
import jGameMain.Unit;

public class Grassland_Tile extends Tile {
	
	public Grassland_Tile() {
		TileSymbol = "G";
		TileName = "Grassland";
		TileDesc = "Gently rolling grasslands.\nOffers little defensive value.\nExcellent for farmland.";
		Defense = 0;
		c = new Color(0, 220, 0);
		//RGB
	}
}
