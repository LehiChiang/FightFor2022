package out.bd;

import datastructure.TreeNode;
import org.junit.jupiter.api.Test;

class OutBDTreeTest {

    @Test
    void preOrderTraverse() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5,6,7");
        OutBDTree.PreOrderTraverse(root);
    }

    @Test
    void inOrderTraverse() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5,6,7");
        OutBDTree.InOrderTraverse(root);
    }

    @Test
    void postOrderTraverse() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5,6,7");
        OutBDTree.PostOrderTraverse(root);
    }

    @Test
    void layerTraverse() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5,6,7");
        OutBDTree.LayerTraverse(root);
    }

    @Test
    void maxNodeNumsInLayer() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5,6,7,null,null,8,null,null,9,null,null");
        System.out.println(OutBDTree.MaxNodeNumsInLayer(root));
        ;
    }
}