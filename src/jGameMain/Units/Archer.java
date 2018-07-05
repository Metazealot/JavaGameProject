package jGameMain.Units;
import jGameMain.Unit;
import java.io.*;
import javax.imageio.ImageIO;

public class Archer extends Unit {
	
	public Archer() {
		UnitName = "Archer";
		UnitSymbol = "AR";
		UnitDesc = "Ranged Combatant";
		HealthMax = 30.0;
		HealthCurrent = 30.0;
		Armor = 0.0;
		MoveRange = 4.0;
		MoveLeft = 4.0;
		AttackRange = 4.0;
		Ranged = true;
		Damage = 10.0;
		cost = 25;
		supply = 1;
	}
}
