/**
 * 
 */
package rubikscube.standard.factories;

import java.util.HashMap;
import java.util.Map;

import rubikscube.interfaces.IRubiksCube;

/**
 * @author Joshua Parsons
 * 
 */
public class RubiksCubeFactory
{
	private static RubiksCubeFactory instance;
	private Map<String, IRubiksCube> registeredRubiksCubes;
	
	/**
	 * Constructor
	 */
	private RubiksCubeFactory()
	{
		registeredRubiksCubes = new HashMap<String, IRubiksCube>();
	}
	
	/**
	 * Singleton instance
	 * @return RubiksCubeFactory
	 */
	public static synchronized RubiksCubeFactory getInstance()
	{
		if (instance == null)
		{
			instance = new RubiksCubeFactory();
		}
		
		return instance;
	}
	
	/**
	 * Register a Rubiks Cube to avoid coupling
	 * @param rubiksCubeType
	 * @param rubiksCubeClass
	 */
	public void registerRubiksCube(String rubiksCubeType, IRubiksCube rubiksCubeClass)
	{
		registeredRubiksCubes.put(rubiksCubeType, rubiksCubeClass);
	}
	
	/**
	 * Create a Rubiks Cube object of rubiksCubeType
	 * @param rubiksCubeType
	 * @return IRubiksCube
	 */
	public IRubiksCube createRubiksCube(String rubiksCubeType)
	{
		return ((IRubiksCube) registeredRubiksCubes.get(rubiksCubeType)).createRubiksCube();
	}
	
	public Map<String, IRubiksCube> getRubiksCubeTypes()
	{	
		return registeredRubiksCubes;
	}
}
