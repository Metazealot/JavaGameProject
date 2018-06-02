package jGameMain;
import java.awt.Color;

public class Forest_Tile extends Tile {
	
	public Forest_Tile() {
		TileSymbol = "F";
		TileName = "Forest";
		TileDesc = "Heavily wooded forest region. Provides modest defense and if harvested yields high production values.";
		Defense = 2;
		UnitContainer = new Unit[10];
		c = new Color(50, 155, 25);
		//RGB
	}
}
