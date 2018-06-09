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
		Defense = 4.0;
		c = new Color(100, 100, 40);
		//RGB
		imgURL1 = getClass().getResource("\\Mountain01.png");
		imgURL2 = getClass().getResource("\\Mountain02.png");
		imgURL3 = getClass().getResource("\\Mountain03.png");
	}
}
