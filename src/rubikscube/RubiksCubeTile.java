/**
 * 
 */
package rubikscube;

/**
 * @author Joshua Parsons
 * 
 */
public class RubiksCubeTile 
{
	private TileColor tileColor;
	private int tileNumber;
	private TileLocation tileLocation;
	
	public RubiksCubeTile(TileColor tileColor, int tileNumber, TileLocation tileLocation)
	{
		setTileColor(tileColor);
		setTileNumber(tileNumber);
		setTileLocation(tileLocation);
	}
	
	public void setTileColor(TileColor tileColor)
	{
		this.tileColor = tileColor;
	}
	
	public void setTileNumber( int tileNumber)
	{
		this.tileNumber = tileNumber;
	}
	
	public void setTileLocation(TileLocation tileLocation)
	{
		this.tileLocation = tileLocation;
	}
	
	public TileColor getTileColor()
	{
		return this.tileColor;
	}
}
