package data_structure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class TreeImpl implements Tree{

    private TreeNode root;
    public int height;

    public TreeImpl(TreeNode root){
        this.root = root;
        this.height = getHeight(root);
    }

    @Override
    public List<TreeNode> preOrder(boolean withRec, TreeNode currentNode){

        List<TreeNode> nodeList = new ArrayList<TreeNode>();

        if (this == null || currentNode == null){
            throw new NullPointerException("空指针异常");
        }

        preOrder(withRec, currentNode, nodeList);

        return nodeList;
    }

    public void preOrder(boolean withRec, TreeNode currentNode, List<TreeNode> nodeList){

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

    @Override
    public List<TreeNode> inOrder(TreeNode currentNode){
        return null;
    }

    @Override
    public List<TreeNode> postOrder(TreeNode currentNode){
        return null;
    }

    @Override
    public List<TreeNode> levelOrder(TreeNode currentNode){
        return null;
    }

    @Override
    public int getHeight(TreeNode currentNode){
        return 1;
    }

    @Override
    public List<TreeNode> getAllAcestors(TreeNode currentNode){
        return null;
    }

    @Override
    public TreeNode getRoot(){
        return this.root;
    }

    @Override
    public void setRoot(TreeNode newRoot){
        this.root = newRoot;
    }

    public void updateHeight(TreeNode currentNode){
        this.height = getHeight(currentNode);
    }
}
