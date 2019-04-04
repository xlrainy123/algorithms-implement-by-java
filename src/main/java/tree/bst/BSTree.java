package tree.bst;

import java.util.*;
import org.jetbrains.annotations.NotNull;

public class BSTree {

    final static class TreeNode<E extends Comparable>{
        E element;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
        TreeNode(E element){
            this.element = element;
        }
        TreeNode(E element, TreeNode left, TreeNode right){
            this.element = element;
            this.left = left;
            this.right = right;
        }
        TreeNode(E element, TreeNode left, TreeNode right, TreeNode parent){
            this.element = element;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public String toString(){
            return "[element:"+element+"]";
        }
    }

    public TreeNode insert(TreeNode root, TreeNode node){
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null){
            parent = current;
            if (node.element.compareTo(current.element) >= 0){
                current = current.right;
            }else {
                current = current.left;
            }
        }
        node.parent = parent;
        if (parent == null){
            root = node;
            return root;
        }
        if (parent.element.compareTo(node.element) > 0){
            parent.left = node;
        }else {
            parent.right = node;
        }
        return root;
    }

    public <E extends Comparable> TreeNode buildFromArray(@NotNull E[] arrs){
        TreeNode root = null;
        for (E ele : arrs){
            TreeNode current = new TreeNode(ele,null,null);
            root = insert(root,current);
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] arrs = {4,7,2,1,3,5,9,8};
        BSTree bsTree = new BSTree();
        TreeNode root = bsTree.buildFromArray(arrs);
        bsTree.preOrder(root);
//        bsTree.inOrder(root);
    }


    public void preOrder(TreeNode root){
        if (root == null){
            return;
        }
        System.out.println(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(TreeNode root){
        if (root == null){
            return;
        }
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }
}
