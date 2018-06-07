package jGameMain;
import java.util.Random;

public class Player {
    String username = "Player";
    Double resource1 = 0.0;
    Double resource2 = 0.0;
    Double resource3 = 0.0;
    Boolean AI = false;
    Boolean yourturn, actionqueued, order_move, order_attack, order_build;
    Tile Tileselected;
    Integer PlayerID = 0;
    Unit Unitselected;
    Unit Target;
    
    
    public Player(String u) {
    	username = u;
    	actionqueued = false;
		PlayerID = new Random().nextInt(10000);
		yourturn = false;
		actionqueued = false;
		order_move = false;
		order_attack = false;
		order_build = false;
    }
    
    public void selectTile(Tile T) {
    	Tileselected = T;
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
    	order_attack = false;
    	order_build = false;
    	//add any other order types here
    }
    
    public void setorder(int inord) {
    	if(inord==1) {
        	actionqueued = true;
        	order_move = true;
    	} else if (inord==2) {
    		actionqueued = true;
    		order_attack = true;
    	}
    }
    
    public void selectUnit(Unit U) {
    	Unitselected = U;
    }
    
    public void targetUnit(Unit U) {
    	Target = U;
    }
}
