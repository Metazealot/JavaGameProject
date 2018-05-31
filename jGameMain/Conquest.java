package jGameMain;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.io.*;
import java.util.Properties;

//this version is updated

public class Conquest implements Runnable{
	

	String directory = System.getProperty("user.dir");
	String settingsfile = (directory + "\\Settings.txt");
   
//Properties of Conquest.java, all variables.
	



	File configFile;
	Display maindisplay;
	String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	String configPath;
	String username;
    Lobby currentLobby;
    Game currentGame;
    Integer FrameWidth;
    Integer FrameHeight;
    Integer GameWidth;
    Integer GameHeight;
	
	public Conquest(){
		//This is the constructor, activated when a Conquest class object is instantiated.
		
		
//Loading settings from config file	
		
		configPath = (rootPath + "jGameMain\\config.properties");
		configFile = new File(configPath);
		username = "DefaultName";
		FrameWidth = 1000;
		FrameHeight = 710;
		GameWidth = 20;
		GameHeight = 20;
		
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);		 
		    username = props.getProperty("username");
		    FrameWidth = Integer.parseInt(props.getProperty("FWidth"));
		    FrameHeight = Integer.parseInt(props.getProperty("FHeight"));
		    GameWidth = Integer.parseInt(props.getProperty("GWidth"));
		    GameHeight = Integer.parseInt(props.getProperty("GHeight"));
		    reader.close();
		} catch (FileNotFoundException ex) {
			System.out.print("Config File not found.");
		} catch (IOException ex) {
			System.out.print("I/O Error");
		}
		
		maindisplay = new Display(this);
		

   }
	
	public void StartLobby() {
		Player hostplayer = new Player();
		currentLobby = new Lobby (hostplayer, this);
	}
	
	public void StartGame(Player[] playerArr) {
		currentGame = new Game (playerArr, this, GameWidth, GameHeight);
	}

	
	long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
	boolean running = true;
	private double x = 0;
   
	public void run(){
      
		//Primary runnable. This initiates action of the Conquest class
		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;
      
		while(running){
			beginLoopTime = System.nanoTime();
         
			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
         
			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;
           
			if(deltaLoop > desiredDeltaLoop){
				//Do nothing. We are already late.
			}else{
				try{
					Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
				}catch(InterruptedException e){
                   //Do nothing
               }
           }
		}
	}
	
	protected void update(int deltaTime){
		//Time operator. May end up removing this section as it was designed for real-time system alterations.
		//However, it could be useful for animation purposes.
		x += deltaTime * 0.2;
		while(x > 500){
			x -= 500;
			
		}
	}

	public static void main(String [] args){
		//Executes on program start, creating a new instance of the game itself.
		Conquest ex = new Conquest();
		new Thread(ex).start();
	}
	
}


