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
public class TopEdgeLeftFace extends SolveDownFaceTemplate
{

	/**
	 * 
	 */
	public TopEdgeLeftFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("TOP_EDGE LEFT_FACE", new TopEdgeLeftFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new TopEdgeLeftFace();
	}
	
	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve(rubikscube.interfaces.IRubiksCube)
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("TOP_EDGE LEFT_FACE");
		rubiksCube.rotateDownInverse();
		rubiksCube.rotateLeft();
		rubiksCube.rotateDown();
		rubiksCube.rotateFrontInverse();
	}

	
}
