/**
 * 
 */
package rubikscube.three;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import rubikscube.RubiksCubeInterface;
import rubikscube.enums.FaceName;
import rubikscube.enums.TileColor;

/**
 * @author Joshua Parsons
 * 
 */
public class RubiksCubeThree implements RubiksCubeInterface
{
	private RubiksCubeFace topFace;
	private RubiksCubeFace leftFace;
	private RubiksCubeFace frontFace;
	private RubiksCubeFace rightFace;
	private RubiksCubeFace backFace;
	private RubiksCubeFace bottomFace;
	
	private Map<FaceName, RubiksCubeTile[]> tileSets;
	
	private final int numRows =  3;
	private final int numCols = 3;
	
	/**
	 * Default Constructor
	 */
	public RubiksCubeThree()
	{
		setTopFace(new RubiksCubeFace(numRows, numCols, FaceName.TOP, TileColor.WHITE));
		setLeftFace(new RubiksCubeFace(numRows, numCols, FaceName.LEFT, TileColor.ORANGE));
		setFrontFace(new RubiksCubeFace(numRows, numCols, FaceName.FRONT, TileColor.GREEN));
		setRightFace(new RubiksCubeFace(numRows, numCols, FaceName.RIGHT, TileColor.RED));
		setBackFace(new RubiksCubeFace(numRows, numCols, FaceName.BACK, TileColor.BLUE));
		setBottomFace(new RubiksCubeFace(numRows, numCols, FaceName.BOTTOM, TileColor.YELLOW));
		setTileSets();
	}
	
	public RubiksCubeThree(TileColor topFaceColor, TileColor leftFaceColor, TileColor frontFaceColor, TileColor rightFaceColor, TileColor backFaceColor, TileColor bottomFaceColor)
	{
		setTopFace(new RubiksCubeFace(numRows, numCols, FaceName.TOP, topFaceColor));
		setLeftFace(new RubiksCubeFace(numRows, numCols, FaceName.LEFT, leftFaceColor));
		setFrontFace(new RubiksCubeFace(numRows, numCols, FaceName.FRONT, frontFaceColor));
		setRightFace(new RubiksCubeFace(numRows, numCols, FaceName.RIGHT, rightFaceColor));
		setBackFace(new RubiksCubeFace(numRows, numCols, FaceName.BACK, backFaceColor));
		setBottomFace(new RubiksCubeFace(numRows, numCols, FaceName.BOTTOM, bottomFaceColor));
		setTileSets();
	}

	/**
	 * Shuffle the rubiks cube
	 */
	@Override
	public void shuffleRubiksCube()
	{
		for(int i = 0; i < 100; i++)
		{
			Random random = new Random();
			int rotateMethod = random.nextInt(NUM_FACES);
			//Invert every other
			if(i % 2 == 0)
				rotateMethod *= -1;
			
			switch(rotateMethod)
			{
				case 1:
				{
					this.rotateTopClockwise();
					break;
				}
				case -1:
				{
					this.rotateTopCounterClockwise();
					break;
				}
				case 2:
				{
					this.rotateLeftClockwise();
					break;
				}
				case -2:
				{
					this.rotateLeftCounterClockwise();
					break;
				}
				case 3:
				{
					this.rotateFrontClockwise();
					break;
				}
				case -3:
				{
					this.rotateFrontCounterClockwise();
					break;
				}
				case 4:
				{
					this.rotateRightClockwise();
					break;
				}
				case -4:
				{
					this.rotateRightCounterClockwise();
					break;
				}
				case 5:
				{
					this.rotateBackClockwise();
					break;
				}
				case -5:
				{
					this.rotateBackCounterClockwise();
					break;
				}
				case 6:
				{
					this.rotateBottomClockwise();
					break;
				}
				case -6:
				{
					this.rotateBottomCounterClockwise();
					break;
				}
				default:
				{
					continue;
				}
			}
		}
	}
	
	/**
	 * Reset the rubiks cube
	 */
	@Override
	public void resetRubiksCube()
	{
		
	}
	
	/**
	 * Solve the rubiks cube
	 */
	@Override
	public void solveRubiksCube()
	{
		//Step 1
		this.solveWhiteCross();
	}
	
	/**
	 * 
	 */
	private void solveWhiteCross()
	{
		
	}
	
