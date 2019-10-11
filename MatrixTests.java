import java.util.Random;

/**
 * A class with a main method for testing the functions of the Matrix class.
 *
 * @author Nathan Jobe
 * @version 10/10/2019
 */
public class MatrixTests
{
	/**
	 * The main method.
	 *
	 * @param args - command line arguments (not used).
	 */
	public static void main(String[] args)
	{
		emptyMatrixConstructorTests();
		nonEmptyMatrixConstructorTests();
	}

	/**
	 * emptyMatrixConstructorTests() ensures that a call to Matrix() creates a -1-initialized Matrix.
	 */
	public static void emptyMatrixConstructorTests()
	{
		Matrix m = new Matrix();
		System.out.println(m);
	}

	/**
	 * nonEmptyMatrixConstructorTests() ensures that a call to Matrix(int[][] givenInformation)
	 * correctly creates a matrix with that information.
	 */
	public static void nonEmptyMatrixConstructorTests()
	{
		int[][] infoPassed = {
								{-1, -1, -1,  4, -1,  7, -1, -1, -1},
								{-1, -1, -1, -1, -1, -1, -1, -1, -1},
								{-1, -1, -1, -1, -1, -1, -1, -1, -1},
								{-1, -1, -1, -1, -1, -1, -1, -1, -1},
								{-1, -1, -1, -1, -1, -1, -1, -1, -1},
								{-1, -1,  2,  3, -1, -1, -1, -1, -1},
								{-1, -1, -1, -1, -1, -1, -1, -1, -1},
								{-1, -1, -1, -1, -1, -1, -1, -1, -1},
								{-1, -1, -1, -1, -1, -1, -1, -1, -1}
							};
		Matrix m = new Matrix(infoPassed);
		System.out.println(m);
	}
}

