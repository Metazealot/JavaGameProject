package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Plains_Tile extends Tile {
	
	public Plains_Tile() {
		TileID = 2;
		TileSymbol = "P";
		TileName = "Plains";
		TileDesc = "Sparsely vegetated plains.\nOffers little defensive value.\nDusty and barren.";
		Defense = 0.0;
		c = new Color(200, 255, 0);
		//RGB
		InputStream is = getClass().getResourceAsStream("Plains01.png");
    	try{
    		img1 = ImageIO.read(is);
    		img4 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is2 = getClass().getResourceAsStream("Plains02.png");
    	try{
    		img2 = ImageIO.read(is2);
    		is2.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is3 = getClass().getResourceAsStream("Plains03.png");
    	try{
    		img3 = ImageIO.read(is3);
    		is3.close();
    	} catch (IOException ex) {System.out.println("IOError");}
	}
}
