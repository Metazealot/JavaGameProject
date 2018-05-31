package jGameMain;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

import java.io.*;
import java.util.Properties;


public class Display {
	
    final String MAINMENUPANEL = "Main Menu Screen";
    final String SETTINGSMENUPANEL = "Settings Menu Screen";
    final String SETTINGSPANEL2 = "Username Selection Screen";
    final String MULTIPLAYERPANEL = "Multiplayer Connection Screen";
    final String LOBBYPANEL = "Game Lobby";
    final String GAMEPANEL = "Game Screen";
    final String comboBoxItems[] = { MAINMENUPANEL, SETTINGSMENUPANEL, SETTINGSPANEL2, MULTIPLAYERPANEL, LOBBYPANEL, GAMEPANEL };
	int WIDTH = 1000;
	int HEIGHT = 700;
	
	JFrame frame;
    JPanel mainPanel;
    JPanel comboBoxPane;
    JPanel menuPanel;
    JPanel settingsPanel;
    JPanel multiPanel;
    JPanel lobbyPanel;
    JPanel gamePanel;
    JPanel settingsPanel2;
    JPanel cards;
    CardLayout cardindex;
	Canvas canvas;
	String username;
	File configFile;
	BufferStrategy bufferStrategy;
	
	public Display(Conquest con){
		
		//Main Frame Setup
		
				frame = new JFrame("Conquest");
				//frame is the game window itself

				mainPanel = (JPanel) frame.getContentPane();
				mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
				mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
				comboBoxPane = new JPanel(); //use FlowLayout
				
				menuPanel = new JPanel();
				menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
				settingsPanel = new JPanel();
				settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
				multiPanel = new JPanel();
				multiPanel.setLayout(new BoxLayout(multiPanel, BoxLayout.Y_AXIS));
				lobbyPanel = new JPanel();
				lobbyPanel.setLayout(new BoxLayout(lobbyPanel, BoxLayout.Y_AXIS));
				gamePanel = new JPanel();
				gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
				settingsPanel2 = new JPanel();
				settingsPanel2.setLayout(new BoxLayout(settingsPanel2, BoxLayout.Y_AXIS));
				
				cards = new JPanel(new CardLayout());
		        cards.add(menuPanel, MAINMENUPANEL);
		        cards.add(settingsPanel, SETTINGSMENUPANEL);
		        cards.add(settingsPanel2, SETTINGSPANEL2);
		        cards.add(multiPanel, MULTIPLAYERPANEL);
		        cards.add(lobbyPanel, LOBBYPANEL);
		        cards.add(gamePanel, GAMEPANEL);
		         
		        mainPanel.add(comboBoxPane, BorderLayout.PAGE_START);		
		        mainPanel.add(cards,BorderLayout.CENTER);
		        cardindex = (CardLayout)(cards.getLayout()); 
		        
		        username = con.username;
		        configFile = con.configFile;
		                
		        
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
						con.StartLobby();
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
				settingsBmain.setActionCommand("mainmenu");
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
				
				JButton buser=new JButton("Change Username");    
				buser.setBounds(100,100,140, 40);
				buser.setActionCommand("Change Username");
				buser.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cardindex.show(cards, SETTINGSPANEL2);
					}
				});
				settingsPanel.add(buser);
				
		//User Name Selection Page
				
				JButton settings2Bmain=new JButton("Back");    
				settings2Bmain.setBounds(100,100,140, 40);
				settings2Bmain.setActionCommand("settingsmenu");
				settings2Bmain.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cardindex.show(cards, MAINMENUPANEL);
					}
				});
				settingsPanel2.add(settings2Bmain);
				
				JTextField userBox = new JTextField("Enter New Username");
				userBox.setBounds(20,40,40, 20);
				settingsPanel2.add(userBox);

				JButton userRename=new JButton("Done");    
				userRename.setBounds(100,100,140, 40);
				userRename.setActionCommand("ChangeUserComplete");
				userRename.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String tempname = userBox.getText();
						nameLabel.setText(tempname);
						try {
							FileReader reader = new FileReader(configFile);
						    Properties props = new Properties();
						    props.load(reader);
						    props.setProperty("username", tempname);
						    reader.close();
						    FileWriter writer = new FileWriter(configFile);
						    props.store(writer, null);
						    writer.close();
						} catch (FileNotFoundException ex) {
							System.out.print("Config File not found.");
						} catch (IOException ex) {
							System.out.print("I/O Error");
						}
						cardindex.show(cards, SETTINGSMENUPANEL);
						
					}
				});
				settingsPanel2.add(userRename);
				
		//Multiplayer Page
				
				JButton multiBmain=new JButton("Main Menu");    
				multiBmain.setBounds(100,100,140, 40);
				multiBmain.setActionCommand("mainmenu");
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
				lobbyBmain.setActionCommand("menumenu");
				lobbyBmain.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cardindex.show(cards, MAINMENUPANEL);
					}
				});
				lobbyPanel.add(lobbyBmain);
				
				
				
				
		//Game Page
				
				
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
	
}
