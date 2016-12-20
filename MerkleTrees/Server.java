package merkle.implementation;

import merkle.IMerkleTree;
import merkle.IServer;

import java.util.LinkedList;
import java.util.List;


/**
 * TASK 2
 * TODO: IMPLEMENT generateResponse
 *
 * @author Vihar Patel
 * @pso P17
 * @ID patel486
 * @date 10/25/2016
 */
public class Server extends IServer {

    /**
     * Given a node to verify identified by <i>blockToTest</i>
     * which corresponds to the node received by calling <i>merkleTree.getNode(blockToTest)</i>
     * this function generates the path siblings which are required for verification
     * The returned list should contains Nodes in order from node to the root, i.e. bottom up
     */
    public List<IMerkleTree.Node> generateResponse(int blockToTest) {
        List<IMerkleTree.Node> verificationList = new LinkedList<>();
        //TODO:implement

        verificationList.add(merkleTree.getNode(blockToTest));
        IMerkleTree.Node[] tree = merkleTree.getTree();

        for(int i = blockToTest; i > 1; i /= 2)
        {

            if ((i % 2) == 0)
            {
                if((tree.length != i)) {
                    verificationList.add(merkleTree.getNode(i + 1));
                }
            }
            else {
                    verificationList.add(merkleTree.getNode(i - 1));
            }
        }

        return verificationList;
    }
}
