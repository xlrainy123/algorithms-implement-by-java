package tree;

import utils.Utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeImpl implements Tree {

    private TreeNode root;
    public int height;

    public TreeImpl(TreeNode root){
        this.root = root;
        this.height = getHeight(root);
    }

    /**
     * 先序遍历
     * @param withRec：可以控制是否采用递归
     * @param currentNode
     * @return
     */
    @Override
    public List<TreeNode> preOrder(boolean withRec, TreeNode currentNode){

        List<TreeNode> nodeList = new ArrayList<TreeNode>();

        if (this == null || this.root == null){
            throw new NullPointerException("树为空");
        }

        preOrder(withRec, currentNode, nodeList);

        return nodeList;
    }

    private void preOrder(boolean withRec, TreeNode currentNode, List<TreeNode> nodeList){

        //递归
        if ( withRec ){
            if (currentNode != null){
                nodeList.add(currentNode);
                preOrder(withRec, currentNode.left, nodeList);
                preOrder(withRec, currentNode.right, nodeList);
            }
        }else { //非递归
            ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
            TreeNode pNode = currentNode;
            while(!stack.isEmpty() || pNode != null){
                if (pNode != null){
                    nodeList.add(pNode);
                    stack.push(pNode);
                    pNode = pNode.left;
                }else {
                    pNode = stack.pop();
                    pNode = pNode.right;
                }
            }
        }

    }

    /**
     * 中序遍历
     * @param withRec：可以控制是否采用递归
     * @param currentNode
     * @return
     */
    @Override
    public List<TreeNode> inOrder(boolean withRec, TreeNode currentNode){

        List<TreeNode> nodeList = new ArrayList<>();

        if (this.root == null){
            throw new NullPointerException("树为空");
        }

        inOrder(withRec, currentNode, nodeList);

        return nodeList;
    }

    private void inOrder(boolean withRec, TreeNode currentNode, List<TreeNode> nodeList){
        if (withRec){
            if (currentNode != null){
                inOrder(withRec, currentNode.left, nodeList);
                nodeList.add(currentNode);
                inOrder(withRec, currentNode.right, nodeList);
            }
        }else {
            ArrayDeque<TreeNode> stack = new ArrayDeque<>();
            TreeNode pNode = currentNode;
            while (! stack.isEmpty() || pNode != null){
                if (pNode != null){
                    stack.push(pNode);
                    pNode = pNode.left;
                }else {
                    pNode = stack.pop();
                    nodeList.add(pNode);
                    pNode = pNode.right;
                }
            }
        }
    }

    /**
     * 后序遍历
     * @param withRec：可以控制是否采用递归
     * @param currentNode
     * @return
     */
    @Override
    public List<TreeNode> postOrder(boolean withRec, TreeNode currentNode){

        List<TreeNode> nodeList = new ArrayList<>();

        if (this.root == null){
            throw new NullPointerException("树为空");
        }

        postOrder(withRec, currentNode, nodeList);

        return nodeList;
    }

    private void postOrder(boolean withRec, TreeNode currentNode, List<TreeNode> nodeList){

        if (withRec){
            System.out.println("递归遍历");
            if (currentNode != null){
                postOrder(withRec, currentNode.left, nodeList);
                postOrder(withRec, currentNode.right, nodeList);
                nodeList.add(currentNode);
            }
        }else {
            System.out.println("非递归遍历");
            ArrayDeque<TreeNode> stack = new ArrayDeque<>();
            TreeNode pNode = currentNode, preVisited = null;
            while (!stack.isEmpty() || pNode != null){
                if (pNode != null){
                    stack.push(pNode);
                    pNode = pNode.left;
                }else {
                    pNode = stack.peekFirst();
                    if (pNode.right != null && pNode.right != preVisited){
                        pNode = pNode.right;
                        stack.push(pNode);
                        pNode = pNode.left;
                    }else {
                        pNode = stack.pop();
                        nodeList.add(pNode);
                        preVisited = pNode;
                        pNode = null;
                    }
                }
            }
        }
    }


    /**
     * 层序遍历
     * @param currentNode
     * @return
     */
    @Override
    public List<TreeNode> levelOrder(TreeNode currentNode){

        if (this.root == null){
            throw new NullPointerException("树为空");
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        List<TreeNode> nodeList = new ArrayList<>();
        TreeNode pNode = currentNode;
        queue.offer(pNode);
        while (! queue.isEmpty()){
            pNode = queue.poll();
            nodeList.add(pNode);
            if (pNode.left != null){
                queue.offer(pNode.left);
            }
            if (pNode.right != null){
                queue.offer(pNode.right);
            }
        }

        return nodeList;
    }

    /**
     * 求二叉树的高度， 可以有递归和非递归两种求法
     * @param currentNode
     * @return
     */
    @Override
    public int getHeight(TreeNode currentNode){
        return getHeight(currentNode, false);
    }

    public int getHeight(TreeNode currentNode, final boolean useLevel){
        if (!useLevel){
            if (currentNode == null){
                return 0;
            }
            return Math.max(getHeight(currentNode.left, useLevel), getHeight(currentNode.right, useLevel)) + 1;
        }else {
            int height = 0;
            Queue<TreeNode> queue = new ArrayDeque<>();
            TreeNode pNode = currentNode;
            queue.offer(pNode);
            while (! queue.isEmpty()){
                int size = queue.size();
                for (int i = 0; i < size; i++){
                    pNode = queue.poll();
                    if (pNode.left != null){
                        queue.offer(pNode.left);
                    }
                    if (pNode.right != null){
                        queue.offer(pNode.right);
                    }
                }
                height++;
            }
            return height;
        }
    }

    /**
     * 求某个节点的所有祖先节点，利用后续遍历可以得到
     * @param child
     * @return
     */
    @Override
    public List<TreeNode> getAllAncestors(TreeNode child){

        if (child == root){
            return new ArrayList<>(0);
        }

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
     //   List<TreeNode> nodeList = new ArrayList<>();
        TreeNode pNode = root;
        TreeNode preVisited = null;
        while(pNode != null || !stack.isEmpty()){
            if (pNode != null){
                stack.push(pNode);
                pNode = pNode.left;
                if (pNode != null && pNode == child){
                    return new ArrayList<>(stack);
                }
            }else {
                pNode = stack.peekFirst();
                if (pNode.right != null && pNode.right != preVisited){
                    pNode = pNode.right;
                    if (pNode != null && pNode == child){
                        return new ArrayList<>(stack);
                    }
                    stack.push(pNode);
                    pNode = pNode.left;
                    if (pNode != null && pNode == child){
                        return new ArrayList<>(stack);
                    }
                }else {
                    pNode = stack.pop();
                    preVisited = pNode;
                    pNode = null;
                }
            }
        }
        throw new NullPointerException("child节点不存在");
    }


    public TreeNode getNestestAncestor(TreeNode root, TreeNode one, TreeNode two){

        if (root == null || one == root || two == null){
            return root;
        }

        List<TreeNode> nodes1 = getAllAncestors(one);
        List<TreeNode> nodes2 = getAllAncestors(two);
        if (nodes1.size() < nodes2.size() && nodes2.contains(one)){
            return one;
        }
        if (nodes1.size() > nodes2.size() && nodes1.contains(two)){
            return two;
        }

        int index1 = nodes1.size() - 1, index2 = nodes2.size() - 1;
        TreeNode preEqualNode = null;
        for ( ; index1 >= 0 && index2 >= 0; ){
            if (nodes1.get(index1) == nodes2.get(index2)){
                preEqualNode = nodes1.get(index1);
                index1 --;
                index2 --;
            }else {
                break;
            }
        }
        return preEqualNode;
    }

    @Override
    public TreeNode getRoot(){
        return this.root;
    }

    /**
     * 更新二叉树的高度属性
     * @param currentNode
     */
    public void updateHeight(TreeNode currentNode){
        this.height = getHeight(currentNode);
    }
}
