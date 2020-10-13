## FleuryPrimSolver

# Author: Brenden Morton
# Course: Network Optimization
# Professor: Dr. Wei Zhang



# 	This is an implementation of Fleury's algorithm to find an Euler circuit at a specifed starting node in a graph. In this implementation, Prim's algorithm is used
# 	to determine if an edge disconnects the graph for the implementation of Fleury's algorithm.





# To run this program on the command line, do the following:

#	1. Navigate to the directory where the FleuryPrimSolver.jar file is located using 'cd' commands

#	2. Run the following command:
				
#				java -jar FleuryPrimSolver.jar [filename of the graph] [start vertex] [total number of vertices]

# The input file should be a square adjancency matrix of integers separated by commas. A '0' represents no edge between two nodes.
# Additionally, the leftmost character of each row should be an integer representing the row. After, this character should be a '\t' tab character.
# Below is a simple example of what the input file should look like:

#				0   0, 3, 0, 6
#				1   5, 9, 0, 0
#				2   0, 8, 0, 0
#				3   8, 1, 3, 0



# After running the program, you will receive an output in the terminal that says the Euler circuit was written to an output file

# The program outputs the Euler circuit found starting at the specified start node to a file called "output.txt"
# The format of the output file is:

#  startVertex, ... , startVertex (Path taken from start vertex to end vertex)


# Here is an example:

# 12, 2, 1, 89, ..., 23, 10, 12



# Thank you for using this program!!

# Brenden Morton
