package rubikscube.enums;

/**
 * @author Joshua Parsons
 *
 */
public enum RubiksCubeTypes
{
	THREE;
	
	public int getSize()
	{
		switch(this.toString().toLowerCase())
		{
			case "three":
			{
				return 3;
			}
			default:
				return 0;
		}
	}
}
