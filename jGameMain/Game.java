package jGameMain;

public class Game {
	
	public Game (Player Host, Integer W, Integer H) {
		Player[] playerList = new Player[4];
		playerList[0] = Host;
		Integer Width = W;
		Integer Height = H;
		if (W == 0) {
			Width = 20;
		}
		if (H == 0) {
			Height = 20;
		}
		Board gameboard = new Board(Width,Height);
	}
}
