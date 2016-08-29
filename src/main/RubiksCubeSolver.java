/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rubikscube.exceptions.InvalidInputException;
import rubikscube.interfaces.IRubiksCube;
import rubikscube.standard.StandardRubiksCube;
import rubikscube.standard.factories.RubiksCubeFactory;

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
		RubiksCubeFactory rubiksCubeFactory = RubiksCubeFactory.getInstance();
		
		/*Register different Rubiks Cubes here*/
		//BeginnerRubiksCube.registerRubiksCube();
		StandardRubiksCube.registerRubiksCube();
		//AdvancedRubiksCube.registerRubiksCube();
		//ChallengerRubiksCube.registerRubiksCube();
		//ImpossibleRubiksCube.registerRubiksCube();
		
		//Get the users selected option
		//String selectedType = getUserSelection(rubiksCubeFactory);
		
		//Create rubiks cube object using the factory method
		IRubiksCube rubiksCube = rubiksCubeFactory.createRubiksCube("Standard (3x3)");

//		rubiksCube.display();
//		rubiksCube.rotateFront();
//		System.out.println();
//		rubiksCube.display();
//		
//		rubiksCube.rotateBack();
//		System.out.println();
//		rubiksCube.display();
//		
//		rubiksCube.rotateDown();
//		System.out.println();
//		rubiksCube.display();
//		
//		rubiksCube.rotateLeft();
//		System.out.println();
//		rubiksCube.display();
		
		
		for(int i = 0; i < 100; i++)
		{
			rubiksCube.shuffle();
			
			rubiksCube.display();
			
			rubiksCube.solve();
			
			System.out.println();
			
			rubiksCube.display();
		}
		

	}
	
	/**
	 * @return an String of the users selection
	 */
	private static String getUserSelection(RubiksCubeFactory rubiksCubeFactory)
	{		
		//Prompt user 
		System.out.println("Select the type of Rubiks cube that you would like to use:\n");
		String[] optionList = getOptions((Map<String, IRubiksCube>)rubiksCubeFactory.getRubiksCubeTypes());
		long numRubiksCubeTypes = optionList.length;

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
				return optionList[selectedOption - 1];
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
	 * @return a String[]
	 */
	private static String[] getOptions(Map<String, IRubiksCube> rubiksCubeTypes)
	{
		String[] optionList = new String[rubiksCubeTypes.size()];
		int counter = 0;
		for(String rubiksCubeType : rubiksCubeTypes.keySet())
		{
			optionList[counter++] = rubiksCubeType;
			System.out.println((counter) + ". " + rubiksCubeType);
		}
		
		return optionList;
	}

}
