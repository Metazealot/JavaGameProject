package jGameMain;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import jGameMain.Units.*;
import jGameMain.Buildings.*;
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
    JLabel turnLabel;
    JLabel R1, R2, R3;
    JLabel[] players;
	JTextArea tiledesc;
	JTextArea unitdesc;
	JTextArea warningbox;
    
    JButton[][] gamebuttons;
    JButton oldTileRef;
    JButton BMove,BAttack,BCancel,BEndTurn, BTrainmenu, BBuildmenu, BTrain01, BTrain02, BTrain03, BBuild01, BBuild02, BBuild03, BBuild04;
    TileListener buttonlisteners[][];
    JPopupMenu trainpopup, buildpopup;
    
    CardLayout cardindex;
	Canvas canvas;
	String username;
	File configFile;
	BufferStrategy bufferStrategy;
	Conquest con;
	ImageLib ImgLb;
	
	public Display(Conquest conin){
		
		//Main Frame Setup
				con = conin;
				frame = new JFrame("Conquest");
				//frame is the game window itself
				tiledesc = new JTextArea(15,15);
				unitdesc = new JTextArea(15,15);
				warningbox = new JTextArea(15,10);
				ImgLb = new ImageLib();

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
				TOPR.setLayout(new BoxLayout(TOPR, BoxLayout.Y_AXIS));
				
				//TOPR.add(warningbox);
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
				warningbox.setEditable(false);

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
				
				
				String strval = "Enter New Username";
				try {
					InputStream inpt = getClass().getResourceAsStream("config.properties"); 
					BufferedReader reader = new BufferedReader(new InputStreamReader(inpt));
				    Properties props = new Properties();
				    props.load(reader);		 
				    strval = props.getProperty("username");
				    reader.close();
				} catch (FileNotFoundException ex) {
					
				} catch (IOException ex) {
					
				}
				JTextField userBox = new JTextField(strval);
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
							//FileReader reader = new FileReader(configFile);
							InputStream inpt = getClass().getResourceAsStream("config.properties"); 
							BufferedReader reader = new BufferedReader(new InputStreamReader(inpt));
						    Properties props = new Properties();
						    props.load(reader);
						    props.setProperty("username", tempname);
						    reader.close();
						    File propfile = new File("config.properties");
						    OutputStream outpt = new FileOutputStream(propfile); 
						    //FileWriter writer = new FileWriter(outpt);
						    props.store(outpt, "");
						    //writer.close();
						    outpt.close();
							userBox.setText("");
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
				
				turnLabel = new JLabel("Current Turn: ");
				turnLabel.setAlignmentX(SwingConstants.CENTER);
				R1 = new JLabel("Gold:     Supply: ");
				R1.setAlignmentX(SwingConstants.CENTER);
				TILEINFO.add(tiledesc);
				UNITINFO.add(unitdesc);
				TOPR.add(turnLabel);
				TOPR.add(R1);
				
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
				/*
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
				
				JButton SpawnCity=new JButton("Spawn City");    
				SpawnCity.setBounds(100,100,140, 40);
				SpawnCity.setActionCommand("createcity");
				SpawnCity.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							Integer result = T.CreateBuilding(new City());
							if (result==1) {
								System.out.print("Success");
								T.BuildingContainer.get(0).setOwner(con.host);
								con.currentGame.Buildings.add(T.BuildingContainer.get(0));
							} else {
								System.out.print("Failure");
							}
							UpdateSidePanel(T);
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
				TOPL.add(SpawnCity);
				*/
				
				BMove=new JButton("Move Unit");    
				BMove.setBounds(100,100,140, 40);
				BMove.setActionCommand("action_move");
				BMove.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						Tile T = con.host.Tileselected;
						JButton N = gamebuttons[T.xloc][T.yloc];						
						if (con.host.yourturn == true) {
							if (con.host.actionqueued == true) {
								con.host.clearorders();
							}
							if (T.UnitCount() != 0){ //A unit is present
								Unit U = T.UnitGet();
								if (U.ownerID == con.host.PlayerID) { //Unit is owned by player
									if (U.MoveLeft > 0.0) {
										con.host.setorder(1);
										con.host.selectUnit(U);
										System.out.print("Click a tile to move this unit to.");
									} else {
										System.out.print("Unit has no moves remaining.");
										T.Flash = 3;
										N.setBorder(BorderFactory.createLineBorder(Color.red));
									}
								} else {
									System.out.print("You do not own that unit. Unit's owner is " + Integer.toString(con.host.Tileselected.UnitGet().ownerID) );//+ ". Your ID is " + Integer.toString(con.host.PlayerID));
								}
							} else {
								System.out.print("There is no unit to move at this location.");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.red));
							}
						} else {
							System.out.print("It is not your turn.");
							T.Flash = 3;
							N.setBorder(BorderFactory.createLineBorder(Color.red));
						}
					}
				});
				BOTTOMR.add(BMove);
				
				BAttack=new JButton("Attack");    
				BAttack.setBounds(100,100,140, 40);
				BAttack.setActionCommand("action_attack");
				BAttack.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						Tile T = con.host.Tileselected;
						JButton N = gamebuttons[T.xloc][T.yloc];	
						if (con.host.yourturn == true) {
							if (con.host.actionqueued == true) {
								con.host.clearorders();
							}
							if (T.UnitCount() != 0){ //A unit is present
								Unit U = T.UnitGet();
								if (U.ownerID == con.host.PlayerID) { //Unit is owned by player
									if (U.MoveLeft > 0.0) {
										con.host.setorder(2);
										con.host.selectUnit(U);
										System.out.print("Click a tile to attack.");
									} else {
										System.out.print("Unit has no moves remaining.");
										T.Flash = 3;
										N.setBorder(BorderFactory.createLineBorder(Color.red));
									}

								} else {
									System.out.print("You do not own that unit. Unit's owner is " + Integer.toString(con.host.Tileselected.UnitGet().ownerID) );//+ ". Your ID is " + Integer.toString(con.host.PlayerID));
									T.Flash = 3;
									N.setBorder(BorderFactory.createLineBorder(Color.red));
								}
								
							} else {
								System.out.print("There is no unit to attack with at this location.");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.red));
							}
						} else {
							System.out.print("It is not your turn.");
							T.Flash = 3;
							N.setBorder(BorderFactory.createLineBorder(Color.red));
						}
					}
				});
				BOTTOMR.add(BAttack);
				BCancel=new JButton("Cancel Command");    
				BCancel.setBounds(100,100,140, 40);
				BCancel.setActionCommand("cancel_move");
				BCancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						Tile T = con.host.Tileselected;
						JButton N = gamebuttons[T.xloc][T.yloc];
						T.Flash = 2;
						N.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
						con.host.clearorders();
					}
				});
				BOTTOMR.add(BCancel);
				
				//TRAINING UNIT MENU
				
				Integer workercost = 10;
				Integer infantrycost = 20;
				Integer archercost = 25;
				
				BTrain01=new JButton("<html>" + "Train Worker" + " <font color=\"orange\">" + " (" + workercost + ")" + "</font></html>");    
				BTrain01.setBounds(100,100,140, 40);
				BTrain01.setActionCommand("train01");
				BTrain01.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							JButton N = gamebuttons[T.xloc][T.yloc];
							if (con.host.resource1 >= workercost){
								if (con.host.resource2 < con.host.resource3) {
									Integer result = T.CreateUnit(new Worker());
									if (result==1) {
										System.out.print("Success");
										T.UnitContainer.get(0).setOwner(con.host);
										con.currentGame.Units.add(T.UnitContainer.get(0));
										trainpopup.setVisible(false);
										con.host.resource1 -= workercost;
									} else {
										System.out.print("Failure");
									}
									UpdateSidePanel(T);
								} else {
									System.out.print("Not Enough Supply");
									T.Flash = 3;
									N.setBorder(BorderFactory.createLineBorder(Color.orange));
								}
							} else {
								System.out.print("Not Enough Gold");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.orange));
							}
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
				

				BTrain02=new JButton("<html>" + "Train Infantry" + " <font color=\"orange\">" + " (" + infantrycost + ")" + "</font></html>");     
				BTrain02.setBounds(100,100,140, 40);
				BTrain02.setActionCommand("train02");
				BTrain02.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							JButton N = gamebuttons[T.xloc][T.yloc];
							if (con.host.resource1 >= infantrycost){
								if (con.host.resource2 < con.host.resource3) {
									Integer result = T.CreateUnit(new Infantry());
									if (result==1) {
										System.out.print("Success");
										T.UnitContainer.get(0).setOwner(con.host);
										con.currentGame.Units.add(T.UnitContainer.get(0));
										trainpopup.setVisible(false);
										con.host.resource1 -= infantrycost;
									} else {
										System.out.print("Failure");
									}
									UpdateSidePanel(T);
								} else {
									System.out.print("Not Enough Supply");
									T.Flash = 3;
									N.setBorder(BorderFactory.createLineBorder(Color.orange));
								}
							} else {
								System.out.print("Not Enough Gold");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.orange));
							}
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
					

				BTrain03=new JButton("<html>" + "Train Archer" + " <font color=\"orange\">" + " (" + archercost + ")" + "</font></html>");     
				BTrain03.setBounds(100,100,140, 40);
				BTrain03.setActionCommand("train02");
				BTrain03.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							JButton N = gamebuttons[T.xloc][T.yloc];
							if (con.host.resource1 >= archercost){
								if (con.host.resource2 < con.host.resource3) {
									Integer result = T.CreateUnit(new Archer());
									if (result==1) {
										System.out.print("Success");
										T.UnitContainer.get(0).setOwner(con.host);
										con.currentGame.Units.add(T.UnitContainer.get(0));
										trainpopup.setVisible(false);
										con.host.resource1 -= archercost;
									} else {
										System.out.print("Failure");
									}
									UpdateSidePanel(T);
								} else {
									System.out.print("Not Enough Supply");
									T.Flash = 3;
									N.setBorder(BorderFactory.createLineBorder(Color.orange));
								}
							} else {
								System.out.print("Not Enough Gold");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.orange));
							}
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
				
				
			    trainpopup = new JPopupMenu();
			    trainpopup.add(BTrain01);
			    trainpopup.add(BTrain02);
			    trainpopup.add(BTrain03);
			    
		        BTrainmenu = new JButton("Train");
		        BTrainmenu.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                trainpopup.show(e.getComponent(), 0, 0);
		            }
		        });
			    
		        BOTTOMR.add(BTrainmenu);
		        
		        //BUILDING STRUCTURE MENU
		        buildpopup = new JPopupMenu();
		        
		        Integer citycost = 500;
		        Integer farmcost = 100;
		        Integer improvecost = 150;
		        Integer fortcost = 300;
		        
		        BBuild01=new JButton("<html>" + "Build City" + " <font color=\"orange\">" + " (" + citycost + ")" + "</font></html>");
				BBuild01.setBounds(100,100,140, 40);
				BBuild01.setActionCommand("build01");
				BBuild01.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							JButton N = gamebuttons[T.xloc][T.yloc];
							if (con.host.resource1 >= citycost){
								Integer result = T.CreateBuilding(new City());
									if (result==1) {
										System.out.print("Success");
										T.BuildingContainer.get(0).setOwner(con.host);
										con.currentGame.Buildings.add(T.BuildingContainer.get(0));
										buildpopup.setVisible(false);
										con.host.resource1 -= citycost;
										T.UnitGet().MoveLeft = 0.0;
									} else {
										System.out.print("Failure");
									}
									UpdateSidePanel(T);
							} else {
								System.out.print("Not Enough Gold");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.orange));
							}
							
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
			    buildpopup.add(BBuild01);
			    
		        
				BBuild02=new JButton("<html>" + "Build Farm" + " <font color=\"orange\">" + " (" + farmcost + ")" + "</font></html>");
				BBuild02.setBounds(100,100,140, 40);
				BBuild02.setActionCommand("build02");
				BBuild02.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							JButton N = gamebuttons[T.xloc][T.yloc];
							if (con.host.resource1 >= farmcost){
								Integer result = T.CreateBuilding(new Farm());
									if (result==1) {
										System.out.print("Success");
										T.BuildingContainer.get(0).setOwner(con.host);
										con.currentGame.Buildings.add(T.BuildingContainer.get(0));
										buildpopup.setVisible(false);
										con.host.resource1 -= farmcost;
										T.UnitGet().MoveLeft = 0.0;
									} else {
										System.out.print("Failure");
									}
									UpdateSidePanel(T);
							} else {
								System.out.print("Not Enough Gold");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.orange));
							}
							
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
			    buildpopup.add(BBuild02);

		        
				BBuild03=new JButton("<html>" + "Improve Tile" + " <font color=\"orange\">" + " (" + improvecost + ")" + "</font></html>");
				BBuild03.setBounds(100,100,140, 40);
				BBuild03.setActionCommand("build02");
				BBuild03.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							JButton N = gamebuttons[T.xloc][T.yloc];
							if (con.host.resource1 >= improvecost){
								Integer result = 0;
								if (T.TileID == 0) {result = T.CreateBuilding(new Granary());}
								if (T.TileID == 1) {result = T.CreateBuilding(new Lumbermill());}
								if (T.TileID == 2) {result = T.CreateBuilding(new Granary());}
								if (T.TileID == 5) {result = T.CreateBuilding(new Mine());}
									if (result==1) {
										System.out.print("Success");
										T.BuildingContainer.get(0).setOwner(con.host);
										con.currentGame.Buildings.add(T.BuildingContainer.get(0));
										buildpopup.setVisible(false);
										con.host.resource1 -= improvecost;
										T.UnitGet().MoveLeft = 0.0;
									} else {
										System.out.print("Failure");
									}
									UpdateSidePanel(T);
							} else {
								System.out.print("Not Enough Gold");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.orange));
							}
							
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
			    buildpopup.add(BBuild03);
			    
		        
				BBuild04=new JButton("<html>" + "Build Fort" + " <font color=\"orange\">" + " (" + fortcost + ")" + "</font></html>");
				BBuild04.setBounds(100,100,140, 40);
				BBuild04.setActionCommand("build02");
				BBuild04.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							Tile T = con.host.Tileselected;
							JButton N = gamebuttons[T.xloc][T.yloc];
							if (con.host.resource1 >= fortcost){
								Integer result = T.CreateBuilding(new Fort());
									if (result==1) {
										System.out.print("Success");
										T.BuildingContainer.get(0).setOwner(con.host);
										con.currentGame.Buildings.add(T.BuildingContainer.get(0));
										buildpopup.setVisible(false);
										con.host.resource1 -= fortcost;
										T.UnitGet().MoveLeft = 0.0;
									} else {
										System.out.print("Failure");
									}
									UpdateSidePanel(T);
							} else {
								System.out.print("Not Enough Gold");
								T.Flash = 3;
								N.setBorder(BorderFactory.createLineBorder(Color.orange));
							}
							
						} catch (NullPointerException ex) {
							//no tile was selected
						}
					}
				});
			    buildpopup.add(BBuild04);
			    
		        BBuildmenu = new JButton("Build");
		        BBuildmenu.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                buildpopup.show(e.getComponent(), 0, 0);
		            }
		        });
			    
		        BOTTOMR.add(BBuildmenu);
		        
		        
		        
		        
		        
				BEndTurn=new JButton("End Turn");    
				BEndTurn.setBounds(100,100,140, 40);
				BEndTurn.setActionCommand("end_turn");
				BEndTurn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						con.currentGame.cycleturn();
						UpdateSidePanel(con.host.Tileselected); 
					}
				});
				BOTTOMR.add(BEndTurn);
				
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
		    		ImageIcon tileicon = new ImageIcon();
		    		tileicon = ImgLb.getImage(T.TileName, T.Anim);
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
		if(T.UnitCount()==0) {
			unitdesc.setText("No Units on this Tile");
		}else {
			Unit U = T.UnitGet();
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
		if (T.BuildingCount()==0) {
			tiledesc.setText(T.TileName +
					"\nDefense: " + decimalFixer(T.Defense) +
					"\n"+ T.TileDesc +
					""
					);
		} else {
			Building bld = T.BuildingGet();
			tiledesc.setText(T.TileName +
					"\nDefense: " + decimalFixer(T.Defense) + " (+" + decimalFixer(bld.DefenseBonus) + ")" +
					"\n"+ T.TileDesc +
					"\n" +
					"\n" + bld.BuildingName + 
					"\n"+bld.BuildingDesc +
					""
					);
		}

	}
	
	public void UpdateDisplay() {
		Board B = con.currentGame.gameBoard;
		int W = con.currentGame.Width;
		int H = con.currentGame.Height;
		turnLabel.setText("Current Turn: " + con.currentGame.getCurrentPlayer().username + "   Turn: " + Integer.toString(con.currentGame.Turncount));
		R1.setText("Gold: " + decimalFixer(con.currentGame.getCurrentPlayer().resource1) + "   Supply: " + decimalFixer(con.currentGame.getCurrentPlayer().resource2) +"/" + decimalFixer(con.currentGame.getCurrentPlayer().resource3));
		if (con.host != con.currentGame.getCurrentPlayer()) {
			BMove.setEnabled(false);
			BAttack.setEnabled(false);
			BCancel.setEnabled(false);
			BTrainmenu.setEnabled(false);
			trainpopup.setEnabled(false);
			BBuildmenu.setEnabled(false);
			buildpopup.setEnabled(false);
			BEndTurn.setEnabled(false);
		} else {
			BEndTurn.setEnabled(true);
			if (con.host.Tileselected.UnitCount() != 0) {
				BTrainmenu.setEnabled(false);
				trainpopup.setEnabled(false);
				Unit Utest = con.host.Tileselected.UnitGet();
				if ((Utest.ownerOBJ != con.host)|(Utest.MoveLeft ==0)){
					BMove.setEnabled(false);
					BAttack.setEnabled(false);
					BBuildmenu.setEnabled(false);
					buildpopup.setEnabled(false);
				} else {
					BMove.setEnabled(true);
					BAttack.setEnabled(true);
					if (Utest.UnitName == "Worker") {
						if (con.host.Tileselected.BuildingCount() == 0){
							BBuildmenu.setEnabled(true);
							buildpopup.setEnabled(true);
						} else {
							BBuildmenu.setEnabled(false);
							buildpopup.setEnabled(false);
						}
					} else {
						BBuildmenu.setEnabled(false);
						buildpopup.setEnabled(false);
					}
				}

			} else {
				
				if (con.host.Tileselected.BuildingCount() !=0){
					if (con.host.Tileselected.UnitCount() ==0) {
						Building Btest = con.host.Tileselected.BuildingGet();
						if (Btest.BuildingName == "City") {
							BTrainmenu.setEnabled(true);
							trainpopup.setEnabled(true);
						}
					}
				} else {
					BTrainmenu.setEnabled(false);
					trainpopup.setEnabled(false);
				}
				
				
				BMove.setEnabled(false);
				BAttack.setEnabled(false);
				BBuildmenu.setEnabled(false);
				buildpopup.setEnabled(false);
			}
			if (con.host.actionqueued == true) {
				BCancel.setEnabled(true);
				BMove.setEnabled(false);
				BEndTurn.setEnabled(false);
				BAttack.setEnabled(false);
				BBuildmenu.setEnabled(false);
				buildpopup.setEnabled(false);
			} else {
				BCancel.setEnabled(false);
			}
			
			

			
		}
	    for(int x = 0; x < W; x++)
	    {
	        for(int y = 0; y < H; y++)
	        {

		    	try {
		    		Tile T = B.tileArray[x][y];
		    		JButton N = gamebuttons[x][y];
		    		Unit U;
		    		Building Bld;
		    		
		    		ImageIcon tileicon = new ImageIcon();
		    		ImageIcon uniticon = new ImageIcon();
		    		ImageIcon bldicon = new ImageIcon();
		    		
		    		T.animCycle();
		    		
		    		tileicon = ImgLb.getImage(T.TileName, T.Anim);
		    		String tiletext = T.TileSymbol;

		    		if ((T.UnitCount() != 0) && (T.BuildingCount() ==0)) {
		    			U = T.UnitGet();
		    			tiletext = U.UnitSymbol + " " + U.ownerOBJ.username.substring(0, 4);
		    			String lifeC = decimalFixer(U.HealthCurrent);
		    			N.setText("<html>" + tiletext + " <font color=\"red\">" + "(" + lifeC + ")" + "</font></html>");
		    			N.setFont(new Font("Verdana",1,12));
		    			uniticon = ImgLb.getImage(U.UnitName, T.Anim);
			    		Icon top = uniticon;
			    		Icon bot = tileicon;
			    		Icon newicon = new CombineIcon(top,bot);
			    		N.setIcon(newicon);
		    		} else if ((T.UnitCount() == 0 ) && (T.BuildingCount() != 0)) {
		    			Bld = T.BuildingGet();
		    			bldicon = ImgLb.getImage(Bld.BuildingName, T.Anim);
			    		Icon top = bldicon;
			    		Icon bot = tileicon;
			    		Icon newicon = new CombineIcon(top,bot);
			    		N.setIcon(newicon);
			    		N.setText("");
		    		} else if ((T.UnitCount() != 0 ) && (T.BuildingCount() != 0)) {
		    			Bld = T.BuildingGet();
		    			bldicon = ImgLb.getImage(Bld.BuildingName, T.Anim);
			    		Icon top = bldicon;
			    		Icon bot = tileicon;
			    		Icon newicon = new CombineIcon(top,bot);
		    			U = T.UnitGet();
		    			tiletext = U.UnitSymbol + " " + U.ownerOBJ.username.substring(0, 4);
		    			String lifeC = decimalFixer(U.HealthCurrent);
		    			N.setText("<html>" + tiletext + " <font color=\"red\">" + "(" + lifeC + ")" + "</font></html>");
		    			N.setFont(new Font("Verdana",1,12));
		    			uniticon = ImgLb.getImage(U.UnitName, T.Anim);
			    		top = uniticon;
			    		bot = newicon;
			    		newicon = new CombineIcon(top,bot);
			    		N.setIcon(newicon);
		    		} else {
		    			N.setIcon(tileicon);
		    			N.setText("");
		    		}
		    		warningbox.setText("");

		    		
		    		if (T == con.host.Tileselected) {
		    			N.setBorder(BorderFactory.createLineBorder(Color.white));
		    		} else {
		    			if (T.Flash == 0) {
		    				N.setBorder(BorderFactory.createLineBorder(Color.black));	
		    			}
		    		}
		    	} catch (NullPointerException ex) {
		    		warningbox.setText("Display Error");
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
	
	public class CombineIcon implements Icon {
	    private Icon top;
	    private Icon bottom;

	    public CombineIcon(Icon top, Icon bottom) {
	        this.top = top;
	        this.bottom = bottom;
	    }

	    public int getIconHeight() {
	        return Math.max(top.getIconHeight(), bottom.getIconHeight());
	    }

	    public int getIconWidth() {
	        return Math.max(top.getIconWidth(), bottom.getIconWidth());
	    }

	    public void paintIcon(Component c, Graphics g, int x, int y) {
	        bottom.paintIcon(c, g, x, y);
	        top.paintIcon(c, g, x, y);
	    }
	}
	
	private String decimalFixer(Double inp) {
		if (inp % 1.0 == 0.0){
			return Integer.toString(inp.intValue());
		} else {
			return Double.toString(inp);
		}
	}
}