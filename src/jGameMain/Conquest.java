package jGameMain;
import java.util.*;
import java.io.*;
import java.util.Properties;

//to compile into an executable, run the following in a command line in the current folder:
// jar -cvmf manifest.txt conquest.jar *.class

public class Conquest implements Runnable{
	

	String directory = System.getProperty("user.dir");
	String settingsfile = (directory + "\\Settings.txt");
   
//Properties of Conquest.java, all variables.
	



	File configFile;
	Display maindisplay;
	String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	//String configPath;
	String username;
    Lobby currentLobby;
    Game currentGame;
    Integer FrameWidth;
    Integer FrameHeight;
    Integer GameWidth;
    Integer GameHeight;
    Player host;
    public java.net.URL configURL;
	boolean gameactive;
    
	public Conquest(){
		//This is the constructor, activated when a Conquest class object is instantiated.
		
		
//Loading settings from configuration file	
		
		configURL = getClass().getResource("config.properties");
		System.out.println(rootPath);
		System.out.println(configURL.getPath());
		try {
			configFile = new File(rootPath + "//jGameMain//config.properties");
			//configFile = new File(configURL.getPath());
		} catch (NullPointerException ex) {
			try {
			configFile = new File(configURL.getPath());
			} catch (NullPointerException ex2) {
				
			}
		}
		//configPath = (rootPath + "jGameMain\\config.properties");
		//configFile = new File(configPath);
		username = "DefaultName";
		FrameWidth = 1000;
		FrameHeight = 710;
		GameWidth = 20;
		GameHeight = 20;
		
		
		try {
			InputStream inpt = getClass().getResourceAsStream("config.properties"); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(inpt));
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
		
		try {
		    //FileReader reader = new FileReader(configFile);
			InputStream inpt = getClass().getResourceAsStream("config.properties"); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(inpt));
		    Properties props = new Properties();
		    props.load(reader);		 
		    username = props.getProperty("username");
		    reader.close();
		    host = new Player(username);
			currentLobby = new Lobby (host, this);
		} catch (FileNotFoundException ex) {
			System.out.print("Config File not found.");
			host = new Player(rootPath + configURL.getPath());
			currentLobby = new Lobby (host, this);
		} catch (IOException ex) {
			System.out.print("I/O Error");
			host = new Player("IOERR");
			currentLobby = new Lobby (host, this);
		}

	}
	
	public void StartGame(LinkedList<Player> playerList) {
		try {
		    gameactive = true;
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);		 
		    String WidthS = props.getProperty("GWidth");
		    GameWidth = Integer.parseInt(WidthS);
		    String HeightS = props.getProperty("GHeight");
		    GameHeight = Integer.parseInt(HeightS);
		    reader.close();
		} catch (FileNotFoundException ex) {
			System.out.print("Config File not found.");
		} catch (IOException ex) {
			System.out.print("I/O Error");
		}
		for(int x = 0; x < playerList.size(); x++){ 
			playerList.get(x).SetID((x+1));
		}
		currentGame = new Game (playerList, this, GameWidth, GameHeight);
	}
	
	long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
	boolean running = true;
	private double x = 0;
   
	public void run(){
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
		x += deltaTime * 1;
		while(x > 100){
			if (gameactive==true) {
				try {
					maindisplay.UpdateDisplay();
					currentGame.supplyscan();
				} catch (NullPointerException ex) {
					System.out.print("Display Update Pending\n");
					
					try {
						maindisplay.warningbox.setText("Display Update Pending");

					} catch (NullPointerException ex2) {
						
					}
				} 
				
			}
			x -= 100;
		}
	}

	public static void main(String [] args){
		//Executes on program start, creating a new instance of the game itself.
		Conquest ex = new Conquest();
		new Thread(ex).start();
	}
	
}


