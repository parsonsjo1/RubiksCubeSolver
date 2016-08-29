/**
 * 
 */
package rubikscube.standard.strategies.solvedownface.topedge;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.templates.strategies.SolveDownFaceTemplate;

/**
 * @author Joshua Parsons
 * 
 */
public class TopEdgeRightFace extends SolveDownFaceTemplate
{

	/**
	 * 
	 */
	public TopEdgeRightFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("TOP_EDGE RIGHT_FACE", new TopEdgeRightFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new TopEdgeRightFace();
	}

	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve(rubikscube.interfaces.IRubiksCube)
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("TOP_EDGE RIGHT_FACE");
		rubiksCube.rotateDown();
		rubiksCube.rotateRightInverse();
		rubiksCube.rotateDownInverse();
		rubiksCube.rotateFront();
	}

}
