//CIS35A
//Professor Lee-Klawender
//Lab 7 GUI version of Lab6
//11 December 2012
//Samira C. Oliva Madrigal
//
// Program:
// This is a test driver class that instantiates the game GUI class, 
// only once and calls methods on for certain settings like
// its size, etc.
//

import java.awt.*;

public class TestDriver {

	public static void main(String[] args)
	{
		RouletteGame obj = new RouletteGame();
		obj.setSizeForMainFrame(680, 855);
		obj.setMainFrameVisibility(true);
		obj.setMainFrameBGColor(Color.lightGray);
	}//_end:main

}//_end:TestDriver_Class
