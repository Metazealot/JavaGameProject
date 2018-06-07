package jGameMain;
import java.util.*;

public class Path {
	
	//Code derived from the following source:
	//https://github.com/OneLoneCoder/videos/blob/master/OneLoneCoder_PathFinding_AStar.cpp
	//Need to ensure that the use of the A* algorithm is accredited properly.
	
	LinkedList<Tile> TileContainer;
	Integer Length, orX, orY, dX, dY, mW, mH, Iterations;
	PathNode[][] NodeArr;
	PathNode originNode, destNode;
	
	public Path(Tile origin, Tile dest, Board B) {
		Length = 0;
		Iterations = 0;
		mW = B.Width;
		mH = B.Height;
		NodeArr = new PathNode[mW][mH];
		TileContainer = new LinkedList<Tile>();

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
		
		originNode = NodeArr[origin.xloc][origin.yloc];
		destNode = NodeArr[dest.xloc][dest.yloc];
		originNode.Blocked = false; //ensure that it is not counting the origin as blocked since the unit starts there
	
		for (int x = 0; x < mW; x++) {
			for (int y = 0; y < mH; y++) {
				
				if (y>0) {
					NodeArr[x][y].AdjacentTiles.add(NodeArr[x][y-1]);
				}

				if (y<mH-1) {
					NodeArr[x][y].AdjacentTiles.add(NodeArr[x][y+1]);
				}

				if (x>0) {
					NodeArr[x][y].AdjacentTiles.add(NodeArr[x-1][y]);
				}
				if (x<mW-1) {
					NodeArr[x][y].AdjacentTiles.add(NodeArr[x+1][y]);
				}
				
			}
		}
	
		Solver(NodeArr);
		for (int x = 0; x < mW; x++) {
			for (int y = 0; y < mH; y++) {
				if(NodeArr[x][y].Checked) {Iterations+=1;}
			}
		}

		if (destNode.Parent != null) {
			PathNode tracer = destNode;
			while (tracer !=null) {
				TileContainer.add(B.tileArray[tracer.x][tracer.y]);
				tracer = tracer.Parent;
				if (tracer !=originNode) {Length+=1;} else {
				}
			}
		} else {
			Length = -1;
		}
	}
	
	public boolean Solver(PathNode[][] Nodes) {
		for (int x = 0; x < mW; x++) {
			for (int y = 0; y < mH; y++) {
				Nodes[x][y].Checked = false;
				Nodes[x][y].globalgoal = Double.POSITIVE_INFINITY;
				Nodes[x][y].localgoal = Double.POSITIVE_INFINITY;
				Nodes[x][y].Parent = null;
			}
		}
		PathNode currNode = originNode;
		currNode.localgoal = 0.0;
		currNode.globalgoal = distance(originNode,destNode);
		LinkedList<PathNode> untested = new LinkedList<PathNode>();
		untested.add(currNode);
		while(!untested.isEmpty()&&(currNode!=destNode)) {
			//System.out.print("Testing Node");
			untested.sort(new NodeCompare());
			while((!untested.isEmpty())&&(untested.getFirst().Checked == true)){
				PathNode junk = untested.removeFirst();
			}
			if(untested.isEmpty()) {
				break;
			}
			currNode = untested.getFirst();
			currNode.Checked = true;
			for (final PathNode adjNode: currNode.AdjacentTiles) {
				if ((!adjNode.Checked)&&(!adjNode.Blocked)) {
					untested.addLast(adjNode);
				}
				double pgoal = (currNode.localgoal + distance(currNode,adjNode));
				if (pgoal < adjNode.localgoal) {
					adjNode.Parent = currNode;
					adjNode.localgoal = pgoal;
					adjNode.globalgoal = (adjNode.localgoal + distance(adjNode, destNode));

				}
				
			}
		}
		return true;
	}
	
	public double distance(PathNode a, PathNode b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
	
	public class NodeCompare implements Comparator<PathNode>{
		@Override
		public int compare(PathNode a, PathNode b) {
			return Double.compare(a.globalgoal,b.globalgoal);
		}
	}
}