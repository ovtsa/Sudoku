SUDOKU PUZZLE SOLVER

This software allows a user to create a Sudoku puzzle in a terminal window, and using a recursive algorithm, 
it will solve the puzzle, if it is possible, showing the number of steps taken to do so as well.

INSTALLATION

You will need JRE (Java Runtime Environment). Simply download all the .class files, and in a terminal window,
type "java Sudoku" without the quotation marks to run the program.

MODIFICATION

This is fully open-source, and any branches and modifications are appreciated.

HOW TO USE

Text in the terminal window will guide you through the process. You will first be asked for information of the puzzle.
Then, you will be asked whether you want to see every step taken to solve the puzzle (y = yes, n = no). When it asks 
you to input a command, this is the syntax:

	done  			 - I am finished inputting information, and would like the puzzle solved

	print 			 - I would like to see the current state of the puzzle

	row,column,value - at the row and column specified, place value (row, column and value can be 1-9)
