package jGameMain;
import java.util.Random;
import jGameMain.Tiles.*;

public class Board {
	Tile[][] tileArray;
	Integer Chance, Width, Height;
	Tile chosenspot;
	
	public Board (Integer W, Integer H) {
		Width=W;
		Height=H;
		tileArray =new Tile[Width][Height];
		
	
	
		for(int x = 0; x < Width; x++){
        	for(int y = 0; y < Height; y++){     		
        		Random rand = new Random();
        		Chance = rand.nextInt(100);
        		if(Chance>70) {
        			tileArray[x][y] = new Grassland_Tile();
        		}else if (Chance>40) {
        			tileArray[x][y] = new Forest_Tile();
        		}else if (Chance>20){
        			tileArray[x][y] = new Plains_Tile();
        		}else if (Chance>10){
        			tileArray[x][y] = new Mountain_Tile();
        		}else {
        			tileArray[x][y] = new Water_Tile();
        		}
        		tileArray[x][y].setCoord(x, y);
        	}
		}
		Tile[][] spawnregion = new Tile[6][6];
		for(int x = 0; x < 6; x++){
        	for(int y = 0; y < 6; y++){ 
        		spawnregion[x][y] = tileArray[x][y];
        	}
		}
		Random rd = new Random();
		chosenspot = spawnregion[rd.nextInt(6)][rd.nextInt(6)];
		while ((chosenspot.TileID == 3)|(chosenspot.TileID ==4)) {
			chosenspot = spawnregion[rd.nextInt(6)][rd.nextInt(6)];
		}
	}

}