/*	MatrixGraph.java
*
*	Implements an adjacency matrix version of a graph
*	Implements the abstract class Graph
*	
*	You must submit this file
* TODO: VIHAR PATEL
* TODO: P17 (12/2/2016)
* TODO: references: Princeton library
*/

import java.util.*;

public class MatrixGraph extends Graph
{
	private int[][] adjMatrix;

	public MatrixGraph(Scanner input)
	{
		super(input);
		adjMatrix = new int[numVertices][numVertices];

		for(int i = 0; i < numVertices; i++)
		{
			String line = input.nextLine();
			if(line.equals(""))
			{
				i--;
				continue;
			}
			String[] edges = line.split(" ");
			for(int j = 1; j < edges.length; j++)
			{
				this.addEdge(i,Integer.parseInt(edges[j]));
			}
			}
		}

		public void addEdge(int u, int v)
		{
			if(u >= 0 && u < numVertices && v > 0 && v < numVertices) {
				adjMatrix[u][v] = 1;
				adjMatrix[v][u] = 1;
			}

		}



		public boolean hasEdge(int u, int v)
		{
			return (adjMatrix[u][v] == 1 && adjMatrix[v][u] == 1);
		}

		public void removeEdge(int u, int v)
		{
			if(u >= 0 && u < numVertices && v > 0 && v < numVertices)
			{
				adjMatrix[u][v] = 0;
				adjMatrix[v][u] = 0;
			}
	}

	LinkedList<Integer> getAdjacentVertices(int v) {
		LinkedList<Integer> adjVertices = new LinkedList<>();
		for(int i = 0; i < adjMatrix[v].length; i++) {
			if(adjMatrix[v][i] == 1)
				adjVertices.add(i);
		}
		return adjVertices;
	}

	public int degree(int v){
		return getAdjacentVertices(v).size();
	}

}
