/**
 * 
 */
package rubikscube.three;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rubikscube.RubiksCubeInterface;

/**
 * @author Joshua Parsons
 * 
 */
public class TestRotations
{
	private RubiksCubeInterface rubiksCube;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		rubiksCube = new RubiksCubeThree();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		rubiksCube.displayRubiksCube();
		
		//TOP
		rubiksCube.rotateTopClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		rubiksCube.rotateTopCounterClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		//LEFT
		rubiksCube.rotateLeftClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		rubiksCube.rotateLeftCounterClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		//FRONT
		rubiksCube.rotateFrontClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		rubiksCube.rotateFrontCounterClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		//RIGHT
		rubiksCube.rotateRightClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		rubiksCube.rotateRightCounterClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		//BACK
		rubiksCube.rotateBackClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		rubiksCube.rotateBackCounterClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		//BOTTOM
		rubiksCube.rotateBottomClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
		
		rubiksCube.rotateBottomCounterClockwise();
		
		System.out.println();
		
		rubiksCube.displayRubiksCube();
	}

}
