package jGameMain;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLib {
	Hashtable<String,ImageIcon> contents = new Hashtable<String,ImageIcon>();
	public Image img;
	public ImageIcon img2;
	
	public ImageLib() {
		insertimg("Infantry", 3, 0);
		insertimg("Worker", 3, 0);
		insertimg("Archer", 3, 0);
		insertimg("Grassland", 3, 1);
		insertimg("Hills", 3, 1);
		insertimg("Mountain", 3, 1);
		insertimg("Forest", 3, 1);
		insertimg("Plains", 3, 1);
		insertimg("Water", 3, 1);
		insertimg("City", 3, 2);
		insertimg("Farm", 3, 2);
		insertimg("Fort", 3, 2);
		insertimg("Lumbermill", 3, 2);
		insertimg("Mine", 3, 2);
		insertimg("Granary", 3, 2);
	}
	
	public void insertimg(String keyname, Integer Count, Integer Loc) {
		
		Integer counter = Count;
		String locator = "Units/";
		String prefix = keyname;
		String suffix = ".png";
		String cycleID = "0";
		switch(Loc) {
			case 0: locator = "Units/images/"; break;
			case 1: locator = "Tiles/images/"; break;
			case 2: locator = "Buildings/images/"; break;
			default: locator = "";
		}
		while (counter > 0) {
			suffix = ".png";
			if (counter >= 10) {
				suffix = (counter.toString() + suffix);
				cycleID = counter.toString();
			} else {
				suffix = ("0" + counter.toString() + suffix);
				cycleID = "0" + counter.toString();
			}
			
	    	try{
	    		String filename = locator + prefix + suffix;
	    		System.out.println(filename + " Loaded");
	    		InputStream is = getClass().getResourceAsStream(filename);
	    		try {
	    			img = ImageIO.read(is);
	    		} catch (IllegalArgumentException ex) {
	    			System.out.println("Illegal Argument");
	    			img = null;
	    		}
	    		is.close();
	    		
	    	} catch (IOException ex) {
	    		System.out.println("IOError");
	    		img = null;
	    	}
	    	
	    	img2 = new ImageIcon(img.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
	    	
	    	String truekey = keyname + cycleID;
	    	contents.put(truekey, img2);
			counter -= 1;
		}
		
		
	}
	
	public ImageIcon getImage(String keyname) {
		if (contents.containsKey(keyname)){
			return contents.get(keyname);
		} else {
			System.out.println("Image Not Found");
			return null;	
		}
	}
	
	public ImageIcon getImage(String keyname, Integer cycleID) {
		String truekey;
		if (cycleID >= 10) {
			truekey = keyname + cycleID.toString();
		} else {
			truekey = keyname + "0" + cycleID.toString();
		}
		if (contents.containsKey(truekey)){
			return contents.get(truekey);
		} else {
			System.out.println("Image Not Found");
			return null;
		}
	}

}
