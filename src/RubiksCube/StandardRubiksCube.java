/**
 * 
 */
package RubiksCube;

/**
 * @author Joshua Parsons
 * 
 */
public class StandardRubiksCube implements RubiksCubeInterface
{
	private RubiksCubeFace frontFace;
	private RubiksCubeFace backFace;
	private RubiksCubeFace leftFace;
	private RubiksCubeFace rightFace;
	private RubiksCubeFace bottomFace;
	private RubiksCubeFace topFace;
	
	public StandardRubiksCube()
	{
		setFrontFace();
		setBackFace();
		setLeftFace();
		setRightFace();
		setBottomFace();
		setTopFace();
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

	@Override
	public void displayRubiksCube() 
	{
		frontFace.display();
		backFace.display();
		leftFace.display();
		rightFace.display();
		topFace.display();
		bottomFace.display();
	}
	
	public void setFrontFace() 
	{
		this.frontFace = new RubiksCubeFace();
	}

	public void setBackFace() 
	{
		this.backFace = backFace;
	}

	public void setLeftFace() 
	{
		this.leftFace = leftFace;
	}

	public void setRightFace() 
	{
		this.rightFace = rightFace;
	}

	public void setBottomFace() 
	{
		this.bottomFace = bottomFace;
	}

	public void setTopFace() 
	{
		this.topFace = topFace;
	}
}
