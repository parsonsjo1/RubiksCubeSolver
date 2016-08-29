/**
 * 
 */
package rubikscube.interfaces.strategies;

import rubikscube.interfaces.IRubiksCube;

/**
 * @author Joshua Parsons
 * 
 */
public interface ISolveDownFaceStrategy
{
	public void solve(IRubiksCube rubiksCube);

	public ISolveDownFaceStrategy createStrategy();
}
