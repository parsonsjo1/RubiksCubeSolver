/**
 * 
 */
package RubiksCube;

/**
 * @author Joshua Parsons
 * 
 */
public enum TileColor 
{	
	WHITE, GREEN, RED, BLUE, ORANGE, YELLOW;
	
	private int tileNumber = 0;
	
	public TileColor getTileColor()
	{
		tileNumber++;
		
		if(tileNumber < 10)
			return WHITE;
		else if(tileNumber < 19)
			return GREEN;
		else if(tileNumber < 28)
			return RED;
		else if(tileNumber < 37)
			return BLUE;
		else if(tileNumber < 46)
			return ORANGE;
		else if(tileNumber < 55)
			return YELLOW;
		else
		{
			tileNumber = 1;
			return WHITE;
		}
	}
	
	public int getTileNumber()
	{
		return this.tileNumber;
	}
}
