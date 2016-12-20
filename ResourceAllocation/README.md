# Resource Allocation Handler using Red-Black binary search tree and a hash table

Two strategies used:

* Min-Job
* Min-Space

### Usage:

1. Running TestTree.java

    -h or -help: list all the options and their format
    
    -m [N] [M]: Allocate N machines, each with a maximum free space of size M.
    
    -s [strategy]: Scheduling strategy used, either minSpace or minJob
    

2. Running QueryGenerator.java

    -h or -help: list all the options and their format
    
    -s [n] [maxload] Schedule n jobs, each job takes up to maxload space. Loads are created randomly from 1 to maxload. Note that maxload can be larger than M.
    
    -c Enable generating counting query. When this flag is on, after every 0.1*n schedule queries, a counting query is generated. A random argument M is generated from [1, 2M].
    
    -r Enable random release query. When this flag is on, a random release query is created with certain probability.
    
    -m Enable machine utilization query. When this flag is on, after every 0.1*n schedule queries, a machine utilization query is executed.
    
