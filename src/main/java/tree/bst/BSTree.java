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

    private boolean isLeaf (TreeNode node){
        if (node == null) {
            throw new NoSuchElementException("节点为空");
        }
        return node.left == null && node.right == null;
    }

    /**
     * 自己写的delete，还存在一些问题
     * @param root
     * @param target
     * @param <E>
     * @return
     */
    public <E extends Comparable> boolean delete(TreeNode root, E target){
        System.out.println("target: "+target);
        System.out.println("root: "+root);
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null ){
            if (target.compareTo(current.element) > 0){
                parent = current;
                current = current.right;
            } else if (target.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            } else {
                System.out.println("break current:"+current);
                break;
            }
        }
        if (current == null){
            System.out.println(parent);
            throw new NoSuchElementException("待删除节点"+new TreeNode<>(target)+"不存在!");
        }
        System.out.println("current:"+current);
        System.out.println("parent:"+parent);
        /**
         * 待删除的节点没有孩子节点，那么直接删除就可以
         */
        if (isLeaf(current)){
            if (parent.left == current){
                parent.left = null;
            } else {
                parent.right = null;
            }
            current.parent = null;
            return true;
        }
        /**
         * 待删除的节点只有一个孩子，那么把这个孩子提升到待删除节点的位置代替它
         */
        boolean isLeft = parent.left == current ? true : false;         //删除的节点是根节点的时候，这个parent == null
        if ((current.right == null && current.left != null) ||
                (current.left == null && current.left == null)){
            TreeNode child = current.left != null ? current.left : current.right;
            if (isLeft){
                parent.left = child;
            }else {
                parent.right = child;
            }
            child.parent = parent;
            current.parent = null;
            current.left = null;
            current.right = null;
            return true;
        }

        /**
         * 待删除的节点有两个孩子节点
         */
        TreeNode successor = successorOf(current.element, root);
        System.out.println("successor:"+successor);
        current.element = successor.element;
        System.out.println("successor.element:"+successor.element);
        //如果这个后继是待删除节点的右孩子的话
        if (current.right == successor){
            current.right = successor.right;
            successor.right.parent = current;
            successor.parent = null;
            successor.right = null;
            return true;
        }
        //如果这个后继不是待删除节点的右孩子的话
        //这个后继的父亲一定在待删除节点的右子树上，并且，这个后继一定是父亲的左孩子，后继一定只有右孩子
        TreeNode preParent = successor.parent;
        System.out.println("preParent: "+preParent);
        if (successor.right != null){
            successor.right.parent = preParent;
            preParent.left = successor.right;
            successor.parent = null;
            successor.right = null;
        } else {
            preParent.left = null;
            successor.parent = null;
            successor.right = null;
        }
        return true;
    }

    public <E extends Comparable> TreeNode deleteByAlgorithmInductionWithEle(TreeNode root, E target){
        return deleteByAlgorithmInductionWithNode(root, search_1(root, target));
    }

    public TreeNode deleteByAlgorithmInductionWithNode(TreeNode root, TreeNode target){
        if (target.left == null){
           return transplate(root, target, target.right);
        }
        if (target.right == null){      // 只有左子树，没有右子树
            return transplate(root, target, target.left);
        }
        TreeNode directSuccessor = getMinNode(target.right);
        target.element = directSuccessor.element;
        return deleteByAlgorithmInductionWithNode(root, directSuccessor);
    }

    private void nulled(TreeNode target){
        target.parent = null;
        target.left = null;
        target.right = null;
    }

    private TreeNode transplate(TreeNode root, TreeNode transplated, TreeNode transplate){
        if (transplated.parent == null){
            root = transplate;
        } else if (transplated.parent.left == transplated){
            transplated.parent.left = transplate;
        } else {
            transplated.parent.right = transplate;
        }
        if (transplate != null){
            transplate.parent = transplated.parent;
        }
        return root;
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
//        System.out.println(bsTree.search_2(root, 9));
//        System.out.println("getMinNode:"+bsTree.getMinNode(root));
//        System.out.println("getMaxNode:"+bsTree.getMaxNode(root));
//        System.out.println("successorOf(8, root):"+bsTree.successorOf(8, root));
//        System.out.println("successorOf(8, root):"+bsTree.preDecessorOf(5, root));
        TreeNode node = bsTree.search_1(root,7);
        root = bsTree.deleteByAlgorithmInductionWithEle(root, 7);
        bsTree.preOrder(root);
        System.out.println();
        bsTree.inOrder(root);
        System.out.println();
        System.out.println(node);
        System.out.println(node.parent);
        System.out.println(node.left);
        System.out.println(node.right);
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
