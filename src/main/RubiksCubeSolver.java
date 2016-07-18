/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rubikscube.RubiksCubeInterface;
import rubikscube.enums.FaceName;
import rubikscube.enums.RubiksCubeTypes;
import rubikscube.exceptions.InvalidInputException;
import rubikscube.three.RubiksCubeThree;

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
		//Get the users selected option
		//int selectedOption = getUserSelection();
		int selectedOption = 1;
		
		//Create rubiks cube object
		RubiksCubeInterface rubiksCube = null;
		switch(selectedOption)
		{
			case 1:
			{
				rubiksCube = new RubiksCubeThree();
			}
		}

		rubiksCube.shuffleRubiksCube();
		
		rubiksCube.displayRubiksCube();
		
		//Prompt user for input
		
		//Display rubiks cube and reprompt
		

	}
	
	/**
	 * @return an int of the users selection
	 */
	private static int getUserSelection()
	{
		//maxLength is the number of digits in the number of RubiksCubeTypes
		int numRubiksCubeTypes = RubiksCubeTypes.values().length;
		String optionList = getOptions();

		//Prompt user 
		System.out.println("Select the type of Rubiks cube that you would like to use:\n");
		System.out.println(optionList);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		while(true)
		{
			try
			{
				String input = br.readLine();
				//Check the length users input
				if(input.length() > numRubiksCubeTypes)
				{
					throw new InvalidInputException("\nSelection should be a number between 1 and " + numRubiksCubeTypes + ".\nPlease select a valid option from the list.\n");
				}
				
				//Create a regex pattern to match digits
				String pattern = "\\d+";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(input);
				
				//Check if the users input matches the pattern (checking that the user input a number)
				if(!m.find())
				{
					throw new InvalidInputException("\nInvalid Input. Input should be a valid number between 1 and " + numRubiksCubeTypes + ".\nPlease seect a valid option from the list.\n");
				}
				
				int selectedOption = Integer.parseInt(input);
				
				//Check that the users selection not greater than the number of options available
				if(selectedOption > numRubiksCubeTypes)
				{
					throw new InvalidInputException("\nInvalid Input. Input should be a valid number between 1 and " + numRubiksCubeTypes + ".\nPlease seect a valid option from the list.\n");
				}
				
				//If all checks pass return the users selected option
				System.out.println();
				return selectedOption;
			} 
			catch (InvalidInputException e)
			{
				System.out.println(e.getMessage());
				br = new BufferedReader(new InputStreamReader(System.in));
			}
			catch (NumberFormatException | IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return a string list of options for the user to choose from
	 */
	private static String getOptions()
	{
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		for(RubiksCubeTypes type : RubiksCubeTypes.values())
		{
			int rubiksCubeSize = type.getSize();
			String option = counter++ + ". " + rubiksCubeSize + "x" + rubiksCubeSize + " Rubiks Cube";
			sb.append(option);
		}
		
		return sb.toString();
	}

}
