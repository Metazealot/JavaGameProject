package jGameMain;
import java.util.*;
import jGameMain.Buildings.*;
import jGameMain.Units.*;

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
		gameBoard.chosenspot.CreateBuilding(new City());
		gameBoard.chosenspot.BuildingGet().setOwner(originhost);
		originhost.selectTile(gameBoard.chosenspot);
		con.maindisplay.UpdateSidePanel(gameBoard.chosenspot);
		if (players.size() > 1) {
			if (gameBoard.EnemyPlacements.size() > 0){
				for (Tile T: gameBoard.EnemyPlacements) {
					T.CreateUnit(new Infantry());
					Unit U = T.UnitGet();
					U.setOwner(players.get(1));
					Units.add(U);
				}
			}
		}
		
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
				if(U.ownerOBJ == turnorder.get(0)){
					U.MoveLeft = U.MoveRange;
					if (U.HealthCurrent < U.HealthMax) {
						U.HealthCurrent += 1.0;
						Tile T = gameBoard.tileArray[U.xloc][U.yloc];
						if (T.BuildingCount() != 0) {
							if (T.BuildingGet().ownerOBJ == U.ownerOBJ){
								if (U.HealthCurrent < U.HealthMax) {
									U.HealthCurrent += 1.0;
								}
							}
						}
					}
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
						if (Bld.BuildingName == "City") {activeplayer.resource1 += 5;}
						else if (Bld.BuildingName == "Granary") {activeplayer.resource1 += 2;}
						else if (Bld.BuildingName == "Lumbermill") {activeplayer.resource1 += 2;}
						else if (Bld.BuildingName == "Mine") {activeplayer.resource1 += 3;}
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
						if (Bld.BuildingName=="City") {
							owner.resource3 += 4;
						} else if (Bld.BuildingName =="Farm") {
							owner.resource3 += 2;
							if (T.TileID == 0){
								owner.resource3 += 1;
							}
						}
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