	/**
	 * Rotate the top of the cube clockwise
	 */
	public void rotateTopClockwise()
	{	
		int row = 0;
		
		topFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceRow(row));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceRow(row));
		this.tileSets.put(FaceName.BACK, backFace.getFaceRow(row));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceRow(row));
		
		frontFace.setFaceRow(row, this.tileSets.get(FaceName.RIGHT));
		rightFace.setFaceRow(row, this.tileSets.get(FaceName.BACK));
		backFace.setFaceRow(row, this.tileSets.get(FaceName.LEFT));
		leftFace.setFaceRow(row, this.tileSets.get(FaceName.FRONT));
	}
	
	/**
	 * Rotate the top of the cube counterclockwise
	 */
	public void rotateTopCounterClockwise()
	{	
		int row = 0;
		
		topFace.rotateTilesCounterClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceRow(row));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceRow(row));
		this.tileSets.put(FaceName.BACK, backFace.getFaceRow(row));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceRow(row));
		
		frontFace.setFaceRow(row, this.tileSets.get(FaceName.LEFT));
		rightFace.setFaceRow(row, this.tileSets.get(FaceName.FRONT));
		backFace.setFaceRow(row, this.tileSets.get(FaceName.RIGHT));
		leftFace.setFaceRow(row, this.tileSets.get(FaceName.BACK));
	}
	
	/**
	 * Rotate the left of the cube clockwise
	 */
	public void rotateLeftClockwise()
	{	
		int col = 0;
		
		leftFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceCol(col));
		this.tileSets.put(FaceName.TOP, topFace.getFaceCol(col));
		this.tileSets.put(FaceName.BACK, backFace.getFaceCol(col));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceCol(col));
		
		frontFace.setFaceCol(col, this.tileSets.get(FaceName.TOP));
		topFace.setFaceCol(col, this.tileSets.get(FaceName.BACK));
		backFace.setFaceCol(col, this.tileSets.get(FaceName.BOTTOM));
		bottomFace.setFaceCol(col, this.tileSets.get(FaceName.FRONT));
	}
	
	/**
	 * Rotate the left of the cube counterclockwise
	 */
	public void rotateLeftCounterClockwise()
	{	
		int col = 0;
		
		leftFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceCol(col));
		this.tileSets.put(FaceName.TOP, topFace.getFaceCol(col));
		this.tileSets.put(FaceName.BACK, backFace.getFaceCol(col));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceCol(col));
		
		frontFace.setFaceCol(col, this.tileSets.get(FaceName.BOTTOM));
		topFace.setFaceCol(col, this.tileSets.get(FaceName.FRONT));
		backFace.setFaceCol(col, this.tileSets.get(FaceName.TOP));
		bottomFace.setFaceCol(col, this.tileSets.get(FaceName.BACK));
	}
	
	/**
	 * Rotate the front of the cube clockwise
	 */
	public void rotateFrontClockwise()
	{	
		int topRow = 2;
		int rightCol = 0;
		int bottomRow = 0;
		int leftCol = 2;
		
		frontFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceRow(topRow));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceCol(rightCol));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceRow(bottomRow));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceCol(leftCol));
		
		topFace.setFaceRow(topRow, this.tileSets.get(FaceName.LEFT));
		rightFace.setFaceCol(rightCol, this.tileSets.get(FaceName.TOP));
		bottomFace.setFaceRow(bottomRow, this.tileSets.get(FaceName.RIGHT));
		leftFace.setFaceCol(leftCol, this.tileSets.get(FaceName.BOTTOM));
	}
	
	/**
	 * Rotate the front of the cube counterclockwise
	 */
	public void rotateFrontCounterClockwise()
	{	
		int topRow = 2;
		int rightCol = 0;
		int bottomRow = 0;
		int leftCol = 2;
		
		frontFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceRow(topRow));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceCol(rightCol));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceRow(bottomRow));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceCol(leftCol));
		
		topFace.setFaceRow(topRow, this.tileSets.get(FaceName.RIGHT));
		rightFace.setFaceCol(rightCol, this.tileSets.get(FaceName.BOTTOM));
		bottomFace.setFaceRow(bottomRow, this.tileSets.get(FaceName.LEFT));
		leftFace.setFaceCol(leftCol, this.tileSets.get(FaceName.TOP));
	}	
	
	/**
	 * Rotate the right of the cube clockwise
	 */
	public void rotateRightClockwise()
	{	
		int col = 2;
		
		rightFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceCol(col));
		this.tileSets.put(FaceName.TOP, topFace.getFaceCol(col));
		this.tileSets.put(FaceName.BACK, backFace.getFaceCol(col));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceCol(col));
		
		frontFace.setFaceCol(col, this.tileSets.get(FaceName.TOP));
		topFace.setFaceCol(col, this.tileSets.get(FaceName.BACK));
		backFace.setFaceCol(col, this.tileSets.get(FaceName.BOTTOM));
		bottomFace.setFaceCol(col, this.tileSets.get(FaceName.FRONT));
	}
	
	/**
	 * Rotate the right of the cube clockwise
	 */
	public void rotateRightCounterClockwise()
	{	
		int col = 2;
		
		rightFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceCol(col));
		this.tileSets.put(FaceName.TOP, topFace.getFaceCol(col));
		this.tileSets.put(FaceName.BACK, backFace.getFaceCol(col));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceCol(col));
		
		frontFace.setFaceCol(col, this.tileSets.get(FaceName.BOTTOM));
		topFace.setFaceCol(col, this.tileSets.get(FaceName.FRONT));
		backFace.setFaceCol(col, this.tileSets.get(FaceName.TOP));
		bottomFace.setFaceCol(col, this.tileSets.get(FaceName.BACK));
	}
	
	/**
	 * Rotate the back of the cube clockwise
	 */
	public void rotateBackClockwise()
	{	
		int topRow = 0;
		int rightCol = 2;
		int bottomRow = 2;
		int leftCol = 0;
		
		backFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceRow(topRow));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceCol(rightCol));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceRow(bottomRow));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceCol(leftCol));
		
		topFace.setFaceRow(topRow, this.tileSets.get(FaceName.RIGHT));
		rightFace.setFaceCol(rightCol, this.tileSets.get(FaceName.BOTTOM));
		bottomFace.setFaceRow(bottomRow, this.tileSets.get(FaceName.LEFT));
		leftFace.setFaceCol(leftCol, this.tileSets.get(FaceName.TOP));
	}
	
	/**
	 * Rotate the back of the cube counterclockwise
	 */
	public void rotateBackCounterClockwise()
	{	
		int topRow = 0;
		int rightCol = 2;
		int bottomRow = 2;
		int leftCol = 0;
		
		backFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceRow(topRow));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceCol(rightCol));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceRow(bottomRow));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceCol(leftCol));
		
		topFace.setFaceRow(topRow, this.tileSets.get(FaceName.LEFT));
		rightFace.setFaceCol(rightCol, this.tileSets.get(FaceName.TOP));
		bottomFace.setFaceRow(bottomRow, this.tileSets.get(FaceName.RIGHT));
		leftFace.setFaceCol(leftCol, this.tileSets.get(FaceName.BOTTOM));
	}	

	
	/**
	 * Rotate the bottom of the cube clockwise
	 */
	public void rotateBottomClockwise()
	{	
		int row = 2;
		
		bottomFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceRow(row));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceRow(row));
		this.tileSets.put(FaceName.BACK, backFace.getFaceRow(row));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceRow(row));
		
		frontFace.setFaceRow(row, this.tileSets.get(FaceName.LEFT));
		rightFace.setFaceRow(row, this.tileSets.get(FaceName.FRONT));
		backFace.setFaceRow(row, this.tileSets.get(FaceName.RIGHT));
		leftFace.setFaceRow(row, this.tileSets.get(FaceName.BACK));
	}

	/**
	 * Rotate the bottom of the cube clockwise
	 */
	public void rotateBottomCounterClockwise()
	{	
		int row = 2;
		
		bottomFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceRow(row));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceRow(row));
		this.tileSets.put(FaceName.BACK, backFace.getFaceRow(row));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceRow(row));
		
		frontFace.setFaceRow(row, this.tileSets.get(FaceName.RIGHT));
		rightFace.setFaceRow(row, this.tileSets.get(FaceName.BACK));
		backFace.setFaceRow(row, this.tileSets.get(FaceName.LEFT));
		leftFace.setFaceRow(row, this.tileSets.get(FaceName.FRONT));
	}
	
	/**
	 * @return
	 */
	public RubiksCubeFace getTopFace()
	{
		return this.topFace;
	}
	
	/**
	 * @param topFace
	 */
	public void setTopFace(RubiksCubeFace topFace)
	{
		this.topFace = topFace;
	}

	/**
	 * @return the leftFace
	 */
	public RubiksCubeFace getLeftFace()
	{
		return this.leftFace;
	}

	/**
	 * @param leftFace the leftFace to set
	 */
	public void setLeftFace(RubiksCubeFace leftFace)
	{
		this.leftFace = leftFace;
	}

	/**
	 * @return the frontFace
	 */
	public RubiksCubeFace getFrontFace()
	{
		return this.frontFace;
	}

	/**
	 * @param frontFace the frontFace to set
	 */
	public void setFrontFace(RubiksCubeFace frontFace)
	{
		this.frontFace = frontFace;
	}

	/**
	 * @return the rightFace
	 */
	public RubiksCubeFace getRightFace()
	{
		return this.rightFace;
	}

	/**
	 * @param rightFace the rightFace to set
	 */
	public void setRightFace(RubiksCubeFace rightFace)
	{
		this.rightFace = rightFace;
	}

	/**
	 * @return the backFace
	 */
	public RubiksCubeFace getBackFace()
	{
		return this.backFace;
	}

	/**
	 * @param backFace the backFace to set
	 */
	public void setBackFace(RubiksCubeFace backFace)
	{
		this.backFace = backFace;
	}

	/**
	 * @return the bottomFace
	 */
	public RubiksCubeFace getBottomFace()
	{
		return this.bottomFace;
	}

	/**
	 * @param bottomFace the bottomFace to set
	 */
	public void setBottomFace(RubiksCubeFace bottomFace)
	{
		this.bottomFace = bottomFace;
	}
	
	public void setTileSets()
	{
		this.tileSets = new EnumMap<FaceName, RubiksCubeTile[]>(FaceName.class);
	}

	/**
	 * Format and print the rubiks cube out to the console
	 */
	@Override
	public void displayRubiksCube() 
	{	
		//TOP FACE
		RubiksCubeTile[][] topFaceTiles = topFace.getFaceTiles();
		
		for(int row = 0; row < this.numRows; row++)
		{
			System.out.printf("%-30s", "");
			for(int col = 0; col < this.numCols; col++)
			{
				System.out.printf("%-10s", topFaceTiles[row][col].getTileColor());
			}
			System.out.println();
		}
		
		for(int row = 0; row < this.numRows; row++)
		{
			//LEFT
			RubiksCubeTile[][] leftFaceTiles = leftFace.getFaceTiles();
			for(int col = 0; col < numCols; col++)
			{
				System.out.printf("%-10s", leftFaceTiles[row][col].getTileColor());
			}
			
			//FRONT
			RubiksCubeTile[][] frontFaceTiles = frontFace.getFaceTiles();
			for(int col = 0; col < this.numCols; col++)
			{
				System.out.printf("%-10s", frontFaceTiles[row][col].getTileColor());
			}
			
			//RIGHT
			RubiksCubeTile[][] rightFaceTiles = rightFace.getFaceTiles();
			for(int col = 0; col < this.numCols; col++)
			{
				System.out.printf("%-10s", rightFaceTiles[row][col].getTileColor());
			}
			
			//BACK
			RubiksCubeTile[][] backFaceTiles = backFace.getFaceTiles();
			for(int col = 0; col < this.numCols; col++)
			{
				System.out.printf("%-10s", backFaceTiles[row][col].getTileColor());
			}

			System.out.println();
		}
		
		//BOTTOM
		RubiksCubeTile[][] bottomFaceTiles = bottomFace.getFaceTiles();
		for(int row = 0; row < this.numRows; row++)
		{
			System.out.printf("%-30s", "");
			for(int col = 0; col < this.numCols; col++)
			{
				System.out.printf("%-10s", bottomFaceTiles[row][col].getTileColor());
			}
			System.out.println();
		}
	}
}