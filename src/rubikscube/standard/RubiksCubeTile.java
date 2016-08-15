/**
 * 
 */
package rubikscube.standard;

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
	
	public boolean isTileColor(TileColor tileColor)
	{
		if(this.tileColor == tileColor)
			return true;
		else
			return false;
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
