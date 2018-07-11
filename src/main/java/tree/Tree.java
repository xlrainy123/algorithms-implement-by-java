package tree;

import java.util.List;


public interface Tree {

    List<TreeNode> preOrder(boolean withRec, TreeNode currentNode);

    List<TreeNode> inOrder(boolean withRec, TreeNode currentNode);

    List<TreeNode> postOrder(boolean withRec, TreeNode currentNode);

    List<TreeNode> levelOrder(TreeNode currentNode);

    int getHeight(TreeNode currentNode);

    List<TreeNode> getAllAncestors(TreeNode currentNode);

    TreeNode getRoot();

}
