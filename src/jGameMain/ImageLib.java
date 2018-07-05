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
		insertimg("Infantry", 3);
		insertimg("Worker", 3);
	}
	
	public void insertimg(String keyname, Integer Count) {
		
		Integer counter = Count;
		String prefix = keyname;
		String suffix = ".png";
		String cycleID = "0";
		while (counter > 0) {
			if (counter < 10) {
				suffix = (counter.toString() + suffix);
				cycleID = counter.toString();
			} else {
				suffix = ("0" + counter.toString() + suffix);
				cycleID = "0" + counter.toString();
			}
			InputStream is = getClass().getResourceAsStream(prefix + "01.png");
	    	try{
	    		img = ImageIO.read(is);
	    		is.close();
	    		
	    	} catch (IOException ex) {System.out.println("IOError");}
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
			return null;
		}
	}
	
	public ImageIcon getImage(String keyname, Integer cycleID) {
		String truekey = keyname + cycleID.toString();
		if (contents.containsKey(truekey)){
			return contents.get(truekey);
		} else {
			return null;
		}
	}

}
