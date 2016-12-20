package merkle.implementation;

import merkle.hash.HashFunction;

/**
 * TASK 3
 * TODO: IMPLEMENT hash
 *
 * @author Vihar Patel
 * @pso P17
 * @ID patel486
 * @date 10/25/2016
 */
public class SumHash implements HashFunction {

    /**
     * This function returns an integer (cast to a string) which is
     * the sum of all the bytes in the <i>input</i> String
     * You can cast bytes to integer using <i>(int)</i>
     * You can cast integers to String using <i>String.valueOf</i>
     */
    @Override
    public String hashBlock(String input) throws Exception {
        int hash = 0;
        //TODO:implement
        byte[] b = input.getBytes();
        for(int i = 0; i < b.length; i++)
        {
            hash += b[i];
        }
        return String.valueOf(hash);
    }

    @Override
    public String concatenateHash(MerkleTree.Node leftNode, MerkleTree.Node rightNode) throws Exception {
        return String.valueOf(Integer.parseInt(leftNode.getHash()) + Integer.parseInt(rightNode.getHash()));
    }
}
