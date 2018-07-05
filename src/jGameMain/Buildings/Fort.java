package jGameMain.Buildings;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Building;

public class Fort extends Building {
	
	public Fort() {
		BuildingName = "Fort";
		BuildingSymbol = "FT";
		BuildingDesc = "Provides a massive\nDefense bonus";
		HealthMax = 200.0;
		HealthCurrent = 200.0;
		DefenseBonus = 8.0;
		ActionsLeft = 0.0;
		ActionsMax = 0.0;
	}
}
