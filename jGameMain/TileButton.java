package jGameMain;

import javax.swing.JButton;

public class TileButton extends JButton {
	int xloc;
	int yloc;
	
	public TileButton(String name,int x,int y) {
		this.setText(name);
		xloc = x;
		yloc = y;
		
	}
	
	public int[] coord() {
		int[] crds = {xloc,yloc};
		return crds;
	}
}
