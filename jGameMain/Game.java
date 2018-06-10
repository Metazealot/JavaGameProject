package jGameMain;
import java.util.*;

public class Game {
	
	LinkedList<Player> players, turnorder;
	LinkedList<Unit> Units;
	LinkedList<Building> Buildings;
	Integer Width;
	Integer Height;
	Board gameBoard;
	Integer Turncount;
	Player originhost;
	
	public Game (LinkedList<Player> playerList, Conquest con, Integer W, Integer H) {
		players = playerList;
		Units = new LinkedList<Unit>();
		Buildings = new LinkedList<Building>();
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
		originhost = con.host;
		Turncount = 1;
		originhost.selectTile(gameBoard.tileArray[0][0]);
		
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
		Player activeplayer = turnorder.get(0);
		activeplayer.turnOn();
		if (activeplayer == originhost) {
			Turncount += 1;
		}
		if (Units.size() != 0 ){
			for (Unit U : Units) {
				if(U.ownerOBJ == activeplayer){
					U.MoveLeft = U.MoveRange;
				}
			}
		}

		if (activeplayer.AI == true){
			AIturn();
		}
	
	}
	
	public void AIturn() {
		cycleturn();
		System.out.println("AI Turn Cycled");
	}
	
	
}
