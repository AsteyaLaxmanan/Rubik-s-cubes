package com.myDomain;

import lejos.nxt.Motor; 
import lejos.nxt.SensorPort;
import lejos.util.*;
import lejos.nxt.addon.ColorHTSensor;
import rep.Cube;
import rep.CubieCube;
import solution.Thistle;
import global.Utils;


public class Thistlethwaite {
		
	int maxDepth = 22; //Set the maximum amount of moves to start the solution. 
	long timeToSearch = 15; //The time in seconds to find the soltuion
	int maxTime = 15; //Maximum time to search for solution
	int motorRotatePower = 35; 
	int ArmPower = 15;
	int DebugSettings = 0;
	
	//static cube variables
	
	int U = 1; //variable for white, up
	int R = 2; //variable for red, right
	int F = 3; //variable for green, front
	int D = 4; //variable for yellow, down
	int L = 5; //variable for orange, left
	int B = 6; //variable for blue, back
	int x = 0; //variable for a side when it is reassigned
	
	//variables for each of the 9 facelets to each side
	
	String f1 = ""; 
	String f2 = "";
	String f3 = "";
	String f4 = "";
	String f5 = "";
	String f6 = "";
	String f7 = "";
	String f8 = "";
	String f9 = "";
	
	//variables to manage cube-solving process
	
	String side = ""; //current side
	String side1 = ""; //new side
	String cubeString = ""; //all facelets on all sides concatenated together
	String cubeStringU = ""; //all facelts on side U concatenated together
	String cubeStringR = ""; //all facelts on side R concatenated together
	String cubeStringF = ""; //all facelts on side F concatenated together
	String cubeStringD = ""; //all facelts on side D concatenated together
	String cubeStringL = ""; //all facelts on side L concatenated together
	String cubeStringB = ""; //all facelts on side B concatenated together
	String printCubeString = ""; //all facelets on all sides concatenated together
	String printcubeStringU = ""; //all facelts on side U concatenated together
	String printcubeStringR = ""; //all facelts on side R concatenated together
	String printcubeStringF = ""; //all facelts on side F concatenated together
	String printcubeStringD = ""; //all facelts on side D concatenated together
	String printcubeStringL = ""; //all facelts on side L concatenated together
	String printcubeStringB = ""; //all facelts on side B concatenated together
	
	String result = ""; //the solution from Thistlewaite's 2-phase algorithm
	String fResult = ""; //the solution from Thistlewaite's 2-phase algorithm
	String move = ""; //current solving move being performed
	String moveModifier = ""; //move modifier to turn the side multiple times or backwards
	boolean useSeparator = false; //use of a separator
	long timeSearching = 0; 
	long timeStart = 0;
	long timeEnd = 0;
	int subStringStart = 0; //String start for move
	int subStringEnd = 1; //String end for move
	int subStringStart2 = subStringStart + 1; //next string start for next move
	int subStringEnd2 = subStringEnd + 1; //next string end for next move
	int colorIDValue = 0; //color ID value return from color sensor 
	int RedValue = 0; //Red value return from color sensor 
	int BlueValue = 0; //Blue value return from color sensor 
	int GreenValue = 0; //Green value return from color sensor 
	long startScan;
	long startCalculate;
	long startSolve;
	long endTime;
	int numOfMovies = -1;
	
