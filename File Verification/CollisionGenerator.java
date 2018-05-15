package merkle.implementation;

import merkle.Configuration;
import merkle.ICollisionGenerator;
import merkle.IMerkleTree;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;



public class CollisionGenerator extends ICollisionGenerator {

    /**
     * Given a <i>merkleTree</i> this function needs to
     * generate a file which will generate the merkleTree
     * The file then has to be dumped to <i>outputFile</i>
     * Basic code for writing blocks to a file is provided.
     */
    @Override
    public void generateCollision(File outputFile, IMerkleTree merkleTree) throws Exception {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            byte[] bytes = new byte[Configuration.blockSize];
            //TODO:implement
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        }
    }

}
