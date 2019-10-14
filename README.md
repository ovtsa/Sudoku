SUDOKU PUZZLE SOLVER

This software allows a user to create a Sudoku puzzle in a terminal window, and using a recursive algorithm, 
it will solve the puzzle, if it is possible, showing the number of steps taken to do so as well.

INSTALLATION

You will need JRE (Java Runtime Environment). To run the program, technically all you need is all the .class
files in "Sudoku/bin/". Download that directory, and from inside that directory, type "java Sudoku".

MODIFICATION

This is fully open source. To suggest a modification, create a branch yourself and in the commit message,
explain what changes you made, and I will review them and merge to my master branch if it is clearly beneficial.
You are also welcome to clone my project and expand upon it however you wish in your own repositories.

HOW TO USE

Text in the terminal window will guide you through the process. You will first be asked for information of the puzzle.
Then, you will be asked whether you want to see every step taken to solve the puzzle (y = yes, n = no). When it asks 
you to input a command, this is the syntax:

	done  			 	- I am finished inputting information, and would like the puzzle solved

	print 			 	- I would like to see the current state of the puzzle

	row,column,value 		- at the row and column specified, place value (row, column and value can be 1-9)
