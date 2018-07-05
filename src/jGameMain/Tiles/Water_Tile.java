package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Water_Tile extends Tile {
	
	public Water_Tile() {
		TileID = 3;
		TileSymbol = "W";
		TileName = "Water";
		TileDesc = "A body of water.\nImpassible on foot.\nUse boats to cross.";
		Defense = 0.0;
		c = new Color(25, 50, 200);
		//RGB
		InputStream is = getClass().getResourceAsStream("Water01.png");
    	try{
    		img1 = ImageIO.read(is);
    		img4 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is2 = getClass().getResourceAsStream("Water02.png");
    	try{
    		img2 = ImageIO.read(is2);
    		is2.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is3 = getClass().getResourceAsStream("Water03.png");
    	try{
    		img3 = ImageIO.read(is3);
    		is3.close();
    	} catch (IOException ex) {System.out.println("IOError");}
	}
}
