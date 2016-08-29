/**
 * 
 */
package rubikscube.standard.factories.strategies;

import java.util.HashMap;
import java.util.Map;

import rubikscube.interfaces.IRubiksCube;
import rubikscube.interfaces.strategies.AbstractStrategyFactory;
import rubikscube.interfaces.strategies.ISolveDownFaceStrategy;
import rubikscube.interfaces.strategies.ISolveUpFaceStrategy;

/**
 * @author Joshua Parsons
 * 
 */
public class SolveDownFaceFactory
{
	private static SolveDownFaceFactory instance;
	private Map<String, ISolveDownFaceStrategy> registeredStrategies;
	
	private SolveDownFaceFactory()
	{
		this.registeredStrategies = new HashMap<String, ISolveDownFaceStrategy>();
	}
	
	public static synchronized SolveDownFaceFactory getInstance()
	{
		if(instance == null)
		{
			instance = new SolveDownFaceFactory();
		}
		
		return instance;
	}
	
	public void registerStrategy(String strategyType, ISolveDownFaceStrategy strategy)
	{
		this.registeredStrategies.put(strategyType, strategy);
	}
	
	/**
	 * @param rubiksCubeType
	 * @return IRubiksCube
	 */
	public ISolveDownFaceStrategy createStrategy(String strategyType)
	{
		return ((ISolveDownFaceStrategy) this.registeredStrategies.get(strategyType)).createStrategy();
	}
	
	public Map<String, ISolveDownFaceStrategy> getStrategyTypes()
	{	
		return this.registeredStrategies;
	}
}
