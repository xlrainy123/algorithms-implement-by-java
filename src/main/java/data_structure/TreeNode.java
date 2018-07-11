package data_structure;

public class TreeNode implements Comparable<TreeNode>{

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(){ }
    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(TreeNode node){
        return this.val - node.val;
    }

    @Override
    public String toString(){
        return this.val+"";
    }
}
