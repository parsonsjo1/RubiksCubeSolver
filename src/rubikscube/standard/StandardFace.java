package rubikscube.standard;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import rubikscube.Cubie;
import rubikscube.enums.Direction;
import rubikscube.enums.FaceName;
import rubikscube.enums.TileColor;
import rubikscube.interfaces.IRubiksCubeFace;
import rubikscube.standard.enums.CubieLocation;

/**
 * The faces of a Rubiks Cube
 * @author Joshua Parsons
 */
public class StandardFace implements IRubiksCubeFace
{
	private FaceName faceName;
	private final int NUM_ROWS =  3;
	private final int NUM_COLUMNS = 3;
	private Cubie[][] cubies;
//	private Map<TileLocation, Cubie> cubiesMap;
//	private Map<Direction, List<FaceName>> rotationMap;
	
	/**
	 * RubiksCubeFace Constructor
	 * @param size
	 * @param faceName
	 */
	public StandardFace(FaceName faceName, String color)
	{
		setFaceName(faceName);
		setCubies(color);
		//setcubiesMap(new EnumMap<TileLocation, Cubie>(TileLocation.class));
		//setRotationMap();
	}
	
//	public TileLocation findCornerLocation(TileColor tileColor)
//	{
//		if(this.isTileColor(TileLocation.TOP_LEFT_CORNER, TileColor.WHITE))
//			return TileLocation.TOP_LEFT_CORNER;
//		if(this.isTileColor(TileLocation.TOP_RIGHT_CORNER, TileColor.WHITE))
//			return TileLocation.TOP_RIGHT_CORNER;
//		if(this.isTileColor(TileLocation.BOTTOM_LEFT_CORNER, TileColor.WHITE))
//			return TileLocation.BOTTOM_LEFT_CORNER;
//		if(this.isTileColor(TileLocation.BOTTOM_RIGHT_CORNER, TileColor.WHITE))
//			return TileLocation.BOTTOM_RIGHT_CORNER;
//		
//		return null;
//	}
	
	public CubieLocation findEdge(String color)
	{
		String topEdgeColor = this.getColor(CubieLocation.TOP_EDGE);
		String leftEdgeColor = this.getColor(CubieLocation.LEFT_EDGE);;
		String bottomEdgeColor = this.getColor(CubieLocation.BOTTOM_EDGE);;
		String rightEdgeColor = this.getColor(CubieLocation.RIGHT_EDGE);;
		
		if(topEdgeColor.equals(color))
			return CubieLocation.TOP_EDGE;
		if(leftEdgeColor.equals(color))
			return CubieLocation.LEFT_EDGE;
		if(bottomEdgeColor.equals(color))
			return CubieLocation.BOTTOM_EDGE;
		if(rightEdgeColor.equals(color))
			return CubieLocation.RIGHT_EDGE;
		
		return null;
	}
	
	public String getColor()
	{
		return this.getCubie(1, 1).getColor();
	}
	
	public String getColor(int row, int col)
	{
		return this.cubies[row][col].getColor();
	}
	
	public String getColor(CubieLocation cubieLocation)
	{
		return this.getCubie(cubieLocation).getColor();
	}
//	
//	public boolean isTileColor(TileLocation tileLocation, String tileColor)
//	{
//		return this.cubiesMap.get(tileLocation).getColor().equals(tileColor);
//	}
	
	/**
	 * Rotate the face tiles in the clockwise direction
	 */
	public void rotate()
	{
		Cubie topLeftCorner = this.cubies[0][0];
		Cubie topEdge = this.cubies[0][1];
		Cubie topRightCorner = this.cubies[0][2];
		Cubie rightEdge = this.cubies[1][2];
		Cubie bottomRightCorner = this.cubies[2][2];
		Cubie bottomEdge = this.cubies[2][1];
		Cubie bottomLeftCorner = this.cubies[2][0];
		Cubie leftEdge = this.cubies[1][0];
		
		this.cubies[0][0] = bottomLeftCorner;
		this.cubies[0][1] = leftEdge;
		this.cubies[0][2] = topLeftCorner;
		this.cubies[1][2] = topEdge;
		this.cubies[2][2] = topRightCorner;
		this.cubies[2][1] = rightEdge;
		this.cubies[2][0] = bottomRightCorner;
		this.cubies[1][0] = bottomEdge;
		
		//setcubiesMap();
	}
	
	/**
	 * Rotate the face tiles in the counterclockwise direction
	 */
	public void rotateInverse()
	{
		Cubie topLeftCorner = this.cubies[0][0];
		Cubie topEdge = this.cubies[0][1];
		Cubie topRightCorner = this.cubies[0][2];
		Cubie rightEdge = this.cubies[1][2];
		Cubie bottomRightCorner = this.cubies[2][2];
		Cubie bottomEdge = this.cubies[2][1];
		Cubie bottomLeftCorner = this.cubies[2][0];
		Cubie leftEdge = this.cubies[1][0];
		
		this.cubies[0][0] = topRightCorner;
		this.cubies[0][1] = rightEdge;
		this.cubies[0][2] = bottomRightCorner;
		this.cubies[1][2] = bottomEdge;
		this.cubies[2][2] = bottomLeftCorner;
		this.cubies[2][1] = leftEdge;
		this.cubies[2][0] = topLeftCorner;
		this.cubies[1][0] = topEdge;
		
		//setcubiesMap();
	}
	
//	public boolean isEqual(Cubie tile)
//	{
//		return this.getFaceTile(TileLocation.MIDDLE).equals(tile);
//	}
//	
//	public boolean isEqual(TileLocation tileLocation)
//	{
//		return this.getFaceTile(TileLocation.MIDDLE).equals(this.getFaceTile(tileLocation));
//	}
//	
//	public boolean isEqual(TileLocation tileLocation1, TileLocation tileLocation2)
//	{
//		return this.getFaceTile(tileLocation1).equals(this.getFaceTile(tileLocation2));
//	}
//	
//	public boolean isEqual(TileLocation tileLocation, Cubie otherTile)
//	{
//		return this.getFaceTile(tileLocation).equals(otherTile);
//	}
	
