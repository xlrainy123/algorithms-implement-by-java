package tree.bst;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BSTree {
    public final Logger logger = LoggerFactory.getLogger(BSTree.class);
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
        public boolean equals(Object obj){
            if (obj == null) {return false;}
            if (obj instanceof TreeNode){
                TreeNode target = (TreeNode) obj;
                return target.element.compareTo(this.element) == 0;
            }
            return false;
        }

        @Override
        public String toString(){
            return "[element:"+element+"]";
        }
    }

    /**
     * 递归
     * @param root
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable> TreeNode search_1(TreeNode root, E target){
//        System.out.println(root);
        if (root == null || target.compareTo(root.element) == 0){
            return root;
        }
        if (target.compareTo(root.element) < 0){
            return search_1(root.left, target);
        } else {
            return search_1(root.right, target);
        }
    }

    /**
     * 非递归、迭代
     * @param root
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable>TreeNode search_2(TreeNode root, E target){
        TreeNode current = root;
        while (current != null){
            if (target.compareTo(current.element) == 0){
                return current;
            }
            if (target.compareTo(current.element) < 0){
                current = current.left;
            }else {
                current = current.right;
            }
        }
        return root;
    }

    /**
     * 获得bst中的最小元素
     * @param root
     * @return
     */
    public TreeNode getMinNode(TreeNode root){
        TreeNode current = root;
        while (current.left != null){
            current = current.left;
        }
        return current;
    }

    /**
     * 获得bst中的最大元素
     * @param root
     * @return
     */
    public TreeNode getMaxNode(TreeNode root){
        TreeNode current = root;
        while (current.right != null){
            current = current.right;
        }
        return current;
    }

    /**
     * 求bst中某个节点的直接后继
     * @param start
     * @param root
     * @param <E>
     * @return
     */
    public <E extends Comparable> TreeNode successorOf(E start, TreeNode root){
        TreeNode target = null;
        if ((target = search_1(root, start)) == null){
            throw new NoSuchElementException("指定的节点"+new TreeNode<>(start)+"不存在!");
        }
        if (target.right != null) {
            return getMinNode(target.right); //target有右子树，那么后继一定是右子树的最小值
        }
        /**
         * 否则
         * 1. 没后继：target是在所有祖先点的右子树上
         * 2. 有后继：一定存在一个祖先节点，这个target是在这个祖先节点的左子树上
         */
        TreeNode parent = target.parent;
        while (parent != null) {
            if (target.equals(parent.left)) {
                return parent;
            }
            target = parent;
            parent = parent.parent;
        }
        return null;
    }

    /**
     * 求某个节点的直接前驱
     * @param start
     * @param root
     * @param <E>
     * @return
     */
    public <E extends Comparable> TreeNode preDecessorOf(E start, TreeNode root) {
        TreeNode target = search_1(root, start);
        /**
         * 如果有左子树，那么前驱就是左子树上的最大值
         */
        System.out.println(target);
        if (target.left != null){
            return getMaxNode(target.left);
        }
        /**
         * 如果没有左子树
         * 1. 有前驱：那么target的祖先节点中，target一定在某个祖先节点的右子树上
         */
        TreeNode parent = target.parent;
        System.out.println(parent);
        while (parent != null){
            if (target.equals(parent.right)){
                return parent;
            }
            target = parent;
            parent = parent.parent;
        }
        return null;
    }

    public <E extends Comparable> boolean delete(TreeNode root, E target){
        throw new UnsupportedOperationException();
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
        /**
         *                 4
         *            2         7
         *          1   3     5    9
         *                        8
         */
        System.out.println(bsTree.search_2(root, 9));
        System.out.println("getMinNode:"+bsTree.getMinNode(root));
        System.out.println("getMaxNode:"+bsTree.getMaxNode(root));
        System.out.println("successorOf(8, root):"+bsTree.successorOf(8, root));
        System.out.println("successorOf(8, root):"+bsTree.preDecessorOf(5, root));
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
