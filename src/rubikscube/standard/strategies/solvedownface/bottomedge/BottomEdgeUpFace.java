/**
 * 
 */
package rubikscube.standard.strategies.solvedownface.bottomedge;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.StandardRubiksCube;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.templates.strategies.SolveDownFaceTemplate;

/**
 * @author Joshua Parsons
 * 
 */
public class BottomEdgeUpFace extends SolveDownFaceTemplate
{
	/**
	 * 
	 */
	public BottomEdgeUpFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("BOTTOM_EDGE UP_FACE", new BottomEdgeUpFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new BottomEdgeUpFace();
	}

	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve()
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("BottomEdgeUpFace");
		rubiksCube.rotateFront();
		rubiksCube.rotateFront();
	}
	
	

}
