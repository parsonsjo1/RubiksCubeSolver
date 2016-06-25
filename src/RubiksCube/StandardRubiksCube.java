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
	
	/**
	 * Set Rubiks cube back to inital state
	 */
	@Override
	public void resetRubiksCube()
	{
		setFaces(3);
	}
	
	@Override
	public void solveRubiksCube()
	{
		
	}
	
	/*Wrapper*/
	public void rotateRight(FaceName faceName, int row)
	{
		final int ROTATIONS = 4;
		
		RubiksCubeFace face = this.rubiksCubeFaces.get(faceName);
		rotateRight(face, row, ROTATIONS);
	}
	
	private void rotateRight(RubiksCubeFace face, int row, int rotationCount)
	{	
		Map<FaceName, RubiksCubeTile[]> collectedRows = this.collectRows(face, row);
		
		for(int i = 0; i < 4; i++)
		{
			FaceName faceLeftName = face.getAdjacentFace(Direction.LEFT);

			face.setFaceRow(row, collectedRows.get(faceLeftName));
			
			face = this.rubiksCubeFaces.get(face.getAdjacentFace(Direction.RIGHT));
		}
		
		//Rotate top
		if(row == 0)
		{
			FaceName faceTopName = face.getAdjacentFace(Direction.UP);
			RubiksCubeFace faceTop = this.rubiksCubeFaces.get(faceTopName);
			
			faceTop.rotateRight();
		}
		
		//Rotate bottom
		if(row == 2)
		{
			
		}
	}
	
	public void rotateLeft(FaceName faceName, int row)
	{
		RubiksCubeFace face = rubiksCubeFaces.get(faceName);
		
		face.getFaceRow(row);
	}
	
	/**
	 * @param faceName
	 * @param row
	 * @return Map<FaceName, RubiksCubeTile[]>
	 */
	private Map<FaceName, RubiksCubeTile[]> collectRows(RubiksCubeFace face, int row)
	{
		Map<FaceName, RubiksCubeTile[]> collectedRows = new EnumMap<FaceName, RubiksCubeTile[]>(FaceName.class);

		for(int i = 0; i < 4; i++)
		{
			collectedRows.put(face.getFaceName(), face.getFaceRow(row));
			face = this.rubiksCubeFaces.get(face.getAdjacentFace(Direction.RIGHT));
		}
		return collectedRows;
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