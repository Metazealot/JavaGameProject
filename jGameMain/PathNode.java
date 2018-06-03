package jGameMain;

public class PathNode {
	Integer x, y;
	Boolean Blocked;
	Boolean Checked;
	PathNode Parent;

	public PathNode(int inx, int iny) {
		x = inx;
		y = iny;
		Blocked = false;
		Checked = false;
	}
}
