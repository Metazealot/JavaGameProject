package jGameMain;
import java.awt.*;

abstract class Tile {
	
	String TileName;
	Integer Defense;
	Unit[] UnitContainer;
	Color c;
	
	public Tile() {
		TileName = "Blank Tile";
		 Defense = 0;
		UnitContainer = new Unit[10];
		c = Color.GRAY;
	}
}
