package project_3_skeleton.merkle.implementation;

import merkle.Configuration;
import merkle.IMerkleTree;

import java.io.*;

import static merkle.Configuration.blockSize;
import static merkle.Configuration.hashFunction;

/**
 * TASK 1
 * TODO: IMPLEMENT build
 *
 * @author TODO
 * @pso TODO
 * @date TODO
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

        tree = new Node[0];//Initialize this with a proper size
        tree[0] = new Node("dummy", 0);//Zeroth dummy node

        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(inputFile))) {
            byte[] byteArray = new byte[blockSize];
            int readStatus;

            while ((readStatus = reader.read(byteArray)) != -1) {
                String block = padBytes(byteArray, readStatus);
                //TODO:implement
            }
        }

        String masterHash = null;//to initialize
        return masterHash;
    }
}
