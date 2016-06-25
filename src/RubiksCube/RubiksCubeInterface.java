/**
 * 
 */
package RubiksCube;

/**
 * @author Joshua Parsons
 * 
 */
public interface RubiksCubeInterface 
{	
	public void shuffleRubiksCube();
	
	public void resetRubiksCube();
	
	public void solveRubiksCube();
	
	public void displayRubiksCube();
	
	public void rotateRight(FaceName faceName, int row);
}
