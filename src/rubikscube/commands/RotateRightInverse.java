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
public class RotateRightInverse implements IRotate
{
	private IRubiksCube rubiksCube;

	/**
	 * 
	 */
	public RotateRightInverse(IRubiksCube rubiksCube)
	{
		setRubiksCube(rubiksCube);
	}

	/* (non-Javadoc)
	 * @see rubikscube.interfaces.IRotate#execute()
	 */
	@Override
	public void execute()
	{
		this.rubiksCube.rotateRightInverse();
	}
	
	private void setRubiksCube(IRubiksCube rubiksCube)
	{
		this.rubiksCube = rubiksCube;
	}
}
