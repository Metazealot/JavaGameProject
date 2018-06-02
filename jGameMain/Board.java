package jGameMain;
import java.util.Random;

public class Board {
	Tile[][] tileArray;
	Integer Chance;
	
	
	public Board (Integer Width, Integer Height) {
		tileArray =new Tile[Width][Height];
	
	
		for(int x = 0; x < Width; x++){
        	for(int y = 0; y < Height; y++){     		
        		Random rand = new Random();
        		Chance = rand.nextInt(100);
        		if(Chance>50) {
        			tileArray[x][y] = new Grassland_Tile();
        		}else {
        			tileArray[x][y] = new Forest_Tile();
        		}
        	}
		}
	
	}

}