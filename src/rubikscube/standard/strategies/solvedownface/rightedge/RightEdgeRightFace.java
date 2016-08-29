/**
 * 
 */
package rubikscube.standard.strategies.solvedownface.rightedge;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.templates.strategies.SolveDownFaceTemplate;

/**
 * @author Joshua Parsons
 * 
 */
public class RightEdgeRightFace extends SolveDownFaceTemplate
{

	/**
	 * 
	 */
	public RightEdgeRightFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("RIGHT_EDGE RIGHT_FACE", new RightEdgeRightFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new RightEdgeRightFace();
	}

	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve(rubikscube.interfaces.IRubiksCube)
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("RIGHT_EDGE RIGHT_FACE");
		rubiksCube.rotateDown();
		rubiksCube.rotateDown();
		rubiksCube.rotateBackInverse();
		rubiksCube.rotateDown();
		rubiksCube.rotateDown();
	}

}
