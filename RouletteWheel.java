//RouletteWheel Class contains only instance methods
//Samira C. Oliva Madrigal
//for GUI lab7

import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;

public class RouletteWheel{

	private int resNum; //choice number
	public static final int min = 0;
	public static final int max = 36;
	public enum COLOUR{RED,BLACK,GREEN};
	COLOUR  [] ary = new COLOUR[37];
	
	//-------------------------------------------------------------
	//initialize array so each element has the correct colour
	public RouletteWheel()
	{		
		if(ary != null)
		{
			ary[0] = COLOUR.GREEN;
			
			for(int idx = 1; idx < 11; idx++)
			{
				if(idx%2 == 0)
					ary[idx] = COLOUR.BLACK;//2-10 even (black)
				else
					ary[idx] = COLOUR.RED; //1-9 odd (red)
			}		
			for(int idx = 11; idx <= 18; idx++)
			{
			   if(idx%2 == 0)
				   ary[idx] = COLOUR.RED;//12-18 even (red)
			   else
				   ary[idx] = COLOUR.BLACK;//11-17 odd (black)
			}
			ary[19] = COLOUR.RED;
			for(int idx = 20; idx <= 28; idx++)
			{
				if(idx%2 == 0)
					ary[idx] = COLOUR.BLACK;//20-28 even (black)
				else
					ary[idx] = COLOUR.RED;//21-27 odd (red)
			}
			ary[29] = COLOUR.BLACK;
			for(int idx = 30; idx <= max; idx++)
			{
				if(idx%2 == 0)
					ary[idx] = COLOUR.RED;
				else
					ary[idx] = COLOUR.BLACK;
			}
		}
	}//end:Constructor
	
	//-------------------------------------------------------------
	// "Spin" the wheel and set resNum to a # between 0 - 36 inclusive
	public void spinTheWheel()
	{
		resNum = (int)(Math.random() * (max - min + 1)) + min;
	}//_end:spinTheWheel
	
	//-------------------------------------------------------------
	public int getChosenNum(){return resNum;};
	
	//-------------------------------------------------------------
	public COLOUR getChosenIntColour()
	{
		if(resNum != min)//myNum != 0
		{
			if( ary[resNum] == COLOUR.RED)
				return COLOUR.RED;
			else
				return COLOUR.BLACK;
		}
		else 
			return COLOUR.GREEN;
	}
	
}//_end:RouletteWheel Class



