import sun.awt.image.ImageWatched;

import java.util.*;
import java.lang.*;
/*		Part 2
 * 
 * 		TODO: Implement the following methods
 *              
 *      maxInDegree
 *      maxOutDegree
 *      hasOneCycle 
 * 		numEdgeTriangles
 * 		numTriangles
 * 		vertexClusterCoeff
 * 		globalClusterCoeff
 * 		TODO: VIHAR PATEL
 *      TODO: P17 (12/2/2016)
 *      TODO: references: Princeton library
 * 
 */
public class Part2 {

//	private boolean[] marked;    // marked[v] = has vertex v been marked?
//	private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
//	private boolean[] onStack;       // onStack[v] = is vertex on the stack?
	    // directed cycle (or null if no such cycle)
//	private
//	// TODO:
	// Returns the maximum in-degree in the graph G
	public int maxInDegree(Graph G)
	{
		int max;
		int in[] = new int[G.getNumVertices()];
		LinkedList<Integer> adj = new LinkedList<Integer>();
		for(int i = 0; i < G.getNumVertices(); i++)
		{
			adj = G.getAdjacentVertices(i);
			for(int j : adj)
			{
				in[j]++;
			}
		}
		max = in[0];
		for(int i = 0; i < in.length; i++)
		{
			if(in[i] > max)
			{
				max = in[i];
			}
		}
		return max;
	}
	
	
	
	// TODO:
	// Returns the maximum out degree in the graph G
	public int maxOutDegree(Graph G)
	{
		int max;
		int out[] = new int[G.getNumVertices()];
		for(int i = 0; i < G.getNumVertices(); i++)
		{
			out[i] = G.getAdjacentVertices(i).size();
		}
		max = out[0];
		for(int i = 0; i < out.length; i++)
		{
			if(out[i] > max)
			{
				max = out[i];
			}
		}
		return max;
	}

	
	
	// TODO:
	// Determines if a graph has only one cycle
	// Returns a linked list containing the vertices in the cycle if there was only one cycle
	// Returns an empty linked list if there are none or more than one cycle
	public LinkedList<Integer> hasOneCycle(Graph G)
	{
		return new LinkedList<Integer>();
	}
//	public LinkedList<Integer> NonrecursiveDirectedCycle(Graph G) {
//		LinkedList<Integer> finalCycle = new LinkedList<>();
//		int[] edgeTo = new int[G.getNumVertices()];            // edgeTo[v] = previous vertex on path to v
//		boolean[] marked = new boolean[G.getNumVertices()];    // marked[v] = has vertex v been marked?
//		boolean[] onStack = new boolean[G.getNumVertices()];   // onStack[v] = is vertex on the stack?
//		Stack<Integer> stack = new Stack<Integer>();
//		Stack<Integer> cycle;
//
//		// to be able to iterate over each adjacency list, keeping track of which
//		// vertex in each adjacency list needs to be explored next
//		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.getNumVertices()];
//		for (int v = 0; v < G.getNumVertices(); v++)
//			adj[v] = G.getAdjacentVertices(v).iterator();
//
//		for (int s = 0; s < G.getNumVertices(); s++) {
//			if (!marked[s]) {
//				onStack[s] = true;
//				marked[s] = true;
//				stack.push(s);
//				while (!stack.isEmpty()) {
//					int v = stack.peek();
//					if (adj[v].hasNext()) {
//						int w = adj[v].next();
//						if (!marked[w]) {
//							// discovered vertex w for the first time
//							marked[w] = true;
//							edgeTo[w] = v;
//							stack.push(w);
//							onStack[w] = true;
//						}
//						// trace back directed cycle
//						else if (onStack[w]) {
//							cycle = new Stack<Integer>();
//							for (int x = v; x != w; x = edgeTo[x]) {
//								cycle.push(x);
//							}
//							cycle.push(w);
//							cycle.push(v);
//							//assert check();
//							return new LinkedList<Integer>();
//						}
//					}
//					else {
//						// v's adjacency list is exhausted
//						int vCopy = stack.pop();
//						assert v == vCopy;
//						onStack[v] = false;
//					}
//				}
//			}
//		}
//	}


