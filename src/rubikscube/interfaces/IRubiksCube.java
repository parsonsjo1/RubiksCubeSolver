/**
 * 
 */
package rubikscube.interfaces;

/**
 * @author Joshua Parsons
 * 
 */
public interface IRubiksCube 
{	
	public IRubiksCube createRubiksCube();
	
	public final int NUM_FACES = 6;
	
	public void shuffle();
	public void reset();
	public void solve();
	public void display();
	
	public void rotateUp();
	public void rotateUpInverse();
	public void rotateLeft();
	public void rotateLeftInverse();
	public void rotateFront();
	public void rotateFrontInverse();
	public void rotateRight();
	public void rotateRightInverse();
	public void rotateBack();
	public void rotateBackInverse();
	public void rotateDown();
	public void rotateDownInverse();
}
