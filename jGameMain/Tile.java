package jGameMain;
import java.awt.*;
import java.util.*;

public abstract class Tile {
	
	public String TileSymbol;
	public String TileName;
	public String TileDesc;
	public Integer Defense;
	public ArrayList<Unit> UnitContainer;
	public Color c;
	
	public Tile() {
		TileSymbol = "B";
		TileName = "Blank Tile";
		TileDesc = "";
		 Defense = 0;
		UnitContainer = new ArrayList<Unit>();
		c = Color.GRAY;
	}
	
	public Integer Count() {
		return UnitContainer.size();
	}
}
