/**
 * 
 */
package rubikscube.standard.strategies.solvedownface.bottomedge;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.templates.strategies.SolveDownFaceTemplate;

/**
 * @author Joshua Parsons
 * 
 */
public class BottomEdgeRightFace extends SolveDownFaceTemplate
{

	/**
	 * 
	 */
	public BottomEdgeRightFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("BOTTOM_EDGE RIGHT_FACE", new BottomEdgeRightFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new BottomEdgeRightFace();
	}

	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve(rubikscube.interfaces.IRubiksCube)
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("BottomEdgeRightFace");
		rubiksCube.rotateRight();
		rubiksCube.rotateFront();
	}

}
