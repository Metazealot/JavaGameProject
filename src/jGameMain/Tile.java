package jGameMain;
import java.awt.*;
import java.util.*;

public abstract class Tile {
	
	public String TileSymbol, TileName, TileDesc;
	public Double Defense;
	public ArrayList<Unit> UnitContainer;
	public ArrayList<Building> BuildingContainer;
	public Color c;
	public Integer xloc,yloc, TileID, Anim, Anim2, Flash;
	public Boolean selected = false;
	public Image img1, img2, img3, img4;
	public Integer Yield;
	
	public Tile() {
		TileSymbol = "B";
		TileName = "Blank Tile";
		TileDesc = "";
		 Defense = 0.0;
		UnitContainer = new ArrayList<Unit>();
		BuildingContainer = new ArrayList<Building>();
		c = Color.GRAY;
		Anim = 0;
		Anim2  = 0;
		Flash = 0;
	}
	
	public void animCycle() {	
		if(Anim2 < 1){
			Anim2+=1;
		} else {
			Anim2 = 0;
			if (Anim < 2) {
				Anim +=1;
			} else {
				Anim = 0;
			}
		}
		
		if(Flash > 0) {
			Flash -= 1;
		}
	}
	
	public Integer UnitCount() {
		return UnitContainer.size();
	}
	
	public Unit UnitGet() {
		return UnitContainer.get(0);
	}
	
	public Integer BuildingCount() {
		return BuildingContainer.size();
	}
	
	public Building BuildingGet() {
		return BuildingContainer.get(0);
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
	
	public void BuildingAdd(Building B) {
		BuildingContainer.add(B);
	}
	
	public Integer CreateUnit(Unit U) { //Returns an integer value so that success or failure is reportable.
		if (UnitContainer.size() == 0) {
			if ((TileID !=4)&(TileID != 3)){
				UnitContainer.add(U);
				U.MoveLeft = 0.0;
				U.locate(xloc, yloc);
				return 1; //Placement success
			} else {
				return 0; //Tile is mountains or water
			}
		} else {
			return 0; //Already a unit here
		}
	}
	
	public Integer CreateBuilding(Building B) { //Returns an integer value so that success or failure is reportable.
		if (BuildingContainer.size() == 0) {
			if ((TileID !=4)&(TileID != 3)){
				BuildingContainer.add(B);
				B.locate(xloc, yloc);
				return 1; //Placement success
			} else {
				return 0; //Tile is mountains or water
			}
		} else {
			return 0; //Already a building here
		}
	}
	
	public void setCoord(Integer x, Integer y) {
		xloc = x;
		yloc = y;
	}

}
