package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Grassland_Tile extends Tile {
	
	public Grassland_Tile() {
		TileID = 0;
		TileSymbol = "G";
		TileName = "Grassland";
		TileDesc = "Gently rolling grasslands.\nOffers little defensive value.\nExcellent for farmland.";
		Defense = 0.0;
		c = new Color(0, 220, 0);
		//RGB
	}
}
