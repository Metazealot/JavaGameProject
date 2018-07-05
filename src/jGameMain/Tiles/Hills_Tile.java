package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Hills_Tile extends Tile {
	
	public Hills_Tile() {
		TileID = 5;
		TileSymbol = "H";
		TileName = "Hills";
		TileDesc = "Rocky Hills.\nEasily fortified.\nRich in minerals.";
		Defense = 3.0;
		c = new Color(200, 255, 0);
		//RGB
		InputStream is = getClass().getResourceAsStream("Hills01.png");
    	try{
    		img1 = ImageIO.read(is);
    		img4 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is2 = getClass().getResourceAsStream("Hills02.png");
    	try{
    		img2 = ImageIO.read(is2);
    		is2.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is3 = getClass().getResourceAsStream("Hills03.png");
    	try{
    		img3 = ImageIO.read(is3);
    		is3.close();
    	} catch (IOException ex) {System.out.println("IOError");}
	}
}
