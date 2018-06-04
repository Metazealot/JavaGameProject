package jGameMain;
import java.util.*;

public class Path {
	
	LinkedList<Tile> TileContainer;
	Integer Length, orX, orY, dX, dY, mW, mH;
	PathNode[][] NodeArr;
	
	public Path(Tile origin, Tile destination, Board B) {
		mW = B.Width;
		mH = B.Height;
		NodeArr = new PathNode[mW][mH];
		orX = origin.xloc;
		orY = origin.yloc;
		for (int x = 0; x < mW; x++) {
			for (int y = 0; y < mH; y++) {
				NodeArr[x][y] = new PathNode(x,y);
				if ((B.tileArray[x][y].UnitCount() != 0)|
						(B.tileArray[x][y].TileID == 3)|
						(B.tileArray[x][y].TileID == 4) ){
					NodeArr[x][y].Blocked = true; //If there is a unit, or if the tile is unpathable, consider it blocked.
				}
				
			}
		}
		
		
		
	}
	
	
}
