/**
 * 
 */
package rubikscube;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Joshua Parsons
 * 
 */
public class RubiksCubeThree implements RubiksCubeInterface
{
	private Map<FaceName, RubiksCubeFace> rubiksCubeFaces;
	
	/**
	 * Default Constructor
	 */
	public RubiksCubeThree()
	{
		setRubiksCubeFaces(new EnumMap<FaceName, RubiksCubeFace>(FaceName.class));
	}

	/**
	 * Shuffle the rubiks cube
	 */
	@Override
	public void shuffleRubiksCube()
	{
		
	}
	
	/**
	 * Reset the rubiks cube
	 */
	@Override
	public void resetRubiksCube()
	{
		setRubiksCubeFaces(new EnumMap<FaceName, RubiksCubeFace>(FaceName.class));
	}
	
	/**
	 * Solve the rubiks cube
	 */
	@Override
	public void solveRubiksCube()
	{
		
	}
	
	@Override
	public void rotateClockwise(FaceName faceName, int row)
	{	

	}
	
	@Override
	public void rotateCounterClockwise(FaceName faceName, int row)
	{

	}

	/**
	 * Format and print the rubiks cube out to the console
	 */
	@Override
	public void displayRubiksCube() 
	{	
		//TOP FACE
		RubiksCubeFace topFace = rubiksCubeFaces.get(FaceName.TOP);
		RubiksCubeTile[][] topFaceTiles = topFace.getFaceTiles();
		
		int numRows = topFace.getFaceTiles().length;
		int numCols = topFace.getFaceTiles().length;
		
		for(int row = 0; row < numRows; row++)
		{
			System.out.printf("%-30s", "");
			for(int col = 0; col < numCols; col++)
			{
				System.out.printf("%-10s", topFaceTiles[row][col].getTileColor());
			}
			System.out.println();
		}
		
		for(int row = 0; row < numRows; row++)
		{
			//LEFT
			RubiksCubeFace leftFace = rubiksCubeFaces.get(FaceName.LEFT);
			RubiksCubeTile[][] leftFaceTiles = leftFace.getFaceTiles();
			for(int col = 0; col < numCols; col++)
			{
				System.out.printf("%-10s", leftFaceTiles[row][col].getTileColor());
			}
			
			//FRONT
			RubiksCubeFace frontFace = rubiksCubeFaces.get(FaceName.FRONT);
			RubiksCubeTile[][] frontFaceTiles = frontFace.getFaceTiles();
			for(int col = 0; col < numCols; col++)
			{
				System.out.printf("%-10s", frontFaceTiles[row][col].getTileColor());
			}
			
			//RIGHT
			RubiksCubeFace rightFace = rubiksCubeFaces.get(FaceName.RIGHT);
			RubiksCubeTile[][] rightFaceTiles = rightFace.getFaceTiles();
			for(int col = 0; col < numCols; col++)
			{
				System.out.printf("%-10s", rightFaceTiles[row][col].getTileColor());
			}
			
			//BACK
			RubiksCubeFace backFace = rubiksCubeFaces.get(FaceName.BACK);
			RubiksCubeTile[][] backFaceTiles = backFace.getFaceTiles();
			for(int col = 0; col < numCols; col++)
			{
				System.out.printf("%-10s", backFaceTiles[row][col].getTileColor());
			}

			System.out.println();
		}
		
		//BOTTOM
		RubiksCubeFace bottomFace = rubiksCubeFaces.get(FaceName.BOTTOM);
		RubiksCubeTile[][] bottomFaceTiles = bottomFace.getFaceTiles();
		for(int row = 0; row < numRows; row++)
		{
			System.out.printf("%-30s", "");
			for(int col = 0; col < numCols; col++)
			{
				System.out.printf("%-10s", bottomFaceTiles[row][col].getTileColor());
			}
			System.out.println();
		}
	}
	
	
	public void setRubiksCubeFaces(EnumMap<FaceName, RubiksCubeFace> facesEnumMap)
	{
		this.rubiksCubeFaces = facesEnumMap;
		
		final int numRows = 3;
		final int numCols = 3;
		
		for(FaceName faceName : FaceName.values())
		{
			this.rubiksCubeFaces.put(faceName, new RubiksCubeFace(numRows, numCols, faceName));
		}
	}
}