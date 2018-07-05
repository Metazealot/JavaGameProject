package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Mountain_Tile extends Tile {
	
	public Mountain_Tile() {
		TileID = 4;
		TileSymbol = "M";
		TileName = "Mountain";
		TileDesc = "A tall mountain\nImpassible on foot.\n.";
		Defense = 4.0;
		c = new Color(100, 100, 40);
		//RGB
		InputStream is = getClass().getResourceAsStream("Mountain01.png");
    	try{
    		img1 = ImageIO.read(is);
    		img4 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is2 = getClass().getResourceAsStream("Mountain02.png");
    	try{
    		img2 = ImageIO.read(is2);
    		is2.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is3 = getClass().getResourceAsStream("Mountain03.png");
    	try{
    		img3 = ImageIO.read(is3);
    		is3.close();
    	} catch (IOException ex) {System.out.println("IOError");}
	}
}
