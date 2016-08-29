/**
 * 
 */
package rubikscube.standard.strategies.solvedownface.topedge;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.StandardRubiksCube;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.templates.strategies.SolveDownFaceTemplate;

/**
 * @author Joshua Parsons
 * 
 */
public class TopEdgeUpFace extends SolveDownFaceTemplate
{
	/**
	 * 
	 */
	public TopEdgeUpFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("TOP_EDGE UP_FACE", new TopEdgeUpFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new TopEdgeUpFace();
	}

	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve()
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("TopEdgeUpFace");
		rubiksCube.rotateDown();
		rubiksCube.rotateDown();
		rubiksCube.rotateBack();
		rubiksCube.rotateBack();
		rubiksCube.rotateDownInverse();
		rubiksCube.rotateDownInverse();
	}
	
	

}
