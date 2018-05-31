package jGameMain;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.io.*;
import java.util.Properties;


public class Lobby {
	
	public Lobby (Player Host, Conquest con) {
		final String GAMEPANEL = "Game Screen";
		Player[] playerList = new Player[4];
		playerList[0] = Host;
		Integer Width = 20;
		Integer Height = 20;
		try {
		    FileReader reader = new FileReader(con.configFile);
		    Properties props = new Properties();
		    props.load(reader);		 
		    String WidthS = props.getProperty("GWidth");
		    Width = Integer.parseInt(WidthS);
		    String HeightS = props.getProperty("GHeight");
		    Height = Integer.parseInt(HeightS);
		    reader.close();
		} catch (FileNotFoundException ex) {
			System.out.print("Config File not found.");
		} catch (IOException ex) {
			System.out.print("I/O Error");
		}
		
		JButton Bstartgame=new JButton("Start Game");    
		Bstartgame.setBounds(100,100,140, 40);
		Bstartgame.setActionCommand("menumenu");
		Bstartgame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				con.maindisplay.cardindex.show(con.maindisplay.cards, GAMEPANEL);
			}
		});
		con.maindisplay.lobbyPanel.add(Bstartgame);

//When a Game object is created by the Lobby it will read the Width and Height values
//The map size is effectively customizable here, in the lobby.
	}
	
	public void start(String [] args){
		
	}
	
}
