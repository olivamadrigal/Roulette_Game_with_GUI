//RouletteTable Class
//Samira C. Oliva Madrigal
//for GUI lab7

import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;

public class RouletteTable{
	
	//all possible bet spots
	private RouletteSpot   odd; //<----- each has 1:1 payoff, except 0 is none of these
	private RouletteSpot   even;
	private RouletteSpot   red;
	private RouletteSpot   black;
	private RouletteSpot   firstThird;   //idx (1-12)<------ each has 2:1 payoff
	private RouletteSpot   secondThird;  //idx (13-24)         
	private RouletteSpot   thirdThird;   //idx (25-36)
	private RouletteSpot   [] RSPary;	 //idx (0-36)<----- 35:1 payoff

	//---------------------------------------------------------
	//initialize all bet spots to 0
	public RouletteTable()
	{

		odd          = new RouletteSpot(1);
		even         = new RouletteSpot(1);
		red 		 = new RouletteSpot(1);
		black		 = new RouletteSpot(1);
		firstThird 	 = new RouletteSpot(2);
		secondThird  = new RouletteSpot(2);
		thirdThird   = new RouletteSpot(2);
		RSPary       = new RouletteSpot[37];
		

	}//_end:constructor
		
	//---------------------------------------------------------
	public void initilializeRSbets()
	{
		odd.setBet(0);
		even.setBet(0);
		red.setBet(0);
		black.setBet(0);
		firstThird.setBet(0);
		secondThird.setBet(0);
		thirdThird.setBet(0);
		
		for(int idx = 0; idx < RSPary.length; idx++)
		{
		    RSPary[idx] = new RouletteSpot(35); 
			RSPary[idx].setBet(0);
		}
		
	}//_end:initilializeRSbets
	
	//---------------------------------------------------------
	//	  				      setters
	//---------------------------------------------------------
	public void setOddSpotBet(int newBet){ odd.setBet(newBet);}
	public void setEvenSpotBet(int newBet){ even.setBet(newBet);}
	public void setRedSpotBet(int newBet){ red.setBet(newBet);}
	public void setBlackSpotBet(int newBet){ black.setBet(newBet);}
	public void setFirstThirdSpotBet(int newBet){firstThird.setBet(newBet);}
	public void setSecondThirdSpotBet(int newBet){ secondThird.setBet(newBet);}
	public void setThirdThirdSpotBet(int newBet){ thirdThird.setBet(newBet);}
		
	//---------------------------------------------------------
	public void userRouletteSpotNumber(String choice, int bet)
	{
		char c = choice.charAt(0);
		c = Character.toUpperCase(c);

		if(Character.isDigit(c))
		{
			int idx = Integer.parseInt(choice);
			if(idx >= 0 && idx <= RSPary.length)
			{	
				RSPary[idx].setBet(bet); 
			}
		}
		else if (Character.isLetter(c))
		{
			if(c == 'O')
				setOddSpotBet(bet); 
			if(c == 'E')
				setEvenSpotBet(bet);
			if(c == 'R')
				setRedSpotBet(bet); 
			if(c == 'B')
			    setBlackSpotBet(bet); 	
			if(c == 'F')
				setFirstThirdSpotBet(bet);
			if(c == 'S')
				setSecondThirdSpotBet(bet);
			if(c == 'T')
				setThirdThirdSpotBet(bet);
	    }		
		//return 0.0;
		
	}//_end:setrouletteSpotNumber
	
	//---------------------------------------------------------
	//	  					  getters
	//---------------------------------------------------------
	public double getOddSpotWinnings(){  return odd.winningsOnBet();}
	public double getEvenSpotWinnings(){ return even.winningsOnBet();}
	public double getRedSpotWinnings(){ return red.winningsOnBet();}
	public double getBlackSpotWinnings(){return black.winningsOnBet();}
	public double getFirstThirdSpotWinnings(){return firstThird.winningsOnBet();}
	public double getSecondThirdSpotWinnings(){return secondThird.winningsOnBet();}
	public double getThirdThirdSpotWinnings(){return thirdThird.winningsOnBet();};
	public double getArySpotWinnings(int num){return RSPary[num].winningsOnBet();}
	
	//---------------------------------------------------------------
	public double totalWinnings(int chosenNum, RouletteWheel.COLOUR c)
	{
		double sum = 0;
		double aryElementWinnings;
		
		aryElementWinnings = getArySpotWinnings(chosenNum);
		if(aryElementWinnings > 0)
			sum += aryElementWinnings;
		if(chosenNum%2 != 0)
			sum += getOddSpotWinnings();
		if(chosenNum%2 == 0)
			sum += getEvenSpotWinnings();
		if(c == RouletteWheel.COLOUR.RED)
			sum += getRedSpotWinnings();
		if(c == RouletteWheel.COLOUR.BLACK)
			sum += getBlackSpotWinnings();
		if(chosenNum >= 1 && chosenNum <= 12)
			sum += getFirstThirdSpotWinnings();
		if(chosenNum >= 13 && chosenNum <= 24)
			sum += getSecondThirdSpotWinnings();
		if(chosenNum >= 25 && chosenNum <= 36)
			sum += getThirdThirdSpotWinnings();
		
		return sum;
	}//_end:TotalWinnings

}//_end:RouletteTable
