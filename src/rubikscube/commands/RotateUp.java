package rubikscube.commands;

import rubikscube.interfaces.IRotate;
import rubikscube.interfaces.IRubiksCube;

public class RotateUp implements IRotate
{
	private IRubiksCube rubiksCube;

	public RotateUp(IRubiksCube rubiksCube)
	{
		setRubiksCube(rubiksCube);
	}


	/* (non-Javadoc)
	 * @see rubikscube.interfaces.IRotate#execute()
	 */
	@Override
	public void execute()
	{
		this.rubiksCube.rotateUp();
	}
	
	private void setRubiksCube(IRubiksCube rubiksCube)
	{
		this.rubiksCube = rubiksCube;
	}

}
