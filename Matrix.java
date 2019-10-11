
/**
 * The Matrix class is a representation of a Sudoku grid.
 *
 * @author Nathan Jobe
 * @version 10/10/2019
 */
public class Matrix
{
	// The number of number slots in each row, column, and box
	private static final int DIMENSION = 9;
	// A 2d array to represent the grid itself
	private int[][] grid;
	// A 2d array to remember the given values that cannot be edited
	private int[][] givenInformation;
	// A boolean to remember whether the grid is solved or not
	private boolean solved;
	// A setting for which style of sudoku board is preferred in viewing
	private int style;

	/** 
	 * Empty Matrix constructor
	 */
	public Matrix()
	{
		// The grid should be square and uninitialized
		this.grid = new int[DIMENSION][DIMENSION];
		// The given information for an empty Matrix will also be empty
		this.givenInformation = new int[DIMENSION][DIMENSION];
		// Loops to iterate over entire grid and null-initiate slots
		for (int rowIndex = 0; rowIndex < DIMENSION; rowIndex++)
		{
			for (int colIndex = 0; colIndex < DIMENSION; colIndex++)
			{
				// Initializing whole Matrix to -1's, because that will be "empty value"
				this.grid[rowIndex][colIndex] = -1;
				// The given information also does not exist, so it should be initialized to -1 too
				this.givenInformation[rowIndex][colIndex] = -1;
			}
		}
		// An empty Matrix is definitely not solved
		this.solved = false;
		// Default style
		this.style = 0;
	}

	/**
	 * Matrix constructor with known information given.
	 *
	 * @param givenInformation - known immutable information for the grid
	 */
	public Matrix(int[][] givenInformation) throws IllegalArgumentException
	{
		// to remove object dependency bc of Java storing arrays as objects
		// copying all information passed into a new array
		// givenInformation SHOULD BE CHECKED BEFORE BEING PASSED.
		// Only checking for ILLEGAL values is done here (no duplicates, no solvability, etc).
		if (givenInformation.length != DIMENSION || givenInformation.length != DIMENSION)
		{
			throw new IllegalArgumentException("Invalid array dimensions passed into public Matrix(int[][])");
		}

		// checking for illegal values in the givenInformation array
		for (int i = 0; i < DIMENSION; i++)
		{
			for (int j = 0; j < DIMENSION; j++)
			{
				if (givenInformation[i][j] < -1 || givenInformation[i][j] > 9)
				{
					throw new IllegalArgumentException("Invalid values in the array passed in public Matrix(int[][])");
				}
			}
		}

		// copies givenInformation array to avoid object dependencies
		// this also initializes the board itself, in the same loop
		this.grid = new int[DIMENSION][DIMENSION];
		int[][] newGivenInformation = new int[DIMENSION][DIMENSION];
		for (int i = 0; i < DIMENSION; i++)
		{
			for (int j = 0; j < DIMENSION; j++)
			{
				newGivenInformation[i][j] = givenInformation[i][j];
				this.grid[i][j] = newGivenInformation[i][j];
			}
		}
		
		this.givenInformation = newGivenInformation;
		this.solved = false;
		this.style = 0;

		// TBI ??
		//this.checkIfSolved();
	}

	/**
	 * An accessor for the set (unchangeable) beginning information of a potential game.
	 *
	 * @return the preset grid values
	 */
	public int[][] getGivenInformation()
	{
		// Copying because Java arrays are objects
		int[][] newGivenInformation = new int[DIMENSION][DIMENSION];
		for (int i = 0; i < DIMENSION; i++)
		{
			for (int j = 0; j < DIMENSION; j++)
			{
				newGivenInformation[i][j] = givenInformation[i][j];
			}
		}
		return newGivenInformation;
	}

	/**
	 * Gets the number (or lack thereof) stored at a location on the grid.
	 *
	 * @param row - the row index
	 * @param col - the column index
	 *
	 * @return the int value stored at those indeces
	 */
	public int getAt(int row, int col) throws IllegalArgumentException
	{
		if (row < 0 || col < 0 || row > 8 || col > 8)
		{
			throw new IllegalArgumentException("Invalid arguments in getAt(int row, int col)");
		}
		return this.grid[row][col];
	}

