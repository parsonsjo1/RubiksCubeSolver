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
public class RightEdgeBackFace extends SolveDownFaceTemplate
{

	/**
	 * 
	 */
	public RightEdgeBackFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("RIGHT_EDGE BACK_FACE", new RightEdgeBackFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new RightEdgeBackFace();
	}
	
	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve(rubikscube.interfaces.IRubiksCube)
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("RIGHT_EDGE BACK_FACE");
		rubiksCube.rotateDownInverse();
		rubiksCube.rotateLeftInverse();
		rubiksCube.rotateDown();
	}

}
