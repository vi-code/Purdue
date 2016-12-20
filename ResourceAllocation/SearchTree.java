/*Search Tree class
 * Your implementation goes in this file
 */
import java.util.*;

public class SearchTree {

	private Node root; //The root of the RB Tree
	private JobTable jobs; //The jobTable
	//The following variables are used for measuring utilities, do not change them!
	private int[] machineLoads;
	private int numOfMachine;
	private int scheduled;
	private int requests;
	//self declared variables
	RedBlackBST b;
	private Node w = null;
	private List<Node> best;
	//You can add any other variables here if needed

	//Create a balanced search tree consisting of all the machines initially empty
	public SearchTree(int space, int numOfMachine) {
		jobs = new JobTable();
		scheduled = requests = 0;
		this.numOfMachine = numOfMachine;
		machineLoads = new int[numOfMachine];
		machineLoads[0] = 0;
		root = new Node(0, space, 0);
		for (int i = 1; i < numOfMachine; i++){
			insertNewNode(i, space, 0);
			machineLoads[i] = 0;
		}
	}

	public SearchTree(JobTable jt) {
		jobs = jt;
	}

	public void insertNewNode(int machine, int free, int numjobs){
		root = RedBlackBST.insert(root, new Node(machine,free, numjobs));
	}

	//TODO: All parts needed for you implementation are in functions listed below:

	//Find the machine with just enough free space to schedule a job
	//Update the free size and number of jobs on the machine
	//Return machine id... -1 if no such machine exists
	public int scheduleJobMinSpace(int jobid, int size) {
		Node m;
		requests++;
		/* Do not modify the code above */
		/* TODO: Start your implementation here, find m to schedule */

		if(!(count(size) == 0))
		{
			Node r = root;
			//m = copyNode(root);
			int min = 0;

			LinkedList<Node> list = new LinkedList();
			int index = 0; // need this little goober
			while (r != null) //Adds the nodes in the path to a linked list
			{

				if(r.free > size)
				{
					list.add(r);
					r = r.left;
				}
				else if(r.free < size)
				{
					list.add(r);
					r = r.right;
				}
				else if(r.free == size)
				{
					//m.free = m.free - size;
					//Node assignedMachine = jobs.jobMachine(jobid);
					Node tbInsert = copyNode(r);
					root = RedBlackBST.delete(root, r); //deleting the node
					tbInsert.free -= size;
					tbInsert.numjobs++;
					root = RedBlackBST.insert(root, tbInsert); // inserting the updating node

					jobs.addJob(jobid, size, tbInsert);
					m = copyNode(tbInsert);
					scheduled++;
					machineLoads[m.id]++;
					return m.id;
				}
			}
			List<Integer> differences = new ArrayList<>();
			for(int i = 0; i < list.size(); i++) //stores the differences of every element in the list with the size
			{
				min = list.get(i).free - size;
				differences.add(min);
			}

			min = Integer.MAX_VALUE; //Using same variable because I can't come up with names :(
			boolean max_val = false; //At this point my brain stops functioning. Excuse the silliness


			for (int i = 0; i < list.size(); i++) { //finds the smallest element
				if(min > differences.get(i) && differences.get(i) > 0) {
					min = differences.get(i);
				}
				//Edge Case, if all numbers are negative and a MAX value is present
				//if(differences[i] == Integer.MAX_VALUE)
				//	max_val = true;
			}

			//if(min == Integer.MAX_VALUE && !max_val)
			//	return -1;

			//m = list.get(java.util.Arrays.asList(differences).indexOf(min));
			//index = find(differences, min);
			m = list.get(differences.indexOf(min));
			Node tbInsert = copyNode(m);
			root = RedBlackBST.delete(root,m); //deleting the node
//			tbInsert.addJob(jobs.jobSize(jobid));
			tbInsert.free -= size;
			tbInsert.numjobs++;
			root = RedBlackBST.insert(root, tbInsert); // inserting the updating node
			jobs.addJob(jobid, size, tbInsert);
			m = copyNode(tbInsert);
		}
		else
			return -1;

		/* Do not modify the following part */
		scheduled++;
		machineLoads[m.id] ++;
		return m.id;

	}


	//Find the machine with enough free space and minimum number of jobs to schedule a job
	//Update the free size and number of jobs on the machine
	//Return machine id... -1 if no such machine exists
	public int scheduleJobMinJob(int jobid, int size) {

		Node m;
		requests++;
		/* TODO: Start your implementation here: Find node m to schedule the job  */

		Node bestOption = null;
		m = root;
		m = scheduleJobMinJob(m, size, bestOption);
		if(m == null)
			return -1;

		Node tbInsert = copyNode(m);
		root = RedBlackBST.delete(root, m); //deleting the node
		tbInsert.free -= size;
		tbInsert.numjobs++;
		root = RedBlackBST.insert(root, tbInsert); // inserting the updating node
		jobs.addJob(jobid, size, tbInsert);
		m = copyNode(tbInsert);



		/* Do not modify the following part */
		machineLoads[m.id] ++;
		scheduled++;
		return m.id;
		//return -1; //TO DELETE
	}

