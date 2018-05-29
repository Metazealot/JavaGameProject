package jGameMain;
import java.awt.*;
import java.awt.event.*;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import javax.swing.*;

//this version is updated

public class GameTest implements Runnable{
	
	final int WIDTH = 1000;
	final int HEIGHT = 700;
   
	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	
	public GameTest(){
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
		JPanel settingsPanel = new JPanel();
		JPanel multiPanel = new JPanel();
		JPanel lobbyPanel = new JPanel();
		JPanel gamePanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		
		JPanel cards = new JPanel(new CardLayout());
        cards.add(menuPanel, MAINMENUPANEL);
        cards.add(settingsPanel, SETTINGSMENUPANEL);
         
        mainPanel.add(comboBoxPane, BorderLayout.PAGE_START);		
        mainPanel.add(cards,BorderLayout.CENTER);
        CardLayout cardindex = (CardLayout)(cards.getLayout());
       
		
        
        
        
		
        JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("CONQUEST");
		titleLabel.setFont(new Font("Verdana",1,20));
		titlePanel.add(titleLabel);	
		menuPanel.add(titlePanel);
		
		JButton b01=new JButton("Create Game Lobby");    
		b01.setBounds(100,100,140, 40);
		//b01.setMnemonic(KeyEvent.GAMESTART);
		b01.setActionCommand("startlobby");
		//b01.addActionListener(this);
		menuPanel.add(b01);

		JButton b02=new JButton("Connect to IP");    
		b02.setBounds(100,100,140, 40);
		//b02.setMnemonic(KeyEvent.GAMEMULTI);
		b02.setActionCommand("connectmulti");
		//b02.addActionListener(this);
		menuPanel.add(b02);

		JButton b03=new JButton("Settings");    
		b03.setBounds(100,100,140, 40);
		//b03.setMnemonic(KeyEvent.QUIT);
		b03.setActionCommand("settingsmenu");
		b03.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardindex.show(cards, SETTINGSMENUPANEL);
			}
		});
		menuPanel.add(b03);
		
		JButton b04=new JButton("Quit");    
		b04.setBounds(100,100,140, 40);
		//b04.setMnemonic(KeyEvent.QUIT);
		b04.setActionCommand("quitgame");
		b04.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuPanel.add(b04);
		
		JButton bmain=new JButton("Main Menu");    
		bmain.setBounds(100,100,140, 40);
		//b03.setMnemonic(KeyEvent.QUIT);
		bmain.setActionCommand("settingsmenu");
		bmain.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardindex.show(cards, MAINMENUPANEL);
			}
		});
		settingsPanel.add(bmain);
		
		JButton b05=new JButton("Change Resolution");    
		b05.setBounds(100,100,140, 40);
		//b01.setMnemonic(KeyEvent.GAMESTART);
		b05.setActionCommand("startlobby");
		//b01.addActionListener(this);
		settingsPanel.add(b05);
		
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
		x += deltaTime * 0.2;
		while(x > 500){
			x -= 500;
			
		}
	}

	public static void main(String [] args){
		GameTest ex = new GameTest();
		new Thread(ex).start();
	}
	
}


