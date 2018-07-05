package jGameMain.Units;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Unit;

public class Worker extends Unit {
	
	public Worker() {
		UnitName = "Worker";
		UnitSymbol = "WK";
		UnitDesc = "Construction or repair unit.\nCreates buildings.";
		HealthMax = 20.0;
		HealthCurrent = 20.0;
		Armor = 0.0;
		MoveRange = 4.0;
		MoveLeft = 4.0;
		AttackRange = 1.0;
		Ranged = false;
		Damage = 5.0;
		cost = 10;
		supply = 1;
	}
}
