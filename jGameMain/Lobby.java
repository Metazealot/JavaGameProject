package jGameMain;
import java.util.*;

public class Lobby {
	
	LinkedList<Player> playerList;
	
	public Lobby (Player Host, Conquest con) {
		playerList = new LinkedList<Player>();
		playerList.add(Host);
		con.maindisplay.updateLobby(playerList.toArray(new Player [4]));
		


//When a Game object is created by the Lobby it will read the Width and Height values
//The map size is effectively customizable here, in the lobby.
	}
	
	public void start(String [] args){
		
	}
	
	public void addAI() {
		playerList.add(new Player("AI"));
		
	}
}
