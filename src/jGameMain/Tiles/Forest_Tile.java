package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Forest_Tile extends Tile {
	
	public Forest_Tile() {
		TileID = 1;
		TileSymbol = "F";
		TileName = "Forest";
		TileDesc = "Heavily wooded forest region.\nProvides modest defense.\nYields high production.";
		Defense = 2.0;
		c = new Color(50, 155, 25);
		//RGB
		InputStream is = getClass().getResourceAsStream("Forest01.png");
    	try{
    		img1 = ImageIO.read(is);
    		img4 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is2 = getClass().getResourceAsStream("Forest02.png");
    	try{
    		img2 = ImageIO.read(is2);
    		
    		is2.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is3 = getClass().getResourceAsStream("Forest03.png");
    	try{
    		img3 = ImageIO.read(is3);
    		is3.close();
    	} catch (IOException ex) {System.out.println("IOError");}
	}
}
