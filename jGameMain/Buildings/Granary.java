package jGameMain.Buildings;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Building;

public class Granary extends Building {
	
	public Granary() {
		BuildingName = "Granary";
		BuildingSymbol = "G";
		BuildingDesc = "Generates +2 Gold";
		HealthMax = 200.0;
		HealthCurrent = 200.0;
		DefenseBonus = 1.0;
		ActionsLeft = 0.0;
		ActionsMax = 0.0;
		
		String prefix = "Granary";
		InputStream is = getClass().getResourceAsStream(prefix +"01.png");
    	try{
    		img1 = ImageIO.read(is);
    		img2 = ImageIO.read(is);
    		img3 = ImageIO.read(is);
    		img4 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
	}
}