	/**
	 * Gets all information stored in the row selected.
	 *
	 * @param row - the row index
	 * @return - the row's data
	 */
	public int[] getRowAt(int row) throws IllegalArgumentException
	{
		if (row < 0 || row > 8)
		{
			throw new IllegalArgumentException("Invalid argument in getRowAt(int row)");
		}
		int[] rowData = new int[DIMENSION];
		for (int i = 0; i < DIMENSION; i++)
		{
			rowData[i] = this.grid[row][i];
		}
		return rowData;
	}

	/**
	 * Gets all information stored in the column selected.
	 *
	 * @param col - the column index
	 * @return - the column's data
	 */
	public int[] getColAt(int col) throws IllegalArgumentException
	{
		if (col < 0 || col > 8)
		{
			throw new IllegalArgumentException("Invalid argument in getColAt(int col)");
		}
		int[] colData = new int[DIMENSION];
		for (int i = 0; i < DIMENSION; i++)
		{
			colData[i] = this.grid[i][col];
		}
		return colData;
	}

	/**
	 * Gets all information stored in the cell selected.
	 *
	 * @param row - the row index
	 * @param col - the column index
	 * @return - the cell's data
	 */
	public int[] getCellAt(int row, int col) throws IllegalArgumentException
	{
		if (row < 0 || col < 0 || row > 8 || col > 8)
		{
			throw new IllegalArgumentException("Invalid arguments in getCellAt(int row, int col)");
		}

		int topRow = row - (row % 3);
		int leftCol = col - (col % 3);

		int usedIndeces = 0;
		int[] cellData = new int[DIMENSION];

		for (int i = topRow; i < topRow + 3; i++)
		{
			for (int j = leftCol; j < leftCol + 3; j++)
			{
				cellData[usedIndeces] = grid[i][j];
				usedIndeces++;
			}
		}

		return cellData;
	}

	/**
	 * Gets the entire grid.
	 *
	 * @return the grid's information as a 2d int array
	 */
	public int[][] getGrid()
	{
		// Copying 2d array into another array bc of Java's object-array style
		int[][] newGrid = new int[DIMENSION][DIMENSION];
		for (int i = 0; i < DIMENSION; i++)
		{
			for (int j = 0; j < DIMENSION; j++)
			{
				newGrid[i][j] = grid[i][j];
			}
		}
		return newGrid;
	}

	/**
	 * Allows you to set the given information for a matrix.
	 *
	 * @param givenInformation - the new 2d array showing given values
	 */
	public void setGivenInformation(int[][] givenInformation) throws IllegalArgumentException
	{
		if (givenInformation.length != DIMENSION || givenInformation[0].length != DIMENSION)
		{
			throw new IllegalArgumentException("Array passed into setGivenInformation is of the wrong dimensions");
		}
		// Copying 2d array to prevent dependencies in other places
		int[][] newGivenInformation = new int[DIMENSION][DIMENSION];
		for (int i = 0; i < DIMENSION; i++)
		{
			for (int j = 0; j < DIMENSION; j++)
			{
				newGivenInformation[i][j] = givenInformation[i][j];
			}
		}

		this.givenInformation = newGivenInformation;
	}

	/**
	 * Allows you to set given information at a particular index
	 *
	 * @param row - the row index, 0-8
	 * @param col - the column index, 0-8
	 * @param val - the value to be passed, 1-9
	 */
	public void setGivenInformationAt(int row, int col, int val) throws IllegalArgumentException
	{
		if (row < 0 || col < 0 || val < 1 || row > 8 || col > 8 || val > 9)
		{
			throw new IllegalArgumentException("Invalid values passed to setGivenInformationAt(int,int,int)");
		}
		this.givenInformation[row][col] = val;
		this.grid[row][col] = val;
	}

