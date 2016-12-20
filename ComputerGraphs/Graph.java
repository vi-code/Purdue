/*		Graph.java
*		This abstract class represents the basic functions required to work with a graph
*		Specific implementations of this abstract class are ListGraph and MatrixGraph
*
*		You may edit this file, including adding additional methods as you see fit
*       This file must be submitted
*       TODO: VIHAR PATEL
* 		TODO: P17 (12/2/2016)
* 		TODO: references: Princeton library
*/

import java.util.Scanner;


public abstract class Graph
{
	/* Constructor
	*
	*	Constructs a graph by reading in edges from standard input
	*/


	protected int numVertices;

	public Graph(Scanner input)
	{
		numVertices = input.nextInt();
	}


	abstract boolean hasEdge(int u, int v);

	abstract void addEdge(int u, int v);

	abstract void removeEdge(int u, int v);
	
	abstract LinkedList<Integer> getAdjacentVertices(int v);




	public int getNumVertices()
	{
		return numVertices;
	}

	abstract int degree(int v);
}
