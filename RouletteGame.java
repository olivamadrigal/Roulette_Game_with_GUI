//This is a re-do the RouletteGame to be the "GUI Layer" that has the main frame 
//with the buttons & textfield(s) in it from which you get the 
//user input, as well as the RouletteWheel and RouletteTable objects. 
//The methods of RouletteWheel and RouletteTable are called depending on 
//the events of the buttons. 
//The main is in  TestDriver.java class
//PUTS SPECS INSIDE PANELS
//THEN ARRANGE THE PANELS INSIDE THE WINDOW
// 
// Samira C. Oliva  Madrigal
//
import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class RouletteGame {// implements GameInterface {

	private RouletteWheel  rWheel;
	private RouletteTable  rTable;
	private double     player;
	private JFrame     mainFrame;
	private JPanel     header; 				//v_panel1
	private JPanel     userPromptGrid;
	private JPanel     centerArea;  		//v_panel2 (buttons)
	private JPanel 	   betSpots;    		//e_pabel1 (odd,even,red,black buttons)
	private JPanel     rightBetSpots;  		 //thirds etc.
	private JPanel     buttonsGridLeft;
	private JPanel	   buttonsGridRight;
	private JPanel     buttonsGridCenter;
	private JPanel     miniGrid;
	private JLabel     promptUser;  		//h_userInput
	private JTextField screenOutput;
	private JTextField betInput;
	private JPanel     footer;
	private JButton    oddSpotBtn;    		 //betSpots
	private JButton    evenSpotBtn;
	private JButton    redSpotBtn;
	private JButton    blackSpotBtn;
	private JButton    firstThirdsBtn;
	private JButton    secondThirdsBtn;
	private JButton    thirdThirdsBtn;
	private JButton    newGame;
	private JButton    spinBtn;
	private JButton []ary = new JButton[37];  // array of JButtons 0-36
	Scanner scanf = new Scanner(System.in);
	
	//##################################################################################
	//Instantiate the RouletteTable & RouletteWheel
	//instantiate FRAME, JPANELS, AND COMPONENTS (JBUTTONS, JTEXTFIELDS, JLabel)
	public RouletteGame() //p.601 listeners
	{	
		
		rWheel     = new RouletteWheel(); 
		rTable     = new RouletteTable();
		
		mainFrame =  new JFrame(".:Roulette Game:.");     			//:::mainFrame
		mainFrame.setSize(640,855);									//default settings
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.setBackground(Color.LIGHT_GRAY);
		mainFrame.setLayout(new BorderLayout(5,5));   				//set Layout
	
		//:::instantiate other params
		header 			 = new JPanel();
		centerArea 		 = new JPanel();
		betSpots         = new JPanel();
		rightBetSpots    = new JPanel();
		userPromptGrid   = new JPanel();
		buttonsGridLeft  = new JPanel();
		buttonsGridRight = new JPanel();
		buttonsGridCenter= new JPanel();
		miniGrid         = new JPanel();
		promptUser 		 = new JLabel();
		screenOutput	 = new JTextField();
		betInput         = new JTextField();
		footer           = new JPanel();
		oddSpotBtn 	     = new JButton();
		evenSpotBtn		 = new JButton();
		redSpotBtn		 = new JButton();
		blackSpotBtn     = new JButton();
		firstThirdsBtn	 = new JButton();
		secondThirdsBtn  = new JButton();
		thirdThirdsBtn   = new JButton();
		spinBtn			 = new JButton();
		newGame			 = new JButton();
		
		for(Integer idx = 0; idx <ary.length; idx++)
			  ary[idx]    = new JButton();//ary[idx] = new JButton(idx.toString());
		initializeAry(); //_set the Button text and color	
		initializePanelsAndButtons();
		setVisibilityForParams();   
		setParamRegisterActionListeners(); //_register params and initializer rTable
		initializeRouletteSpotBets();
	}//_end:constructor
	
	//-----------------------------------------------------------------------------------------
	//########################################################################################
	public void setSizeForMainFrame(int arg0, int arg1 )//###START_ FRAME SPECIFIC SETTINGS
	{
		mainFrame.setSize(arg0,arg1);
		
	}//_end:setSizeForMainFrame
	
	//------------------------------------------------
	public void setMainFrameVisibility(boolean v)
	{
		mainFrame.setVisible(v);
		
	}//_end:setMainFrameVisibility
	
	//------------------------------------------------
	public void setMainFrameBGColor(Color c)
	{
		mainFrame.setBackground(c);
		
	}//_end:setMainFrameBGColor					    //###END_FRAME SPECIFICS SETTINGS

	//------------------------------------------------
	public void initializeAry()
	{
		try{
		
			for(Integer idx = 0; idx <ary.length; idx++)
				ary[idx].setText(idx.toString());
			setAryColors();
		}
		catch(ArrayIndexOutOfBoundsException idx){
			arrayIdxOutOfBoundsHandler("initializeAry()");
		}
	
	}//_initializeAry
	//############################################################################## ADD Buttons etc.
	//:::add params to mainFrame
	public void initializePanelsAndButtons()
	{
		Font f1 = new Font("Dialog",Font.ROMAN_BASELINE,12); 		//:::FONT
		Border lineBorder = new LineBorder(Color.CYAN,3);			//:::Border
		Border textFieldBorder = new LineBorder(Color.lightGray,3);
		
		mainFrame.add(header,BorderLayout.NORTH); 				    
		              header.setBackground(new Color(50,39,65));//white
		                         userPromptGrid.setLayout(new GridLayout(1,1,5,5));              
		              header.add(userPromptGrid);
		                         userPromptGrid.add(promptUser);  //JLabel
		                         userPromptGrid.add(betInput);   //JTextField                
		              header.setBorder(lineBorder);
		              header.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		              
		mainFrame.add(centerArea,BorderLayout.CENTER); //IMPERATIVE ADD PANELS <<<INSIDE>>> PANELS...		
		              centerArea.setBackground(Color.lightGray);	
		              				 miniGrid.setBackground(Color.darkGray);
		              				 miniGrid.setBorder(new LineBorder(Color.darkGray,3));
		              			     miniGrid.setLayout(new FlowLayout(FlowLayout.LEADING,170,1));   
		              			     miniGrid.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		              centerArea.add(miniGrid);
		              				 miniGrid.add(ary[0]);
		              				buttonsGridCenter.setBackground(Color.lightGray);	//CENTER BUTTONS
		              				buttonsGridCenter.setLayout(new GridLayout(12,3,9,23));
		              				buttonsGridCenter.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		             centerArea.add(buttonsGridCenter);
		              			    
		     		try{
		                 for(Integer idx = 1; idx <ary.length; idx++)
		            	     buttonsGridCenter.add(ary[idx]); //_add buttons to center grid
		     		}
		    		catch(ArrayIndexOutOfBoundsException idx){
		    			  arrayIdxOutOfBoundsHandler("initializePanelsAndButtons()");
		    		} 
		              				footer.setBackground(Color.darkGray);
		              				footer.setBorder(new LineBorder(Color.darkGray,3));
		              				footer.setLayout(new GridLayout(1,1,5,5)); 
		              				footer.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		              centerArea.add(footer);
		              			    spinBtn.setText("Spin the Wheel");//_spin button
		              				footer.add(spinBtn);
		              				footer.add(screenOutput);
		              				newGame.setText("New Game");//### button to "reset" game and text in buttons         				 
		              				footer.add(newGame);
		mainFrame.add(betSpots,BorderLayout.WEST);
					  betSpots.setBackground(Color.lightGray);
					  betSpots.setBorder(new LineBorder(Color.CYAN,5));	
					  			   buttonsGridLeft.setBackground(Color.lightGray);
					  	 		   buttonsGridLeft.setLayout(new GridLayout(4,1,5,5));// LEFT BUTTONS
					  betSpots.add(buttonsGridLeft);
					  			   buttonsGridLeft.add(oddSpotBtn);   buttonsGridLeft.add(oddSpotBtn);
					  			   buttonsGridLeft.add(evenSpotBtn);
					  			   buttonsGridLeft.add(redSpotBtn); 
					  			   buttonsGridLeft.add(blackSpotBtn);
					  betSpots.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));			   
					  		
					  oddSpotBtn.setText("ODD");
					  evenSpotBtn.setText("EVEN");
					  redSpotBtn.setText("RED");
					  blackSpotBtn.setText("BLACK");
					  
		mainFrame.add(rightBetSpots,BorderLayout.EAST);							//RIGHT BUTTONS
					  rightBetSpots.setBackground(Color.lightGray);
					  rightBetSpots.setBorder(new LineBorder(Color.CYAN,5));
					  					buttonsGridRight.setBackground(Color.lightGray);
					  				    buttonsGridRight.setLayout(new GridLayout(4,1,5,5));
					  rightBetSpots.add(buttonsGridRight);
					  					buttonsGridRight.add(firstThirdsBtn);
					  					buttonsGridRight.add(secondThirdsBtn);
					  					buttonsGridRight.add(thirdThirdsBtn);
					  					
					  rightBetSpots.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));	
					  firstThirdsBtn.setText("First 12");
					  secondThirdsBtn.setText("Second 12");
					  thirdThirdsBtn.setText("Third 12");
					 	             			 
		//:::set params	settings
		Color cColor = new Color(96,57,140);//custom_color
	    promptUser.setForeground(cColor);
	    promptUser.setFont(f1);
	    promptUser.setText("Enter bet here then click on spot to place bet: ");
	    promptUser.setHorizontalAlignment(JTextField.CENTER);
	    promptUser.setBorder(textFieldBorder);
		betInput.setSize(45,25);
		betInput.setLocation(12,20);
		betInput.setToolTipText("Enter the number of chips to bet click on a spot. "+
							    "Example: Enter 10, then click ODD spot.");
		

	}//_initializePanelsAndButtons
	
	//---------------------------------------------------------------------------------------------
	public void initializeRouletteSpotBets()
	{
		rTable.initilializeRSbets();
	}//_end:initializeRouletteSpotBets
	
	//#####################################################################SET FOREGROUND COLORS
	//### NOTE: background colours on OS X do not display and
	//### adding a border to a button makes it no longer appear like 
	//### a button! Only foreground colouring is set here.
	//### setting the size for a button also has no effect (this may be
	//### because of the OS X.
	public void setAryColors()
	{
		if(	ary != null)
		{
			ary[0].setForeground(Color.GREEN.darker());			
			for(int idx = 1; idx < 11; idx++)
			{
				if(idx%2 == 0)
					ary[idx].setForeground(Color.BLACK);//2-10 even (black)
				else
					ary[idx].setForeground(Color.RED); //1-9 odd (red)
			}		
			for(int idx = 11; idx <= 18; idx++)
			{
			   if(idx%2 == 0)
				  ary[idx].setForeground(Color.RED);//12-18 even (red)
			   else
				  ary[idx].setForeground(Color.BLACK);//11-17 odd (black)
			}
			ary[19].setForeground(Color.RED);
			for(int idx = 20; idx <= 28; idx++)
			{
				if(idx%2 == 0)
					ary[idx].setForeground(Color.BLACK);//20-28 even (black)
				else
					ary[idx].setForeground(Color.RED);//21-27 odd (red)
			}
			ary[29].setForeground(Color.BLACK);
			for(int idx = 30; idx <= RouletteWheel.max; idx++)
			{
				if(idx%2 == 0)
					ary[idx].setForeground(Color.RED);
				else
					ary[idx].setForeground(Color.BLACK);
			}
		}

	}//_end:setAryColors
	
	//-----------------------------------------------------------------------------------------
	//:::visibility#############################################################################
	public void setVisibilityForParams()
	{
		header.setVisible(true);
		centerArea.setVisible(true);
		betSpots.setVisible(true);
		rightBetSpots.setVisible(true);
		promptUser.setVisible(true);	
		betInput.setVisible(true);
	}//_end:setVisibilityForParams
	
	//-----------------------------------------------------------------------------------------
	//RESET Buttons & Ary setText() to play a new Game without instantiating a new GUI object
	public void resetGame()
	{   
		screenOutput.setText("");//clear out
		initializeAry(); //_set the Button text and color	
		initializePanelsAndButtons();
		setVisibilityForParams();   
		initializeRouletteSpotBets();	
		betInput.setText("");//clear out
		
	}//_end:resetGame
	
	//######################################################################### REGISTER LISTENERS
	public void setParamRegisterActionListeners()
	{
		spinBtn.addActionListener(new SPINListener());
		oddSpotBtn.addActionListener(new UserInputAndBetSpotListener());
		evenSpotBtn.addActionListener(new UserInputAndBetSpotListener());
		redSpotBtn.addActionListener(new UserInputAndBetSpotListener());
		blackSpotBtn.addActionListener(new UserInputAndBetSpotListener());
		firstThirdsBtn.addActionListener(new UserInputAndBetSpotListener());
		secondThirdsBtn.addActionListener(new UserInputAndBetSpotListener());
		thirdThirdsBtn.addActionListener(new UserInputAndBetSpotListener());
		for(int idx = 0; idx <ary.length; idx++)
				ary[idx].addActionListener(new UserInputAndBetSpotListener());	
		newGame.addActionListener(new newGameListener());
	}//_end:paramRegisterActionListeners
	

	//########################################################################################
	//### 								IMPLEMENT ACTION LISTENERS					       ###
	//########################################################################################
	//----------------------------------------------------------------------------------------
	//################################## START INNER CLASSES #################################
	class UserInputAndBetSpotListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String userBet;
			StringBuilder userSpotOrNum = new StringBuilder();;
			Object obj;
			StringBuilder str = new StringBuilder();
			boolean validInput;
			
			userBet = betInput.getText(); //USE EXCEPTION HANDLERS_VALIDATE
			obj = e.getSource();
		
			validInput = true;
			if(userBet.length() == 0)
				 validInput = nullPointerExeptionHANDLER();	  //_if_NULL
			
			if(validInput == true)//_if it's not NULL                  //userBet != null)
			{
					try{
						  Integer.parseInt(betInput.getText()); //_verify it's all digits
		     	    } // end try
		            catch( NumberFormatException nfe ){
		        	  validInput = intergerInputExceptionHANDLER();
		            } // end catch 
					if(Integer.parseInt(betInput.getText()) <= 0)
					  validInput = intergerInputExceptionHANDLER();
			}//_if_userBet != NULL
			
	      if(validInput == true)//_IF_VALID_INPUT_setText()_IN_BUTTON
	      {
	    	  
			if(obj == oddSpotBtn)
			{
				userSpotOrNum.append("O");
				str.append(oddSpotBtn.getText());
				str.append("["+userBet+"]");
				oddSpotBtn.setText(str.toString());
				
			}
			if(obj == evenSpotBtn)
			{
				userSpotOrNum.append("E");
				str.append(evenSpotBtn.getText());
				str.append("["+userBet+"]");
				evenSpotBtn.setText(str.toString());
			}
			if(obj == redSpotBtn)
			{
				userSpotOrNum.append("R");
				str.append(redSpotBtn.getText());
				str.append("["+userBet+"]");
				redSpotBtn.setText(str.toString());
			}
			if(obj == blackSpotBtn)
			{
				userSpotOrNum.append("B");
				str.append(blackSpotBtn.getText());
				str.append("["+userBet+"]");
				blackSpotBtn.setText(str.toString());
			}
			if(obj == firstThirdsBtn)
			{
				userSpotOrNum.append("F");
				str.append(firstThirdsBtn.getText());
				str.append("["+userBet+"]");
				firstThirdsBtn.setText(str.toString());
			}
			if(obj == secondThirdsBtn)
			{
				userSpotOrNum.append("S");
				str.append(secondThirdsBtn.getText());
				str.append("["+userBet+"]");
				secondThirdsBtn.setText(str.toString());
			}
			if(obj == thirdThirdsBtn)
			{
				userSpotOrNum.append("T");
				str.append(thirdThirdsBtn.getText());
				str.append("["+userBet+"]");
				thirdThirdsBtn.setText(str.toString());
			}
			
			else
			{
				for(int idx = 0; idx <ary.length; idx++)
				{
					if(obj ==ary[idx])
					{
						userSpotOrNum.append(ary[idx].toString());
						str.append(ary[idx].getText());
						str.append("["+userBet+"]");
						ary[idx].setText(str.toString());
						
					}//_if
				}//_fof
				
				
			}//_else
			
			rTable.userRouletteSpotNumber(userSpotOrNum.toString(),Integer.parseInt(userBet));
	      }//_VALID_INPUT
	   }//_end:actionPerformed
		
	}//_end:UserInputAndBetSpotListener_Class
	
	//---------------------------------------------------------------------------------
	class SPINListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{ 	
			int num;
			RouletteWheel.COLOUR c;
			StringBuilder str = new StringBuilder();
			Object obj;
			
			obj = e.getSource();
			
			if(obj == spinBtn)
			{
				rWheel.spinTheWheel();
				num    = rWheel.getChosenNum();
				c      = rWheel.getChosenIntColour();
				player = rTable.totalWinnings(num, c);
				str.append("You won $" + player + "!");	
				screenOutput.setForeground(Color.BLACK);
				screenOutput.setText(str.toString());
		
			}//_if
		}//_end:actionPerformed
		
	}//_end:SPINListener_Class
	
	//-------------------------------------------------------------------------
	class newGameListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Object obj;
			obj = e.getSource();
			
			if(obj == newGame)
				resetGame();//_RESET FIELDS FOR BUTTONS and Spin result
		}

	}//_newGameListener
	
	//##########################################################################################
	//### 								END INNER CLASSES								     ###
	//##########################################################################################
	
	//-----------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------
	
	//######################################################################################### 
	//### 								START EXECEPION HANDLERS						    ###
	//######################################################################################### 
	public boolean intergerInputExceptionHANDLER()
	{
	   JOptionPane.showMessageDialog(null, "ERROR: Enter a positive bet (digits only)!",
			   						 "Input Error",  JOptionPane.ERROR_MESSAGE);
	   betInput.setText(""); //_clear_invalid_text
       return false;

	}//_end:intergerInputExceotionHANDLER
	//-----------------------------------------------------------------------------------
	public boolean nullPointerExeptionHANDLER()
	{ 
			JOptionPane.showMessageDialog(null, "ERROR: Cannot place an empty bet!",
										   "", JOptionPane.ERROR_MESSAGE);
			return false;
	}//_nullPointerExeptionHANDLER

	//-----------------------------------------------------------------------------------
	public void arrayIdxOutOfBoundsHandler(String methodName)
	{
		throw new ArrayIndexOutOfBoundsException("From method: " + methodName + "array index is out of bounds!");
		
	}//_end:arrayIdxOutOfBounds
	//######################################################################################### 
	//###								END EXECEPION HANDLERS								###
	//######################################################################################### 
	
}//_end:RouletteGame_Class













