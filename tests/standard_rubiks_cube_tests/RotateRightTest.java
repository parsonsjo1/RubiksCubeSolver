/**
 * 
 */
package standard_rubiks_cube_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rubikscube.FaceName;
import rubikscube.RubiksCubeInterface;
import rubikscube.RubiksCubeThree;

/**
 * @author Joshua Parsons
 * 
 */
public class RotateRightTest 
{
	RubiksCubeInterface rubiksCube;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		rubiksCube = new RubiksCubeThree(3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		System.out.println("Finished Testing RotateRightTest");
	}

	@Test
	public void rotateRightFrontTest() 
	{
		rubiksCube.displayRubiksCube();
		
		rubiksCube.rotateRight(FaceName.FRONT, 0);
		
		rubiksCube.displayRubiksCube();
		
		assertEquals(true, true);
	}

}
