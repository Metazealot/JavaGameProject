package jGameMain;
import java.util.*;

public class Lobby {
	
	LinkedList<Player> playerList;
	Integer AICount;
	Conquest con;
	
	public Lobby (Player Host, Conquest conin) {
		con = conin;
		playerList = new LinkedList<Player>();
		playerList.add(Host);
		con.maindisplay.initLobby(playerList.toArray(new Player [4]));
		AICount = 0;

//When a Game object is created by the Lobby it will read the Width and Height values
//The map size is effectively customizable here, in the lobby.
	}
	
	public void addAI() {
		if (playerList.size() < 4) {
			AICount += 1;
			Player newplayer = new Player("AI " + Integer.toString(AICount));
			newplayer.AI = true;
			playerList.add(newplayer);
			con.maindisplay.updateLobby(playerList.toArray(new Player [4]));
		} else {
			System.out.print("Maximum player count reached");
		}
	}
	
	public void kick(Integer x) {
		if (playerList.size() >= x) {
			playerList.remove(x);
			con.maindisplay.updateLobby(playerList.toArray(new Player [4]));
		} else {
			System.out.print("No player in this slot");
		}
	}
}
