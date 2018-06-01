package jGameMain;

public class Lobby {
	
	Player[] playerArr;
	
	public Lobby (Player Host, Conquest con) {
		playerArr = new Player[4];
		playerArr[0] = Host;
		con.maindisplay.updateLobby(playerArr);
		


//When a Game object is created by the Lobby it will read the Width and Height values
//The map size is effectively customizable here, in the lobby.
	}
	
	public void start(String [] args){
		
	}
	
}
