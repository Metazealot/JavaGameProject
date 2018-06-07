package jGameMain;

public abstract class Unit {
	public String UnitName;
	public String UnitSymbol;
	public String UnitDesc;
	public Double HealthMax = 0.;
	public Double HealthCurrent = 0.;
	public Double Armor = 0.;
	public Integer ownerID;
	public Player ownerOBJ;
	public Integer unitID = 0;
	public Double MoveRange;
	public Double MoveLeft;
	public Double AttackRange;
	public boolean Ranged;
	public Double Damage;
	
	public void setOwner(Player P) {
		ownerID = P.PlayerID;
		ownerOBJ = P;
	}
	//actual units will be made as an inherited version of this abstract class.
	
	public Integer attack(Tile Location, Double scale) {
		if (MoveLeft != 0.0){
			Unit Target = Location.UnitGet();
			Double dmg = (Damage * scale);
			Target.HealthCurrent = Target.HealthCurrent - (dmg - (Target.Armor + Location.Defense));
			MoveLeft = 0.0;
			if(Target.HealthCurrent <= 0.0){
				Location.UnitContainer.clear();
				return 2;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}
	
	public void ReduceMoves(Integer a){
		MoveLeft = MoveLeft - a;
	}
}
