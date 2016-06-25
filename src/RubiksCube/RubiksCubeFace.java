package RubiksCube;

import java.util.EnumMap;
import java.util.Map;

public class RubiksCubeFace 
{
	private FaceName faceName;
	private RubiksCubeTile[][] faceTiles;
	private Map<Direction, FaceName> adjacentFaces;
	
	public RubiksCubeFace(int size, FaceName faceName)
	{
		setFaceTiles(size);
		setFaceName(faceName);
		setAdjacentFaces(faceName);
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
	 */
	public void setFaceRow(int row, RubiksCubeTile[] rowSet)
	{
		for(int col = 0; col < this.faceTiles.length; col++)
		{
			this.faceTiles[row][col] = rowSet[col];
		}
	}
	
	/**
	 * @param row
	 * @return
	 */
	public RubiksCubeTile[] getFaceRow(int row)
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
	
	private void setAdjacentFaces(FaceName faceName)
	{
		this.adjacentFaces = new EnumMap<Direction, FaceName>(Direction.class);
		
		switch(faceName)
		{
		case FRONT:
		{
			this.adjacentFaces.put(Direction.UP, FaceName.TOP);
			this.adjacentFaces.put(Direction.RIGHT, FaceName.RIGHT);
			this.adjacentFaces.put(Direction.DOWN, FaceName.BOTTOM);
			this.adjacentFaces.put(Direction.LEFT, FaceName.LEFT);
			break;
		}
		case BACK:
		{
			this.adjacentFaces.put(Direction.UP, FaceName.TOP);
			this.adjacentFaces.put(Direction.RIGHT, FaceName.LEFT);
			this.adjacentFaces.put(Direction.DOWN, FaceName.BOTTOM);
			this.adjacentFaces.put(Direction.LEFT, FaceName.RIGHT);
			break;
		}
		case LEFT:
		{
			this.adjacentFaces.put(Direction.UP, FaceName.TOP);
			this.adjacentFaces.put(Direction.RIGHT, FaceName.FRONT);
			this.adjacentFaces.put(Direction.DOWN, FaceName.BOTTOM);
			this.adjacentFaces.put(Direction.LEFT, FaceName.BACK);
			break;
		}
		case RIGHT:
		{
			this.adjacentFaces.put(Direction.UP, FaceName.TOP);
			this.adjacentFaces.put(Direction.RIGHT, FaceName.BACK);
			this.adjacentFaces.put(Direction.DOWN, FaceName.BOTTOM);
			this.adjacentFaces.put(Direction.LEFT, FaceName.FRONT);
			break;
		}
		case TOP:
		{
			this.adjacentFaces.put(Direction.UP, FaceName.BACK);
			this.adjacentFaces.put(Direction.RIGHT, FaceName.RIGHT);
			this.adjacentFaces.put(Direction.DOWN, FaceName.FRONT);
			this.adjacentFaces.put(Direction.LEFT, FaceName.LEFT);
			break;
		}
		case BOTTOM:
		{
			this.adjacentFaces.put(Direction.UP, FaceName.FRONT);
			this.adjacentFaces.put(Direction.RIGHT, FaceName.RIGHT);
			this.adjacentFaces.put(Direction.DOWN, FaceName.BACK);
			this.adjacentFaces.put(Direction.LEFT, FaceName.LEFT);
			break;
		}
		}
	}
	
	public FaceName getAdjacentFace(Direction direction)
	{
		return this.adjacentFaces.get(direction);
	}
}
