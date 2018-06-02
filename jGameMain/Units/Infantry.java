package jGameMain.Units;
import jGameMain.Unit;

public class Infantry extends Unit {
	
	public Infantry() {
		UnitName = "Infantry";
		UnitSymbol = "IN";
		UnitDesc = "Basic ground soldier";
		HealthMax = 50;
		HealthCurrent = 50;
		Armor = 1;

	}
}
