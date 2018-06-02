package jGameMain;
import java.util.Random;
import jGameMain.Tiles.*;

public class Board {
	Tile[][] tileArray;
	Integer Chance;
	
	
	public Board (Integer Width, Integer Height) {
		tileArray =new Tile[Width][Height];
	
	
		for(int x = 0; x < Width; x++){
        	for(int y = 0; y < Height; y++){     		
        		Random rand = new Random();
        		Chance = rand.nextInt(100);
        		if(Chance>66) {
        			tileArray[x][y] = new Grassland_Tile();
        		}else if (Chance>33) {
        			tileArray[x][y] = new Forest_Tile();
        		}else if (Chance>10){
        			tileArray[x][y] = new Plains_Tile();
        		}else {
        			tileArray[x][y] = new Water_Tile();
        		}
        	}
		}
	
	}

}