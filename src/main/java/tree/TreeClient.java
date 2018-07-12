package tree;

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


        Utils.println("先序遍历");
        List<TreeNode> nodeList = tree.preOrder(true, tree.getRoot());
        Utils.println(nodeList);

        nodeList.clear();
        Utils.println("中序遍历");
        nodeList = tree.inOrder(false, tree.getRoot());
        Utils.println(nodeList);
        Utils.println("后序遍历");
        nodeList.clear();
        nodeList = tree.postOrder(false, tree.getRoot());
        Utils.println(nodeList);

        nodeList.clear();
        nodeList = tree.levelOrder(tree.getRoot());
        Utils.println(nodeList);

        Utils.println(tree.getRoot());
        Utils.println(tree.getHeight(tree.getRoot(), true));

        Utils.println(tree.getAllAncestors(tree.getRoot().right.right.left));
        Utils.println(tree.getAllAncestors(tree.getRoot().left.left));
        Utils.println(tree.getNestestAncestor(tree.getRoot(), tree.getRoot().right.right.left, tree.getRoot().right.left, true));

        int[] pre = new int[]{1, 2, 4, 5, 3, 6, 7, 8};
        int[] in = new int[]{4, 2, 5, 1, 6, 3, 8, 7};

        TreeImpl t = tree.reConstructBinaryTree(pre, in);
        Utils.println("测试的后序遍历");
        nodeList.clear();
        nodeList = t.postOrder(false, tree.getRoot());
        Utils.println(nodeList);
    }
}
