package jGameMain;

public class Game {
	
	Player[] players;
	Integer Width;
	Integer Height;
	Board gameBoard;
	
	public Game (Player[] playerArr, Conquest con, Integer W, Integer H) {
		players = playerArr;
		Width = 20;
		Height = 20;
		if (W != 0) {
			Width = W;
		}
		if (H != 0) {
			Height = H;
		}
		gameBoard = new Board(Width,Height);
		con.maindisplay.Instantiate(Width, Height, gameBoard);
	}
}
