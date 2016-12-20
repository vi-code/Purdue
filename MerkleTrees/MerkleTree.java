package merkle.implementation;

import merkle.Configuration;
import merkle.IMerkleTree;

import java.io.*;

import static merkle.Configuration.blockSize;
import static merkle.Configuration.hashFunction;

/**
 * TASK 1
 * TODO: IMPLEMENT build
 *
 * @author Vihar Patel
 * @id patel486@purdue.edu
 * @pso P17
 * @date 10/21/2016
 */
public class MerkleTree extends IMerkleTree {


    /**
     * Given an <i>inputFile</i> this function builds a Merkle Tree and return the <i>masterHash</i>
     * <i>this.tree</i> is the array representation of the tree which you need to create
     * You can use <i>Configuration.hashFunction</i>
     * The basic code to read a file block wise is provided. You can choose to use it.
     * The tree should be 1-indexed
     */
    @Override
    public String build(File inputFile) throws Exception {
        int blocks = (int) Math.ceil((double) inputFile.length() / Configuration.blockSize);

        tree = new Node[2 * blocks];//Initialize this with a proper size (Size of tree is twice the number of blocks]
        tree[0] = new Node("dummy", 0);//Zeroth dummy node

        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(inputFile))) {
            byte[] byteArray = new byte[blockSize];
            int readStatus;
            int temp = blocks;
            while ((readStatus = reader.read(byteArray)) != -1) {
                String block = padBytes(byteArray, readStatus);
                //TODO:implement

                //Filling the leaves
                tree[temp] = new Node(Configuration.hashFunction.hashBlock(block), temp);
                temp++;
            }


            //Filling the internal nodes to complete the tree
            for (int i = tree.length-1; i > 1; i-=2)
            {
                tree[i/2] = new Node(Configuration.hashFunction.concatenateHash(tree[i-1], tree[i]), i/2); // Parent node = concatenateHash(leftNode, rightNode, pos)
            }
        }

        String masterHash = tree[1].getHash(); //to initialize
        return masterHash;
    }
}
