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
		
		String prefix = "Worker";
		InputStream is = getClass().getResourceAsStream(prefix + "01.png");
    	try{
    		img1 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is2 = getClass().getResourceAsStream(prefix + "02.png");
    	try{
    		img2 = ImageIO.read(is2);
    		img4 = ImageIO.read(is2);
    		is2.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is3 = getClass().getResourceAsStream(prefix + "03.png");
    	try{
    		img3 = ImageIO.read(is3);
    		is3.close();
    	} catch (IOException ex) {System.out.println("IOError");}

	}
}