	// TODO:
	// Returns the total number of triangles in the entire graph G that contain the edge (u,v)
	public int numEdgeTriangles(Graph G, int u, int v)
	{
		LinkedList<Integer> adjacentToU = G.getAdjacentVertices(u);
		LinkedList<Integer> adjacentToV = G.getAdjacentVertices(v);
		int[] au = new int[adjacentToU.size()];
		int[] av = new int[adjacentToV.size()];
		int counter = 0;
		for(int i = 0; i < au.length; i++)
		{
			au[i] = adjacentToU.removeLast();

		}
		for(int j = 0; j < av.length; j++)
		{
			av[j] = adjacentToV.removeLast();

		}
		for(int i = 0; i < au.length; i++)
		{
			for(int j = 0; j < av.length; j++)
			{
				if(au[i] == av[j])
					counter++;
			}
		}
		return counter;
	}
	

	// TODO:
	// Returns the total number of triangles in the graph
	public int numTriangles(Graph G)
	{
		LinkedList<Integer> friends;
		LinkedList<Integer> friendsOfAdj;
//		LinkedList<Integer>
		int counter = 0;
		String s1;
		double sum = 0.0;
		boolean[] flag = new boolean[G.getNumVertices()];
//		for(int i = 0; i < G.getNumVertices(); i++){
//			flag[i] = false;
//		}
		LinkedList<Integer> last = new LinkedList<>();
		LinkedList<String> triangles = new LinkedList<>();
		for(int x = 0; x < G.getNumVertices(); x++)
		{
			String s;
			friends = G.getAdjacentVertices(x);
			for (int i: friends) {
				friendsOfAdj = G.getAdjacentVertices(i);
				for (int j : friendsOfAdj) {
					last = G.getAdjacentVertices(j);

					int[] tri = {i, j, x};
					int[] temp = new int[3];
					Arrays.sort(tri);
					s1 = Arrays.toString(tri);
					boolean check = last.contains(x) && !(friends.equals(last));
					if (check && !(triangles.contains(s1))){
						counter++;
//						flag[x] = true;
//						flag[j] = true;
//						flag[i] = true;
						temp[0] = x;
						temp[1] = i;
						temp[2] = j;
						Arrays.sort(temp);
						s = Arrays.toString(temp);
						triangles.add(s);



					}
				}
			}
		}

		return triangles.size();

	}

	
	
	// TODO:
	// Returns the vertex clustering coefficient of v
	// The vertex clustering coefficient is the fraction of pairs of vertices
	// that are adjacent to v that are also adjacent to each other.
	public double vertexClusterCoeff(Graph G, int v)
	{
		LinkedList<Integer> friends;
		LinkedList<Integer> friendsOfAdj;
//		LinkedList<Integer>
		int counter = 0;
		double sum = 0.0;
		friends = G.getAdjacentVertices(v);
		for (int i: friends) {
			friendsOfAdj = G.getAdjacentVertices(i);
			for (int j : friendsOfAdj) {
				boolean check = friends.contains(j);
				if (check && !(j == i) && !(j == v)) {
					counter++;
				}
			}
		}

		if((((friends.size()-1)*friends.size())/2)==0)
			return 0;
		//System.out.println("Counter: "+(double)(counter/2)+"\n Size: "+ (((friends.size()-1)*friends.size())/2));
		return ((double)(counter/2)/(((friends.size()-1)*friends.size())/2));
	}
	

	// TODO:
	// Returns the average of all n vertices clustering coefficients
	public double globalClusterCoeff(Graph G)
	{
		double sum = 0.0;
		for(int i = 0; i < G.getNumVertices(); i++)
		{
			sum += vertexClusterCoeff(G, i);

		}

		return sum/(double)G.getNumVertices();
	}

}