	/**
	 * Allows you to change the value stored at a location IF it is not part of the givenInformation.
	 *
	 * This is the only setter method.
	 * @param row - the row index of the value to change
	 * @param col - the column index of the value to change
	 * @param val - the new value to put into the location
	 */
	public void setAt(int row, int col, int val) throws IllegalArgumentException
	{
		// row, col, and val must be 0-9
		if (row < 0 || col < 0 || val < -1 ||
			row > 8 || col > 8 || val > 9)
		{
			throw new IllegalArgumentException("Invalid arguments in setAt(int row, int col, int val");
		}
		if (givenInformation[row][col] == -1)
		{
			grid[row][col] = val;
		}
	}

	/**
	 * Allows you to change the style of the grid.
	 *
	 * @param style - the new style chosen
	 */
	public void setStyle(int style) throws IllegalArgumentException
	{
		if (style > 1)
		{
			throw new IllegalArgumentException("Style must be 0 or 1 until more styles are implemented.");
		}
		this.style = style;
	}

	/**
	 * toString() - converts the matrix into a graphical representation depending on the style field.
	 *
	 * @return the matrix in string form
	 */
	public String toString()
	{
		// both these values depend on the style used, so they will be initialized in the switch statement
		// textLineLength represents the number of characters (except spaces for formatting) in a line
		// barrierIndeces represents the indeces where there should be lines
		int textLineLength;
		int[] barrierIndeces;
		switch (style)
		{	
			// 0 is a simplified style. Much preferred
			case 0:
				barrierIndeces = new int[] { 0, 4, 8, 12 };
				textLineLength = 13;
				break;
			// 1 is a complicated style. Optional, but not recommended by me.
			case 1:
				barrierIndeces = new int[] { 0, 1, 3, 5, 7, 8, 10, 12, 14, 15, 17, 19, 21, 22 };
				textLineLength = 23;
				break;
			// Default choice will be style 0.
			default:
				barrierIndeces = new int[] { 0, 4, 8, 12 };
				textLineLength = 13;
				break;
		}

		// realGridRowIndex and realGridColumnIndex keep track of what values in the grid we
		// are looking at at the moment.
		// matrixAsString is the variable that holds the String form of the matrix object, that will be returned
		int realGridRowIndex = 0;
		int realGridColumnIndex = 0;
		String matrixAsString = "";

		// Begin iterating through all rows of TEXT (not necessarily information)
		for (int textRow = 0; textRow < textLineLength; textRow++)
		{
			// Begin iterating through all columns of TEXT (not necessarily information)
			for (int textColumn = 0; textColumn < textLineLength; textColumn++)
			{
				// rowIsBarrier and colIsBarrier determine whether the current text row and column are just lines
				boolean rowIsBarrier = false;
				boolean colIsBarrier = false;

				// This loop compares the current rows and columns with known barrier rows and columns.
				for (int i = 0; i < barrierIndeces.length; i++)
				{
					if (textRow == barrierIndeces[i])
					{
						rowIsBarrier = true;
					}
					if (textColumn == barrierIndeces[i])
					{
						colIsBarrier = true;
					}
				}
				
				// The next three conditions determine the type of character to use in barrier rows
				if (rowIsBarrier && colIsBarrier)
				{
					matrixAsString += '+';
				}
				else if (rowIsBarrier)
				{
					matrixAsString += '-';
				}
				else if (colIsBarrier)
				{
					matrixAsString += '|';
				}

				// This condition arrives if we are looking at a valid number box.
				else
				{
					if (grid[realGridRowIndex][realGridColumnIndex] == -1)
					{
						// This represents no already known information.
						matrixAsString += ' ';
					}
					else
					{
						// This represents an actual number we've determined somehow.
						matrixAsString += Integer.toString(grid[realGridRowIndex][realGridColumnIndex]);
					}

					// This chunk of logic determines how to increment row and column indexes for the game information
					// we care about.
					if (realGridColumnIndex <= 7)
					{
						realGridColumnIndex++;
					}
					else
					{
						realGridColumnIndex = 0;
						realGridRowIndex++;
					}
				}

				// This space is necessary to keep the chart from being crushed horizontally.
				matrixAsString += ' ';
			}

			// This newline ends the current row and starts the next one.
			matrixAsString += '\n';
		}

		// Return our creation
		return matrixAsString;
	}
}

