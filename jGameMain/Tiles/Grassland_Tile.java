package jGameMain.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jGameMain.Tile;

public class Grassland_Tile extends Tile {
	
	public Grassland_Tile() {
		TileID = 0;
		TileSymbol = "G";
		TileName = "Grassland";
		TileDesc = "Gently rolling grasslands.\nOffers little defensive value.\nExcellent for farmland.";
		Defense = 0.0;
		c = new Color(0, 220, 0);
		//RGB
		InputStream is = getClass().getResourceAsStream("Grassland01.png");
    	try{
    		img1 = ImageIO.read(is);
    		img4 = ImageIO.read(is);
    		is.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is2 = getClass().getResourceAsStream("Grassland02.png");
    	try{
    		img2 = ImageIO.read(is2);
    		is2.close();
    	} catch (IOException ex) {System.out.println("IOError");}
		InputStream is3 = getClass().getResourceAsStream("Grassland03.png");
    	try{
    		img3 = ImageIO.read(is3);
    		is3.close();
    	} catch (IOException ex) {System.out.println("IOError");}
	}
}
