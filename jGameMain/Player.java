package jGameMain;

public class Player {
    String username = "Player";
    Double resource1 = 0.0;
    Double resource2 = 0.0;
    Double resource3 = 0.0;
    Boolean AI;
    Boolean actionqueued;
    
    public Player(String u) {
    	username = u;
    	actionqueued = false;
    }

    
}
