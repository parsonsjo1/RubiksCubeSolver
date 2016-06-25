package RubiksCube;

import java.util.EnumMap;
import java.util.Map;

public class RubiksCubeFace 
{
	private FaceName faceName;
	private RubiksCubeTile[][] faceTiles;
	private Map<Direction, FaceName> adjacentFaces;
	
	/**
	 * RubiksCubeFace Constructor
	 * @param size
	 * @param faceName
	 */
	public RubiksCubeFace(int size, FaceName faceName)
	{
		setFaceTiles(size);
		setFaceName(faceName);
		setAdjacentFaces(faceName);
	}
	
	/**
	 * Rotate the faces tiles around the center tile in rotation to the right
	 */
	public void rotateRight()
	{
		Map<Direction, RubiksCubeTile[]> collectedTiles = this.collectTiles();
		
		this.setFaceRow(0, collectedTiles.get(Direction.RIGHT));
		this.setFaceRow(2, collectedTiles.get(Direction.LEFT));
		
		this.setFaceCol(0, collectedTiles.get(Direction.UP));
		this.setFaceCol(2, collectedTiles.get(Direction.DOWN));
	}
	
	/**
	 * @param faceName
	 * @param row
	 * @return Map<FaceName, RubiksCubeTile[]>
	 */
	private Map<Direction, RubiksCubeTile[]> collectTiles()
	{
		Map<Direction, RubiksCubeTile[]> collectTiles = new EnumMap<Direction, RubiksCubeTile[]>(Direction.class);

		collectTiles.put(Direction.UP, this.getFaceRow(0));
		collectTiles.put(Direction.DOWN, this.getFaceRow(2));
		
		collectTiles.put(Direction.LEFT, this.getFaceCol(0));
		collectTiles.put(Direction.RIGHT, this.getFaceCol(2));
		
		return collectTiles;
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

	/**
	 * 
	 * @param size
	 */
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
	
	/**
	 * 
	 * @param faceName
	 */
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
	 */
	public void setFaceCol(int col, RubiksCubeTile[] colSet)
	{
		for(int row = 0; row < this.faceTiles.length; row++)
		{
			this.faceTiles[row][col] = colSet[row];
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
	public RubiksCubeTile[] getFaceCol(int col)
	{
		RubiksCubeTile[] colSet = new RubiksCubeTile[this.faceTiles.length];
		
		for(int row = 0; row < this.faceTiles.length; row++)
		{
			colSet[row] = this.faceTiles[row][col];
		}
		
		return colSet;
	}
	
	/**
	 * Set adjacent faces for the current face
	 * @param faceName
	 */
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
	
	/**
	 * Return the adjacent face that is adjacent in the direction specified in the parameter
	 * @param direction
	 * @return FaceName
	 */
	public FaceName getAdjacentFace(Direction direction)
	{
		return this.adjacentFaces.get(direction);
	}
}
