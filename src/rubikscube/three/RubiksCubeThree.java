/**
 * 
 */
package rubikscube.three;

import java.util.EnumMap;
import java.util.Map;

import rubikscube.RubiksCubeInterface;
import rubikscube.enums.Direction;
import rubikscube.enums.FaceName;
import rubikscube.enums.TileColor;

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
		setRubiksCubeFaces();
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
		setRubiksCubeFaces();
	}
	
	/**
	 * Solve the rubiks cube
	 */
	@Override
	public void solveRubiksCube()
	{
		
	}
	
	@Override
	public void rotateRowClockwise(FaceName faceName, int row)
	{	
		RubiksCubeFace currentFace = this.rubiksCubeFaces.get(faceName);
		RubiksCubeTile[] currentRowTiles = currentFace.getFaceRow(row);
		
		for(FaceName nextFaceName : currentFace.getRotationMap(Direction.RIGHT))
		{
			RubiksCubeFace nextFace = this.rubiksCubeFaces.get(nextFaceName);
			RubiksCubeTile[] nextRowTiles = nextFace.getFaceRow(row);
			
			nextFace.setFaceRow(row, currentRowTiles);
			
			currentRowTiles = nextRowTiles;
		}
	}
	
	@Override
	public void rotateRowCounterClockwise(FaceName faceName, int row)
	{

	}
	
	@Override
	public void rotateColClockwise(FaceName faceName, int col)
	{	
		
	}
	
	@Override
	public void rotateColCounterClockwise(FaceName faceName, int col)
	{

	}
	
	public void setRubiksCubeFaces()
	{
		this.rubiksCubeFaces = new EnumMap<FaceName, RubiksCubeFace>(FaceName.class);
		
		final int numRows = 3;
		final int numCols = 3;
		
		for(int i = 0; i < FaceName.values().length; i++)
		{
			FaceName faceName = FaceName.values()[i];
			TileColor tileColor = TileColor.values()[i];
			this.rubiksCubeFaces.put(faceName, new RubiksCubeFace(numRows, numCols, faceName, tileColor));
		}
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
		
		final int numRows = topFace.getFaceTiles().length;
		final int numCols = topFace.getFaceTiles().length;
		
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
}