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
import rubikscube.enums.TileLocation;

/**
 * @author Joshua Parsons
 * 
 */
public class RubiksCubeThree implements RubiksCubeInterface
{
	private Map<FaceName, RubiksCubeTile[]> tileSets;
	private RubiksCubeFace[] faces;
	
	private RubiksCubeFace topFace;
	private RubiksCubeFace leftFace;
	private RubiksCubeFace frontFace;
	private RubiksCubeFace rightFace;
	private RubiksCubeFace backFace;
	private RubiksCubeFace bottomFace;
	
	private final int numRows =  3;
	private final int numCols = 3;
	
	/**
	 * Default Constructor
	 */
	public RubiksCubeThree()
	{
		setTileSets(new EnumMap<FaceName, RubiksCubeTile[]>(FaceName.class));
		setFaces(new RubiksCubeFace[NUM_FACES]);
		setTopFace(new RubiksCubeFace(numRows, numCols, FaceName.TOP, TileColor.WHITE));
		setLeftFace(new RubiksCubeFace(numRows, numCols, FaceName.LEFT, TileColor.ORANGE));
		setFrontFace(new RubiksCubeFace(numRows, numCols, FaceName.FRONT, TileColor.GREEN));
		setRightFace(new RubiksCubeFace(numRows, numCols, FaceName.RIGHT, TileColor.RED));
		setBackFace(new RubiksCubeFace(numRows, numCols, FaceName.BACK, TileColor.BLUE));
		setBottomFace(new RubiksCubeFace(numRows, numCols, FaceName.BOTTOM, TileColor.YELLOW));
	}
	
	public RubiksCubeThree(TileColor topFaceColor, TileColor leftFaceColor, TileColor frontFaceColor, TileColor rightFaceColor, TileColor backFaceColor, TileColor bottomFaceColor)
	{
		setTileSets(new EnumMap<FaceName, RubiksCubeTile[]>(FaceName.class));
		setFaces(new RubiksCubeFace[NUM_FACES]);
		setTopFace(new RubiksCubeFace(numRows, numCols, FaceName.TOP, topFaceColor));
		setLeftFace(new RubiksCubeFace(numRows, numCols, FaceName.LEFT, leftFaceColor));
		setFrontFace(new RubiksCubeFace(numRows, numCols, FaceName.FRONT, frontFaceColor));
		setRightFace(new RubiksCubeFace(numRows, numCols, FaceName.RIGHT, rightFaceColor));
		setBackFace(new RubiksCubeFace(numRows, numCols, FaceName.BACK, backFaceColor));
		setBottomFace(new RubiksCubeFace(numRows, numCols, FaceName.BOTTOM, bottomFaceColor));
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
		//this.solveTopCross();
		this.solveWhiteCross();
		
		//Step 2
		//this.solveTopCorners();
		this.solveWhiteCorners();
		
		//Step 3
		//this.solveMiddleLayers();
		
		//Step 4
		//this.solveBottomCross();
		
		//Step 5
		//this.solveBottomCorners();
	}
	