	public static String translatedSolution = "";
	public static String printTranslatedSolution = "";
	
	
	void scanCube() { //to scan the whole cube
		scanFace(); //scan inside U
		cubeStringU(); //store facelets to cubeStringU
		rotateCubeSides(1); //rotate cube to side r
		flip(); //flip cube to place side R (red) on top
		rotateCubeSides(-1); //rotate cube to side R (red)
		delay.msDelay(200); //wait to make sure cube has stopped moving
		scanFace(); //scan inside R
		cubeStringR(); //store facelets to cubeStringR
		flip();//flip cube to place side f (green) on top
		scanFace(); //read inside F
		cubeStringF(); //store facelets to cubeStringF
		rotateCubeSides(1); //rotate cube to side D (red)
		flip(); //flip cube to place side D (yellow) on top
		rotateCubeSides(-1); //returns the turn table to original position
		scanFace(); //read in side D
		cubeStringD();//store facelets to cubeStringD
		flip(); //flip cube to move side L on top
		scanFace(); //read in side L
		cubeStringL();//store facelets to cubeStringL
		rotateCubeSides(1);//rotate cube to side B (red)
		flip(); //flip cube to side B (blue) on top
		rotateCubeSides(-1); //returns the turn table to original position
		scanFace(); //read in side B
		cubeStringB(); //add the read in facelets to cubeString
		cubeString = cubeStringD + cubeStringU + cubeStringL + cubeStringR + cubeStringB + cubeStringF;
		flip();
		if (DebugSettings == 1) {
			System.out.println("cubeString = " + cubeString);
		}
		
	}
	}
	void scanFace() {
		String testFace = ""; //variable to check if every facelet was detected
		while (testFace.length() != 6)  {
			testFace = "";
			Motor.B.setPower(40);
			Motor.A.setPower(40);
			Motor.B.rotateTo(140);
			f5 = scanColor();
			Motor.B.rotateTo(173);
			f9 = scanColor();
			Motor.A.rotateTo(-90);
			f3 = scanColor();
			Motor.A.rotateTo(-180);
			f1 = scanColor()
			Motor.A.rotateTo(-70);
			f7 = scanColor();
			Motor.B.rotateTo(168);
			Motor.A.rotateTo(-230);
			f4 = scanColor();
			Motor.A.rotateTo(-140);
			f2 = scanColor();
			Motor.A.rotateTo(-50);
			f6 = scanColor();
			Motor.A.rotateTo(40);
			f8 = scanColor();
			Motor.A.rotateTo(0);
			Motor.B.rotateTo(20);
			testFace = testFace + f1;
			testFace = testFace + f2;
			testFace = testFace + f3;
			testFace = testFace + f4;
			testFace = testFace + f5;
			testFace = testFace + f6;
			testFace = testFace + f7;
			testFace = testFace + f8;
			testFace = testFace + f9;
			if (DebugSettings == 1) {
				System.out.println("f5 = " + f5);
				System.out.println("f9 = " + f9);
				System.out.println("f3 = " + f3);
				System.out.println("f1 = " + f1);
				System.out.println("f7 = " + f7);
				System.out.println("f4 = " + f4);
				System.out.println("f2 = " + f2);
				System.out.println("f6 = " + f6);
				System.out.println("f8 = " + f8);
				System.out.println("testFace = " + testFace);
			}
			
			}
		
	}
	public static String TranslateCube(String StringMove) {
		String RetValue = "";
		switch (StringMove) {
		case "U1" : RetValue = "D";
		break;
		case "U2" : RetValue = "D2";
		break;
		case "U3" : RetValue = "D";
		break;
		case "D1" : RetValue = "U";
		break;
		case "D2" : RetValue = "U2";
		break; 
		case "D3" : RetValue = "U";
		break;
		case "L1" : RetValue = "L";
		break; 
		case "L2" : RetValue = "L2";
		break;
		case "L3" : RetValue = "L";
		break;
		case "R1" : RetValue = "R";
		break; 
		case "R2" : RetValue = "R2";
		break; 
		case "R3" : RetValue = "R";
		break;
		case "f1" : RetValue = "B";
		break;
		case "f2" : RetValue = "B2";
		break;
		case "f3" : RetValue = "B";
		break; 
		case "B1" : RetValue = "F";
		break; 
		case "B2" : RetValue = "f2";
		break;
		case "B3" : RetValue = "F";
		break;
		}
		return RetValue;
		
	}

	public static String PrintTranslateCube(String StringMove) {
		String RetValue = "";
		switch (StringMove) {
		case "U1" : RetValue = "W";
		break;
		case "U2" : RetValue = "W2";
		break;
		case "U3" : RetValue = "W3";
		break;
		case "D1" : RetValue = "Y";
		break;
		case "D2" : RetValue = "Y2";
		break; 
		case "D3" : RetValue = "Y3";
		break;
		case "L1" : RetValue = "O";
		break; 
		case "L2" : RetValue = "O2";
		break;
		case "L3" : RetValue = "O3";
		break;
		case "R1" : RetValue = "R";
		break; 
		case "R2" : RetValue = "R2";
		break; 
		case "R3" : RetValue = "R3";
		break;
		case "f1" : RetValue = "G";
		break;
		case "f2" : RetValue = "G2";
		break;
		case "f3" : RetValue = "G3";
		break; 
		case "B1" : RetValue = "B";
		break; 
		case "B2" : RetValue = "B2";
		break;
		case "B3" : RetValue = "B3";
		break;
		}
		return RetValue;
		
	}
	public static String PrintCube(String StringColor) {
		String RetValue = "";
		switch (StringColor) {
		case "1" : RetValue = "B";
		break;
		case "2" : RetValue = "G";
		break; 
		case "3" : RetValue = "O";
		break; 
		case "4" : RetValue = "R";
		break;
		case "5" : RetValue = "W";
		break; 
		case "6" : RetValue = "Y";
		break;
		
		}
		return RetValue;
		
	}
	

	public Thistlethwaite() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
