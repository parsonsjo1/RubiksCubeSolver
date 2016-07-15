package rubikscube.three;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
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
	private Map<Direction, List<FaceName>> rotationMap;
	
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
		setRotationMap();
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
	public List<FaceName> getAdjacentFace(Direction direction)
	{
		return this.rotationMap.get(direction);
	}
	
	/**
	 * Set adjacent faces for the current face
	 * @param faceName
	 */
	private void setRotationMap()
	{
		this.rotationMap = new EnumMap<Direction, List<FaceName>>(Direction.class);
		ArrayList<FaceName> rotateUpList = new ArrayList<FaceName>();
		ArrayList<FaceName> rotateRightList = new ArrayList<FaceName>();
		ArrayList<FaceName> rotateDownList = new ArrayList<FaceName>();
		ArrayList<FaceName> rotateLeftList = new ArrayList<FaceName>();
		
		switch(this.faceName)
		{
			case FRONT:
			{
				rotateUpList.set(0, FaceName.TOP);
				rotateUpList.set(1, FaceName.BACK);
				rotateUpList.set(2, FaceName.BOTTOM);
				rotateUpList.set(3, FaceName.FRONT);
				
				rotateRightList.set(0, FaceName.RIGHT);
				rotateRightList.set(1, FaceName.BACK);
				rotateRightList.set(2, FaceName.LEFT);
				rotateRightList.set(3, FaceName.FRONT);
				
				rotateDownList.set(0, FaceName.BOTTOM);
				rotateDownList.set(1, FaceName.BACK);
				rotateDownList.set(2, FaceName.TOP);
				rotateDownList.set(3, FaceName.FRONT);
				
				rotateLeftList.set(0, FaceName.LEFT);
				rotateLeftList.set(1, FaceName.BACK);
				rotateLeftList.set(2, FaceName.RIGHT);
				rotateLeftList.set(3, FaceName.FRONT);
				
				this.rotationMap.put(Direction.UP, rotateUpList);
				this.rotationMap.put(Direction.RIGHT, rotateRightList);
				this.rotationMap.put(Direction.DOWN, rotateDownList);
				this.rotationMap.put(Direction.LEFT, rotateLeftList);
				break;
			}
			case BACK:
			{
				rotateUpList.set(0, FaceName.TOP);
				rotateUpList.set(1, FaceName.FRONT);
				rotateUpList.set(2, FaceName.BOTTOM);
				rotateUpList.set(3, FaceName.BACK);
				
				rotateRightList.set(0, FaceName.LEFT);
				rotateRightList.set(1, FaceName.FRONT);
				rotateRightList.set(2, FaceName.LEFT);
				rotateRightList.set(3, FaceName.BACK);
				
				rotateDownList.set(0, FaceName.BOTTOM);
				rotateDownList.set(1, FaceName.FRONT);
				rotateDownList.set(2, FaceName.TOP);
				rotateDownList.set(3, FaceName.BACK);
				
				rotateLeftList.set(0, FaceName.RIGHT);
				rotateLeftList.set(1, FaceName.FRONT);
				rotateLeftList.set(2, FaceName.LEFT);
				rotateLeftList.set(3, FaceName.BACK);
				
				this.rotationMap.put(Direction.UP, rotateUpList);
				this.rotationMap.put(Direction.RIGHT, rotateRightList);
				this.rotationMap.put(Direction.DOWN, rotateDownList);
				this.rotationMap.put(Direction.LEFT, rotateLeftList);
				break;
			}
			case LEFT:
			{
				rotateUpList.set(0, FaceName.TOP);
				rotateUpList.set(1, FaceName.RIGHT);
				rotateUpList.set(2, FaceName.BOTTOM);
				rotateUpList.set(3, FaceName.LEFT);
				
				rotateRightList.set(0, FaceName.FRONT);
				rotateRightList.set(1, FaceName.RIGHT);
				rotateRightList.set(2, FaceName.BACK);
				rotateRightList.set(3, FaceName.LEFT);
				
				rotateDownList.set(0, FaceName.BOTTOM);
				rotateDownList.set(1, FaceName.RIGHT);
				rotateDownList.set(2, FaceName.TOP);
				rotateDownList.set(3, FaceName.LEFT);
				
				rotateLeftList.set(0, FaceName.BACK);
				rotateLeftList.set(1, FaceName.RIGHT);
				rotateLeftList.set(2, FaceName.FRONT);
				rotateLeftList.set(3, FaceName.LEFT);
				
				this.rotationMap.put(Direction.UP, rotateUpList);
				this.rotationMap.put(Direction.RIGHT, rotateRightList);
				this.rotationMap.put(Direction.DOWN, rotateDownList);
				this.rotationMap.put(Direction.LEFT, rotateLeftList);
				break;
			}
			case RIGHT:
			{
				rotateUpList.set(0, FaceName.TOP);
				rotateUpList.set(1, FaceName.LEFT);
				rotateUpList.set(2, FaceName.BOTTOM);
				rotateUpList.set(3, FaceName.RIGHT);
				
				rotateRightList.set(0, FaceName.BACK);
				rotateRightList.set(1, FaceName.LEFT);
				rotateRightList.set(2, FaceName.FRONT);
				rotateRightList.set(3, FaceName.RIGHT);
				
				rotateDownList.set(0, FaceName.BOTTOM);
				rotateDownList.set(1, FaceName.LEFT);
				rotateDownList.set(2, FaceName.TOP);
				rotateDownList.set(3, FaceName.RIGHT);
				
				rotateLeftList.set(0, FaceName.FRONT);
				rotateLeftList.set(1, FaceName.LEFT);
				rotateLeftList.set(2, FaceName.BACK);
				rotateLeftList.set(3, FaceName.RIGHT);
				
				this.rotationMap.put(Direction.UP, rotateUpList);
				this.rotationMap.put(Direction.RIGHT, rotateRightList);
				this.rotationMap.put(Direction.DOWN, rotateDownList);
				this.rotationMap.put(Direction.LEFT, rotateLeftList);
				break;
			}
			case TOP:
			{
				rotateUpList.set(0, FaceName.BACK);
				rotateUpList.set(1, FaceName.BOTTOM);
				rotateUpList.set(2, FaceName.FRONT);
				rotateUpList.set(3, FaceName.TOP);
				
				rotateRightList.set(0, FaceName.RIGHT);
				rotateRightList.set(1, FaceName.BOTTOM);
				rotateRightList.set(2, FaceName.LEFT);
				rotateRightList.set(3, FaceName.TOP);
				
				rotateDownList.set(0, FaceName.FRONT);
				rotateDownList.set(1, FaceName.BOTTOM);
				rotateDownList.set(2, FaceName.BACK);
				rotateDownList.set(3, FaceName.TOP);
				
				rotateLeftList.set(0, FaceName.LEFT);
				rotateLeftList.set(1, FaceName.BOTTOM);
				rotateLeftList.set(2, FaceName.RIGHT);
				rotateLeftList.set(3, FaceName.TOP);
				
				this.rotationMap.put(Direction.UP, rotateUpList);
				this.rotationMap.put(Direction.RIGHT, rotateRightList);
				this.rotationMap.put(Direction.DOWN, rotateDownList);
				this.rotationMap.put(Direction.LEFT, rotateLeftList);
				break;
			}
			case BOTTOM:
			{
				rotateUpList.set(0, FaceName.FRONT);
				rotateUpList.set(1, FaceName.TOP);
				rotateUpList.set(2, FaceName.BACK);
				rotateUpList.set(3, FaceName.BOTTOM);
				
				rotateRightList.set(0, FaceName.RIGHT);
				rotateRightList.set(1, FaceName.TOP);
				rotateRightList.set(2, FaceName.LEFT);
				rotateRightList.set(3, FaceName.BOTTOM);
				
				rotateDownList.set(0, FaceName.BACK);
				rotateDownList.set(1, FaceName.TOP);
				rotateDownList.set(2, FaceName.FRONT);
				rotateDownList.set(3, FaceName.BOTTOM);
				
				rotateLeftList.set(0, FaceName.LEFT);
				rotateLeftList.set(1, FaceName.TOP);
				rotateLeftList.set(2, FaceName.RIGHT);
				rotateLeftList.set(3, FaceName.BOTTOM);
				
				this.rotationMap.put(Direction.UP, rotateUpList);
				this.rotationMap.put(Direction.RIGHT, rotateRightList);
				this.rotationMap.put(Direction.DOWN, rotateDownList);
				this.rotationMap.put(Direction.LEFT, rotateLeftList);
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