	/**
	 * 
	 */
	private void solveWhiteCross()
	{
		TileColor topFaceTileColor = topFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		
		System.out.println("SOLVE TOP");
		for(RubiksCubeFace face : this.faces)
		{
			if(bottomFace.isTileColor(TileLocation.TOP_EDGE, topFaceTileColor))
				break;
			else
				solveWhiteCrossTop(face);
		}
		this.displayRubiksCube();
		
		System.out.println("SOLVE LEFT");
		
		for(RubiksCubeFace face : this.faces)
		{
			if(bottomFace.isTileColor(TileLocation.LEFT_EDGE, topFaceTileColor))
				break;
			else
				solveWhiteCrossLeft(face);
		}
		this.displayRubiksCube();
		
		System.out.println("SOLVE BOTTOM");
		
		for(RubiksCubeFace face : this.faces)
		{
			if(bottomFace.isTileColor(TileLocation.BOTTOM_EDGE, topFaceTileColor))
				break;
			else
				solveWhiteCrossBottom(face);
		}
		this.displayRubiksCube();
		
		System.out.println("SOLVE RIGHT");
		
		for(RubiksCubeFace face : this.faces)
		{
			if(bottomFace.isTileColor(TileLocation.RIGHT_EDGE, topFaceTileColor))
				break;
			else
				solveWhiteCrossRight(face);
		}
		this.displayRubiksCube();

		//Bottom cross check
		if(!bottomFace.isTileColor(TileLocation.TOP_EDGE, topFaceTileColor) ||
		   !bottomFace.isTileColor(TileLocation.RIGHT_EDGE, topFaceTileColor) ||
		   !bottomFace.isTileColor(TileLocation.LEFT_EDGE, topFaceTileColor) ||
		   !bottomFace.isTileColor(TileLocation.BOTTOM_EDGE, topFaceTileColor))
		{
			this.displayRubiksCube();
			new java.util.Scanner(System.in).nextLine();
		}
		
		/*Flip cross from bottom face to top face*/
		while(true)
		{
			if(this.backFace.isEqual(TileLocation.BOTTOM_EDGE, TileLocation.MIDDLE) &&
			   this.bottomFace.isTileColor(TileLocation.BOTTOM_EDGE, topFaceTileColor))
			{
				System.out.println("FLIP BACK");
				
				this.rotateBackClockwise();
				this.rotateBackClockwise();

				this.displayRubiksCube();
			}
			if(this.leftFace.isEqual(TileLocation.BOTTOM_EDGE, TileLocation.MIDDLE) &&
			   this.bottomFace.isTileColor(TileLocation.LEFT_EDGE, topFaceTileColor))
			{
				System.out.println("FLIP LEFT");
				
				this.rotateLeftClockwise();
				this.rotateLeftClockwise();
				
				this.displayRubiksCube();
			}
			if(this.frontFace.isEqual(TileLocation.BOTTOM_EDGE, TileLocation.MIDDLE) &&
			   this.bottomFace.isTileColor(TileLocation.TOP_EDGE, topFaceTileColor))
			{
				System.out.println("FLIP FRONT");

				this.rotateFrontClockwise();
				this.rotateFrontClockwise();

				this.displayRubiksCube();
			}
			if(this.rightFace.isEqual(TileLocation.BOTTOM_EDGE, TileLocation.MIDDLE) &&
			   this.bottomFace.isTileColor(TileLocation.RIGHT_EDGE, topFaceTileColor))
			{
				System.out.println("FLIP RIGHT");

				this.rotateRightClockwise();
				this.rotateRightClockwise();
				
				this.displayRubiksCube();
			}

			/*Top cross solved*/
			if(this.topFace.isTileColor(TileLocation.TOP_EDGE, topFaceTileColor) &&
			   this.topFace.isTileColor(TileLocation.LEFT_EDGE, topFaceTileColor) &&
			   this.topFace.isTileColor(TileLocation.BOTTOM_EDGE, topFaceTileColor) &&
			   this.topFace.isTileColor(TileLocation.RIGHT_EDGE, topFaceTileColor))
			{
				break;
			}
			else /*Not solved yet*/
			{
				System.out.println("Rotate Bottom");
				this.rotateBottomClockwise();
				this.displayRubiksCube();
			}
		}

	}
	
	private boolean solveWhiteCrossTop(RubiksCubeFace face)
	{			
		TileColor tileColor = topFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		TileLocation tileLocation = face.findEdgeLocation(tileColor);
		
		if(tileLocation == null)
			return false;
		
		switch(tileLocation)
		{
			case TOP_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case BACK:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case FRONT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case LEFT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				case RIGHT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case BOTTOM_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateFrontClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				case BACK:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomCounterClockwise();					
					return true;
				}
				case FRONT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case LEFT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateLeftCounterClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				case RIGHT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateRightClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case LEFT_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case BACK:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case FRONT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case LEFT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case RIGHT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateFrontClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case RIGHT_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case BACK:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case FRONT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case LEFT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateFrontCounterClockwise();
					return true;
				}
				case RIGHT:
				{
					System.out.println("Location: " + tileLocation + " Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
		default:
			break;
		}
		return false;
	}
	
