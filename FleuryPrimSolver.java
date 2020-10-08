import java.util.*;
import java.io.*;

class FleuryPrimSolver{



	// Stores the number of nodes in a the graph
	int N;

	int graph_size;
	// Creating an adjacency Matrix
	int [][] matrix;
	ArrayList<ArrayList<Integer>> edge_in_graph;
	ArrayList<HashSet<Integer>> edges;
	ArrayList<HashMap<Integer, Boolean>> edges_avail;
	// HashSet<Integer> V_S = new HashSet<Integer>();
	ArrayList<Integer> V_S = new ArrayList<Integer>();
	Iterator node;
	ArrayList<Integer> S = new ArrayList<Integer>();


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

		init_LL();
		init_LH();
		init_LM();
		init_VS();

	}

	private void init_VS(){
		// for(int i = 0; i < N; i++)
		// 	for(int j = 0; j < N; j++)
		// 		if(matrix[i][j] == 1){
		// 			V_S.add(j);
		// 		}

		for(int i = 0; i < N; i++){
			V_S.add(i);
		}

	}
	private void init_LL(){
		// Storing all edges for each vertex

		// Initlializing data structure to store possible moves to make (edges)
		edge_in_graph = new ArrayList<ArrayList<Integer>>();

		// Initalizing arraylists for each node's set of edges
		for(int i = 0; i < N; i++)
			edge_in_graph.add(new ArrayList<Integer>());

		// Storing the neighborhood of each vertex of the graph
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(matrix[i][j] == 1){
					edge_in_graph.get(i).add(j);
				}
	}
	private void init_LH(){
		edges = new ArrayList<HashSet<Integer>>();

		// Initalizing arraylists for each node's set of edges
		for(int i = 0; i < N; i++)
			edges.add(new HashSet<Integer>());

		// Storing the neighborhood of each vertex of the graph
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(matrix[i][j] == 1){
					edges.get(i).add(j);
				}
	}
	private void init_LM(){
		edges_avail = new ArrayList<HashMap<Integer, Boolean>>();

		// Initalizing arraylists for each node's set of edges
		for(int i = 0; i < N; i++)
			edges_avail.add(new HashMap<Integer, Boolean>());

		// Storing the neighborhood of each vertex of the graph
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(matrix[i][j] == 1){
					edges_avail.get(i).put(j, true);
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

	private int size_of_graph(){
		int degree_sum = 0;

		for(int i = 0;i < N; i++)
			for(int j = 0; j < N; j++)
				if(matrix[i][j] == 1)
					degree_sum++;

		return degree_sum / 2;
	}

	// The parameters together represent an edge; more specifically, the edge to be deleted
	public boolean prims_algo_check(int current, int endPoint){
	/*
		In the Fleury's algorithm, after choosing an edge leaving the current vertex,
		run Prim's algorithm to check deleting that edge will not
		separate the graph into two disconnected sets of edges.
	*/

	/*
		S = {0,1} <-- Contains all edges from 				0: [1, 2]
																		1: [0, 3]
		V-S = {2,3,4,5,6,7,8}
	*/

/* OLD SOLUTION BUT BROKEN
		System.out.printf("prims_algo_check-->\n\tcurrent = [%d]\n\tendpoint = [%d]\n", current, endPoint);



		System.out.println(V_S.toString());
		// if(V_S.size() == 0){
		// 	System.out.println("Size == 0");
		// 	return true;
		// }
		// Adding the edge currentEndpoint will mean the next current node is endpoint
		if(V_S.contains(endPoint)){

			System.out.println("Contains");
			V_S.remove(endPoint);
			S.add(endPoint);



			for(int i = 0; i < N; i++){
				if(edges.get(endPoint).contains(i)){
					return true;
				}
			}



			V_S.add(endPoint);
			S.remove(endPoint);

			return false;
		}
		System.out.println("Does not contain");
*/

		boolean [] visited = new boolean[N];
		Arrays.fill(visited, false);
		int v;

		if(V_S.size() == 0){
			System.out.println("All vertices visited: V_S is empty");
			return true;
		}
		// if(V_S.contains(endPoint)){

			// Random rand = new Random();
			// v = rand.nextInt(N);
			//
			// int verts_visited = N;
			if(!S.contains(current))
				S.add(current);
			if(V_S.contains(current)){
				for(int k = 0; k < V_S.size(); k++){
					if(V_S.get(k) == current)
					V_S.remove(k);
				}
			}


			System.out.printf("prims_algo_check-->\n\tstartpoint = [%d]\n\tendpoint = [%d]\n", current, endPoint);
			System.out.println("V_S: " + V_S.toString());
			System.out.println("S: " + S.toString());

			// do{
			// 	int flag = 0;
			// 	for(int i = 0; i < S.size(); i++){
			// 		for(int j = 0; j < V_S.size(); j++){
			// 			if(edges.get(S.get(i)).contains(V_S.get(j))){
			// 				System.out.println("Returning True with: " + V_S.get(j) + " from: " + S.get(i));
			//
			// 				return true;
			// 			}
			// 			System.out.println("Trying again..." );
			// 		}
			// 	}
			// }while(!V_S.isEmpty());

			// S.add(endPoint);
			// for(int k = 0; k < V_S.size(); k++){
			// 	if(V_S.get(k) == endPoint)
			// 	V_S.remove(k);
			// }
			while(!V_S.isEmpty()){

				int flag = 0;
				for(int i = 0; i < S.size(); i++){
					for(int j = 0; j < V_S.size(); j++){
						if(edges.get(S.get(i)).contains(V_S.get(j)) ){
							System.out.println("Returning True with: " + V_S.get(j) + " from: " + S.get(i));
							System.out.printf("S[i]: %d\tV_S[j]: %d\n", S.get(i), V_S.get(j));
							S.add(endPoint);
							for(int k = 0; k < V_S.size(); k++){
								if(V_S.get(k) == endPoint)
								V_S.remove(k);
							}

							return true;
						}
						System.out.println("Trying again... from " +V_S.get(j) + " from: " + S.get(i) );
					}
				}
			}


			System.out.println("V_S is empty");
			return false;
		// }
		// System.out.println("Returing False");
		// return false;
		// return true;
	}

	private void print_neighborhood_graph(){

		// Printing neighborhood of each vertex
		for(int i = 0; i < N; i++)
			System.out.println(i +": " + edge_in_graph.get(i).toString());
	}
	private void print_neighborhood_set(){
		System.out.println();
		// Printing neighborhood of each vertex
		for(int i = 0; i < N; i++)
			System.out.println(i +": " + edges.get(i).toString());
		System.out.println();

	}
	private void print_neighborhood_map(){
		System.out.println();
		// Printing neighborhood of each vertex
		for(int i = 0; i < N; i++)
			System.out.println(i +": " + edges_avail.get(i).toString());
		System.out.println();

	}
	private void print_matrix(int [][] tmp){
		System.out.println();
		for(int i = 0; i < N; i++){
			System.out.print("\n" + i + ":\t");
			for(int j = 0; j < N; j++)
				System.out.print(tmp[i][j]);
		}
		System.out.println();
	}

	// Removes inverted instances of an edge in the graph
	// i.e. edge AB = edge BA, they are the same edge but are each stored in the data structure
	private void remove_edge_in_graph(int endL, int endR){
		edge_in_graph.get(endL).remove(endR);
		edge_in_graph.get(endR).remove(endL);
	}
	private void remove_edge_in_set(int endL, int endR){
		edges.get(endL).remove(endR);
		edges.get(endR).remove(endL);
	}
	private void remove_edge_in_map(int endL, int endR){
		edges_avail.get(endL).replace(endR, false);
		edges_avail.get(endR).replace(endL,false);
	}
	private void remove_edge_in_matrix(int endL, int endR, int [][] temp){
		temp[endL][endR] = 0;
		temp[endR][endL] = 0;
	}

	// All vertices in the graph have an even degree
	public void Fleuryify(int start){
		int temp [][];
		int current;
		int endPoint = 0;
		int numVertices = N;
		int flag = 0;
		ArrayList<Integer> circuit = new ArrayList<Integer>();

		temp = new int [N][N];
		temp = matrix;
		print_matrix(temp);
	
		// for(int i = 0; i < N; i++){
		// }


		graph_size = size_of_graph();

		// Start at any vertex (start vertex)
		current = start;

		circuit.add(current);
		// print_neighborhood_graph();
		// System.out.print("List: ");
		// print_neighborhood_map();
		// print_neighborhood_graph();
		// print_matrix(temp);

		// System.out.print((char)(current + 65)+ " ");
		while (graph_size > 0){
			// Choose an edge that doesnt increase connected components
				// use prims_algo()
				// print_neighborhood_set();
				// print_matrix(temp);

			// System.out.println("Current: " + (char)(current + 65));
			System.out.println("Current: " + (current));
			print_neighborhood_set();
			print_matrix(temp);
			for(int del_vert = 0; del_vert < N; del_vert++){
				// if(temp[current][del_vert] == 1 && prims_algo_check(current, del_vert)){
				// 		System.out.println("Back in Fleuify");
				// 		System.out.println();
				// 		System.out.println("-------------------------------");
				// 		endPoint = del_vert;
				//
				// 		flag = 1;
				// 		break;
				// }

				// System.out.println("Checking current row: " + current);
				// print_matrix(temp);
				if(temp[current][del_vert] == 1){
					if(prims_algo_check(current, del_vert)){

						System.out.println("Back in Fleuify");
						System.out.println();
						System.out.println("-------------------------------");
						endPoint = del_vert;

						//flag = 1;
						break;
					}
				}
				System.out.println("Miss");

			}

			// Add edge to the circuit and delete it from the graph
				circuit.add(endPoint);

				// System.out.print((char)(endPoint + 65));
				// Removes edges that have end vertices current and endPoint
				// remove_edge_in_graph(current, endPoint);
				// remove_edge_in_map(current, endPoint);

				remove_edge_in_set(current, endPoint);
				remove_edge_in_matrix(current, endPoint, temp);
				graph_size--;

				// Walking to the next vertex in the path
				current = endPoint;
			}

		// print_neighborhood_map();
		// print_neighborhood_set();
		// print_neighborhood_graph();

		// print_matrix(temp);

		System.out.println("Path: " +  circuit.toString() );
		for(Integer node : circuit){
			System.out.print((char)(node + 65));
		}

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
