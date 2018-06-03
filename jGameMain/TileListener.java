package jGameMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TileListener implements ActionListener {
	int xloc = 0;
	int yloc = 0;
	Conquest con;
	Player currPlayer;
	Player user;
	Boolean checkturn;
	Boolean actionqueued;
	Tile tileref;
	
    public void setInfo(Integer xin, Integer yin, Conquest conin){
    	xloc = xin;
    	yloc = yin;
    	con = conin;
    }
    
    public void actionPerformed(ActionEvent e)
    {
		try {
			tileref = con.currentGame.gameBoard.tileArray[xloc][yloc];
		} catch (NullPointerException ex) {
			System.out.print("No Tile Exists.");
		}
		
       //Temporary console visualization to determine that this is reading the correct values. Clicking a button on the display
       //causes the console to print off this information.
       System.out.print("The tile you have selected is at "+ Integer.toString(xloc)+", "+Integer.toString(yloc)+".\n");
       System.out.print("This tile is a " + tileref.TileName + ".\n");
       
       
       
       //All primary button logic will go below.
       
       user = con.host;
       currPlayer = con.currentGame.getCurrentPlayer(); //This tells whose turn it is currently.
       if (user==currPlayer) {
    	   System.out.print("It is your turn, player "+ currPlayer.PlayerID + "\n");
    	   checkturn = true;
       }else {
    	   System.out.print("It is not your turn.\n");
    	   System.out.print("User is " + user.username);
    	   System.out.print(", current turn player is " + currPlayer.username + "\n");
    	   checkturn = false;
       }
       
       actionqueued = user.actionqueued;
       
       if(!actionqueued){
    	   //If no action is queued then display tile info on button click.
    	   con.maindisplay.UpdateSidePanel(tileref);
    	   tileref.selected = true;
    	   currPlayer.selectTile(tileref);
    	   //display panel logic goes here
       } else if(user.order_move == true) {
    	   //Unit movement logic
    	   Double distance = tileDistance(currPlayer.Tileselected,tileref);
    	   if (distance !=0) {
    		   if (distance <= currPlayer.Unitselected.MoveRange) {
    			   if (tileref.UnitCount() == 0){
    				   if ((tileref.TileID !=4)&(tileref.TileID != 3)){
    					   currPlayer.Tileselected.MoveUnit(tileref);
        				   currPlayer.clearorders();
        				   con.maindisplay.UpdateSidePanel(tileref);
        				   currPlayer.selectTile(tileref);
    				   } else {
    					   System.out.print("You cannot move into that tile.\n");  
    				   }
    			   } else {
    				   System.out.print("There is already a unit there.\n");
    				   //Attack move logic will eventually go here
    			   }
    		   } else {
    			   System.out.print("That is too far away to move!\n"); 
    		   }
    	   } else {
    		   System.out.print("Cannot move to the same tile.\n");
    	   }
    	   
       } else if (user.order_rangeattack == true){
    	   //Ranged attack logic
       } else if (user.order_build == true) {
    	   //Building Logic
       }
       

       
    }
    
    public Double tileDistance(Tile A, Tile B) {
   		Double diffx, diffy = 0.0;
   		diffx = ((double)A.xloc-(double)B.xloc);
   		if (diffx<0) { diffx = diffx * -1.0; }
   		diffy = ((double)A.yloc-(double)B.yloc);
   		if (diffy<0) { diffy = diffy * -1.0; }
   		return (diffx+diffy);
    }
    
    public Boolean path(Tile origin, Tile destination) {
    	
    	
    	return false;
    }
    
}