package client.data_structure;

import data_structure.TreeImpl;
import data_structure.TreeNode;
import utils.Utils;

import java.util.List;

public class TreeClient {

    public TreeNode createTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node7.left = node8;
        return node1;
    }

    public static void main(String[] args){
        TreeClient client = new TreeClient();

        TreeNode node = client.createTree();
        TreeImpl tree = new TreeImpl(node);

        List<TreeNode> nodeList = tree.preOrder(true, tree.getRoot());
        Utils.print(nodeList);
    }
}
