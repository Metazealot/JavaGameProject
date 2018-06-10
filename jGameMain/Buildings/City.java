package jGameMain.Buildings;
import jGameMain.Building;

public class City extends Building {
	
	public City() {
		BuildingName = "City";
		BuildingSymbol = "C";
		BuildingDesc = "Population Center";
		HealthMax = 300.0;
		HealthCurrent = 300.0;
		DefenseBonus = 4.0;
		ActionsLeft = 1.0;
		ActionsMax = 1.0;
		imgURL1 = getClass().getResource("\\City01.png");
		imgURL2 = getClass().getResource("\\City02.png");
		imgURL3 = getClass().getResource("\\City03.png");		
	}
}
