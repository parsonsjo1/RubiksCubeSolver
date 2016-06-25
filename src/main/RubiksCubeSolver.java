/**
 * 
 */
package main;

import java.io.IOException;

import RubiksCube.FaceName;
import RubiksCube.RubiksCubeInterface;
import RubiksCube.StandardRubiksCube;

/**
 * @author Joshua Parsons
 *
 */
public class RubiksCubeSolver 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//Create rubiks cube object
		RubiksCubeInterface rubiksCube = new StandardRubiksCube(3);
		
		rubiksCube.displayRubiksCube();
		
		rubiksCube.rotateRight(FaceName.FRONT, 0);
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		//Prompt user for input
		
		//Display rubiks cube and reprompt
		

	}

}
