package jGameMain.Units;
import jGameMain.Unit;

public class Worker extends Unit {
	
	public Worker() {
		UnitName = "Worker";
		UnitSymbol = "Wk";
		UnitDesc = "Construction or repair unit. Creates buildings.";
		HealthMax = 20.0;
		HealthCurrent = 20.0;
		Armor = 0.0;
		MoveRange = 4.0;
		MoveLeft = 4.0;
		AttackRange = 1.0;
		Ranged = false;
		Damage = 5.0;
		imgURL1 = getClass().getResource("\\Worker01.png");
		imgURL2 = getClass().getResource("\\Worker02.png");
		imgURL3 = getClass().getResource("\\Worker03.png");

	}
}
