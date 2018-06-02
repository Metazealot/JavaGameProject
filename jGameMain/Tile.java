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
	
	public Integer UnitCount() {
		return UnitContainer.size();
	}
	
	public Unit UnitGet() {
		return UnitContainer.get(0);
	}
	
	public Integer CreateUnit(Unit U) { //Returns an integer value so that success or failure is reportable.
		if (UnitContainer.size() == 0) {
			UnitContainer.add(U);
			return 1; //Empty tile
		} else {
			return 0; //Already a unit here
		}
	}
	

}
