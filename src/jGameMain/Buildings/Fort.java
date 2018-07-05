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
		
		String prefix = "Fort";
		InputStream is = getClass().getResourceAsStream(prefix +"01.png");
    	try{
    		img1 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is2 = getClass().getResourceAsStream(prefix +"02.png");
    	try{
    		img2 = ImageIO.read(is2);
    		img4 = ImageIO.read(is2);
    		is2.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is3 = getClass().getResourceAsStream(prefix +"03.png");
    	try{
    		img3 = ImageIO.read(is3);
    		is3.close();
    	} catch (IOException ex) {System.out.println("IOError");}
	}
}
