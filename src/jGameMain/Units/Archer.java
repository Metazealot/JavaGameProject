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
		
		String prefix = "Archer";
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
