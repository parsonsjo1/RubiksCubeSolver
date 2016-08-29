/**
 * 
 */
package rubikscube.commands;

import rubikscube.interfaces.IRotate;
import rubikscube.interfaces.IRubiksCube;

/**
 * @author Joshua Parsons
 * 
 */
public class RotateLeft implements IRotate
{
	private IRubiksCube rubiksCube;
	
	/**
	 * 
	 */
	public RotateLeft(IRubiksCube rubiksCube)
	{
		setRubiksCube(rubiksCube);
	}

	/* (non-Javadoc)
	 * @see rubikscube.interfaces.IRotate#execute()
	 */
	@Override
	public void execute()
	{
		this.rubiksCube.rotateLeft();
	}
	
	private void setRubiksCube(IRubiksCube rubiksCube)
	{
		this.rubiksCube = rubiksCube;
	}

}
