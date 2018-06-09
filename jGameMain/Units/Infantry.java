package jGameMain.Units;
import jGameMain.Unit;

public class Infantry extends Unit {
	
	public Infantry() {
		UnitName = "Infantry";
		UnitSymbol = "IN";
		UnitDesc = "Basic ground soldier";
		HealthMax = 50.0;
		HealthCurrent = 50.0;
		Armor = 1.0;
		MoveRange = 4.0;
		MoveLeft = 4.0;
		AttackRange = 1.0;
		Ranged = false;
		Damage = 20.0;
		imgURL1 = getClass().getResource("\\Infantry01.png");
		imgURL2 = getClass().getResource("\\Infantry02.png");
		imgURL3 = getClass().getResource("\\Infantry03.png");

	}
}
