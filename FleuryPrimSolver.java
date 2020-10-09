import java.util.*;
import java.io.*;

class FleuryPrimSolver{

	/*-----------DEPRACATED-----------*/
	/*	private void init_VS(){
	// for(int i = 0; i < N; i++)
	// 	for(int j = 0; j < N; j++)
	// 		if(matrix[i][j] == 1){
	// 			V_S.add(j);
	// 		}

	for(int i = 0; i < N; i++){
	V_S.add(i);
}

}
*/
private void print_neighborhood_graph(){

	// Printing neighborhood of each vertex
	for(int i = 0; i < N; i++)
	System.out.println(i +": " + edge_in_graph.get(i).toString());
}
private void print_neighborhood_map(){
	System.out.println();
	// Printing neighborhood of each vertex
	for(int i = 0; i < N; i++)
	System.out.println(i +": " + edges_avail.get(i).toString());
	System.out.println();

}
private void reset_edges(){
	edges = edges_copy;
}
private void reset_V_S(){
	V_S.clear();
	for(int i = 0; i < N; i++)
	V_S.add(i);
}
private void reset_S(){
	S.clear();
}
private void remove_edge_in_graph(int endL, int endR){
	edge_in_graph.get(endL).remove(endR);
	edge_in_graph.get(endR).remove(endL);
}
private void remove_edge_in_map(int endL, int endR){
	edges_avail.get(endL).replace(endR, false);
	edges_avail.get(endR).replace(endL,false);
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
/*-----------DEPRACATED-----------*/


	// Stores the number of nodes in a the graph
	int N;

	int graph_size;
	// Creating an adjacency Matrix
	int [][] matrix;
	ArrayList<ArrayList<Integer>> edge_in_graph;
	ArrayList<HashSet<Integer>> edges;
	ArrayList<HashSet<Integer>> edges_copy;
	ArrayList<HashMap<Integer, Boolean>> edges_avail;
	// HashSet<Integer> V_S = new HashSet<Integer>();
	ArrayList<Integer> V_S = new ArrayList<Integer>();
	ArrayList<Integer> V_S_Master = new ArrayList<Integer>();
	ArrayList<Integer> S = new ArrayList<Integer>();


	ArrayList<Integer> Visited = new ArrayList<Integer>();

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
		// init_LM();
		// init_VS();

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
		edges_copy = new ArrayList<HashSet<Integer>>();

		// Initalizing arraylists for each node's set of edges
		for(int i = 0; i < N; i++)
		{
			edges.add(new HashSet<Integer>());
			edges_copy.add(new HashSet<Integer>());
		}

		// Storing the neighborhood of each vertex of the graph
		for(int i = 0; i < N; i++)
		for(int j = 0; j < N; j++)
		if(matrix[i][j] == 1){
			edges.get(i).add(j);
			edges_copy.get(i).add(j);
		}

		// edges_copy = edges;
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

	// Writes the input string (input) into the first line of the output file (filename)
	public static void writeToFile(String filename, int input){

		try{
			FileWriter writer = new FileWriter(filename);
			writer.write(input + "\n");
			writer.close();

		}catch(IOException f){
			System.out.println("Error in: FleuryPrimSolver(" + filename +")\n Exception: " + f);
		}


	}

	private static void writeArrayListToFile(String filename, ArrayList<Integer> list){
		for(Integer element : list){
			writeToFile(filename, element);
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




	//	In the Fleury's algorithm, after choosing an edge leaving the current vertex,
	//	run Prim's algorithm to check deleting that edge will not
	//	separate the graph into two disconnected sets of edges.
	// The parameters together represent an edge; more specifically, the edge to be deleted

	/*public boolean prims_algo_check(int current, int endPoint){


	int flag = 7;

	if(V_S.size() == 0){
	System.out.println("All vertices visited: V_S is empty");
	return true;
	}

	// if(!S.contains(current))
	// 	S.add(current);
	// if(V_S.contains(current)){
	// 	for(int k = 0; k < V_S.size(); k++){
	// 		if(V_S.get(k) == current)
	// 		V_S.remove(k);
	// 	}
	// }

	System.out.println("Edge removed: " + current +"->"+ endPoint);
	remove_edge_in_set(current, endPoint);
	System.out.println(edges.toString());
	System.out.println(edges_copy.toString());


	S.add(current);

	for(int k = 0; k < V_S.size(); k++)
	if(V_S.get(k) == current)
	V_S.remove(k);

	System.out.println("V_S: " + V_S.toString());
	System.out.println("S: " + S.toString());
	print_neighborhood_set();
	while(!V_S.isEmpty()){
	// System.out.println("w");

	flag = 0;
	for(int i = 0; i < S.size(); i++){
	for(int j = 0; j < V_S.size(); j++){
	if(edges.get(S.get(i)).contains(V_S.get(j)) ){
	// flag = 1;
	// System.out.println("Returning True with: " + V_S.get(j) + " from: " + S.get(i));
	// System.out.printf("S[i]: %d\tV_S[j]: %d\n", S.get(i), V_S.get(j));
	// remove_edge_in_set(S.get(i), V_S.get(j));
	// // nextCurr = V_S.get(j);
	// S.add(V_S.get(j));
	// for(int k = 0; k < V_S.size(); k++){
	// 	if(V_S.get(k) == V_S.get(j))
	// 	V_S.remove(k);
	// }
	// break;
	}
	}
	}


	}
	System.out.println("hrer");

	if(flag == 0){
	System.out.println("its 0");
	//add back edge
	// reset edge arraylist
	// edges = edges_copy;
	reset_S();
	reset_V_S();
	reset_edges();

	return false;
	}



	System.out.println("not 0");
	System.out.println("Removing: " + current+"->"+endPoint);
	edges_copy.get(endPoint).remove(current);
	edges_copy.get(current).remove(endPoint);

	// edges = edges_copy;
	reset_S();
	reset_V_S();
	reset_edges();
	// return true;}
	*/


	// Clears the S set and then adds the starting vertex for prim's
	private void init_S(int y){
		S.clear();
		S.add(y);
	}

	// Adds a new element to the S set
	private void update_S(int vertex){
		S.add(vertex);
	}


	private void init_V_S_Master(){
		for(int i = 0; i < N ; i++)
			V_S_Master.add(i);
	}

	// Removes a node that has a degree of 0, this node will no longer be considered for a prim's algo
	private void update_V_S_Master(int x){
		for(int i = 0; i < N; i++){
			if(V_S_Master.get(i) == x){
				V_S_Master.remove(i);
				return;
			}
		}
	}


	// Initializes the V_S set and updates the current available nodes (removes nodes that have a degree of 0)
	private void init_V_S(int y){
		V_S.clear();
		V_S = (ArrayList)V_S_Master.clone();
	}

	// Removes an element from V_S
	private void update_V_S(int y){
		for(int i = 0; i < N; i++){
			if(V_S.get(i) == y){
				V_S.remove(i);
				return;
			}
		}

	}


	// Initalizes the Visited array list
	private void init_Visited(){
		for(int i = 0; i < N; i++)
		Visited.add(i);
	}

	// Returns true if all the vertices have been visited
	private boolean all_verts_visited(){
		return (Visited.size() == 0);
	}

	// Removes a node from considered vertices if that node has a degree of 0
	private void isDegreeZero(int x){
		// Checks the position of edge [X,Y] if node has a degree of 0

		// Nodes that have a degree of 0 should no longer be considered in Prims's algorithm
		if(edges.get(x).size() == 0){
			update_V_S_Master(x);
			return;
		}

		return;
	}

	private void print_neighborhood_set(){
		System.out.println();
		// Printing neighborhood of each vertex
		for(int i = 0; i < N; i++)
		System.out.println(i +": " + edges.get(i).toString());
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
	private void remove_edge_in_set(int endL, int endR){
		edges.get(endL).remove(endR);
		edges.get(endR).remove(endL);

		// Mark node as visited
		Visited.remove(endL);
	}
	private void remove_edge_in_matrix(int endL, int endR, int [][] temp){
		temp[endL][endR] = 0;
		temp[endR][endL] = 0;
	}

	// Adds an edge back to edges and unvisits a node
	private void addBack(int x, int y){
		edges.get(x).add(y);
		edges.get(y).add(x);

		Visited.add(x);
	}

	private boolean foundLink(int i, int j){
		return (edges.get(S.get(i)).contains(V_S.get(j)));
	}

	private boolean y_is_start(int y, int start){
		return (y == start);
	}

	public boolean prims_algo_check(int x, int y, int start){

		int flag = -1;
		System.out.printf("Removing edge: [x,y]->[%d,%d]\n", x, y);

		init_stuff(x,y);
		while(!all_verts_visited()){
			remove_edge_in_set(x,y);
			isDegreeZero(x);

			if(y_is_start(y, start) && all_verts_visited())
				return true;
			else if (y_is_start(y, start)){
				addBack(x,y);
				continue;
			}

			init_S();
			init_V_S();

			while(!V_S.isEmpty()){
				for(int i = 0; i < S.size(); i++){
					flag = 0;
					for(int j = 0; j < V_S.size(); j++){
						if(foundLink(i,j)){
							doStuff();
							flag = 1;
							break;
						}
					}
					if (flag == 1)
						break;
				}
				if(flag == 0)
					return false;
			}
		}
		return true;
	}






	// All vertices in the graph have an even degree
	public void Fleuryify(int start){
		int temp [][];
		int current;
		int endPoint = -1;
		int numVertices = N;
		int flag = 0;
		ArrayList<Integer> circuit = new ArrayList<Integer>();

		temp = new int [N][N];
		temp = matrix;

		graph_size = size_of_graph();

		// Start at any vertex (start vertex)
		current = start;

		circuit.add(current);

		while (graph_size > 0){
			// Choose an edge that doesnt increase connected components
			System.out.println("Current: " + (current));
			print_neighborhood_set();

			for(int del_vert = 0; del_vert < N; del_vert++){
				if(temp[current][del_vert] == 1 && prims_algo_check(current, del_vert, start)){
					endPoint = del_vert;
					break;
				}
			}

			// Add edge to the circuit and delete it from the graph
			circuit.add(endPoint);
			remove_edge_in_matrix(current, endPoint, temp);
			current = endPoint;

			graph_size--;
		}

		System.out.println("Path: " +  circuit.toString() );
		for(Integer node : circuit){
			System.out.print((char)(node + 65));
		}

		// writeArrayListToFile("output.txt", circuit);

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


/*
class FleuryPrimSolver{

// Stores the number of nodes in a the graph
int N;

int graph_size;
// Creating an adjacency Matrix
int [][] matrix;

ArrayList<HashSet<Integer>> edges;
ArrayList<Integer> S = new ArrayList<Integer>();
ArrayList<Integer> V_S = new ArrayList<Integer>();

//	Constuctor
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

init_VS();
init_LH();
}




private void init_VS(){
for(int i = 0; i < N; i++){
V_S.add(i);
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


// Writes the input string (input) into the first line of the output file (filename)
public static void writeToFile(String filename, int input){

try{
FileWriter writer = new FileWriter(filename);
writer.write(input + "\n");
writer.close();

}catch(IOException f){
System.out.println("Error in: FleuryPrimSolver(" + filename +")\n Exception: " + f);
}


}

private static void writeArrayListToFile(String filename, ArrayList<Integer> list){
for(Integer element : list){
writeToFile(filename, element);
}
}

// Returns the number of edges in the graph
private int size_of_graph(){
int degree_sum = 0;

for(int i = 0;i < N; i++)
for(int j = 0; j < N; j++)
if(matrix[i][j] == 1)
degree_sum++;

return degree_sum / 2;
}


private boolean prims(int startPoint, int endPoint){

return true;
}
private void Fleuryify(int startPoint){

ArrayList<Integer> circuit = new ArrayList<Integer>();

int graph_size = size_of_graph();
int currNode = startPoint;
int newStep = -1;



// System.out.println(V_S.toString());
// System.out.println(edges.toString());

while ((graph_size) > 0){
// System.out.println("graph_size: " + graph_size);


for(int i = 0; i < N ; i++){
System.out.println(edges.get(i).toString());
}


circuit.add(newStep);
graph_size--;
// System.out.println(circuit.toString());
writeArrayListToFile("output.txt", circuit);
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
*/
