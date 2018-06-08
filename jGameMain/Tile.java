package jGameMain;
import java.awt.*;
import java.util.*;

public abstract class Tile {
	
	public String TileSymbol;
	public String TileName;
	public String TileDesc;
	public Double Defense;
	public Integer TileID;
	public ArrayList<Unit> UnitContainer;
	public Color c;
	public Integer xloc,yloc;
	public Boolean selected = false;
	public Integer Anim;
	public java.net.URL imgURL1, imgURL2, imgURL3; 
	
	public Tile() {
		TileSymbol = "B";
		TileName = "Blank Tile";
		TileDesc = "";
		 Defense = 0.0;
		UnitContainer = new ArrayList<Unit>();
		c = Color.GRAY;
		Anim = 0;
	}
	
	public void animCycle() {
		if(Anim < 2){
			Anim+=1;
		} else {
			Anim = 0;
		}
	}
	
	public Integer UnitCount() {
		return UnitContainer.size();
	}
	
	public Unit UnitGet() {
		return UnitContainer.get(0);
	}
	
	public Unit UnitClear() {
		Unit U = UnitGet();
		UnitContainer.clear();
		return U;
	}
	
	public void MoveUnit(Tile T) {
		Unit U = UnitClear();
		T.UnitAdd(U);
	}
	
	public void UnitAdd(Unit U) {
		UnitContainer.add(U);
	}
	
	public Integer CreateUnit(Unit U) { //Returns an integer value so that success or failure is reportable.
		if (UnitContainer.size() == 0) {
			if ((TileID !=4)&(TileID != 3)){
				UnitContainer.add(U);
				return 1; //Placement success
			} else {
				return 0; //Tile is mountains or water
			}
		} else {
			return 0; //Already a unit here
		}
	}
	
	public void setCoord(Integer x, Integer y) {
		xloc = x;
		yloc = y;
	}

}
