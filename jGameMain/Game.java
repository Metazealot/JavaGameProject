package jGameMain;
import java.util.*;

public class Game {
	
	LinkedList<Player> players, turnorder;
	LinkedList<Unit> Units;
	Integer Width;
	Integer Height;
	Board gameBoard;
	
	public Game (LinkedList<Player> playerList, Conquest con, Integer W, Integer H) {
		players = playerList;
		turnorder = new LinkedList<Player>();
		for(int x = 0; x < players.size(); x++){ 
			turnorder.add(players.get(x));
		}
		turnorder.get(0).turnOn();
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
	
	public Player getCurrentPlayer() {
		try {
			return turnorder.get(0);
		} catch (NullPointerException ex) {
			return new Player("Default");
		}
	}
	
	public void cycleturn() {
		Player tempplayer = turnorder.removeFirst();
		tempplayer.turnOff();
		turnorder.addLast(tempplayer);
		turnorder.get(0).turnOn();
		for (Unit U : Units) {
			if(U.ownerOBJ == turnorder.get(0)){
				U.MoveLeft = U.MoveRange;
			}
		}
		//Insert AI decision starter here
	
	}
	
	
}
