package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Plains_Tile extends Tile {
	
	public Plains_Tile() {
		TileID = 2;
		TileSymbol = "P";
		TileName = "Plains";
		TileDesc = "Sparsely vegetated plains.\nOffers little defensive value.\nDusty and barren.";
		Defense = 0.0;
		c = new Color(200, 255, 0);
		//RGB
	}
}
