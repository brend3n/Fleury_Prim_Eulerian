import java.util.*;
import java.io.*;

class FleuryPrimSolver{



	// Stores the number of nodes in a the graph
	int N;

	// Creating an adjacency Matrix
	int [][] matrix;
	ArrayList<ArrayList<Integer>> edge_in_graph;

	// Constuctor
	public FleuryPrimSolver (String fileName, int numVertices) throws Exception{


		// Opens the passed in file and allows the filed to be read
		Scanner scan = new Scanner(new File(fileName));

		// Storing the number of vertices/nodes in the graph
		this.N = numVertices;

		// Declaring a 2-D  integer array to act as the adjacency matrix
		matrix = new int[numVertices][numVertices];

		// Not sure what this is for
		int [] edgeCount = new int[N];


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



		// Storing all edges for each vertex

		// Initlializing data structure to store possible moves to make (edges)
		edge_in_graph = new ArrayList<ArrayList<Integer>>();

		// Initalizing arraylists for each node's set of edges
		for(int i = 0; i < N; i++)
			edge_in_graph.add(new ArrayList<Integer>());

		// Storing the neighborhood of each vertex of the graph
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(matrix[i][j] == 1)
					edge_in_graph.get(i).add(j);

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

	private int size_of_graph(){
		int degree_sum = 0;

		for(int i = 0;i < N; i++)
			for(int j = 0; j < N; j++)
				if(matrix[i][j] == 1)
					degree_sum++;

		return degree_sum / 2;
	}

	public ArrayList<Integer> prims_algo(){

		int v;
		int [] cost = new int[N];
		boolean [] edge = new boolean[N];
		// ArrayList<Integer> vertex_set = new ArrayList<Integer>();
		PriorityQueue<Integer> vertex_set = new PriorityQueue<Integer>();
		ArrayList<Integer> forest = new ArrayList<Integer>();

		Arrays.fill(cost, 99999);
		// System.out.print(Arrays.toString(cost));
		Arrays.fill(edge, false);
		// System.out.print(Arrays.toString(edge));

		// Initialize the starting vertex here
		// edge[]

		while(!vertex_set.isEmpty()){
			// Get vertex with the smallest cost

			// Add vertex into forest
				// if the edge of the vertex is not in the forest
					// Add it to the forest

			// Loop through all vertices connected to the current vertex
			for(int i = 0; i < N; i++){

				// if some other vertex, w, belongs to the vertex_set and vw < cost[w]
					// cost[w] = cost of the edge vw
					// edge[w] = edge vw
			}
		}

		return forest;
	}

	// All vertices in the graph have an even degree
	public void Fleuryify(int start){
		int current;
		int edge;
		int numVertices = N;
		ArrayList<Integer> circuit = new ArrayList<Integer>();


		int graph_size = size_of_graph();
		System.out.print("graph_size: " + graph_size);

		// Start at any vertex (start vertex)
		current = start;

		// Printing neighborhood of each vertex
		for(int i = 0; i < N; i++)
			System.out.println(i +": " + edge_in_graph.get(i).toString());




/*
		while (!edge_in_graph.isEmpty()){



			// Choose an edge that doesnt increase connected components
				// use prims_algo()
			// edge = prims_algo(current);

			// Add edge to the circuit and delete it from the graph
				// circuit.add(edge);
				// edge_in_graph.remove(edge);
		}
*/

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
