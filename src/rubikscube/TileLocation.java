/**
 * 
 */
package rubikscube;

/**
 * @author Joshua Parsons
 * 
 */
public enum TileLocation 
{
	MIDDLE, TOP_LEFT, TOP, TOP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT;
	
	public TileLocation getTileLocation(int row, int col)
	{
		if(row == 0 && col == 0)
			return TOP_LEFT;
		else if( row == 0 && col == 1)
			return TOP;
		else if(row == 0 && col == 2)
			return TOP_RIGHT;
		else if(row == 1 && col == 0)
			return LEFT;
		else if(row == 1 && col == 1)
			return MIDDLE;
		else if(row == 1 && col == 2)
			return RIGHT;
		else if(row == 2 && col == 0)
			return BOTTOM_LEFT;
		else if (row == 2 && col == 1)
			return BOTTOM;
		else if(row == 2 && col == 2)
			return BOTTOM_RIGHT;
		else
			return null;
	}
}
