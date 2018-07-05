package jGameMain.Units;
import jGameMain.Unit;
import java.io.*;
import javax.imageio.ImageIO;

public class Infantry extends Unit {
	
	public Infantry() {
		UnitName = "Infantry";
		UnitSymbol = "IN";
		UnitDesc = "Basic ground soldier";
		HealthMax = 50.0;
		HealthCurrent = 50.0;
		Armor = 1.0;
		MoveRange = 4.0;
		MoveLeft = 4.0;
		AttackRange = 1.0;
		Ranged = false;
		Damage = 20.0;
		cost = 20;
		supply = 1;
/*
		String prefix = "Infantry";
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
*/
	}
}
