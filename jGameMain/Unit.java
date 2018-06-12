package jGameMain;
import java.awt.*;

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
	public Integer cost = 0;
	public Integer supply = 0;
	public Double MoveRange;
	public Double MoveLeft;
	public Double AttackRange;
	public boolean Ranged;
	public Double Damage;
	public Image img1, img2, img3, img4;
	public Integer xloc, yloc;
	
	public void setOwner(Player P) {
		ownerID = P.PlayerID;
		ownerOBJ = P;
	}
	//actual units will be made as an inherited version of this abstract class.
	
	public Integer attack(Tile Location, Double scale) {
		if (MoveLeft != 0.0){
			Unit Target = Location.UnitGet();
			Double dmg = (Damage * scale);
			Double dVal = Location.Defense;
			if (Location.BuildingCount() != 0) {
				dVal += Location.BuildingGet().DefenseBonus;
			}
			Target.HealthCurrent = Target.HealthCurrent - (dmg - (Target.Armor + dVal ));
			MoveLeft = 0.0;
			if(Target.HealthCurrent <= 0.0){
				Location.UnitContainer.clear();
				this.ownerOBJ.Kills += 1;
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
	
	public void locate(Integer x, Integer y) {
		xloc = x;
		yloc = y;
	}
}
