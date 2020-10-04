import java.util.*;
import java.io.*;

class FleuryPrimSolver{



	// Stores the number of nodes in a the graph
	int N;

	// Creating an adjacency Matrix
	int [][] matrix;

	// Constuctor
	public FleuryPrimSolver (String fileName, int numVertices) throws Exception{


		// Opens the passed in file and allows the filed to be read
		Scanner scan = new Scanner(new File(fileName));

		// Storing the number of vertices/nodes in the graph
		this.N = numVertices;

		// Declaring a 2-D  integer array to act as the adjacency matrix
		matrix = new int[numVertices][numVertices];


		// Reading in the text file and creating the matrix
		for (int i = 0; i < N; i++){

			// Reading in the first charater of each row. This represents the number of the row starting at 0.
			int rowNum = scan.nextInt();

			// Ignoring the tab
			scan.skip("\t");

			// Capture the entire row as a String to be parsed
			String str = scan.nextLine();

			// Setting up the string for parsing.
			// Replacing deleting all commas
			str = str.replace(",", " ");

			// Begin to parse string
			Scanner strScan = new Scanner(str);

			// Reading each edge in the text file and adding it to the adjacency matrix
			for (int j = 0; j < N; j++){
				if(strScan.hasNextInt())
					matrix[i][j] = strScan.nextInt();
				else
					strScan.skip(" ");
			}
		}
	}

	// Writes the input string (input) into the first line of the output file (filename)
	public static void writeToFile(String filename, String input){

		try{
			FileWriter writer = new FileWriter(filename);
			writer.write(input + "\n");
			writer.close();

		}catch(IOException f){
			System.out.println("Error in: findContrainedShortestPaths(" + filename +")\n Exception: " + f);
		}


	}

	// Appends the input string (input) to the output file (filename)
	public static void appendToFile(String filename, String input){
		try{
			FileWriter writer = new FileWriter(filename, true);
			writer.write(input);
			writer.write("\n");
			writer.close();
			// System.out.println("Input: " + input +" appended to File: " + filename);
			// System.out.println();
		}catch(IOException f){
			System.out.println("Error in: findContrainedShortestPaths(" + filename +")\n Exception: " + f);
		}
	}


	public void Fleuryify(int startVertex){

		System.out.println("Hello, BRenden");



	}




	public static void main(final String[] args) throws Exception {

		if (args.length < 3 ){
			System.out.println("To run this program: ");
			System.out.println("\t javac FleuryPrimSolver [filename] [source] [number of vertices in graph] ");
			// System.out.println("\t java -jar DijkstraSolver.jar [filename of graph] [source vertex] [end vertex] [number of vertices in graph] ");

		}else{


			String fileName = args[0];
			int source = Integer.parseInt(args[1]);
			// int end = Integer.parseInt(args[2]);
			int numberOfVertices = Integer.parseInt(args[2]);

			FleuryPrimSolver solver = new FleuryPrimSolver(fileName, numberOfVertices);
			solver.Fleuryify(source);
		}


	}
}
