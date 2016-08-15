package rubikscube.enums;

/**
 * @author Joshua Parsons
 *
 */
public enum RubiksCubeTypes
{
	BEGINNER, STANDARD;
	
	public int getSize()
	{
		switch(this)
		{
			case BEGINNER:
			{
				return 2;
			}
			case STANDARD:
			{
				return 3;
			}
			default:
				return 0;
		}
	}
}
