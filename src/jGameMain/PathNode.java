package jGameMain;
import java.util.*;

public class PathNode {
	Integer x, y;
	Boolean Blocked;
	Boolean Checked;
	PathNode Parent;
	Vector <PathNode> AdjacentTiles;
	double localgoal, globalgoal;

	public PathNode(int inx, int iny) {
		x = inx;
		y = iny;
		Blocked = false;
		Checked = false;
		AdjacentTiles = new Vector <PathNode>();
	}
}