	/*
	 * Return the requested cubie
	 */
	public Cubie getCubie(int row, int col)
	{
		return this.cubies[row][col];
	}
	
	/*
	 * Return the requested cubie
	 */
	public Cubie getCubie(CubieLocation cubieLocation)
	{
			switch(cubieLocation)
			{
			case MIDDLE:
				return this.cubies[1][1];
			case BOTTOM_EDGE:
				return this.cubies[2][1];
			case BOTTOM_LEFT_CORNER:
				return this.cubies[2][0];
			case BOTTOM_RIGHT_CORNER:
				return this.cubies[2][2];
			case LEFT_EDGE:
				return this.cubies[1][0];
			case RIGHT_EDGE:
				return this.cubies[1][2];
			case TOP_EDGE:
				return this.cubies[0][1];
			case TOP_LEFT_CORNER:
				return this.cubies[0][0];
			case TOP_RIGHT_CORNER:
				return this.cubies[0][2];
			default:
				return null;
			}
	}
	
	/**
	 * @return the cubies
	 */
	public Cubie[][] getCubies() 
	{
		return this.cubies;
	}
	
	/**
	 * 
	 * @param size
	 */
	public void setCubies(String color)
	{
		this.cubies = new Cubie[this.NUM_ROWS][this.NUM_COLUMNS];
		
		for(int row = 0; row < this.NUM_ROWS; row++)
		{
			for(int col = 0; col < this.NUM_COLUMNS; col++)
			{
				CubieLocation location = CubieLocation.getTileLocation(row, col);
				Cubie currentTile = new Cubie(color, location);				
				this.cubies[row][col] = currentTile;
			}
		}	
	}

	public void setFaceName(FaceName faceName)
	{
		this.faceName = faceName;
	}
	
	public FaceName getFaceName()
	{
		return this.faceName;
	}
	
	public Cubie[] getFaceCol(int col, boolean isReverse)
	{
		Cubie[] colSet = new Cubie[this.cubies.length];
		
		if(isReverse)
		{
			int counter = 0;
			for(int row = cubies.length - 1; row >= 0; row--)
			{
				colSet[counter++] = this.cubies[row][col];
			}
		}
		else
		{
			for(int row = 0; row < this.cubies.length; row++)
			{
				colSet[row] = this.cubies[row][col];
			}
		}
		
		return colSet;
	}
	
	public Cubie[] getFaceRow(int row, boolean isReverse)
	{
		Cubie[] rowSet = new Cubie[this.cubies.length];
		
		if(isReverse)
		{
			int counter = 0;
			for(int col = this.cubies.length - 1; col >= 0; col--)
			{
				rowSet[counter++] = this.cubies[row][col];
			}
		}
		else
		{
			for(int col = 0; col < this.cubies.length; col++)
			{
				rowSet[col] = this.cubies[row][col];
			}
		}
		
		return rowSet;
	}
	
	public void setFaceCol(int col, Cubie[] colSet)
	{
		for(int row = 0; row < this.cubies.length; row++)
		{
			this.cubies[row][col] = colSet[row];
		}
		
		//setcubiesMap();
	}
	
	public void setFaceRow(int row, Cubie[] rowSet)
	{
		for(int col = 0; col < this.cubies.length; col++)
		{
			this.cubies[row][col] = rowSet[col];
		}
		
		//setcubiesMap();
	}
	
	/**
	 * Return the adjacent face that is adjacent in the direction specified in the parameter
	 * @param direction
	 * @return FaceName
	 */
//	public List<FaceName> getRotationMap(Direction direction)
//	{
//		return this.rotationMap.get(direction);
//	}
//	
//	public Cubie getFaceTile()
//	{
//		return this.cubiesMap.get(TileLocation.MIDDLE);
//	}
//	
//	public Cubie getFaceTile(TileLocation tileLocation)
//	{
//		return this.cubiesMap.get(tileLocation);
//	}
//	
//	private void setcubiesMap(EnumMap<TileLocation, Cubie> cubiesMap)
//	{
//		this.cubiesMap = new EnumMap<TileLocation, Cubie>(TileLocation.class);
//		
//	}
	
//	private void setcubiesMap()
//	{	
//		for(int row = 0; row < this.NUM_ROWS; row++)
//		{
//			for(int col = 0; col < this.NUM_COLUMNS; col++)
//			{
//				Cubie currentTile = this.cubies[row][col];
//				TileLocation currentTileLocation = TileLocation.getTileLocation(row, col);
//				
//				this.cubies[row][col] = currentTile;
//				this.cubiesMap.put(currentTileLocation, currentTile);
//			}
//		}
//	}
	
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
		
		for(int row = 0; row < this.NUM_ROWS; row++)
		{
			for(int col = 0; col < this.NUM_COLUMNS; col++)
			{
				faceSB.append(this.cubies[row][col].getColor() + " ");
			}
			if(row != 2)
				faceSB.append("\n");
		}
			
		return faceSB.toString();
	}
}
