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
	
	@Override
	public boolean equals(Object obj) 
	{
	    if (obj == null) 
	    {
	        return false;
	    }
	    if (!RubiksCubeTile.class.isAssignableFrom(obj.getClass())) 
	    {
	        return false;
	    }
	    final RubiksCubeTile other = (RubiksCubeTile) obj;
	    if (!this.tileColor.equals(other.tileColor)) {
	        return false;
	    }
	    return true;
	}
}
