package rubikscube.three;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import rubikscube.enums.Direction;
import rubikscube.enums.FaceName;
import rubikscube.enums.TileColor;
import rubikscube.enums.TileLocation;

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
	private Map<TileLocation, RubiksCubeTile> faceTilesMap;
	private Map<Direction, List<FaceName>> rotationMap;
	
	/**
	 * RubiksCubeFace Constructor
	 * @param size
	 * @param faceName
	 */
	public RubiksCubeFace(int numRows, int numCols, FaceName faceName, TileColor tileColor)
	{
		setFaceName(faceName);
		setNumRows(numRows);
		setNumCols(numCols);
		setFaceTiles(tileColor);
		setFaceTilesMap(new EnumMap<TileLocation, RubiksCubeTile>(TileLocation.class));
		//setRotationMap();
	}
	
	public TileLocation findWhiteEdge()
	{
		if(this.isWhiteTile(TileLocation.TOP_EDGE))
			return TileLocation.TOP_EDGE;
		if(this.isWhiteTile(TileLocation.LEFT_EDGE))
			return TileLocation.LEFT_EDGE;
		if(this.isWhiteTile(TileLocation.BOTTOM_EDGE))
			return TileLocation.BOTTOM_EDGE;
		if(this.isWhiteTile(TileLocation.RIGHT_EDGE))
			return TileLocation.RIGHT_EDGE;
		
		return null;
	}
	
	public boolean isWhiteTile(TileLocation tileLocation)
	{
		if(this.faceTilesMap.get(tileLocation).getTileColor() == TileColor.WHITE)
			return true;
		else
			return false;
	}
	
	/**
	 * Rotate the face tiles in the clockwise direction
	 */
	public void rotateTilesClockwise()
	{
		RubiksCubeTile topLeftCorner = this.faceTiles[0][0];
		RubiksCubeTile topEdge = this.faceTiles[0][1];
		RubiksCubeTile topRightCorner = this.faceTiles[0][2];
		RubiksCubeTile rightEdge = this.faceTiles[1][2];
		RubiksCubeTile bottomRightCorner = this.faceTiles[2][2];
		RubiksCubeTile bottomEdge = this.faceTiles[2][1];
		RubiksCubeTile bottomLeftCorner = this.faceTiles[2][0];
		RubiksCubeTile leftEdge = this.faceTiles[1][0];
		
		this.faceTiles[0][0] = bottomLeftCorner;
		this.faceTiles[0][1] = leftEdge;
		this.faceTiles[0][2] = topLeftCorner;
		this.faceTiles[1][2] = topEdge;
		this.faceTiles[2][2] = topRightCorner;
		this.faceTiles[2][1] = rightEdge;
		this.faceTiles[2][0] = bottomRightCorner;
		this.faceTiles[1][0] = bottomEdge;
		
		setFaceTilesMap();
	}
	
	/**
	 * Rotate the face tiles in the counterclockwise direction
	 */
	public void rotateTilesCounterClockwise()
	{
		RubiksCubeTile topLeftCorner = this.faceTiles[0][0];
		RubiksCubeTile topEdge = this.faceTiles[0][1];
		RubiksCubeTile topRightCorner = this.faceTiles[0][2];
		RubiksCubeTile rightEdge = this.faceTiles[1][2];
		RubiksCubeTile bottomRightCorner = this.faceTiles[2][2];
		RubiksCubeTile bottomEdge = this.faceTiles[2][1];
		RubiksCubeTile bottomLeftCorner = this.faceTiles[2][0];
		RubiksCubeTile leftEdge = this.faceTiles[1][0];
		
		this.faceTiles[0][0] = topRightCorner;
		this.faceTiles[0][1] = rightEdge;
		this.faceTiles[0][2] = bottomRightCorner;
		this.faceTiles[1][2] = bottomEdge;
		this.faceTiles[2][2] = bottomLeftCorner;
		this.faceTiles[2][1] = leftEdge;
		this.faceTiles[2][0] = topLeftCorner;
		this.faceTiles[1][0] = topEdge;
		
		setFaceTilesMap();
	}
	
	public boolean isEqual(TileLocation tileLocation1, TileLocation tileLocation2)
	{
		return this.getFaceTile(tileLocation1).equals(this.getFaceTile(tileLocation2));
	}
	
	/*
	 * Return the requested face tile
	 */
	public RubiksCubeTile getFaceTile(int row, int col)
	{
		return this.faceTiles[row][col];
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
				RubiksCubeTile currentTile = new RubiksCubeTile(tileColor);				
				this.faceTiles[row][col] = currentTile;
			}
		}	
	}

	public int getNumRows()
	{
		return numRows;
	}

	public void setNumRows(int numRows)
	{
		this.numRows = numRows;
	}

	public int getNumCols()
	{
		return numCols;
	}

	public void setNumCols(int numCols)
	{
		this.numCols = numCols;
	}

	public void setFaceName(FaceName faceName)
	{
		this.faceName = faceName;
	}
	
	public FaceName getFaceName()
	{
		return this.faceName;
	}
	
	public RubiksCubeTile[] getFaceCol(int col, boolean isReverse)
	{
		RubiksCubeTile[] colSet = new RubiksCubeTile[this.faceTiles.length];
		
		if(isReverse)
		{
			int counter = 0;
			for(int row = faceTiles.length - 1; row >= 0; row--)
			{
				colSet[counter++] = this.faceTiles[row][col];
			}
		}
		else
		{
			for(int row = 0; row < this.faceTiles.length; row++)
			{
				colSet[row] = this.faceTiles[row][col];
			}
		}
		
		return colSet;
	}
	
	public RubiksCubeTile[] getFaceRow(int row, boolean isReverse)
	{
		RubiksCubeTile[] rowSet = new RubiksCubeTile[this.faceTiles.length];
		
		if(isReverse)
		{
			int counter = 0;
			for(int col = this.faceTiles.length - 1; col >= 0; col--)
			{
				rowSet[counter++] = this.faceTiles[row][col];
			}
		}
		else
		{
			for(int col = 0; col < this.faceTiles.length; col++)
			{
				rowSet[col] = this.faceTiles[row][col];
			}
		}
		
		return rowSet;
	}
	
	public void setFaceCol(int col, RubiksCubeTile[] colSet)
	{
		for(int row = 0; row < this.faceTiles.length; row++)
		{
			this.faceTiles[row][col] = colSet[row];
		}
		
		setFaceTilesMap();
	}
	
	public void setFaceRow(int row, RubiksCubeTile[] rowSet)
	{
		for(int col = 0; col < this.faceTiles.length; col++)
		{
			this.faceTiles[row][col] = rowSet[col];
		}
		
		setFaceTilesMap();
	}
	
	/**
	 * Return the adjacent face that is adjacent in the direction specified in the parameter
	 * @param direction
	 * @return FaceName
	 */
	public List<FaceName> getRotationMap(Direction direction)
	{
		return this.rotationMap.get(direction);
	}
	
	public RubiksCubeTile getFaceTile(TileLocation tileLocation)
	{
		return this.faceTilesMap.get(tileLocation);
	}
	
	private void setFaceTilesMap(EnumMap<TileLocation, RubiksCubeTile> faceTilesMap)
	{
		this.faceTilesMap = new EnumMap<TileLocation, RubiksCubeTile>(TileLocation.class);
		
	}
	
	private void setFaceTilesMap()
	{	
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				RubiksCubeTile currentTile = this.faceTiles[row][col];
				TileLocation currentTileLocation = TileLocation.getTileLocation(row, col);
				
				this.faceTiles[row][col] = currentTile;
				this.faceTilesMap.put(currentTileLocation, currentTile);
			}
		}
	}
	
