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
public class BottomEdgeFrontFace extends SolveDownFaceTemplate
{

	/**
	 * 
	 */
	public BottomEdgeFrontFace()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Register the strategy with the factory
	 */
	public static void registerStrategy()
	{
		SolveDownFaceFactory.getInstance().registerStrategy("BOTTOM_EDGE FRONT_FACE", new BottomEdgeFrontFace());
	}
	
	/**
	 * Called by the factory to instantiate at run-time
	 */
	public ISolveDownFaceStrategy createStrategy()
	{
		return new BottomEdgeFrontFace();
	}
	
	/* (non-Javadoc)
	 * @see rubikscube.templates.SolveDownFaceTemplate#solve(rubikscube.interfaces.IRubiksCube)
	 */
	@Override
	public void solve(IRubiksCube rubiksCube)
	{
		System.out.println("Bottom Edge Front Face");
		rubiksCube.rotateFront();
		rubiksCube.rotateDownInverse();
		rubiksCube.rotateLeft();
		rubiksCube.rotateDown();
	}

}
