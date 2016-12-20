package project_3_skeleton.merkle.implementation;

import merkle.IMerkleTree;
import project_3_skeleton.merkle.IServer;

import java.util.LinkedList;
import java.util.List;


/**
 * TASK 2
 * TODO: IMPLEMENT generateResponse
 *
 * @author TODO
 * @pso TODO
 * @date TODO
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
        return verificationList;
    }
}
