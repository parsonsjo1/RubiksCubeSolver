package RubiksCube;

public class RubiksCubeFace 
{
	private FaceName faceName;
	private RubiksCubeTile[][] faceTiles;
	
	public RubiksCubeFace(int size, FaceName faceName)
	{
		setFaceTiles(size);
		setFaceName(faceName);
	}
	
	@Override
	public String toString()
	{
		StringBuilder faceSB = new StringBuilder();
		
		for(int row = 0; row < this.faceTiles.length; row++)
		{
			for(int col = 0; col < this.faceTiles.length; col++)
			{
				faceSB.append(this.faceTiles[row][col].getTileColor() + " ");
			}
			if(row != 2)
				faceSB.append("\n");
		}
			
		return faceSB.toString();
	}

	public void setFaceTiles(int size)
	{
		this.faceTiles = new RubiksCubeTile[size][size];
		for(int row = 0; row < size; row++)
		{
			for(int col = 0; col < size; col++)
			{
				TileColor defaultColor = TileColor.WHITE;
				TileColor tileColor = defaultColor.getTileColor();
				int tileNumber = tileColor.getTileNumber();
				TileLocation defaultLocation = TileLocation.MIDDLE;
				TileLocation tileLocation = defaultLocation.getTileLocation(row, col);
				this.faceTiles[row][col] = new RubiksCubeTile(tileColor, tileNumber, tileLocation);
			}
		}
	}
	
	public void setFaceName(FaceName faceName)
	{
		this.faceName = faceName;
	}
	
	/**
	 * @return the faceName
	 */
	public FaceName getFaceName()
	{
		return this.faceName;
	}

	/**
	 * @return the faceTiles
	 */
	public RubiksCubeTile[][] getFaceTiles() 
	{
		return this.faceTiles;
	}
	
	/**
	 * @param row
	 * @return
	 */
	public RubiksCubeTile[] getRow(int row)
	{
		RubiksCubeTile[] rowSet = new RubiksCubeTile[this.faceTiles.length];
		
		for(int col = 0; col < this.faceTiles.length; col++)
		{
			rowSet[col] = this.faceTiles[row][col];
		}
		
		return rowSet;
	}
	
	/**
	 * @param col
	 * @return
	 */
	public RubiksCubeTile[] getCol(int col)
	{
		RubiksCubeTile[] colSet = new RubiksCubeTile[this.faceTiles.length];
		
		for(int row = 0; row < this.faceTiles.length; row++)
		{
			colSet[row] = this.faceTiles[row][col];
		}
		
		return colSet;
	}
}
