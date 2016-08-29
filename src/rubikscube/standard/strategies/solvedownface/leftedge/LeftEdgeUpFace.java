/**
 * 
 */
package rubikscube.standard.strategies.solvedownface.leftedge;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.StandardRubiksCube;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.templates.strategies.SolveDownFaceTemplate;

/**
 * @author Joshua Parsons
 * 
 */
public class LeftEdgeUpFace extends SolveDownFaceTemplate
{
	/**
	 * 
	 */
	public LeftEdgeUpFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("LEFT_EDGE UP_FACE", new LeftEdgeUpFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new LeftEdgeUpFace();
	}

	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve()
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("LEFT_EDGE UP_FACE");
		rubiksCube.rotateDownInverse();
		rubiksCube.rotateLeft();
		rubiksCube.rotateLeft();
		rubiksCube.rotateDown();
	}
	
	

}
