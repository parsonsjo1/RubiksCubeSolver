/**
 * 
 */
package rubikscube;

import rubikscube.standard.enums.CubieLocation;

/**
 * @author Joshua Parsons
 * 
 */
public class Cubie 
{
	private String color;
	private CubieLocation location;

	public Cubie(String color, CubieLocation location)
	{
		setColor(color.toUpperCase());
		setLocation(location);
	}
	
	public void setColor(String color)
	{
		this.color = color;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	/**
	 * @return the location
	 */
	public CubieLocation getLocation()
	{
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(CubieLocation location)
	{
		this.location = location;
	}

	@Override
	public boolean equals(Object obj) 
	{
	    if (obj == null) 
	    {
	        return false;
	    }
	    if (!Cubie.class.isAssignableFrom(obj.getClass())) 
	    {
	        return false;
	    }
	    final Cubie other = (Cubie) obj;
	    if (!this.color.equals(other.color)) {
	        return false;
	    }
	    return true;
	}
}
