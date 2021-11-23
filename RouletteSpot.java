// RouletteSpot Class
// Samira C. Oliva  Madrigal
//
// Roulette spot represents one betting spot on the roulette table
// Contains private instance variables for the payoff
// for GUI lab7
import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;


public class RouletteSpot{
	
	private int      payoff  = 0;
	private int      userBet = 0; 

	public RouletteSpot(int pof) 
	{
		payoff   = pof;
		// leave userBet  as 0
	}//_end:constructor
	
	//----------- getters------------------------
	int    getPayOff(){return  payoff;};
	int    getUserBet(){return userBet;};
	
	//----------- setters -----------------------
	void   setBet(int newBet){userBet = newBet;};
	
	//-------------------------------------------
	//Return the winnings of a spot
	public double winningsOnBet(){
		
		double winnings;
		winnings = payoff*userBet + userBet;
		return winnings;
		
	}//_end:winningsOnBet
	
}//_end:RouletteSpot Class
