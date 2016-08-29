/**
 * 
 */
package rubikscube.standard.strategies.solvedownface.rightedge;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.StandardRubiksCube;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.templates.strategies.SolveDownFaceTemplate;

/**
 * @author Joshua Parsons
 * 
 */
public class RightEdgeUpFace extends SolveDownFaceTemplate
{
	/**
	 * 
	 */
	public RightEdgeUpFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("RIGHT_EDGE UP_FACE", new RightEdgeUpFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new RightEdgeUpFace();
	}

	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve()
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("RIGHT_EDGE UP_FACE");
		rubiksCube.rotateDown();
		rubiksCube.rotateRight();
		rubiksCube.rotateRight();
		rubiksCube.rotateDownInverse();
	}
	
	

}
