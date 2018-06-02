package jGameMain;
import java.awt.*;

abstract class Tile {
	
	String TileSymbol;
	String TileName;
	String TileDesc;
	Integer Defense;
	Unit[] UnitContainer;
	Color c;
	
	public Tile() {
		TileSymbol = "B";
		TileName = "Blank Tile";
		TileDesc = "";
		 Defense = 0;
		UnitContainer = new Unit[10];
		c = Color.GRAY;
	}
}
