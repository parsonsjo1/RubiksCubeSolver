/**
 * 
 */
package rubikscube.enums;

/**
 * @author Joshua Parsons
 * 
 */
public enum TileLocation 
{
	MIDDLE, TOP_LEFT_CORNER, TOP_EDGE, TOP_RIGHT_CORNER, RIGHT_EDGE, BOTTOM_RIGHT_CORNER, BOTTOM_EDGE, BOTTOM_LEFT_CORNER, LEFT_EDGE;
	
	public static TileLocation getTileLocation(int row, int col)
	{
		if(row == 0 && col == 0)
			return TOP_LEFT_CORNER;
		else if( row == 0 && col == 1)
			return TOP_EDGE;
		else if(row == 0 && col == 2)
			return TOP_RIGHT_CORNER;
		else if(row == 1 && col == 0)
			return LEFT_EDGE;
		else if(row == 1 && col == 1)
			return MIDDLE;
		else if(row == 1 && col == 2)
			return RIGHT_EDGE;
		else if(row == 2 && col == 0)
			return BOTTOM_LEFT_CORNER;
		else if (row == 2 && col == 1)
			return BOTTOM_EDGE;
		else if(row == 2 && col == 2)
			return BOTTOM_RIGHT_CORNER;
		else
			return null;
	}
}
