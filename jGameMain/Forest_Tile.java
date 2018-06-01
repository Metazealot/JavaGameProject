package jGameMain;
import java.awt.Color;

public class Forest_Tile extends Tile {
	
	public Forest_Tile() {
		TileName = "F";
		Defense = 0;
		UnitContainer = new Unit[10];
		c = new Color(50, 155, 25);
		//RGB
	}
}
