package jGameMain;

public class Player {
    String username = "Player";
    Double resource1 = 0.0;
    Double resource2 = 0.0;
    Double resource3 = 0.0;
    Boolean AI = false;
    Boolean yourturn, actionqueued, order_move, order_rangeattack, order_build = false;
    Integer[] Tileselected;
    Integer PlayerID = 0;
    
    
    public Player(String u) {
    	username = u;
    	actionqueued = false;
    	Tileselected = new Integer[]{0,0};
    }
    
    public void selectTile(Integer x, Integer y) {
    	Tileselected = new Integer[] {x,y};
    }
    
    public void SetID(Integer v) {
    	PlayerID = v;
    }
    
    public void turnOn() {
    	yourturn = true;
    }
    public void turnOff() {
    	yourturn = false;
    }
    public void clearorders() {
    	actionqueued = false;
    	order_move = false;
    	order_rangeattack = false;
    	order_build = false;
    	//add any other order types here
    }
    
    public void setorder(int inord) {
    	if(inord==1) {
        	actionqueued = true;
        	order_move = true;
    	}
    }
}
