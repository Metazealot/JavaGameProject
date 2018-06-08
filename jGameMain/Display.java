package jGameMain;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import jGameMain.Units.*;
import java.io.*;

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
	int HEIGHT = 710;
	
	JFrame frame;
    JPanel mainPanel;
    JPanel comboBoxPane;
    JPanel cards;
    JPanel menuPanel;
    JPanel titlePanel;
    JPanel settingsPanel;
    JPanel settingsPanel2;
    JPanel multiPanel;
    JPanel lobbyPanel;
    JPanel gamePanel;
    
    JPanel TOPpanel;	//Three sections for main game display
    JPanel MIDDLEpanel;
    JPanel BOTTOMpanel;
    JPanel TOPL; //Dropdown Menu
    JPanel TOPR; //Resource Bar
    JPanel MIDDLEL; //Icon Displays
    JPanel MIDDLER; //Game Display
    JPanel BOTTOML; //Minimap
    JPanel BOTTOMR; //Command Card
    JPanel TILEINFO; //Shows current tile information
    JPanel UNITINFO; //Shows unit on currently selected tile
    JPanel boardpanel;
    JPanel lobbylist;
    
    JScrollPane boardscroller;
    
    JLabel titleLabel;
    JLabel nameLabel;
    JLabel[] players;
	JTextArea tiledesc;
	JTextArea unitdesc;
	JTextArea warningbox;
    
    JButton[][] gamebuttons;
    JButton oldTileRef;
    TileListener buttonlisteners[][];
    
    CardLayout cardindex;
	Canvas canvas;
	String username;
	File configFile;
	BufferStrategy bufferStrategy;
	Conquest con;
	
	public Display(Conquest conin){
		
		//Main Frame Setup
				con = conin;
				frame = new JFrame("Conquest");
				//frame is the game window itself
				tiledesc = new JTextArea(15,15);
				unitdesc = new JTextArea(15,15);

				mainPanel = (JPanel) frame.getContentPane();
				mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
				mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
				comboBoxPane = new JPanel(); //use FlowLayout
				
				menuPanel = new JPanel();
				menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
				menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				settingsPanel = new JPanel();
				settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
				settingsPanel2 = new JPanel();
				settingsPanel2.setLayout(new BoxLayout(settingsPanel2, BoxLayout.Y_AXIS));
				multiPanel = new JPanel();
				multiPanel.setLayout(new BoxLayout(multiPanel, BoxLayout.Y_AXIS));
				lobbyPanel = new JPanel();
				lobbyPanel.setLayout(new BoxLayout(lobbyPanel, BoxLayout.Y_AXIS));
				
				gamePanel = new JPanel();
				TOPpanel = new JPanel();
				TOPpanel.setBorder(BorderFactory.createLineBorder(Color.black));
				gamePanel.add(TOPpanel);
				MIDDLEpanel = new JPanel();
				MIDDLEpanel.setBorder(BorderFactory.createLineBorder(Color.black));
				gamePanel.add(MIDDLEpanel);
				BOTTOMpanel = new JPanel();
				BOTTOMpanel.setBorder(BorderFactory.createLineBorder(Color.black));
				gamePanel.add(BOTTOMpanel);
				gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
				
				TOPL = new JPanel();
				TOPL.setBorder(BorderFactory.createLineBorder(Color.black));
				TOPL.setLayout(new BoxLayout(TOPL, BoxLayout.PAGE_AXIS));
				TOPL.setSize(1000,250);
				TOPL.setMinimumSize(new Dimension(200, 100));
				TOPL.setPreferredSize(new Dimension(200, 100));
				TOPL.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				TOPR = new JPanel();
				TOPR.setMinimumSize(new Dimension(800, 100));
				TOPR.setPreferredSize(new Dimension(800, 100));
				TOPR.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				TOPR.setBorder(BorderFactory.createLineBorder(Color.black));
				TOPpanel.add(TOPL);
				TOPpanel.add(TOPR);
				TOPpanel.setLayout(new BoxLayout(TOPpanel, BoxLayout.X_AXIS));
				
				MIDDLEL = new JPanel();
				MIDDLEL.setMinimumSize(new Dimension(200, 500));
				MIDDLEL.setPreferredSize(new Dimension(200, 500));
				MIDDLEL.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				MIDDLEL.setBorder(BorderFactory.createLineBorder(Color.black));
				TILEINFO = new JPanel();
				UNITINFO = new JPanel();
				TILEINFO.setPreferredSize(new Dimension(200, 250));
				TILEINFO.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				TILEINFO.setBorder(BorderFactory.createLineBorder(Color.black));
				
				tiledesc.setEditable(false);
				unitdesc.setEditable(false);

				UNITINFO.setPreferredSize(new Dimension(200, 250));
				UNITINFO.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				UNITINFO.setBorder(BorderFactory.createLineBorder(Color.black));
				UNITINFO.add(unitdesc);
				MIDDLEL.add(TILEINFO);
				MIDDLEL.add(UNITINFO);
				MIDDLEL.setLayout(new BoxLayout(MIDDLEL, BoxLayout.Y_AXIS));
				MIDDLER = new JPanel();
				MIDDLER.setMinimumSize(new Dimension(800, 500));
				MIDDLER.setPreferredSize(new Dimension(800, 500));
				MIDDLER.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				MIDDLER.setBorder(BorderFactory.createLineBorder(Color.black));
				boardpanel = new JPanel();
				boardpanel.setPreferredSize(new Dimension(2000, 2000));
				boardscroller = new JScrollPane(boardpanel);
				boardscroller.setPreferredSize(new Dimension(800,500));
				JScrollBar vertical = boardscroller.getVerticalScrollBar();
				InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
				im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
				im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
				JScrollBar horizontal = boardscroller.getHorizontalScrollBar();
				InputMap im2 = horizontal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
				im2.put(KeyStroke.getKeyStroke("RIGHT"), "positiveUnitIncrement");
				im2.put(KeyStroke.getKeyStroke("LEFT"), "negativeUnitIncrement");
				boardscroller.getVerticalScrollBar().setUnitIncrement(16);
				boardscroller.getHorizontalScrollBar().setUnitIncrement(16);
				MIDDLER.add(boardscroller);
				MIDDLEpanel.add(MIDDLEL);
				MIDDLEpanel.add(MIDDLER);
				MIDDLEpanel.setLayout(new BoxLayout(MIDDLEpanel, BoxLayout.X_AXIS));

				BOTTOML = new JPanel();
				BOTTOML.setBorder(BorderFactory.createLineBorder(Color.black));
				BOTTOML.setMinimumSize(new Dimension(200, 100));
				BOTTOML.setPreferredSize(new Dimension(200, 100));
				BOTTOML.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				BOTTOMpanel.add(BOTTOML);
				BOTTOMR = new JPanel();
				BOTTOMR.setBorder(BorderFactory.createLineBorder(Color.black));
				BOTTOMR.setMinimumSize(new Dimension(800, 100));
				BOTTOMR.setPreferredSize(new Dimension(800, 100));
				BOTTOMR.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				BOTTOMpanel.add(BOTTOMR);
				BOTTOMpanel.setLayout(new BoxLayout(BOTTOMpanel, BoxLayout.X_AXIS));

				
				
				
				
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
				
		        titlePanel = new JPanel();
				titleLabel = new JLabel("CONQUEST");
				titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
				titleLabel.setFont(new Font("Verdana",1,20));
				titlePanel.add(titleLabel);	
				titlePanel.setMinimumSize(new Dimension(600, 50));
				titlePanel.setPreferredSize(new Dimension(600, 50));
				titlePanel.setMaximumSize(new Dimension(600, 50));
				menuPanel.add(titlePanel);
				nameLabel = new JLabel("Username: " + username);
				nameLabel.setFont(new Font("Verdana",1,14));
				menuPanel.add(nameLabel);
				nameLabel.setAlignmentX(0.5f);
				
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
				b01.setAlignmentX(0.5f);
				b01.setMaximumSize(new Dimension(200, 50));

				JButton b02=new JButton("Connect to IP");    
				b02.setBounds(100,100,140, 40);
				b02.setActionCommand("connectmulti");
				b02.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cardindex.show(cards, MULTIPLAYERPANEL);
					}
				});
				menuPanel.add(b02);
				b02.setAlignmentX(0.5f);
				b02.setMaximumSize(new Dimension(200, 50));
				
				JButton b03=new JButton("Settings");    
				b03.setBounds(100,100,140, 40);
				b03.setActionCommand("settingsmenu");
				b03.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cardindex.show(cards, SETTINGSMENUPANEL);
					}
				});
				menuPanel.add(b03);
				b03.setAlignmentX(0.5f);
				b03.setMaximumSize(new Dimension(200, 50));
				
				JButton b04=new JButton("Quit");    
				b04.setBounds(100,100,140, 40);
				b04.setActionCommand("quitgame");
				b04.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				menuPanel.add(b04);
				b04.setAlignmentX(0.5f);
				b04.setMaximumSize(new Dimension(200, 50));
				
				
				
				
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
				userBox.setMaximumSize(new Dimension(200, 40));

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
				ipTextBox.setMaximumSize(new Dimension(200, 40));
				
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
						//con.currentLobby = null;
						//kills current lobby object
					}
				});
				lobbyPanel.add(lobbyBmain);
				

				
				JButton BaddAI=new JButton("Add AI");    
				BaddAI.setBounds(100,100,140, 40);
				BaddAI.setActionCommand("addAI");
				BaddAI.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							con.currentLobby.addAI();
							System.out.print("AI Added.");
						} catch (NullPointerException ex) {
							System.out.print("No Lobby Exists.");
						}
					}
				});
				lobbyPanel.add(BaddAI);
				
				lobbylist = new JPanel();
				lobbylist.setBorder(BorderFactory.createLineBorder(Color.black));
				lobbyPanel.add(lobbylist);
				
				JButton Bstartgame=new JButton("Start Game");    
				Bstartgame.setBounds(100,100,140, 40);
				Bstartgame.setActionCommand("menumenu");
				Bstartgame.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cardindex.show(cards, GAMEPANEL);
						try {
							con.StartGame(con.currentLobby.playerList);
						} catch (NullPointerException ex) {
							System.out.print("No Lobby Exists.");
						}
					}
				});
				lobbyPanel.add(Bstartgame);
				
		//Game Page
				

				TILEINFO.add(tiledesc);
				UNITINFO.add(unitdesc);
				
				JButton quit=new JButton("Quit");    
				quit.setBounds(100,100,140, 40);
				quit.setActionCommand("menumenu");
				quit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cardindex.show(cards, MAINMENUPANEL);
						con.currentGame = null;
						displayclear();
						con.gameactive = false;
					}
				});
				TOPL.add(quit);
				
				JButton SpawnInf=new JButton("Spawn Infantry");    
				SpawnInf.setBounds(100,100,140, 40);
				SpawnInf.setActionCommand("createunitinfantry");
				SpawnInf.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							Integer result = T.CreateUnit(new Infantry());
							if (result==1) {
								System.out.print("Success");
								T.UnitContainer.get(0).setOwner(con.host);
								con.currentGame.Units.add(T.UnitContainer.get(0));
							} else {
								System.out.print("Failure");
							}
							UpdateSidePanel(T);
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
				TOPL.add(SpawnInf);
				
				JButton SpawnInf2=new JButton("Spawn Hostile");    
				SpawnInf2.setBounds(100,100,140, 40);
				SpawnInf2.setActionCommand("createunitinfantry");
				SpawnInf2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							Integer result = T.CreateUnit(new Infantry());
							if (result==1) {
								System.out.print("Success");
								try {
									T.UnitContainer.get(0).setOwner(con.currentGame.players.get(1));
								} catch (NullPointerException ex) {
									T.UnitContainer.get(0).setOwner(con.host);
									System.out.print("No Hostile AI was added.\n");
								}
								con.currentGame.Units.add(T.UnitContainer.get(0));
							} else {
								System.out.print("Failure");
							}
							UpdateSidePanel(T);
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
				TOPL.add(SpawnInf2);
				
				JButton MoveUnit=new JButton("Move Unit");    
				MoveUnit.setBounds(100,100,140, 40);
				MoveUnit.setActionCommand("action_move");
				MoveUnit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if (con.host.yourturn == true) {
							if (con.host.actionqueued == true) {
								con.host.clearorders();
							}
							if (con.host.Tileselected.UnitCount() != 0){ //A unit is present
								Unit U = con.host.Tileselected.UnitGet();
								if (U.ownerID == con.host.PlayerID) { //Unit is owned by player
									con.host.setorder(1);
									con.host.selectUnit(U);
									System.out.print("Click a tile to move this unit to.");
								} else {
									System.out.print("You do not own that unit. Unit's owner is " + Integer.toString(con.host.Tileselected.UnitGet().ownerID) );//+ ". Your ID is " + Integer.toString(con.host.PlayerID));
								}
							} else {
								System.out.print("There is no unit to move at this location.");
							}
						} else {
							System.out.print("It is not your turn.");
						}
					}
				});
				BOTTOMR.add(MoveUnit);
				
				JButton BAttack=new JButton("Attack");    
				BAttack.setBounds(100,100,140, 40);
				BAttack.setActionCommand("action_attack");
				BAttack.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if (con.host.yourturn == true) {
							if (con.host.actionqueued == true) {
								con.host.clearorders();
							}
							if (con.host.Tileselected.UnitCount() != 0){ //A unit is present
								Unit U = con.host.Tileselected.UnitGet();
								if (U.ownerID == con.host.PlayerID) { //Unit is owned by player
									con.host.setorder(2);
									con.host.selectUnit(U);
									System.out.print("Click a tile to attack.");
								} else {
									System.out.print("You do not own that unit. Unit's owner is " + Integer.toString(con.host.Tileselected.UnitGet().ownerID) );//+ ". Your ID is " + Integer.toString(con.host.PlayerID));
								}
							} else {
								System.out.print("There is no unit to attack with at this location.");
							}
						} else {
							System.out.print("It is not your turn.");
						}
					}
				});
				BOTTOMR.add(BAttack);
				
				JButton CancelOrder=new JButton("Cancel Command");    
				CancelOrder.setBounds(100,100,140, 40);
				CancelOrder.setActionCommand("cancel_move");
				CancelOrder.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						con.host.clearorders();
					}
				});
				BOTTOMR.add(CancelOrder);
				
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
	
	public void initLobby(Player[] playerArr) {
		lobbylist.removeAll();
		players = new JLabel[4];
		lobbylist.setLayout(new BoxLayout(lobbylist, BoxLayout.Y_AXIS));

	    for(int y = 0; y < 4; y++) {
	    	try {
	    		String temptext = playerArr[y].username;
	    		players[y] = new JLabel(temptext);
	    		players[y].setBackground(Color.cyan);
	    	} catch (NullPointerException ex) {
	    		players[y] = new JLabel("Empty Slot");
	    	}
	        lobbylist.add(players[y]);
        }
	}
	
	public void updateLobby(Player[] playerArr) {
	    for(int y = 0; y < 4; y++) {
	    	try {
	    		String temptext = playerArr[y].username;
	    		players[y].setText(temptext);
	    		players[y].setBackground(Color.cyan);
	    	} catch (NullPointerException ex) {
	    		players[y].setText("Empty Slot");
	    	}
        }
	}
	
	
	public void Instantiate(int W, int H, Board B) {
		boardpanel.removeAll();
		gamebuttons = new JButton[W][H];
		buttonlisteners = new TileListener[W][H];
		boardpanel.setLayout(new GridLayout(W, H));
	    for(int x = 0; x < W; x++)
	    {
	        for(int y = 0; y < H; y++)
	        {

		    	try {
		    		Tile T = B.tileArray[x][y];
		    		//String tiletext = B.tileArray[x][y].TileSymbol;
		    		Color c = T.c;
		    		ImageIcon tileicon = new ImageIcon();
		    		tileicon = new ImageIcon(T.imgURL1, "");
		    		Image tempimg = tileicon.getImage();
		    		tileicon = new ImageIcon (tempimg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		    		gamebuttons[x][y] = new JButton(tileicon);
		        	gamebuttons[x][y].setVerticalTextPosition(SwingConstants.CENTER);
		        	gamebuttons[x][y].setHorizontalTextPosition(SwingConstants.CENTER);		
		        	gamebuttons[x][y].setForeground(Color.BLACK);
		        	gamebuttons[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
		    	} catch (NullPointerException ex) {
		    		gamebuttons[x][y] = new JButton("U");
		    		gamebuttons[x][y].setBackground(Color.cyan);
		        	gamebuttons[x][y].setForeground(Color.BLACK);
		        	gamebuttons[x][y].setBorder(BorderFactory.createLineBorder(Color.gray));
		    	}
	        	
	        	
		    	buttonlisteners[x][y] = new TileListener();
		    	buttonlisteners[x][y].setInfo(x,y, con);
		    	gamebuttons[x][y].addActionListener(buttonlisteners[x][y]);
	            boardpanel.add(gamebuttons[x][y]);
	        }
	    }
	}
	
	public void UpdateSidePanel(Tile T) {
		try {
			oldTileRef.setBorder(BorderFactory.createLineBorder(Color.black));
		} catch (NullPointerException ex) {
			//this is fine
		}
		oldTileRef = gamebuttons[T.xloc][T.yloc]; //overwrite the old reference so that the next time this is called,
		//the color will be overwritten of the last selected button.
		gamebuttons[T.xloc][T.yloc].setBorder(BorderFactory.createLineBorder(Color.white));
		Integer UnitCount = T.UnitCount();
		if(UnitCount==0) {
			unitdesc.setText("No Units on this Tile");
		}else {
			Unit U = T.UnitContainer.get(0);
			unitdesc.setText(U.UnitName + "\n"+U.UnitDesc + "\n" + 
			"\nOwner: " + U.ownerOBJ.username +
			"\nHealth: " + decimalFixer(U.HealthCurrent)+ "/" + decimalFixer(U.HealthMax) +
			"\nArmor: " + decimalFixer(U.Armor) +
			"\nMovement Points: " + decimalFixer(U.MoveLeft) + "/" + decimalFixer(U.MoveRange) +
			"\nAttack Range: " + decimalFixer(U.AttackRange) +
			"\nDamage: " + decimalFixer(U.Damage) +
			""
			);
		}
		tiledesc.setText(T.TileName +
		"\nDefense: " + decimalFixer(T.Defense) +
		"\n"+ T.TileDesc 
		);

	}
	
	public void UpdateDisplay() {
		Board B = con.currentGame.gameBoard;
		int W = con.currentGame.Width;
		int H = con.currentGame.Height;
	    for(int x = 0; x < W; x++)
	    {
	        for(int y = 0; y < H; y++)
	        {

		    	try {
		    		Tile T = B.tileArray[x][y];
		    		JButton N = gamebuttons[x][y];
		    		String tiletext = T.TileSymbol;
		    		Unit U;
		    		if (T.UnitCount() != 0) {
		    			U = T.UnitGet();
		    			tiletext = U.UnitSymbol + " " + U.ownerOBJ.username.substring(0, 4);
		    			String lifeC = decimalFixer(U.HealthCurrent);
		    			N.setText("<html>" + tiletext + " <font color=\"red\">" + "(" + lifeC + ")" + "</font></html>");
		    			N.setFont(new Font("Verdana",1,12));
		    		} else {
		    			N.setText("");
		    		}
		    		if (T == con.host.Tileselected) {
		    			N.setBorder(BorderFactory.createLineBorder(Color.white));
		    		} else {
		    			N.setBorder(BorderFactory.createLineBorder(Color.black));
		    		}
		    		Color c = T.c;
		    		
		    		ImageIcon tileicon = new ImageIcon();
		    		T.animCycle();
		    		if (T.Anim == 0) { tileicon = new ImageIcon(T.imgURL1, ""); }
		    		if (T.Anim == 1) { tileicon = new ImageIcon(T.imgURL2, ""); }
		    		if (T.Anim == 2) { tileicon = new ImageIcon(T.imgURL3, ""); }
		    		Image tempimg = tileicon.getImage();
		    		tileicon = new ImageIcon (tempimg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		    		N.setIcon(tileicon);		    		
		    	} catch (NullPointerException ex) {
		    		
		    	}
	        }
	    }
	}
	
	public void displayclear(){
		unitdesc.setText("");
		tiledesc.setText("");
	}
	
	private class MouseControl extends MouseAdapter{
	      
	  }
	
	private String decimalFixer(Double inp) {
		if (inp % 1.0 == 0.0){
			return Integer.toString(inp.intValue());
		} else {
			return Double.toString(inp);
		}
	}
}