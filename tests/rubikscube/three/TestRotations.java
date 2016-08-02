/**
 * 
 */
package rubikscube.three;

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
	public void testTopRotation()
	{
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateRightClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateLeftClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateTopClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateBottomClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateRightClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateLeftClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateTopClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateBottomClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
	}

	@Test
	public void testRotations()
	{
		rubiksCube.displayRubiksCube();
		System.out.println("\nROTATE RIGHT");
		rubiksCube.rotateRightCounterClockwise();
		rubiksCube.displayRubiksCube();
		
		System.out.println("\nROTATE LEFT");
		rubiksCube.rotateLeftCounterClockwise();
		rubiksCube.displayRubiksCube();
		
		System.out.println("\nROTATE FRONT");
		rubiksCube.rotateFrontCounterClockwise();
		rubiksCube.displayRubiksCube();
		
		System.out.println("\nROTATE BACK");
		rubiksCube.rotateBackCounterClockwise();
		rubiksCube.displayRubiksCube();
		
		System.out.println("\nROTATE TOP");
		rubiksCube.rotateTopCounterClockwise();
		rubiksCube.displayRubiksCube();
		
		System.out.println("\nROTATE BOTTOM");
		rubiksCube.rotateBottomCounterClockwise();
		rubiksCube.displayRubiksCube();
		
//		rubiksCube.displayRubiksCube();
//		
//		//TOP
//		rubiksCube.rotateTopClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateTopCounterClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		//LEFT
//		rubiksCube.rotateLeftClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateLeftCounterClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		//FRONT
//		rubiksCube.rotateFrontClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateFrontCounterClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		//RIGHT
//		rubiksCube.rotateRightClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateRightCounterClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		//BACK
//		rubiksCube.rotateBackClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateBackCounterClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		//BOTTOM
//		rubiksCube.rotateBottomClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
//		
//		rubiksCube.rotateBottomCounterClockwise();
//		
//		System.out.println();
//		
//		rubiksCube.displayRubiksCube();
	}

}
