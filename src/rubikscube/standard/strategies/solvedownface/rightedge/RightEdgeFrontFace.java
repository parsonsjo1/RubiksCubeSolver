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
public class RightEdgeFrontFace extends SolveDownFaceTemplate
{

	/**
	 * 
	 */
	public RightEdgeFrontFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("RIGHT_EDGE FRONT_FACE", new RightEdgeFrontFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new RightEdgeFrontFace();
	}
	
	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve(rubikscube.interfaces.IRubiksCube)
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("RIGHT_EDGE FRONT_FACE");
		rubiksCube.rotateDown();
		rubiksCube.rotateRightInverse();
		rubiksCube.rotateDownInverse();
	}

}
