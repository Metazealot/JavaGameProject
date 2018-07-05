package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Hills_Tile extends Tile {
	
	public Hills_Tile() {
		TileID = 5;
		TileSymbol = "H";
		TileName = "Hills";
		TileDesc = "Rocky Hills.\nEasily fortified.\nRich in minerals.";
		Defense = 3.0;
		c = new Color(200, 255, 0);
		//RGB
	}
}