//	/**
//	 * Set adjacent faces for the current face
//	 * @param faceName
//	 */
//	private void setRotationMap()
//	{
//		this.rotationMap = new EnumMap<Direction, List<FaceName>>(Direction.class);
//		ArrayList<FaceName> rotateUpList = new ArrayList<FaceName>();
//		ArrayList<FaceName> rotateRightList = new ArrayList<FaceName>();
//		ArrayList<FaceName> rotateDownList = new ArrayList<FaceName>();
//		ArrayList<FaceName> rotateLeftList = new ArrayList<FaceName>();
//		
//		for(int i = 0; i < 4; i++)
//		{
//			rotateUpList.add(i, FaceName.TOP);
//			rotateRightList.add(i, FaceName.RIGHT);
//			rotateDownList.add(i, FaceName.BOTTOM);	
//			rotateLeftList.add(i, FaceName.LEFT);
//		}
//		
//		switch(this.faceName)
//		{
//			case FRONT:
//			{
//				rotateUpList.set(0, FaceName.TOP);
//				rotateUpList.set(1, FaceName.BACK);
//				rotateUpList.set(2, FaceName.BOTTOM);
//				rotateUpList.set(3, FaceName.FRONT);
//				
//				rotateRightList.set(0, FaceName.RIGHT);
//				rotateRightList.set(1, FaceName.BACK);
//				rotateRightList.set(2, FaceName.LEFT);
//				rotateRightList.set(3, FaceName.FRONT);
//				
//				rotateDownList.set(0, FaceName.BOTTOM);
//				rotateDownList.set(1, FaceName.BACK);
//				rotateDownList.set(2, FaceName.TOP);
//				rotateDownList.set(3, FaceName.FRONT);
//				
//				rotateLeftList.set(0, FaceName.LEFT);
//				rotateLeftList.set(1, FaceName.BACK);
//				rotateLeftList.set(2, FaceName.RIGHT);
//				rotateLeftList.set(3, FaceName.FRONT);
//				
//				this.rotationMap.put(Direction.UP, rotateUpList);
//				this.rotationMap.put(Direction.RIGHT, rotateRightList);
//				this.rotationMap.put(Direction.DOWN, rotateDownList);
//				this.rotationMap.put(Direction.LEFT, rotateLeftList);
//				break;
//			}
//			case BACK:
//			{
//				rotateUpList.set(0, FaceName.TOP);
//				rotateUpList.set(1, FaceName.FRONT);
//				rotateUpList.set(2, FaceName.BOTTOM);
//				rotateUpList.set(3, FaceName.BACK);
//				
//				rotateRightList.set(0, FaceName.LEFT);
//				rotateRightList.set(1, FaceName.FRONT);
//				rotateRightList.set(2, FaceName.LEFT);
//				rotateRightList.set(3, FaceName.BACK);
//				
//				rotateDownList.set(0, FaceName.BOTTOM);
//				rotateDownList.set(1, FaceName.FRONT);
//				rotateDownList.set(2, FaceName.TOP);
//				rotateDownList.set(3, FaceName.BACK);
//				
//				rotateLeftList.set(0, FaceName.RIGHT);
//				rotateLeftList.set(1, FaceName.FRONT);
//				rotateLeftList.set(2, FaceName.LEFT);
//				rotateLeftList.set(3, FaceName.BACK);
//				
//				this.rotationMap.put(Direction.UP, rotateUpList);
//				this.rotationMap.put(Direction.RIGHT, rotateRightList);
//				this.rotationMap.put(Direction.DOWN, rotateDownList);
//				this.rotationMap.put(Direction.LEFT, rotateLeftList);
//				break;
//			}
//			case LEFT:
//			{
//				rotateUpList.set(0, FaceName.TOP);
//				rotateUpList.set(1, FaceName.RIGHT);
//				rotateUpList.set(2, FaceName.BOTTOM);
//				rotateUpList.set(3, FaceName.LEFT);
//				
//				rotateRightList.set(0, FaceName.FRONT);
//				rotateRightList.set(1, FaceName.RIGHT);
//				rotateRightList.set(2, FaceName.BACK);
//				rotateRightList.set(3, FaceName.LEFT);
//				
//				rotateDownList.set(0, FaceName.BOTTOM);
//				rotateDownList.set(1, FaceName.RIGHT);
//				rotateDownList.set(2, FaceName.TOP);
//				rotateDownList.set(3, FaceName.LEFT);
//				
//				rotateLeftList.set(0, FaceName.BACK);
//				rotateLeftList.set(1, FaceName.RIGHT);
//				rotateLeftList.set(2, FaceName.FRONT);
//				rotateLeftList.set(3, FaceName.LEFT);
//				
//				this.rotationMap.put(Direction.UP, rotateUpList);
//				this.rotationMap.put(Direction.RIGHT, rotateRightList);
//				this.rotationMap.put(Direction.DOWN, rotateDownList);
//				this.rotationMap.put(Direction.LEFT, rotateLeftList);
//				break;
//			}
//			case RIGHT:
//			{
//				rotateUpList.set(0, FaceName.TOP);
//				rotateUpList.set(1, FaceName.LEFT);
//				rotateUpList.set(2, FaceName.BOTTOM);
//				rotateUpList.set(3, FaceName.RIGHT);
//				
//				rotateRightList.set(0, FaceName.BACK);
//				rotateRightList.set(1, FaceName.LEFT);
//				rotateRightList.set(2, FaceName.FRONT);
//				rotateRightList.set(3, FaceName.RIGHT);
//				
//				rotateDownList.set(0, FaceName.BOTTOM);
//				rotateDownList.set(1, FaceName.LEFT);
//				rotateDownList.set(2, FaceName.TOP);
//				rotateDownList.set(3, FaceName.RIGHT);
//				
//				rotateLeftList.set(0, FaceName.FRONT);
//				rotateLeftList.set(1, FaceName.LEFT);
//				rotateLeftList.set(2, FaceName.BACK);
//				rotateLeftList.set(3, FaceName.RIGHT);
//				
//				this.rotationMap.put(Direction.UP, rotateUpList);
//				this.rotationMap.put(Direction.RIGHT, rotateRightList);
//				this.rotationMap.put(Direction.DOWN, rotateDownList);
//				this.rotationMap.put(Direction.LEFT, rotateLeftList);
//				break;
//			}
//			case TOP:
//			{
//				rotateUpList.set(0, FaceName.BACK);
//				rotateUpList.set(1, FaceName.BOTTOM);
//				rotateUpList.set(2, FaceName.FRONT);
//				rotateUpList.set(3, FaceName.TOP);
//				
//				rotateRightList.set(0, FaceName.RIGHT);
//				rotateRightList.set(1, FaceName.BOTTOM);
//				rotateRightList.set(2, FaceName.LEFT);
//				rotateRightList.set(3, FaceName.TOP);
//				
//				rotateDownList.set(0, FaceName.FRONT);
//				rotateDownList.set(1, FaceName.BOTTOM);
//				rotateDownList.set(2, FaceName.BACK);
//				rotateDownList.set(3, FaceName.TOP);
//				
//				rotateLeftList.set(0, FaceName.LEFT);
//				rotateLeftList.set(1, FaceName.BOTTOM);
//				rotateLeftList.set(2, FaceName.RIGHT);
//				rotateLeftList.set(3, FaceName.TOP);
//				
//				this.rotationMap.put(Direction.UP, rotateUpList);
//				this.rotationMap.put(Direction.RIGHT, rotateRightList);
//				this.rotationMap.put(Direction.DOWN, rotateDownList);
//				this.rotationMap.put(Direction.LEFT, rotateLeftList);
//				break;
//			}
//			case BOTTOM:
//			{
//				rotateUpList.set(0, FaceName.FRONT);
//				rotateUpList.set(1, FaceName.TOP);
//				rotateUpList.set(2, FaceName.BACK);
//				rotateUpList.set(3, FaceName.BOTTOM);
//				
//				rotateRightList.set(0, FaceName.RIGHT);
//				rotateRightList.set(1, FaceName.TOP);
//				rotateRightList.set(2, FaceName.LEFT);
//				rotateRightList.set(3, FaceName.BOTTOM);
//				
//				rotateDownList.set(0, FaceName.BACK);
//				rotateDownList.set(1, FaceName.TOP);
//				rotateDownList.set(2, FaceName.FRONT);
//				rotateDownList.set(3, FaceName.BOTTOM);
//				
//				rotateLeftList.set(0, FaceName.LEFT);
//				rotateLeftList.set(1, FaceName.TOP);
//				rotateLeftList.set(2, FaceName.RIGHT);
//				rotateLeftList.set(3, FaceName.BOTTOM);
//				
//				this.rotationMap.put(Direction.UP, rotateUpList);
//				this.rotationMap.put(Direction.RIGHT, rotateRightList);
//				this.rotationMap.put(Direction.DOWN, rotateDownList);
//				this.rotationMap.put(Direction.LEFT, rotateLeftList);
//				break;
//			}
//		}
//	}
	
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
