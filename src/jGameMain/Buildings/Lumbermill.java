package jGameMain.Buildings;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Building;

public class Lumbermill extends Building {
	
	public Lumbermill() {
		BuildingName = "Lumbermill";
		BuildingSymbol = "L";
		BuildingDesc = "Generates +2 Gold";
		HealthMax = 200.0;
		HealthCurrent = 200.0;
		DefenseBonus = 1.0;
		ActionsLeft = 0.0;
		ActionsMax = 0.0;
	}
}
