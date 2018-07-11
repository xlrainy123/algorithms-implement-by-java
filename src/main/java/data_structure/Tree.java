package data_structure;

import java.util.List;


public interface Tree {

    List<TreeNode> preOrder(boolean withRec, TreeNode currentNode);

    List<TreeNode> inOrder(TreeNode currentNode);

    List<TreeNode> postOrder(TreeNode currentNode);

    List<TreeNode> levelOrder(TreeNode currentNode);

    int getHeight(TreeNode currentNode);

    List<TreeNode> getAllAcestors(TreeNode currentNode);

    TreeNode getRoot();

    void setRoot(TreeNode newRoot);
}
