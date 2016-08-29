/**
 * 
 */
package rubikscube.interfaces.strategies;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joshua Parsons
 * 
 */
public abstract class AbstractStrategyFactory
{	
	public abstract ISolveDownFaceStrategy getSolveDownFaceStrategy(String downFaceStrategy);
	public abstract ISolveUpFaceStrategy getSolveUpFaceStrategy(String upFaceStrategy);
}
