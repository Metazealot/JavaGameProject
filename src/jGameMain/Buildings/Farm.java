package jGameMain.Buildings;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Building;

public class Farm extends Building {
	
	public Farm() {
		BuildingName = "Farm";
		BuildingSymbol = "F";
		BuildingDesc = "Produces 2 Supply\n(3 on Grasslands)";
		HealthMax = 200.0;
		HealthCurrent = 200.0;
		DefenseBonus = 1.0;
		ActionsLeft = 0.0;
		ActionsMax = 0.0;
	}
}
