/**
 * 
 */
package rubikscube;

import rubikscube.enums.FaceName;

/**
 * @author Joshua Parsons
 * 
 */
public interface RubiksCubeInterface 
{	
	public final int NUM_FACES = 6;
	
	public void shuffleRubiksCube();
	
	public void resetRubiksCube();
	
	public void solveRubiksCube();
	
	public void displayRubiksCube();
	
	public void rotateRowClockwise(FaceName faceName, int row);
	
	public void rotateRowCounterClockwise(FaceName faceName, int row);
	
	public void rotateColClockwise(FaceName faceName, int col);
	
	public void rotateColCounterClockwise(FaceName faceName, int col);
}
