package RubiksCube;

public class RubiksCubeFace 
{
	private RubiksCubeTile[][] faceTiles;
	
	public RubiksCubeFace()
	{
		
	}
	
	public void display()
	{
		for(RubiksCubeTile[] tile : faceTiles)
		{
			System.out.println(tile);
		}
	}

}
