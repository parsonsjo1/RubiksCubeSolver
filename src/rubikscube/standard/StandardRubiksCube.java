/**
 * 
 */
package rubikscube.standard;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import rubikscube.Cubie;
import rubikscube.enums.FaceName;
import rubikscube.enums.TileColor;
import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.AbstractStrategyFactory;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.enums.CubieLocation;
import rubikscube.standard.factories.RubiksCubeFactory;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.standard.strategies.solvedownface.bottomedge.*;
import rubikscube.standard.strategies.solvedownface.leftedge.*;
import rubikscube.standard.strategies.solvedownface.rightedge.*;
import rubikscube.standard.strategies.solvedownface.topedge.*;

/**
 * @author Joshua Parsons
 * 
 */
public class StandardRubiksCube implements IRubiksCube
{
	private final int NUM_COLUMNS = 3;
	private final int NUM_ROWS = 3;
	
	private StandardFace upFace;
	private StandardFace leftFace;
	private StandardFace frontFace;
	private StandardFace rightFace;
	private StandardFace backFace;
	private StandardFace downFace;

	/**
	 * Default Constructor using standard colors of Rubiks Cube faces
	 */
	public StandardRubiksCube()
	{
		setUpFace("white");
		setLeftFace("orange");
		setFrontFace("green");
		setRightFace("red");
		setBackFace("blue");
		setDownFace("yellow");
	}
	
	/**
	 * Constructor for customization of colors for Rubiks Cube faces
	 * @param topFaceColor
	 * @param leftFaceColor
	 * @param frontFaceColor
	 * @param rightFaceColor
	 * @param backFaceColor
	 * @param downFaceColor
	 */
	public StandardRubiksCube(String upFaceColor, String leftFaceColor, String frontFaceColor, String rightFaceColor, String backFaceColor, String downFaceColor)
	{
		setUpFace(upFaceColor);
		setLeftFace(leftFaceColor);
		setFrontFace(frontFaceColor);
		setRightFace(rightFaceColor);
		setBackFace(backFaceColor);
		setDownFace(downFaceColor);
	}
	
