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
	public void shuffleRubiksCube();
	
	public void resetRubiksCube();
	
	public void solveRubiksCube();
	
	public void displayRubiksCube();
	
	public void rotateTopClockwise();
	public void rotateTopCounterClockwise();
	public void rotateLeftClockwise();
	public void rotateLeftCounterClockwise();
	public void rotateFrontClockwise();
	public void rotateFrontCounterClockwise();
	public void rotateRightClockwise();
	public void rotateRightCounterClockwise();
	public void rotateBackClockwise();
	public void rotateBackCounterClockwise();
	public void rotateBottomClockwise();
	public void rotateBottomCounterClockwise();
}
