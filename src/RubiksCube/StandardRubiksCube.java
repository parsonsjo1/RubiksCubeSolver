/**
 * 
 */
package RubiksCube;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Joshua Parsons
 * 
 */
public class StandardRubiksCube implements RubiksCubeInterface
{
	private Map<FaceName, RubiksCubeFace> rubiksCubeFaces;
	private final int NUM_FACES = 6;
	
	public StandardRubiksCube(int size)
	{
		setFaces(size);
	}

	@Override
	public void shuffleRubiksCube()
	{
		
	}
	
	@Override
	public void resetRubiksCube()
	{
		
	}
	
	@Override
	public void solveRubiksCube()
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
	
	
	public void setFaces(int size)
	{
		this.rubiksCubeFaces = new EnumMap<FaceName, RubiksCubeFace>(FaceName.class);

		for(FaceName faceName : FaceName.values())
		{
			this.rubiksCubeFaces.put(faceName, new RubiksCubeFace(size, faceName));
		}
	}
}