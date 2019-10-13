// For user input
import java.util.Scanner;

/**
 * SudokuPlayer - runs a game of Sudoku for a user on a command line
 *
 * @author Nathan Jobe
 * @version October 11 2019
 */
public class Sudoku
{
	/* DIMENSION represents the number of rows and columns
	 * EMPTY represents the value used for "empty" cells
	 * board represents the 9x9 Matrix we will use for our numbers
	 * kb is a Scanner from System.in to allow user interaction
	 * verbose allows users to see the steps involved in finding a solution
	 *     or just the final solution itself
	 * stepsTaken shows how many unique combinations of numbers the program
	 * tried before finding the solution
	 */
	private static final int DIMENSION = 9;
	private static final int EMPTY = -1;
	private Matrix board;
	private Scanner kb;
	private boolean verbose;
	private int stepsTaken;

	/**
	 * The Sudoku constructor calls multiple initializing functions
	 * involving user input
	 */
	public Sudoku()
	{
		// Get user input from System.in (keyboard)
		this.kb = new Scanner(System.in);
		// User ought to define constraints in the Matrix
		this.board = getMatrixFromUser();
		// User decides whether or not to see every step taken to solve the puzzle
		userDecideVerbose();
		this.stepsTaken = 0;
	}

	/**
	 * The main method - for executing the Sudoku solver program.
	 *
	 * @param args - command line arguments (unnecessary)
	 */
	public static void main(String[] args)
	{
		// Initialize game
		Sudoku game = new Sudoku();
		// Find answer
		game.solve(0, 0);
		// Close the input Scanners and end the game
		game.endGame();
	}


