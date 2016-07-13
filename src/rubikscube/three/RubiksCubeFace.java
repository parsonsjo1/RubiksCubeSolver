package rubikscube.three;

import java.util.EnumMap;
import java.util.Map;

import rubikscube.enums.Direction;
import rubikscube.enums.FaceName;
import rubikscube.enums.TileColor;

/**
 * The faces of a Rubiks Cube
 * @author Joshua Parsons
 */
public class RubiksCubeFace 
{
	private FaceName faceName;
	private int numRows;
	private int numCols;
	private RubiksCubeTile[][] faceTiles;
	private Map<Direction, FaceName> adjacentFaces;
	
	/**
	 * RubiksCubeFace Constructor
	 * @param size
	 * @param faceName
	 */
	public RubiksCubeFace(int numRows, int numCols, FaceName faceName, TileColor tileColor)
	{
		setNumRows(numRows);
		setNumCols(numCols);
		setFaceTiles(tileColor);
		setFaceName(faceName);
		setAdjacentFaces();
	}

	
	/**
	 * @return the faceTiles
	 */
	public RubiksCubeTile[][] getFaceTiles() 
	{
		return this.faceTiles;
	}
	
	/**
	 * 
	 * @param size
	 */
	public void setFaceTiles(TileColor tileColor)
	{
		this.faceTiles = new RubiksCubeTile[numRows][numCols];
		
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				this.faceTiles[row][col] = new RubiksCubeTile(tileColor);
			}
		}	
	}


	/**
	 * @return the numRows
	 */
	public int getNumRows()
	{
		return numRows;
	}

	/**
	 * @param numRows the numRows to set
	 */
	public void setNumRows(int numRows)
	{
		this.numRows = numRows;
	}

	/**
	 * @return the numCols
	 */
	public int getNumCols()
	{
		return numCols;
	}

	/**
	 * @param numCols the numCols to set
	 */
	public void setNumCols(int numCols)
	{
		this.numCols = numCols;
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
	 * @return colSet
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
	 * Return the adjacent face that is adjacent in the direction specified in the parameter
	 * @param direction
	 * @return FaceName
	 */
	public FaceName getAdjacentFace(Direction direction)
	{
		return this.adjacentFaces.get(direction);
	}
	
	/**
	 * Set adjacent faces for the current face
	 * @param faceName
	 */
	private void setAdjacentFaces()
	{
		this.adjacentFaces = new EnumMap<Direction, FaceName>(Direction.class);
		
		switch(this.faceName)
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
			this.adjacentFaces.put(Direction.RIGHT, FaceName.LEFT);
			this.adjacentFaces.put(Direction.DOWN, FaceName.BACK);
			this.adjacentFaces.put(Direction.LEFT, FaceName.RIGHT);
			break;
		}
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder faceSB = new StringBuilder();
		
		for(int row = 0; row < this.numRows; row++)
		{
			for(int col = 0; col < this.numCols; col++)
			{
				faceSB.append(this.faceTiles[row][col].getTileColor() + " ");
			}
			if(row != 2)
				faceSB.append("\n");
		}
			
		return faceSB.toString();
	}
}