	public Node scheduleJobMinJob(Node u, int size, Node bestOption)
	{
		if (u == null)
			return bestOption;

		else if(u.free <= size)
		{
			if(u.right == null) {
//				best.add(bestOption);
				return bestOption;
			}
			u = u.right;
			return scheduleJobMinJob(u, size, bestOption);

		}
		//else if(u.free > size)
		else
		{
			if(u.right == null)
				w = u;
			else
			{
				if (u.numjobs < u.right.minJobsNode.numjobs)
				{
					w = u;
				} else if(u.numjobs > u.right.minJobsNode.numjobs)
				{
					w = u.right.minJobsNode;
				}
				else
				{
					if(u.free < u.right.minJobsNode.free)
						w = u;
					else if(u.free > u.right.minJobsNode.free)
						w = u.right.minJobsNode;
					else
					{
						if(u.id <= u.right.minJobsNode.id)
							w = u;
						else
							w = u.right.minJobsNode;
					}
				}
			}

			if (bestOption == null || w.numjobs <= bestOption.numjobs)
			{
				bestOption = w;
			}

			u = u.left;
			return scheduleJobMinJob(u, size, bestOption);
		}
	}

	//Update the free space and number of jobs on machine releasing a job
	public void releaseJob(int jobid) {

		Node m = jobs.jobMachine(jobid);
		/* TODO: Release m */
		if(m == null)
			return;

		//Search for m in tree
		Node tbInsert = copyNode(m);
		root = RedBlackBST.delete(root, m);
		//m.removeJob(jobs.jobSize(jobid));
		tbInsert.free += jobs.jobSize(jobid);
		tbInsert.numjobs--;
		root = RedBlackBST.insert(root, tbInsert);
		jobs.deleteJob(jobid, tbInsert); //delete job from hashtable

		machineLoads[m.id]--;
	}

	//Return the number of machines that have atleast given free space
	public int count(int free){
		
		/* TODO: start your implementation here */
		//int numberOfMachines = 0;
		Node r = root;
		int numberOfMachines = count(free, r);
		return numberOfMachines;
	}

	//Helper function that takes node and free as parameters (Function overloading lol)
	public int count(int free, Node node) {
		if(node == null)
			return 0;

		int diff = node.free - free; // Can be 0 (equal), -1 (node.free is greater), +1 (node.free is smaller)
		if (diff == 0)
		{
			return b.size(node.right);
		}
		else if (diff > 0)
			return  1 + b.size(node.right) + count(free, node.left);
		else
			return count(free, node.right);

	}

	/*
	* DO NOT EDIT THE FOLLOWING FUNCTION
	* IT IS INVOLVED IN MEASURING THE UTILITIES FOR EXPERIMENTAL SECTION
	*/
	public void measureUtility(){
		double ideal = 0.0;
		double medianload = 0.0;
		ArrayList<Integer> loads = new ArrayList<Integer>();
		int size = 0;
		for (int i = 0; i < numOfMachine; i ++){
			int load = machineLoads[i];
			loads.add(load);
			size+=load;
		}

		int len = loads.size();

		Collections.sort(loads);
		if (size%2 == 0) {
			medianload = loads.get(len/2);
		}
		else {
			medianload = (loads.get(len/2) + loads.get(len/2+1))/2;
		}

		System.out.println(size);
		ideal = size/(double)numOfMachine;
		double fairness = medianload/ideal;
		double thoroughput = scheduled/(double)requests;
		System.out.format("Fairness: %f, Thoroughput: %f\n",fairness,thoroughput);
	}
	/*
	* DO NOT EDIT THE FUNCTION ABOVE
	*/
	//Deep copies the node and shit
	public static Node copyNode(Node original) {
		//Node copy = new Node(original.id, original.free, original.numjobs);
		Node copy = new Node(0, 0, 0);
		copy.free = original.free;
		copy.id = original.id;
		copy.numjobs = original.numjobs;
		return copy;
	}

	//prints the tree
	public void printTree(Node root) {
		if(root!=null)
		{
			printTree(root.left);
			System.out.print(root.free + " ");
			printTree(root.right);
		}
	}
	//Redundant
	public int find(int[] array, int value) {
		for(int i=0; i<array.length; i++)
		{
			if(array[i] == value)
				return i;
			else return -1;
		}
		return -1;
	}
}
