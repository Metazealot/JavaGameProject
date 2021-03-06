package jGameMain.Buildings;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Building;

public class City extends Building {
	
	public City() {
		BuildingName = "City";
		BuildingSymbol = "C";
		BuildingDesc = "Population Center\nGenerates +5 Gold";
		HealthMax = 500.0;
		HealthCurrent = 500.0;
		DefenseBonus = 4.0;
		ActionsLeft = 1.0;
		ActionsMax = 1.0;
/*
		String prefix = "City";
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
*/
	}
}
