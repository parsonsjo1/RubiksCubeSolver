/**
 * 
 */
package rubikscube;

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
	
	public void rotateClockwise(FaceName faceName, int row);
	
	public void rotateCounterClockwise(FaceName faceName, int row);
}