	private boolean solveWhiteCrossLeft(RubiksCubeFace face)
	{	
		TileColor tileColor = topFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		TileLocation tileLocation = face.findEdgeLocation(tileColor);
		
		if(tileLocation == null)
			return false;
		
		switch(tileLocation)
		{
			case TOP_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case BACK:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				case FRONT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				case LEFT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case RIGHT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case BOTTOM_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case BACK:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBackCounterClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				case FRONT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateFrontClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				case LEFT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case RIGHT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case LEFT_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateLeftClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				case BACK:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case FRONT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateLeftClockwise();
					return true;
				}
				case LEFT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case RIGHT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case RIGHT_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case BACK:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateLeftCounterClockwise();
					return true;
				}
				case FRONT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case LEFT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case RIGHT:
				{
					System.out.println("TileLocation: " + tileLocation + "Face: " + face.getFaceName());
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				default:
					break;
				}
			}
		default:
			break;
		}
		return false;
	}
	
	private boolean solveWhiteCrossBottom(RubiksCubeFace face)
	{	
		TileColor tileColor = topFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		TileLocation tileLocation = face.findEdgeLocation(tileColor);
		
		if(tileLocation == null)
			return false;
		
		switch(tileLocation)
		{
			case TOP_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					this.rotateBackClockwise();
					this.rotateBackClockwise();
					return true;
				}
				case BACK:
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case FRONT:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case LEFT:
				{
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					return true;
				}
				case RIGHT:
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case BOTTOM_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case BACK:
				{
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case FRONT:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case LEFT:
				{
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					return true;
				}
				case RIGHT:
				{
					this.rotateRightCounterClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case LEFT_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case BACK:
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case FRONT:
				{
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case LEFT:
				{
					this.rotateBackClockwise();
					return true;
				}
				case RIGHT:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case RIGHT_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case BACK:
				{
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case FRONT:
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case LEFT:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case RIGHT:
				{
					this.rotateBackCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
		default:
			break;
		}
		return false;
	}
	
	private boolean solveWhiteCrossRight(RubiksCubeFace face)
	{	
		TileColor tileColor = topFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		TileLocation tileLocation = face.findEdgeLocation(tileColor);
		
		if(tileLocation == null)
			return false;
		
		switch(tileLocation)
		{
			case TOP_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBackClockwise();
					return true;
				}
				case BACK:
				{
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					return true;
				}
				case FRONT:
				{
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				case LEFT:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case RIGHT:
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case BOTTOM_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case BACK:
				{
					this.rotateBackClockwise();
					this.rotateRightClockwise();
					return true;
				}
				case FRONT:
				{
					this.rotateFrontCounterClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				case LEFT:
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case RIGHT:
				{
					this.rotateRightClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case LEFT_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case BACK:
				{
					this.rotateRightClockwise();
					return true;
				}
				case FRONT:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case LEFT:
				{
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				case RIGHT:
				{
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				default:
					break;
				}
			}
			
			case RIGHT_EDGE:
			{
				switch(face.getFaceName())
				{
				case TOP:
				{
					this.rotateRightClockwise();
					this.rotateRightClockwise();
					return true;
				}
				case BACK:
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case FRONT:
				{
					this.rotateRightCounterClockwise();
					return true;
				}
				case LEFT:
				{
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					return true;
				}
				case RIGHT:
				{
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					return true;
				}
				default:
					break;
				}
			}
		default:
			break;
		}
		return false;
	}
	
	private void solveWhiteCorners()
	{		
		TileColor topFaceTileColor = topFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		TileColor leftFaceTileColor = leftFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		TileColor backFaceTileColor = backFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		TileColor rightFaceTileColor = rightFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		TileColor frontFaceTileColor = frontFace.getFaceTile(TileLocation.MIDDLE).getTileColor();
		
		boolean isTopLeftCornerSolved = false;
		boolean isTopRightCornerSolved = false;
		boolean isBottomLeftCornerSolved = false;
		boolean isBottomRightCornerSolved = false;
		boolean isAllCornersSolved = false;
		
		while(!isAllCornersSolved)
		{
			if(isTopLeftCornerSolved &&
			   isTopRightCornerSolved &&
			   isBottomLeftCornerSolved &&
			   isBottomRightCornerSolved)
			{
				isAllCornersSolved = true;
			}
			System.out.println("TOPLEFT: " + isTopLeftCornerSolved);
			System.out.println("TOPRIGHT: " + isTopRightCornerSolved);
			System.out.println("BOTTOMLEFT: " + isBottomLeftCornerSolved);
			System.out.println("BOTTOMRIGHT: " + isBottomRightCornerSolved);
			
			this.displayRubiksCube();
			
			for(RubiksCubeFace face : this.faces)
			{	
				TileLocation tileLocation = face.findCornerLocation(topFaceTileColor);
				
				if(face.getFaceName() == FaceName.TOP){
					if(tileLocation == TileLocation.TOP_LEFT_CORNER && isTopLeftCornerSolved)
					{
						tileLocation = TileLocation.TOP_RIGHT_CORNER;
					}
					if(tileLocation == TileLocation.TOP_RIGHT_CORNER && isTopRightCornerSolved)
					{
						tileLocation = TileLocation.BOTTOM_LEFT_CORNER;
					}
					if(tileLocation == TileLocation.BOTTOM_LEFT_CORNER && isBottomLeftCornerSolved)
					{
						tileLocation = TileLocation.BOTTOM_RIGHT_CORNER;
					}
					if(tileLocation == TileLocation.BOTTOM_RIGHT_CORNER && isBottomRightCornerSolved)
					{
						tileLocation = null;
					}
				}
	
				if(tileLocation != null)
				{
					this.solveCorners(face.getFaceName(), tileLocation);
				}
				
				if(topFace.isTileColor(TileLocation.TOP_LEFT_CORNER, topFaceTileColor) && 
				   leftFace.isTileColor(TileLocation.TOP_LEFT_CORNER, leftFaceTileColor) &&
				   backFace.isTileColor(TileLocation.TOP_RIGHT_CORNER, backFaceTileColor))
				{
					isTopLeftCornerSolved = true;
				}
				else
				{
					isTopLeftCornerSolved = false;
				}
				
				if(topFace.isTileColor(TileLocation.TOP_RIGHT_CORNER, topFaceTileColor) && 
				   rightFace.isTileColor(TileLocation.TOP_RIGHT_CORNER, rightFaceTileColor) &&
				   backFace.isTileColor(TileLocation.TOP_LEFT_CORNER, backFaceTileColor))
				{
					isTopRightCornerSolved = true;
				}
				else
				{
					isTopRightCornerSolved = false;
				}
				
				if(topFace.isTileColor(TileLocation.BOTTOM_LEFT_CORNER, topFaceTileColor) && 
				   leftFace.isTileColor(TileLocation.TOP_RIGHT_CORNER, leftFaceTileColor) &&
				   frontFace.isTileColor(TileLocation.TOP_LEFT_CORNER, frontFaceTileColor))
				{
					isBottomLeftCornerSolved = true;
				}
				else
				{
					isBottomLeftCornerSolved = false;
				}
				
				if(topFace.isTileColor(TileLocation.BOTTOM_RIGHT_CORNER, topFaceTileColor) && 
				   rightFace.isTileColor(TileLocation.TOP_LEFT_CORNER, rightFaceTileColor) &&
				   frontFace.isTileColor(TileLocation.TOP_RIGHT_CORNER, frontFaceTileColor))
				{
					isBottomRightCornerSolved = true;
				}
				else
				{
					isBottomRightCornerSolved = false;
				}
			}
		}
		
//		for(RubiksCubeFace face : this.faces)
//		{
//			if(bottomFace.isWhiteTile(TileLocation.LEFT_EDGE))
//				break;
//			else
//				solveTopRightWhiteCorner(face);
//		}
//		
//		for(RubiksCubeFace face : this.faces)
//		{
//			if(bottomFace.isWhiteTile(TileLocation.BOTTOM_EDGE))
//				break;
//			else
//				solveBottomLeftWhiteCorner(face);
//		}
//		
//		for(RubiksCubeFace face : this.faces)
//		{
//			if(bottomFace.isWhiteTile(TileLocation.RIGHT_EDGE))
//				break;
//			else
//				solveBottomRightWhiteCorner(face);
//		}
	}
	
	@SuppressWarnings("incomplete-switch")
	private boolean solveCorners(FaceName faceName, TileLocation tileLocation)
	{
		switch(tileLocation)
		{
		case TOP_LEFT_CORNER:
		{
			switch(faceName)
			{
			case TOP:
			{
				System.out.println("TLT");
				
				RubiksCubeTile leftFaceTile = leftFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				RubiksCubeTile backFaceTile = backFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				
				/*Top Right Corner*/
				if((backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, backFaceTile)) ||
				   (backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile)))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if((frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, backFaceTile)) ||
				   (frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
					leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile)))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
	
				
				/*Bottom Right Corner*/
				if((frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, backFaceTile)) ||
				   (frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile)))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				
				return false;
			}
			case FRONT:
			{
				System.out.println("TLF");
				
				RubiksCubeTile leftFaceTile = leftFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				RubiksCubeTile topFaceTile = topFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}		
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				
				return false;
			}
			case LEFT:
			{
				System.out.println("TLL");
				
				RubiksCubeTile backFaceTile = backFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				RubiksCubeTile topFaceTile = topFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				return false;
			}
			case BACK:
			{
				System.out.println("TLB");
				
				RubiksCubeTile rightFaceTile = rightFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				RubiksCubeTile topFaceTile = topFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				return false;
			}
			case RIGHT:
			{
				System.out.println("TLR");
				
				RubiksCubeTile topFaceTile = topFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile frontFaceTile = frontFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				return false;
			}
			case BOTTOM:
			{
				System.out.println("TLBottom");
				
				RubiksCubeTile leftFaceTile = leftFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile frontFaceTile = frontFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				return false;
			}
			}
			return false;
		}
		/*====================================================================================*/
		case TOP_RIGHT_CORNER:
		{
			switch(faceName)
			{
			case TOP:
			{
				System.out.println("TRT");
				
				RubiksCubeTile rightFaceTile = rightFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				RubiksCubeTile backFaceTile = backFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				
				/*Top Left Corner*/
				if((backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, backFaceTile)) || 
				   (backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile)))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Bottom Left Corner*/
				if((frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, backFaceTile)) ||
				   (frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
					leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile)))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
	
				
				/*Bottom Right Corner*/
				if((frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, backFaceTile)) ||
				   (frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile)))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				
				return false;
			}
			case FRONT:
			{
				System.out.println("TRF");
				RubiksCubeTile topFaceTile = topFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile rightFaceTile = rightFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}	
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				
				return false;
			}
			case LEFT:
			{
				System.out.println("TRL");
				RubiksCubeTile frontFaceTile = frontFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				RubiksCubeTile topFaceTile = topFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				return false;
			}
			case BACK:
			{
				System.out.println("TRB");
				RubiksCubeTile leftFaceTile = leftFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				RubiksCubeTile topFaceTile = topFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				return false;
			}
			case RIGHT:
			{
				System.out.println("TRR");
				RubiksCubeTile topFaceTile = topFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				RubiksCubeTile backFaceTile = backFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, topFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, topFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				return false;
			}
			case BOTTOM:
			{
				System.out.println("TRB");
				RubiksCubeTile frontFaceTile = frontFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile rightFaceTile = rightFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				return false;
			}
			}
			return false;
		}
		/*=====================================================================================*/
		case BOTTOM_LEFT_CORNER:
		{
			switch(faceName)
			{
			case TOP:
			{
				System.out.println("BLT");
				
				RubiksCubeTile leftFaceTile = leftFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				RubiksCubeTile frontFaceTile = frontFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				
				/*Top Left Corner*/
				if((backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile)) || 
				   (backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile)))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if((backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile)) ||
				   (backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile)))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
	
				
				/*Bottom Right Corner*/
				if((frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile)) ||
				   (frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile)))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				
				return false;
			}
			case FRONT:
			{
				System.out.println("BLF");
				RubiksCubeTile leftFaceTile = leftFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile bottomFaceTile = bottomFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}	
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				
				return false;
			}
			case LEFT:
			{
				System.out.println("BLL");
				RubiksCubeTile backFaceTile = backFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile bottomFaceTile = bottomFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				return false;
			}
			case BACK:
			{
				System.out.println("BLB");
				RubiksCubeTile rightFaceTile = rightFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile bottomFaceTile = bottomFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				return false;
			}
			case RIGHT:
			{
				System.out.println("BLR");
				RubiksCubeTile bottomFaceTile = bottomFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				RubiksCubeTile frontFaceTile = frontFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				return false;
			}
			case BOTTOM:
			{
				System.out.println("BLB");
				RubiksCubeTile leftFaceTile = leftFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				RubiksCubeTile backFaceTile = backFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				return false;
			}
			}
		}
		/*=========================================================================*/
		case BOTTOM_RIGHT_CORNER:
		{
			switch(faceName)
			{
			case TOP:
			{
				System.out.println("BRT");
				
				RubiksCubeTile rightFaceTile = leftFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				RubiksCubeTile frontFaceTile = backFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				
				/*Top Left Corner*/
				if((backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile)) || 
				   (backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile)))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if((backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile)) ||
				   (backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				    rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile)))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if((frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				    leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile)) ||
				   (frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
					leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile)))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightClockwise();
					
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				return false;
			}
			case FRONT:
			{
				System.out.println("BRF");
				RubiksCubeTile rightFaceTile = rightFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				RubiksCubeTile bottomFaceTile = bottomFace.getFaceTile(TileLocation.TOP_RIGHT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}	
				
				return false;
			}
			case LEFT:
			{
				System.out.println("BRL");
				RubiksCubeTile frontFaceTile = frontFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				RubiksCubeTile bottomFaceTile = bottomFace.getFaceTile(TileLocation.TOP_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, frontFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, frontFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				return false;
			}
			case BACK:
			{
				System.out.println("BRB");
				RubiksCubeTile leftFaceTile = leftFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				RubiksCubeTile bottomFaceTile = bottomFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, leftFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, leftFaceTile))
				{
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				return false;
			}
			case RIGHT:
			{
				System.out.println("BRR");
				RubiksCubeTile bottomFaceTile = bottomFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile backFaceTile = backFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, bottomFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, bottomFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					return true;
				}
				return false;
			}
			case BOTTOM:
			{
				System.out.println("BRB");
				RubiksCubeTile rightFaceTile = rightFace.getFaceTile(TileLocation.BOTTOM_RIGHT_CORNER);
				RubiksCubeTile backFaceTile = backFace.getFaceTile(TileLocation.BOTTOM_LEFT_CORNER);
				
				/*Top Left Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					
					this.rotateBackClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackCounterClockwise();
					return true;
				}
				
				/*Top Right Corner*/
				if(backFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}
				if(backFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateRightClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					
					this.rotateBackCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateBackClockwise();
					return true;
				}	
				
				/*Bottom Left Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   leftFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					return true;
				}
				/*Bottom Right Corner*/
				if(frontFace.isEqual(TileLocation.MIDDLE, rightFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, backFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				if(frontFace.isEqual(TileLocation.MIDDLE, backFaceTile) &&
				   rightFace.isEqual(TileLocation.MIDDLE, rightFaceTile))
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					
					this.rotateFrontClockwise();
					this.rotateBottomClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontCounterClockwise();
					return true;
				}
				return false;
			}
			}
			return false;
		}
		}
		return false;
	}
	
	private void solveMiddleLayers()
	{
		boolean isFrontRightEdgeSolved = false;
		boolean isFrontLeftEdgeSolved = false;
		boolean isRightRightEdgeSolved = false;
		boolean isRightLeftEdgeSolved = false;
		boolean isBackRightEdgeSolved = false;
		boolean isBackLeftEdgeSolved = false;
		boolean isLeftRightEdgeSolved = false;
		boolean isLeftLeftEdgeSolved = false;
		
		boolean isFrontMiddleLayerSolved = false;
		boolean isRightMiddleLayerSolved = false;
		boolean isBackMiddleLayerSolved = false;
		boolean isLeftMiddleLayerSolved = false;
		
		boolean isMiddleLayersSolved = false;
		
		while(!isMiddleLayersSolved)
		{
			
			if(!isFrontMiddleLayerSolved)
			{
				isFrontMiddleLayerSolved = solveFrontFaceMiddleLayer(leftFace.getFaceTile(), rightFace.getFaceTile());
			}
			
			if(!isRightMiddleLayerSolved)
			{
				
			}
			
			if(!isBackMiddleLayerSolved)
			{
				
			}
			
			if(!isLeftMiddleLayerSolved)
			{
				
			}
			
			if(isFrontMiddleLayerSolved &&
			   isRightMiddleLayerSolved &&
			   isBackMiddleLayerSolved &&
			   isLeftMiddleLayerSolved)
			{
				isMiddleLayersSolved = true;
			}
		}
	}
	
	private boolean solveFrontFaceMiddleLayer(RubiksCubeTile leftFaceTile, RubiksCubeTile rightFaceTile)
	{
		boolean isRightEdge = false;
		boolean isLeftEdge = false;
		boolean isRightEdgeSolved = false;
		boolean isLeftEdgeSolved = false;
		
		boolean isMiddleLayerSolved = false;

			if(frontFace.isEqual(TileLocation.BOTTOM_EDGE))
			{
				RubiksCubeTile bottomTopEdgeTile = bottomFace.getFaceTile(TileLocation.TOP_EDGE);
				isRightEdge = bottomTopEdgeTile.equals(rightFaceTile);
				isLeftEdge = bottomTopEdgeTile.equals(leftFaceTile);
				
				if(isRightEdge)
				{
					this.rotateBottomCounterClockwise();
					this.rotateRightCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateRightClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					
					isRightEdgeSolved = true;
				}
				
				if(isLeftEdge)
				{
					this.rotateBottomClockwise();
					this.rotateLeftClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateLeftCounterClockwise();
					this.rotateBottomCounterClockwise();
					this.rotateFrontCounterClockwise();
					this.rotateBottomClockwise();
					this.rotateFrontClockwise();
					
					isLeftEdgeSolved = true;
				}
			}
			else
			{
				this.rotateBottomClockwise();
			}
			
			if(isRightEdgeSolved && isLeftEdgeSolved)
				return true;
			
			return false;
	}
	
	/**
	 * Rotate the top of the cube clockwise
	 */
	public void rotateTopClockwise()
	{	
		int row = 0;
		boolean isReverse = false;
		
		topFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceRow(row, isReverse));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceRow(row, isReverse));
		this.tileSets.put(FaceName.BACK, backFace.getFaceRow(row, isReverse));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceRow(row, isReverse));
		
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
		boolean isReverse = false;
		
		topFace.rotateTilesCounterClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceRow(row, isReverse));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceRow(row, isReverse));
		this.tileSets.put(FaceName.BACK, backFace.getFaceRow(row, isReverse));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceRow(row, isReverse));
		
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
		int frontCol = 0;
		int topCol = 0;
		int backCol = 2;
		int bottomCol = 0;
		boolean isReverse = false;
		
		leftFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceCol(frontCol, isReverse));
		this.tileSets.put(FaceName.TOP, topFace.getFaceCol(topCol, isReverse));
		
		isReverse = true;
		
		this.tileSets.put(FaceName.BACK, backFace.getFaceCol(backCol, isReverse));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceCol(bottomCol, isReverse));
		
		frontFace.setFaceCol(frontCol, this.tileSets.get(FaceName.TOP));
		topFace.setFaceCol(topCol, this.tileSets.get(FaceName.BACK));
		backFace.setFaceCol(backCol, this.tileSets.get(FaceName.BOTTOM));
		bottomFace.setFaceCol(bottomCol, this.tileSets.get(FaceName.FRONT));
	}
	
	/**
	 * Rotate the left of the cube counterclockwise
	 */
	public void rotateLeftCounterClockwise()
	{	
		int frontCol = 0;
		int topCol = 0;
		int backCol = 2;
		int bottomCol = 0;
		boolean isReverse = false;
		
		leftFace.rotateTilesCounterClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceCol(frontCol, isReverse));
		
		isReverse = true;
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceCol(topCol, isReverse));
		this.tileSets.put(FaceName.BACK, backFace.getFaceCol(backCol, isReverse));
		
		isReverse = false;
		
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceCol(bottomCol, isReverse));
		
		frontFace.setFaceCol(frontCol, this.tileSets.get(FaceName.BOTTOM));
		topFace.setFaceCol(topCol, this.tileSets.get(FaceName.FRONT));
		backFace.setFaceCol(backCol, this.tileSets.get(FaceName.TOP));
		bottomFace.setFaceCol(bottomCol, this.tileSets.get(FaceName.BACK));
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
		boolean isReverse = false;
		
		frontFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceRow(topRow, false));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceCol(rightCol, true));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceRow(bottomRow, false));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceCol(leftCol, true));
		
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
		boolean isReverse = false;
		
		frontFace.rotateTilesCounterClockwise();
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceRow(topRow, true));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceCol(rightCol, false));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceRow(bottomRow, true));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceCol(leftCol, false));
		
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
		int frontCol = 2;
		int topCol = 2;
		int backCol = 0;
		int bottomCol = 2;
		boolean isReverse = false;
		
		rightFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceCol(frontCol, false));
		this.tileSets.put(FaceName.TOP, topFace.getFaceCol(topCol, true));
		this.tileSets.put(FaceName.BACK, backFace.getFaceCol(backCol, true));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceCol(bottomCol, false));
		
		frontFace.setFaceCol(frontCol, this.tileSets.get(FaceName.BOTTOM));
		topFace.setFaceCol(topCol, this.tileSets.get(FaceName.FRONT));
		backFace.setFaceCol(backCol, this.tileSets.get(FaceName.TOP));
		bottomFace.setFaceCol(bottomCol, this.tileSets.get(FaceName.BACK));
	}
	
	/**
	 * Rotate the right of the cube clockwise
	 */
	public void rotateRightCounterClockwise()
	{	
		int frontCol = 2;
		int topCol = 2;
		int backCol = 0;
		int bottomCol = 2;
		boolean isReverse = false;
		
		rightFace.rotateTilesCounterClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceCol(frontCol, false));
		this.tileSets.put(FaceName.TOP, topFace.getFaceCol(topCol, false));
		this.tileSets.put(FaceName.BACK, backFace.getFaceCol(backCol, true));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceCol(bottomCol, true));
		
		frontFace.setFaceCol(frontCol, this.tileSets.get(FaceName.TOP));
		topFace.setFaceCol(topCol, this.tileSets.get(FaceName.BACK));
		backFace.setFaceCol(backCol, this.tileSets.get(FaceName.BOTTOM));
		bottomFace.setFaceCol(bottomCol, this.tileSets.get(FaceName.FRONT));
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
		boolean isReverse = false;
		
		backFace.rotateTilesClockwise();
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceRow(topRow, true));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceCol(rightCol, false));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceRow(bottomRow, true));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceCol(leftCol, false));
		
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
		boolean isReverse = false;
		
		backFace.rotateTilesCounterClockwise();
		
		this.tileSets.put(FaceName.TOP, topFace.getFaceRow(topRow, false));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceCol(rightCol, true));
		this.tileSets.put(FaceName.BOTTOM, bottomFace.getFaceRow(bottomRow, false));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceCol(leftCol, true));
		
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
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceRow(row, false));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceRow(row, false));
		this.tileSets.put(FaceName.BACK, backFace.getFaceRow(row, false));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceRow(row, false));
		
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
		
		bottomFace.rotateTilesCounterClockwise();
		
		this.tileSets.put(FaceName.FRONT, frontFace.getFaceRow(row, false));
		this.tileSets.put(FaceName.RIGHT, rightFace.getFaceRow(row, false));
		this.tileSets.put(FaceName.BACK, backFace.getFaceRow(row, false));
		this.tileSets.put(FaceName.LEFT, leftFace.getFaceRow(row, false));
		
		frontFace.setFaceRow(row, this.tileSets.get(FaceName.RIGHT));
		rightFace.setFaceRow(row, this.tileSets.get(FaceName.BACK));
		backFace.setFaceRow(row, this.tileSets.get(FaceName.LEFT));
		leftFace.setFaceRow(row, this.tileSets.get(FaceName.FRONT));
	}
	
	private void setTileSets(EnumMap<FaceName, RubiksCubeTile[]> tileSets)
	{
		this.tileSets = tileSets;
	}
	
	private void setFaces(RubiksCubeFace[] faces)
	{
		this.faces = faces;
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
		this.faces[0] = topFace;
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
		this.faces[1] = leftFace;
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
		this.faces[2] = frontFace;
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
		this.faces[3] = rightFace;
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
		this.faces[4] = backFace;
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
		this.faces[5] = bottomFace;
		this.bottomFace = bottomFace;
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