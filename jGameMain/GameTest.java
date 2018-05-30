package jGameMain;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.io.*;
import java.util.Properties;

//this version is updated

public class GameTest implements Runnable{
	
	final int WIDTH = 1000;
	final int HEIGHT = 700;
	String directory = System.getProperty("user.dir");
	String settingsfile = (directory + "\\Settings.txt");
   
	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	
	public GameTest(){
		//This is the constructor, activated when a GameTest class object is instantiated.
		
		
//Loading settings from config file		
		
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String configPath = rootPath + "jGameMain\\config.properties";
		File configFile = new File(configPath);
		
		String username = "DefaultName";
		
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);		 
		    username = props.getProperty("username");
		    reader.close();
		} catch (FileNotFoundException ex) {
			System.out.print("Config File not found.");
		} catch (IOException ex) {
			System.out.print("I/O Error");
		}
		
//Main Frame Setup
		
		frame = new JFrame("Conquest");
	    final String MAINMENUPANEL = "Main Menu Screen";
	    final String SETTINGSMENUPANEL = "Settings Menu Screen";
	    final String MULTIPLAYERPANEL = "Multiplayer Connection Screen";
	    final String LOBBYPANEL = "Game Lobby";
	    final String GAMEPANEL = "Game Screen";
		//frame is the game window itself
		JPanel mainPanel = (JPanel) frame.getContentPane();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { MAINMENUPANEL, SETTINGSMENUPANEL, MULTIPLAYERPANEL, LOBBYPANEL, GAMEPANEL };
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		JPanel multiPanel = new JPanel();
		multiPanel.setLayout(new BoxLayout(multiPanel, BoxLayout.Y_AXIS));
		JPanel lobbyPanel = new JPanel();
		lobbyPanel.setLayout(new BoxLayout(lobbyPanel, BoxLayout.Y_AXIS));
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

		
		JPanel cards = new JPanel(new CardLayout());
        cards.add(menuPanel, MAINMENUPANEL);
        cards.add(settingsPanel, SETTINGSMENUPANEL);
        cards.add(multiPanel, MULTIPLAYERPANEL);
        cards.add(lobbyPanel, LOBBYPANEL);
        cards.add(gamePanel, GAMEPANEL);
         
        mainPanel.add(comboBoxPane, BorderLayout.PAGE_START);		
        mainPanel.add(cards,BorderLayout.CENTER);
        CardLayout cardindex = (CardLayout)(cards.getLayout());        
        
        
        
//Main Menu       
		
        JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("CONQUEST");
		titleLabel.setFont(new Font("Verdana",1,20));
		titlePanel.add(titleLabel);	
		menuPanel.add(titlePanel);
		JLabel nameLabel = new JLabel("Username: " + username);
		nameLabel.setFont(new Font("Verdana",1,14));
		menuPanel.add(nameLabel);
		
		JButton b01=new JButton("Create Game Lobby");    
		b01.setBounds(100,100,140, 40);
		b01.setActionCommand("startlobby");
		b01.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardindex.show(cards, LOBBYPANEL);
			}
		});
		menuPanel.add(b01);

		JButton b02=new JButton("Connect to IP");    
		b02.setBounds(100,100,140, 40);
		b02.setActionCommand("connectmulti");
		b02.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardindex.show(cards, MULTIPLAYERPANEL);
			}
		});
		menuPanel.add(b02);

		JButton b03=new JButton("Settings");    
		b03.setBounds(100,100,140, 40);
		b03.setActionCommand("settingsmenu");
		b03.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardindex.show(cards, SETTINGSMENUPANEL);
			}
		});
		menuPanel.add(b03);
		
		JButton b04=new JButton("Quit");    
		b04.setBounds(100,100,140, 40);
		b04.setActionCommand("quitgame");
		b04.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuPanel.add(b04);
		
		
		
		
//Settings Page	
		
		JButton settingsBmain=new JButton("Main Menu");    
		settingsBmain.setBounds(100,100,140, 40);
		settingsBmain.setActionCommand("settingsmenu");
		settingsBmain.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardindex.show(cards, MAINMENUPANEL);
			}
		});
		settingsPanel.add(settingsBmain);
		
		JButton b05=new JButton("Change Resolution");    
		b05.setBounds(100,100,140, 40);
		b05.setActionCommand("Change Resolution");
		//b01.addActionListener(this);
		settingsPanel.add(b05);
		
		
		
//Multiplayer Page
		
		JButton multiBmain=new JButton("Main Menu");    
		multiBmain.setBounds(100,100,140, 40);
		multiBmain.setActionCommand("settingsmenu");
		multiBmain.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardindex.show(cards, MAINMENUPANEL);
			}
		});
		multiPanel.add(multiBmain);
		
		JTextField ipTextBox = new JTextField("Enter IP Address");
		ipTextBox.setBounds(100,100,140, 40);
		multiPanel.add(ipTextBox);
		
		JButton multiConnect=new JButton("Connect");    
		multiConnect.setBounds(100,100,140, 40);
		multiConnect.setActionCommand("Connect to IP");
		//b01.addActionListener(this);
		multiPanel.add(multiConnect);
		
		
//Lobby Page		
		
		JButton lobbyBmain=new JButton("Main Menu");    
		lobbyBmain.setBounds(100,100,140, 40);
		lobbyBmain.setActionCommand("settingsmenu");
		lobbyBmain.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardindex.show(cards, MAINMENUPANEL);
			}
		});
		lobbyPanel.add(lobbyBmain);
		
		
		
		
		
//Background mouse detection setup and frame config		
		
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
      
        mainPanel.add(canvas);
      
        canvas.addMouseListener(new MouseControl());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
      
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
      
        canvas.requestFocus();
   }
	
	private class MouseControl extends MouseAdapter{
      
   }
	
	long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
	boolean running = true;
	   private double x = 0;
   
	public void run(){
      
		//Primary runnable. This initiates action of the GameTest class
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
		GameTest ex = new GameTest();
		new Thread(ex).start();
	}
	
}


