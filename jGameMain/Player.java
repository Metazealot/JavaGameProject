package jGameMain;

public class Player {
    String username = "Player";
    Double resource1 = 0.0;
    Double resource2 = 0.0;
    Double resource3 = 0.0;
    Boolean AI;
    Boolean actionqueued;
    Integer[] Tileselected;
    
    public Player(String u) {
    	username = u;
    	actionqueued = false;
    	Tileselected = new Integer[]{0,0};
    }
    
    public void selectTile(Integer x, Integer y) {
    	Tileselected = new Integer[] {x,y};
    }
    
    
}
