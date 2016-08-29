/**
 * 
 */
package rubikscube.standard.strategies.solvedownface.leftedge;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.standard.factories.strategies.SolveDownFaceFactory;
import rubikscube.templates.strategies.SolveDownFaceTemplate;

/**
 * @author Joshua Parsons
 * 
 */
public class LeftEdgeBackFace extends SolveDownFaceTemplate
{

	/**
	 * 
	 */
	public LeftEdgeBackFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("LEFT_EDGE BACK_FACE", new LeftEdgeBackFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new LeftEdgeBackFace();
	}
	
	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve(rubikscube.interfaces.IRubiksCube)
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("LEFT_EDGE BACK_FACE");
		rubiksCube.rotateDown();
		rubiksCube.rotateRight();
		rubiksCube.rotateDownInverse();
	}

}
