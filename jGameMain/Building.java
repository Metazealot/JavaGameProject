package jGameMain;

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
	public java.net.URL imgURL1, imgURL2, imgURL3; 
	
	public void setOwner(Player P) {
		ownerID = P.PlayerID;
		ownerOBJ = P;
	}

	

}
