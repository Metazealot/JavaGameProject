package jGameMain;

public abstract class Unit {
	public String UnitName;
	public String UnitSymbol;
	public String UnitDesc;
	public Integer HealthMax = 0;
	public Integer HealthCurrent = 0;
	public Integer Armor = 0;
	public Integer ownerID;
	public Integer unitID = 0;
	public Double MoveRange;
	
	//action methods
	public void setOwner(Player P) {
		ownerID = P.PlayerID;
	}
	//actual units will be made as an inherited version of this abstract class.
}
