/**
 * 
 */
package rubikscube.three;

import rubikscube.enums.TileColor;

/**
 * @author Joshua Parsons
 * 
 */
public class RubiksCubeTile 
{
	private TileColor tileColor;

	public RubiksCubeTile(TileColor tileColor)
	{
		setTileColor(tileColor);
	}
	
	public void setTileColor(TileColor tileColor)
	{
		this.tileColor = tileColor;
	}
	
	public TileColor getTileColor()
	{
		return this.tileColor;
	}
}