	/**
	 * Register the rubiks cube with the factory
	 */
	public static void registerRubiksCube()
	{
		RubiksCubeFactory.getInstance().registerRubiksCube("Standard (3x3)", new StandardRubiksCube());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public IRubiksCube createRubiksCube()
	{
		return new StandardRubiksCube();
	}

	/**
	 * Shuffle the rubiks cube
	 */
	@Override
	public void shuffle()
	{
		for(int i = 0; i < 100; i++)
		{
			Random random = new Random();
			int rotateMethod = random.nextInt(NUM_FACES);
			
			switch(rotateMethod)
			{
				case 1:
				{
					this.rotateUp();
					break;
				}
				case 2:
				{
					this.rotateLeft();
					break;
				}
				case 3:
				{
					this.rotateFront();
					break;
				}
				case 4:
				{
					this.rotateRight();
					break;
				}
				case 5:
				{
					this.rotateBack();
					break;
				}
				case 6:
				{
					this.rotateDown();
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
	public void reset()
	{
		
	}
	
	/**
	 * Solve the rubiks cube
	 */
	@Override
	public void solve()
	{
		//Step 1
		this.solveDownFaceEdges();

		//Step 2 Flip Down Face Edges to Up Face
	}
	
	//Steps to solve
	private void solveDownFaceEdges()
	{
		//Step 1 Solve Down Face Edges
		SolveDownFaceFactory solveDownFaceFactory = SolveDownFaceFactory.getInstance();
		//SolveUpFaceFactory solveUpFaceFactory = SolveUpFaceFactory.getInstance();
		
		this.registerStrategies();
		
		//Get the users selected option
		//String selectedType = getUserSelection(rubiksCubeFactory);
		
		StandardFace[] faces = {this.upFace, this.leftFace, this.frontFace, this.rightFace, this.backFace};
		
		while(!isDownFaceSolved())
		{
			for(StandardFace face : faces)
			{
				String edgeToFind = this.upFace.getColor();
				CubieLocation edgeLocation = face.findEdge(edgeToFind);
				System.out.println("EdgeLocation: " + edgeLocation + " Face: " + face.getFaceName());
				this.display();
				if(edgeLocation == null)
					continue;
				//Instantiate strategy object using the factory method
				String strategyType = edgeLocation.toString() + " " + face.getFaceName() + "_FACE";
				System.out.println("StratType: " + strategyType);
				ISolveDownFaceStrategy strategy = solveDownFaceFactory.createStrategy(strategyType);
				strategy.solve(this);
				this.rotateDown();
				this.display();
			}
		}
	}
	
	private void registerStrategies()
	{
		/*Register strategies*/
		TopEdgeUpFace.registerStrategy();
		TopEdgeLeftFace.registerStrategy();
		TopEdgeFrontFace.registerStrategy();
		TopEdgeRightFace.registerStrategy();
		TopEdgeBackFace.registerStrategy();
		
		RightEdgeUpFace.registerStrategy();
		RightEdgeLeftFace.registerStrategy();
		RightEdgeFrontFace.registerStrategy();
		RightEdgeRightFace.registerStrategy();
		RightEdgeBackFace.registerStrategy();
		
		LeftEdgeUpFace.registerStrategy();
		LeftEdgeLeftFace.registerStrategy();
		LeftEdgeFrontFace.registerStrategy();
		LeftEdgeRightFace.registerStrategy();
		LeftEdgeBackFace.registerStrategy();
		
		BottomEdgeUpFace.registerStrategy();
		BottomEdgeLeftFace.registerStrategy();
		BottomEdgeFrontFace.registerStrategy();
		BottomEdgeRightFace.registerStrategy();
		BottomEdgeBackFace.registerStrategy();
	}
	
	private boolean isDownFaceSolved()
	{
		String upFaceColor = upFace.getColor();
		if(!this.downFace.getColor(CubieLocation.TOP_EDGE).equals(upFaceColor))
			return false;
		if(!this.downFace.getColor(CubieLocation.LEFT_EDGE).equals(upFaceColor))
			return false;
		if(!this.downFace.getColor(CubieLocation.BOTTOM_EDGE).equals(upFaceColor))
			return false;
		if(!this.downFace.getColor(CubieLocation.RIGHT_EDGE).equals(upFaceColor))
			return false;
		
		return true;
	}

	/**
	 * Rotate the top of the cube clockwise
	 */
	public void rotateUp()
	{	
		int row = 0;
		boolean isInverse = false;
		
		upFace.rotate();
		
		Cubie[] frontFaceRow = this.frontFace.getFaceRow(row, isInverse);
		Cubie[] rightFaceRow = this.rightFace.getFaceRow(row, isInverse);
		Cubie[] backFaceRow = this.backFace.getFaceRow(row, isInverse);
		Cubie[] leftFaceRow = this.leftFace.getFaceRow(row, isInverse);
		
		this.frontFace.setFaceRow(row, rightFaceRow);
		this.rightFace.setFaceRow(row, backFaceRow);
		this.backFace.setFaceRow(row, leftFaceRow);
		this.leftFace.setFaceRow(row, frontFaceRow);
	}
	
	/**
	 * Rotate the top of the cube counterclockwise
	 */
	public void rotateUpInverse()
	{	
		int row = 0;
		boolean isInverse = false;
		
		upFace.rotateInverse();
		
		Cubie[] frontFaceRow = this.frontFace.getFaceRow(row, isInverse);
		Cubie[] rightFaceRow = this.rightFace.getFaceRow(row, isInverse);
		Cubie[] backFaceRow = this.backFace.getFaceRow(row, isInverse);
		Cubie[] leftFaceRow = this.leftFace.getFaceRow(row, isInverse);
		
		frontFace.setFaceRow(row, leftFaceRow);
		rightFace.setFaceRow(row, frontFaceRow);
		backFace.setFaceRow(row, rightFaceRow);
		leftFace.setFaceRow(row, backFaceRow);
	}
	
	/**
	 * Rotate the left of the cube clockwise
	 */
	public void rotateLeft()
	{	
		int frontCol = 0;
		int upCol = 0;
		int backCol = 2;
		int downCol = 0;
		boolean isInverse = false;
		
		leftFace.rotate();
		
		Cubie[] frontFaceCol = this.frontFace.getFaceCol(frontCol, isInverse);
		Cubie[] upFaceCol = this.upFace.getFaceCol(upCol, isInverse);
		
		isInverse = true;
		
		Cubie[] backFaceCol = this.backFace.getFaceCol(backCol, isInverse);
		Cubie[] downFaceCol = this.downFace.getFaceCol(downCol, isInverse);
		
		frontFace.setFaceCol(frontCol, upFaceCol);
		upFace.setFaceCol(upCol, backFaceCol);
		backFace.setFaceCol(backCol, downFaceCol);
		downFace.setFaceCol(downCol, frontFaceCol);
	}
	
	/**
	 * Rotate the left of the cube counterclockwise
	 */
	public void rotateLeftInverse()
	{	
		int frontCol = 0;
		int upCol = 0;
		int backCol = 2;
		int downCol = 0;
		boolean isInverse = false;
		
		leftFace.rotateInverse();
		
		Cubie[] frontFaceCol = this.frontFace.getFaceCol(frontCol, isInverse);
		Cubie[] downFaceCol = this.downFace.getFaceCol(downCol, isInverse);
		
		isInverse = true;
		
		Cubie[] upFaceCol = this.upFace.getFaceCol(upCol, isInverse);
		Cubie[] backFaceCol = this.backFace.getFaceCol(backCol, isInverse);
		
		frontFace.setFaceCol(frontCol, downFaceCol);
		upFace.setFaceCol(upCol, frontFaceCol);
		backFace.setFaceCol(backCol, upFaceCol);
		downFace.setFaceCol(downCol, backFaceCol);
	}
	
	/**
	 * Rotate the front of the cube clockwise
	 */
	public void rotateFront()
	{	
		int upRow = 2;
		int rightCol = 0;
		int downRow = 0;
		int leftCol = 2;
		boolean isInverse = false;
		
		frontFace.rotate();
		
		Cubie[] upFaceRow = this.upFace.getFaceRow(upRow, isInverse);
		Cubie[] downFaceRow = this.downFace.getFaceRow(downRow, isInverse);

		isInverse = true;
		
		Cubie[] rightFaceCol = this.rightFace.getFaceCol(rightCol, isInverse);
		Cubie[] leftFaceCol = this.leftFace.getFaceCol(leftCol, isInverse);
		
		upFace.setFaceRow(upRow, leftFaceCol);
		rightFace.setFaceCol(rightCol, upFaceRow);
		downFace.setFaceRow(downRow, rightFaceCol);
		leftFace.setFaceCol(leftCol, downFaceRow);
	}
	
	/**
	 * Rotate the front of the cube counterclockwise
	 */
	public void rotateFrontInverse()
	{	
		int upRow = 2;
		int rightCol = 0;
		int downRow = 0;
		int leftCol = 2;
		boolean isInverse = false;
		
		frontFace.rotateInverse();
	
		Cubie[] rightFaceCol = this.rightFace.getFaceCol(rightCol, isInverse);
		Cubie[] leftFaceCol = this.leftFace.getFaceCol(leftCol, isInverse);
		
		isInverse = true;
		
		Cubie[] upFaceRow = this.upFace.getFaceRow(upRow, isInverse);
		Cubie[] downFaceRow = this.downFace.getFaceRow(downRow, isInverse);
		
		upFace.setFaceRow(upRow, rightFaceCol);
		rightFace.setFaceCol(rightCol, downFaceRow);
		downFace.setFaceRow(downRow, leftFaceCol);
		leftFace.setFaceCol(leftCol, upFaceRow);
	}	
	
	/**
	 * Rotate the right of the cube clockwise
	 */
	public void rotateRight()
	{	
		int frontCol = 2;
		int upCol = 2;
		int backCol = 0;
		int downCol = 2;
		boolean isInverse = false;
		
		rightFace.rotate();
		
		Cubie[] frontFaceCol = this.frontFace.getFaceCol(frontCol, isInverse);
		Cubie[] downFaceCol = this.downFace.getFaceCol(downCol, isInverse);

		isInverse = true;
		
		Cubie[] upFaceCol = this.upFace.getFaceCol(upCol, isInverse);
		Cubie[] backFaceCol = this.backFace.getFaceCol(backCol, isInverse);
		
		frontFace.setFaceCol(frontCol, downFaceCol);
		upFace.setFaceCol(upCol, frontFaceCol);
		backFace.setFaceCol(backCol, upFaceCol);
		downFace.setFaceCol(downCol, backFaceCol);
	}
	
	/**
	 * Rotate the right of the cube clockwise
	 */
	public void rotateRightInverse()
	{	
		int frontCol = 2;
		int upCol = 2;
		int backCol = 0;
		int downCol = 2;
		boolean isInverse = false;
		
		rightFace.rotateInverse();
		
		Cubie[] frontFaceCol = this.frontFace.getFaceCol(frontCol, isInverse);
		Cubie[] upFaceCol = this.upFace.getFaceCol(upCol, isInverse);

		isInverse = true;
		
		Cubie[] backFaceCol = this.backFace.getFaceCol(backCol, isInverse);
		Cubie[] downFaceCol = this.downFace.getFaceCol(downCol, isInverse);
		
		frontFace.setFaceCol(frontCol, upFaceCol);
		upFace.setFaceCol(upCol, backFaceCol);
		backFace.setFaceCol(backCol, downFaceCol);
		downFace.setFaceCol(downCol, frontFaceCol);
	}
	
	/**
	 * Rotate the back of the cube clockwise
	 */
	public void rotateBack()
	{	
		int upRow = 0;
		int rightCol = 2;
		int downRow = 2;
		int leftCol = 0;
		boolean isInverse = false;
		
		backFace.rotate();
		
		Cubie[] rightFaceCol = this.rightFace.getFaceCol(rightCol, isInverse);
		Cubie[] leftFaceCol = this.leftFace.getFaceCol(leftCol, isInverse);

		isInverse = true;
		
		Cubie[] upFaceRow = this.upFace.getFaceRow(upRow, isInverse);
		Cubie[] downFaceRow = this.downFace.getFaceRow(downRow, isInverse);
		
		upFace.setFaceRow(upRow, rightFaceCol);
		rightFace.setFaceCol(rightCol, downFaceRow);
		downFace.setFaceRow(downRow, leftFaceCol);
		leftFace.setFaceCol(leftCol, upFaceRow);
	}
	
	/**
	 * Rotate the back of the cube counterclockwise
	 */
	public void rotateBackInverse()
	{	
		int upRow = 0;
		int rightCol = 2;
		int downRow = 2;
		int leftCol = 0;
		boolean isInverse = false;
		
		backFace.rotateInverse();
		
		Cubie[] upFaceRow = this.upFace.getFaceRow(upRow, isInverse);
		Cubie[] downFaceRow = this.downFace.getFaceRow(downRow, isInverse);

		isInverse = true;
		
		Cubie[] rightFaceCol = this.rightFace.getFaceCol(rightCol, isInverse);
		Cubie[] leftFaceCol = this.leftFace.getFaceCol(leftCol, isInverse);
		
		upFace.setFaceRow(upRow, leftFaceCol);
		rightFace.setFaceCol(rightCol, upFaceRow);
		downFace.setFaceRow(downRow, rightFaceCol);
		leftFace.setFaceCol(leftCol, downFaceRow);
	}	

	
	/**
	 * Rotate the down of the cube clockwise
	 */
	public void rotateDown()
	{	
		int row = 2;
		boolean isInverse = false;
		
		downFace.rotate();
		
		Cubie[] frontFaceRow = this.frontFace.getFaceRow(row, isInverse);
		Cubie[] rightFaceRow = this.rightFace.getFaceRow(row, isInverse);
		Cubie[] backFaceRow = this.backFace.getFaceRow(row, isInverse);
		Cubie[] leftFaceRow = this.leftFace.getFaceRow(row, isInverse);
		
		frontFace.setFaceRow(row, leftFaceRow);
		rightFace.setFaceRow(row, frontFaceRow);
		backFace.setFaceRow(row, rightFaceRow);
		leftFace.setFaceRow(row, backFaceRow);
	}

	/**
	 * Rotate the down of the cube clockwise
	 */
	public void rotateDownInverse()
	{	
		int row = 2;
		boolean isInverse = false;
		
		downFace.rotateInverse();
		
		Cubie[] frontFaceRow = this.frontFace.getFaceRow(row, isInverse);
		Cubie[] rightFaceRow = this.rightFace.getFaceRow(row, isInverse);
		Cubie[] backFaceRow = this.backFace.getFaceRow(row, isInverse);
		Cubie[] leftFaceRow = this.leftFace.getFaceRow(row, isInverse);
		
		frontFace.setFaceRow(row, rightFaceRow);
		rightFace.setFaceRow(row, backFaceRow);
		backFace.setFaceRow(row, leftFaceRow);
		leftFace.setFaceRow(row, frontFaceRow);
	}

	
	/**
	 * @return
	 */
	public StandardFace getUpFace()
	{
		return this.upFace;
	}
	
	/**
	 * @param topFace
	 */
	public void setUpFace(String upFaceColor)
	{
		this.upFace = new StandardFace(FaceName.UP, upFaceColor);
	}

	/**
	 * @return the leftFace
	 */
	public StandardFace getLeftFace()
	{
		return this.leftFace;
	}

	/**
	 * @param leftFace the leftFace to set
	 */
	public void setLeftFace(String leftFaceColor)
	{
		this.leftFace = new StandardFace(FaceName.LEFT, leftFaceColor);
	}

	/**
	 * @return the frontFace
	 */
	public StandardFace getFrontFace()
	{
		return this.frontFace;
	}

	/**
	 * @param frontFace the frontFace to set
	 */
	public void setFrontFace(String frontFaceColor)
	{
		this.frontFace = new StandardFace(FaceName.FRONT, frontFaceColor);;
	}

	/**
	 * @return the rightFace
	 */
	public StandardFace getRightFace()
	{
		return this.rightFace;
	}

	/**
	 * @param rightFace the rightFace to set
	 */
	public void setRightFace(String rightFaceColor)
	{
		this.rightFace = new StandardFace(FaceName.RIGHT, rightFaceColor);;
	}

	/**
	 * @return the backFace
	 */
	public StandardFace getBackFace()
	{
		return this.backFace;
	}

	/**
	 * @param backFace the backFace to set
	 */
	public void setBackFace(String backFaceColor)
	{
		this.backFace = new StandardFace(FaceName.BACK, backFaceColor);;
	}

	/**
	 * @return the downFace
	 */
	public StandardFace getDownFace()
	{
		return this.downFace;
	}

	/**
	 * @param downFace the downFace to set
	 */
	public void setDownFace(String downFaceColor)
	{
		this.downFace = new StandardFace(FaceName.DOWN, downFaceColor);;
	}

	/**
	 * Format and print the rubiks cube out to the console
	 */
	@Override
	public void display() 
	{	
		//TOP FACE
		Cubie[][] topFaceTiles = upFace.getCubies();
		
		for(int row = 0; row < this.NUM_ROWS; row++)
		{
			System.out.printf("%-30s", "");
			for(int col = 0; col < this.NUM_COLUMNS; col++)
			{
				System.out.printf("%-10s", topFaceTiles[row][col].getColor());
			}
			System.out.println();
		}
		
		for(int row = 0; row < this.NUM_ROWS; row++)
		{
			//LEFT
			Cubie[][] leftFaceTiles = leftFace.getCubies();
			for(int col = 0; col < NUM_COLUMNS; col++)
			{
				System.out.printf("%-10s", leftFaceTiles[row][col].getColor());
			}
			
			//FRONT
			Cubie[][] frontFaceTiles = frontFace.getCubies();
			for(int col = 0; col < this.NUM_COLUMNS; col++)
			{
				System.out.printf("%-10s", frontFaceTiles[row][col].getColor());
			}
			
			//RIGHT
			Cubie[][] rightFaceTiles = rightFace.getCubies();
			for(int col = 0; col < this.NUM_COLUMNS; col++)
			{
				System.out.printf("%-10s", rightFaceTiles[row][col].getColor());
			}
			
			//BACK
			Cubie[][] backFaceTiles = backFace.getCubies();
			for(int col = 0; col < this.NUM_COLUMNS; col++)
			{
				System.out.printf("%-10s", backFaceTiles[row][col].getColor());
			}

			System.out.println();
		}
		
		//down
		Cubie[][] downFaceTiles = downFace.getCubies();
		for(int row = 0; row < this.NUM_ROWS; row++)
		{
			System.out.printf("%-30s", "");
			for(int col = 0; col < this.NUM_COLUMNS; col++)
			{
				System.out.printf("%-10s", downFaceTiles[row][col].getColor());
			}
			System.out.println();
		}
	}
}