	/**
	 * An unfortunately complex recursive method for solving the Sudoku puzzle.
	 *
	 * It uses a depth-first-search (DFS) algorithm to see how far it can get with one number
	 * in one square, and if it doesn't find a complete solution, it tries another number, until
	 * it finds no solution and it "clears" the square again and moves back up one recursive level.
	 * Then, the square behind it will exhaust its options, etc.
	 *
	 * @param row - the row of the index currently being tried with possible answers
	 * @param col - the column of the index currently being tried with possible answers
	 * @return boolean - whether a solution was found in this pathway
	 */
	private boolean solve(int row, int col)
	{
		// allows user to see each step taken to solve the puzzle
		if (verbose)
			System.out.println(board);

		// this would mean all squares have been solved, so recursion ends
		if (row == 9 && col == 0)
			return true;

		// Check if we're looking at an empty cell
		if (board.getGivenInformation()[row][col] == EMPTY)
		{
			// If we are, try the lowest possible value and recurse
			// succeeded - holds whether or not a final solution was found on the path followed
			boolean succeeded = false;

			// This code is very repetitive. To be fixed later
			// trying one in the current space
			if (isAllowed(row, col, 1))
			{
				stepsTaken++;
				board.setAt(row, col, 1);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// trying two
			if (isAllowed(row, col, 2))
			{
				stepsTaken++;
				board.setAt(row, col, 2);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// trying three
			if (isAllowed(row, col, 3))
			{
				stepsTaken++;
				board.setAt(row, col, 3);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// trying four
			if (isAllowed(row, col, 4))
			{
				stepsTaken++;
				board.setAt(row, col, 4);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// trying five
			if (isAllowed(row, col, 5))
			{
				stepsTaken++;
				board.setAt(row, col, 5);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// trying six
			if (isAllowed(row, col, 6))
			{
				stepsTaken++;
				board.setAt(row, col, 6);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// trying seven
			if (isAllowed(row, col, 7))
			{
				stepsTaken++;
				board.setAt(row, col, 7);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// trying eight
			if (isAllowed(row, col, 8))
			{
				stepsTaken++;
				board.setAt(row, col, 8);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// trying nine
			if (isAllowed(row, col, 9))
			{
				stepsTaken++;
				board.setAt(row, col, 9);
				if (col < 8)
					succeeded = solve(row, col + 1);
				else
					succeeded = solve(row + 1, 0);
				if (succeeded)
					return true;
			}
			// this means no solution was found
			// clear the current square
			board.setAt(row, col, EMPTY);
			return false;
		}
		else
		{
			// If we aren't in an empty square, try again on the next square
			if (col < 8)
				return solve(row, col + 1);
			else
				return solve(row + 1, 0);
		}
	}

	/**
	 * Allows the user to define the game and sets the board field appropriately.
	 * 
	 * @return Matrix - the new Matrix (board) object created here
	 */
	private Matrix getMatrixFromUser()
	{
		// A 2d array to store immutable information (NOT CHANGEABLE BY SOLVER)
		int[][] givenInformation = new int[DIMENSION][DIMENSION];

		// Clearing (setting to -1) all values in this immutable values array for now
		// until user can define them himself/herself
		for (int rowIndex = 0; rowIndex < givenInformation.length; rowIndex++)
			for (int colIndex = 0; colIndex < givenInformation[rowIndex].length; colIndex++)
				givenInformation[rowIndex][colIndex] = EMPTY;

		// createdMatrix and board are synonymous (will be corrected later)
		Matrix createdMatrix = new Matrix(givenInformation);
		
		// to be corrected later
		this.board = createdMatrix;

		// explaining to user
		System.out.println("Input known information");
		System.out.println("Format: row, column, new value");
		System.out.println("Example: 6,3,8");
		System.out.println("All three input numbers must be between 1 and 9. -1 is also permitted to \"clear\" an input.");
		System.out.println("Type \"print\" to view the current state of the sudoku board.");
		System.out.println("Enter information change commands as described, or type \"done\" to continue.");

		// boolean to break out of while loop when ready
		boolean inputtingComplete = false;

		// Should all be converted to regex eventually
		// while loop lasts while user is still inputting information
		while (!inputtingComplete)
		{
			// next three lines remove whitespace around answer and make it lowercase
			System.out.print("command: ");
			String userResponse = kb.nextLine().toLowerCase();
			userResponse = userResponse.replaceAll("\\s","");
			
			// if user is finished
			if (userResponse.equals("done"))
				inputtingComplete = true;
			
			// if user wants to see the current state of the board
			else if (userResponse.equals("print"))
				System.out.println(createdMatrix);
			
			// if user gave valid input
			else if ((userResponse.length() == 5 || userResponse.length() == 6) && userResponse.charAt(1) == ',' && userResponse.charAt(3) == ',')
			{
				String[] nums = userResponse.split(",");
				if (nums.length == 3)
				{
					
					int[] userResponseNums = new int[3];
					boolean invalid = false;
					try
					{
						for (int i = 0; i < 3; i++)
						{
							userResponseNums[i] = Integer.parseInt(nums[i]);
							if (userResponseNums[i] < EMPTY || userResponseNums[i] == 0 || userResponseNums[i] > DIMENSION)
								invalid = true;
						}
					}
					catch (NumberFormatException nfe)
					{
						invalid = true;
					}

					if (invalid)
						System.out.println("Invalid input, please try again");

					else
					{
						if (isAllowed(userResponseNums[0] - 1, userResponseNums[1] - 1, userResponseNums[2]))
							createdMatrix.setGivenInformationAt(userResponseNums[0] - 1, userResponseNums[1] - 1, userResponseNums[2]);
						
						else
							System.out.println("Value unusable in this space");
					}
				}
			}
			
			// if user gave invalid input
			else
				System.out.println("Invalid input, please try again");
		}
		return createdMatrix;
	}

	/**
	 * Interacts with the user and asks whether he/she wants to see every step the program took
	 * to solve the puzzle.
	 */
	private void userDecideVerbose()
	{
		System.out.println("Would you like to see each attempted step to solve the game?");
		boolean validResponse = false;
		while (!validResponse)
		{
			System.out.print("y/n: ");
			// next three lines make lowercase the user's response, remove any spaces, and makes sure it's only one character long
			String response = kb.nextLine().toLowerCase();
			response = response.replaceAll("\\s","");
			if (response.length() == 1)
			{
				switch (response.charAt(0))
				{
					case 'y':
						this.verbose = true;
						validResponse = true;
						break;
					case 'n':
						this.verbose = false;
						validResponse = true;
						break;
					default:
						break;
				}
			}
		}
	}

	/**
	 * Closes the user input Scanner and informs the user that the game is over.
	 */
	private void endGame()
	{
		System.out.println(board);
		System.out.println("Game complete.");
		System.out.println("steps taken: " + stepsTaken);
		this.kb.close();
	}

	/**
	 * Examines whether a particular value at a particular row and column index would be permissible
	 * knowing the current state of the board.
	 *
	 * @param row - the row index of the potential value
	 * @param col - the column index of the potential value
	 * @param val - the value that you are attempting to place in that index
	 * @return boolean - whether or not that value at that index is permissible
	 */
	private boolean isAllowed(int row, int col, int val) throws IllegalArgumentException
	{
		// Need to be sure the function is not called with parameters that would try to access outside the board
		// or plug in a value that isn't 1-9
		if (row < 0 || col < 0 || (val < 1 && val != EMPTY) || row > 8 || col > 8 || val > 9)
			throw new IllegalArgumentException("Illegal indeces or value passed in Sudoku.isAllowed(int,int,int)");

		/* valIsLegal  - a variable to remember whether, to the current knowledge of the method, the value being
		 *     tested at that index is legal.
		 * rowToCheck  - an array of values currently in the same row as the potential new one
		 * colToCheck  - an array of values currently in the same column as the potential new one
		 * cellToCheck - an array of values currently in the same cell as the potential new one
		 */
		boolean valIsLegal = true;
		int[] rowToCheck = board.getRowAt(row);
		int[] colToCheck = board.getColAt(col);
		int[] cellToCheck = board.getCellAt(row, col);

		// If val == EMPTY (-1), it should always be legal
		if (val == EMPTY)
			return valIsLegal;

		// Ensuring that the value passed is not in conflict with any already in its row, column, or cell
		for (int i = 0; i < DIMENSION; i++)
			if (rowToCheck[i] == val || colToCheck[i] == val || cellToCheck[i] == val)
				valIsLegal = false;

		return valIsLegal;
	}
}

