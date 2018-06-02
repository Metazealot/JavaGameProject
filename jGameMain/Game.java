package jGameMain;
import java.util.*;

public class Game {
	
	Player[] players;
	Integer Width;
	Integer Height;
	Board gameBoard;
	LinkedList<Player> turnorder;
	
	public Game (Player[] playerArr, Conquest con, Integer W, Integer H) {
		players = playerArr;
		turnorder = new LinkedList<Player>();
		for(int p = 0; p < 4; p++) {
    		try {
    			turnorder.add(players[p]);
    		} catch (NullPointerException ex) {

    		}
		}
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
	
	public Player GetCurrentPlayer() {
		try {
			return turnorder.get(0);
		} catch (NullPointerException ex) {
			return new Player("Default");
		}
	}
	
}
