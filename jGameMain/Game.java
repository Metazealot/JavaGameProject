package jGameMain;
import java.util.*;
import jGameMain.Buildings.*;

public class Game {
	
	LinkedList<Player> players, turnorder;
	LinkedList<Unit> Units;
	LinkedList<Building> Buildings;
	Integer Width;
	Integer Height;
	Board gameBoard;
	Integer Turncount;
	Player originhost;
	Double startingmoney;
	
	public Game (LinkedList<Player> playerList, Conquest con, Integer W, Integer H) {
		startingmoney = 500.0;
		players = playerList;
		Units = new LinkedList<Unit>();
		Buildings = new LinkedList<Building>();
		turnorder = new LinkedList<Player>();
		for(int x = 0; x < players.size(); x++){
			players.get(x).resource1 = startingmoney;
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
		gameBoard.chosenspot.CreateBuilding(new City());
		gameBoard.chosenspot.BuildingGet().setOwner(originhost);
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
		for (int x = 0; x < Width; x++) {
			for (int y = 0; y < Height; y++) {
				Tile T = gameBoard.tileArray[x][y];
				if (T.BuildingCount() != 0) {
					Building Bld = T.BuildingGet();
					if (Bld.ownerOBJ == activeplayer){
						activeplayer.resource1 += 50;
					}
				}
			}
		}
	}
	
	public void supplyscan() {
		for (Player P : players) {
			P.resource2 = 0.0;
			P.resource3 = 0.0;
		}
		for (int x = 0; x < Width; x++) {
			for (int y = 0; y < Height; y++) {
				Tile T = gameBoard.tileArray[x][y];
				if (T.BuildingCount() != 0) {
					Building Bld = T.BuildingGet();
					Player owner = Bld.ownerOBJ;
						owner.resource3 += 4;
				}
				if (T.UnitCount()!=0) {
					Unit U = T.UnitGet();
					Player owner = U.ownerOBJ;
						owner.resource2 += U.supply;
				}
			}
		}
	}
	
	public void AIturn() {
		cycleturn();
		System.out.println("AI Turn Cycled");
	}
	
	
}
