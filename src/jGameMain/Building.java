package jGameMain;

import java.awt.Image;

public abstract class Building {
	public String BuildingName;
	public String BuildingSymbol;
	public String BuildingDesc;
	public Double HealthMax = 0.0;
	public Double HealthCurrent = 0.0;
	public Double DefenseBonus = 0.0;
	public Double ActionsLeft = 0.0;
	public Double ActionsMax = 0.0;
	public Integer ownerID;
	public Player ownerOBJ;
	public Integer unitID = 0;
	public Image img1, img2, img3, img4;
	public Integer xloc, yloc;
	
	public void setOwner(Player P) {
		ownerID = P.PlayerID;
		ownerOBJ = P;
	}

	public void locate(Integer x, Integer y) {
		xloc = x;
		yloc = y;
	}

}
