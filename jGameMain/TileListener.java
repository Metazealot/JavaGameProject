package jGameMain;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

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
		   Path MPath = new Path(currPlayer.Tileselected,tileref,con.currentGame.gameBoard);
		   if (MPath.Length == -1) {
			   System.out.print("Cannot path to that location.\n"); 
		   } else {
			   if (MPath.Length == 0) {
				   System.out.print("Cannot move to the same tile the unit is in.\n");
			   } else {
				   if (MPath.Length > currPlayer.Unitselected.MoveRange) {
					   System.out.print("That is too far away to move!\n"); 
				   } else {
					   if (MPath.Length > currPlayer.Unitselected.MoveLeft) {
						   System.out.print("Not enough movement available.\n");
					   } else {
						   if (tileref.UnitCount() != 0){
							   System.out.print("There is already a unit there.\n");
						   } else {
							   if ((tileref.TileID ==4)|(tileref.TileID == 3)){
								   System.out.print("You cannot move into that tile.\n");  
							   } else {
								   currPlayer.Unitselected.ReduceMoves(MPath.Length);
								   currPlayer.Tileselected.MoveUnit(tileref);
								   currPlayer.clearorders();
								   con.maindisplay.UpdateSidePanel(tileref);
								   currPlayer.selectTile(tileref);
								   for (final Tile T: MPath.TileContainer) {
									   con.maindisplay.gamebuttons[T.xloc][T.yloc].setBackground(Color.CYAN);
								   }
							   }
						   }
					   }
    		   		}
		   		
    	   		}
		   }
       } else if (user.order_attack == true){
    	   if(tileref.UnitCount() == 0) {
    		   System.out.print("There is no unit to target here.\n");
    	   } else {
    		   if (tileref.UnitGet().ownerID == currPlayer.Unitselected.ownerID) {
    			   System.out.print("You cannot attack your own forces.\n");
    		   } else {
    			   if (currPlayer.Unitselected.MoveLeft == 0) {
    				   System.out.print("Unit must have a move remaining in order to attack.\n");
    			   } else {
    		    	   if (currPlayer.Unitselected.Ranged == false) {
    		    		   //melee logic
    		    		   if (meleeDistance(currPlayer.Tileselected,tileref) > 1){
    		    			   System.out.print("Target must be adjacent for a melee unit to attack.\n");
    		    		   } else {
    		    			   //EXECUTE ATTACK M
    		    			   Integer atk = currPlayer.Unitselected.attack(tileref, 1.0);
    		    			   if( atk == 1) { tileref.UnitGet().attack(currPlayer.Tileselected, 0.5); }
    		    			   currPlayer.clearorders();
							   con.maindisplay.UpdateSidePanel(currPlayer.Tileselected);
    		    		   }
    		    	   } else {
    		    		   //ranged logic
    		    		   if (rangedDistance(currPlayer.Tileselected,tileref) > currPlayer.Unitselected.AttackRange){
    		    			   System.out.print("Target is out of this unit's range.\n");
    		    		   } else {
    		    			 //EXECUTE ATTACK R
    		    			   currPlayer.Unitselected.attack(tileref, 1.0);
    		    			   currPlayer.clearorders();
							   con.maindisplay.UpdateSidePanel(currPlayer.Tileselected);
    		    		   }
    		    	   }
    			   }
    		   }
    	   }
       } else if (user.order_build == true) {
    	   //Building Logic
       }
       

       
    }
    
    public Double rangedDistance(Tile A, Tile B) {
   		Double diffx, diffy = 0.0;
   		diffx = ((double)A.xloc-(double)B.xloc);
   		if (diffx<0) { diffx = diffx * -1.0; }
   		diffy = ((double)A.yloc-(double)B.yloc);
   		if (diffy<0) { diffy = diffy * -1.0; }
   		if (diffx > diffy) {
   			return diffx;
   		} else {
   			return diffy;
   		}
    }
    
    public Double meleeDistance(Tile A, Tile B) {
   		Double diffx, diffy = 0.0;
   		diffx = ((double)A.xloc-(double)B.xloc);
   		if (diffx<0) { diffx = diffx * -1.0; }
   		diffy = ((double)A.yloc-(double)B.yloc);
   		if (diffy<0) { diffy = diffy * -1.0; }
   		return (diffx+diffy);
    }
    
}