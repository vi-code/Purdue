package merkle.implementation;

import merkle.Configuration;
import merkle.IClient;
import merkle.IMerkleTree;
import merkle.IServer;

import java.util.List;

/**
 * TASK 2
 * TODO: IMPLEMENT verifyResponse
 *
 * @author Vihar Patel
 * @ID patel486
 * @pso P17
 * @date 10/25/2016
 */
public class Client extends IClient {

    /**
     * Given the path siblings this function has to verify if
     * the masterHash generated by concatenating and hashing
     * level by level is the same as <i>this.masterHash</i>
     * You can use <i>Configuration.hashFunction</i>
     */
    protected boolean verifyResponse(List<IMerkleTree.Node> pathSiblings) throws Exception {
        //TODO:implement
        boolean matched = false;
        String concat;

        IMerkleTree.Node temp = pathSiblings.get(0);
        int index = temp.getIndex();
        if(index%2 != 0)
        {
            concat = Configuration.hashFunction.concatenateHash(pathSiblings.get(1), pathSiblings.get(0));
        }
        else
        {
            concat = Configuration.hashFunction.concatenateHash(pathSiblings.get(0), pathSiblings.get(1));
        }
        index = index/2;
        IMerkleTree.Node nextNode = new IMerkleTree.Node(concat, index);

        for(int i = 2; i < pathSiblings.size(); i++)
        {
            if(index%2 != 0)
            {
                index /=2;
                nextNode = new IMerkleTree.Node(Configuration.hashFunction.concatenateHash(pathSiblings.get(i), nextNode), index);
            }

            else
            {
                index /=2;
                nextNode = new IMerkleTree.Node(Configuration.hashFunction.concatenateHash(nextNode, pathSiblings.get(i)), index);
            }
        }

        if(this.masterHash.equals(nextNode.getHash()));
            matched = true;

        return matched;
    }
}