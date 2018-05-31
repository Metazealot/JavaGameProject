package jGameMain;
import java.awt.Color;

public class Grassland_Tile extends Tile {
	
	public Grassland_Tile() {
		TileName = "Tile";
		Defense = 0;
		UnitContainer = new Unit[10];
		c = Color.GREEN;

	}
}
