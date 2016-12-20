import java.util.*;
import java.lang.*;

/*		Part 1
 * 
 * 		TODO: Implement the following method stubs
 * 		
 * 		medianDegree
 * 		hasGiantConnectedComponent
 * 		is5Clique
 * 		has6DegreeRootN
 * 		has6Degree6
 * 	    TODO: VIHAR PATEL
 *      TODO: P17 (12/2/2016)
 *      TODO: references: Princeton library
 */
public class Part1 {

    boolean[] marked;
//	int id[];
//	int size[];
//	int count;
//	int check = 0;
//	public void variables(Graph G){
//		marked = new boolean[G.getNumVertices()];
//		id = new int[G.getNumVertices()];
//		size = new int[G.getNumVertices()];
//		for(int i = 0; i < G.getNumVertices(); i++)
//		{
//			if(!marked[i]) {
//				dfs(G, i);
//				count++;
//			}
//		}
//	}

	// TODO:
	// Find the median degree of all vertices in G
	public double medianDegree(Graph G)
	{
//		int[] degrees = new int[G.numVertices];
//		for(int i = 0; i < G.numVertices; i++)
//		{
//			degrees[i] = G.degree(i);
//		}
//
//		Arrays.sort(degrees);
//		double median;
//		if(degrees.length % 2 == 0)
//			median = ((double)degrees[degrees.length/2] + (double)degrees[degrees.length/2 - 1])/2;
//		else
//			median = ((double)degrees[degrees.length/2]);
//		return median;
		return 0;
	}

//	private void dfs(Graph G, int v) {
//		if(check == 0)
//			variables(G);
//		check++;
//		marked[v] = true;
//		id[v] = count;
//		size[count]++;
//		for (int w : G.getAdjacentVertices(v)) {
//			if (!marked[w]) {
//				dfs(G, w);
//			}
//		}
//	}
	/**
	 * Computes the vertices connected to the source vertex {@code s} in the graph {@code G}.
	 * @param G the graph
	 * @param s the source vertex
	 * @throws IllegalArgumentException unless {@code 0 <= s < V}
	 */
	public LinkedList<Integer> NonrecursiveDFS(Graph G, int s) {
		marked = new boolean[G.getNumVertices()];
		LinkedList<Integer> pathV = new LinkedList<>();

		// to be able to iterate over each adjacency list, keeping track of which
		// vertex in each adjacency list needs to be explored next
		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.getNumVertices()];
		for (int v = 0; v < G.getNumVertices(); v++)
			adj[v] = G.getAdjacentVertices(v).iterator();

		// depth-first search using an explicit stack
		Stack<Integer> stack = new Stack<Integer>();
		marked[s] = true;
		stack.push(s);
		pathV.add(s);
		while (!stack.isEmpty()) {
			int v = stack.peek();
			if (adj[v].hasNext()) {
				int w = adj[v].next();
				// StdOut.printf("check %d\n", w);
				if (!marked[w]) {
					// discovered vertex w for the first time
					marked[w] = true;
					// edgeTo[w] = v;
					stack.push(w);
					pathV.add(w);
					// StdOut.printf("dfs(%d)\n", w);
				}
			}
			else {
				// StdOut.printf("%d done\n", v);
				stack.pop();
			}
		}
		return pathV;
	}
	
	// TODO:
	// Determines if G has a connected component of size greater than or equal to ceil(n/2)
	// If it does, return a linked list contining the vertices in the giant connected component
	// Returns an empty list if there is no giant connected component
	public LinkedList<Integer> hasGiantConnectedComponent(Graph G)
	{
		double Giant = Math.ceil((double)(G.getNumVertices()/2));
		LinkedList<Integer> path;
		for(int i = 0; i < G.numVertices; i++)
		{
			path = NonrecursiveDFS(G,i);
			if(path.size() >= Giant)
				return path;
		}

		return null;
	}
	
	
	
	// TODO:
	// Returns true if the given 5 vertices in the list form a clique
	// Returns false otherwise
	public boolean is5Clique(Graph G, LinkedList<Integer> vertices)
	{
		Iterator<Integer> loop = vertices.iterator();

		for(int i : vertices)
		{
			LinkedList<Integer> adj = G.getAdjacentVertices(i);
			for(int j : vertices)
			{
				if(!adj.contains(j) && j != i)
					return false;
			}
		}
		return true;
	}
	
	
	
	// TODO:
	// Determines if the given graph has at least 6 vertices of degree 
	// greater than or equal to floor(sqrt(n))
	// If so, return a linked list containing 6 vertices of degree greater than or equal to
	// floor(sqrt(n))
	// Return an empty list if there are not at least 6 vertices with the required degree
	public LinkedList<Integer> has6DegreeRootN(Graph G)
	{
//		LinkedList<Integer> list = new LinkedList<Integer>();
//		int counter = 0;
//		for(int i = 0; i < G.numVertices; i++)
//		{
//			if(G.degree(i) >= Math.floor(Math.sqrt(G.numVertices)))// && list.size() != 6)
//			{
//				list.add(i);
//				counter++;
//				if(counter == 6)
//					break;
//			}
//		}
//		if(list.size() == 6) return list;
//		return new LinkedList<>();
		return null;
	}
	
	
	
	// TODO:
	// Determines if the graph has at least 6 vertices of degree less than 6
	// If so, return a linked list containing 6 vertices that satisfy the condition
	// If not, return an empty linked list.
	public LinkedList<Integer> has6Degree6(Graph G)
	{
//		LinkedList<Integer> list = new LinkedList<Integer>();
//		int counter = 0;
//		for(int i = 0; i < G.numVertices; i++)
//		{
//			if(G.degree(i) < 6)// && list.size() != 6)
//			{
//				list.add(i);
//				counter++;
//				if(counter == 6)
//					break;
//			}
//		}
//		if(list.size() == 6) return list;
//		return new LinkedList<Integer>();
		return null;
	}
}
