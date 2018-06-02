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
       //con.maindisplay.UpdateDisplay();
       
       
       
       //All primary button logic will go below.
       
       user = con.host;
       currPlayer = con.currentGame.getCurrentPlayer(); //This tells whose turn it is currently.
       if (user==currPlayer) {
    	   System.out.print("It is your turn, player "+ currPlayer.PlayerID);
    	   checkturn = true;
       }else {
    	   System.out.print("It is not your turn.\n");
    	   System.out.print("User is " + user.username);
    	   System.out.print(", current turn player is " + currPlayer.username);
    	   checkturn = false;
       }
       
       actionqueued = user.actionqueued;
       
       if(!actionqueued){
    	   //If no action is queued then display tile info on button click.
    	   con.maindisplay.UpdateSidePanel(tileref);
    	   currPlayer.selectTile(xloc,yloc);
    	   //display panel logic goes here
       } else if(user.order_move == true) {
    	   //Unit movement logic
       } else if (user.order_rangeattack == true){
    	   //Ranged attack logic
       } else if (user.order_build == true) {
    	   //Building Logic
       }
       
       
       
    }

}