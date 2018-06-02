package jGameMain;
import java.awt.Color;

public class Grassland_Tile extends Tile {
	
	public Grassland_Tile() {
		TileSymbol = "G";
		TileName = "Grassland";
		TileDesc = "Rolling grassy plains. Offers little defensive value but excellent for farmland.";
		Defense = 0;
		UnitContainer = new Unit[10];
		c = new Color(0, 220, 0);
		//RGB
	}
}
