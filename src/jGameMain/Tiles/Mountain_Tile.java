package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Mountain_Tile extends Tile {
	
	public Mountain_Tile() {
		TileID = 4;
		TileSymbol = "M";
		TileName = "Mountain";
		TileDesc = "A tall mountain\nImpassible on foot.\n.";
		Defense = 4.0;
		c = new Color(100, 100, 40);
		//RGB
	}
}
