package 二叉树的路径之和等于指定值;

import tree.TreeNode;
import java.util.*;

public class Solution {

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        if (root == null){
            return result;
        }
        dfs(root, target, result, path, 0);
        return result;
    }

    public void dfs(TreeNode root, int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path, int currentSum){
        TreeNode pNode = root;
        if (pNode != null){
            path.add(pNode.val);
            currentSum += pNode.val;
            if (target - currentSum == 0 && pNode.left == null && pNode.right == null){
                result.add(new ArrayList<>(path));
            }
            dfs(root.left, target, result, path, currentSum);
            dfs(root.right, target, result, path, currentSum);
            path.remove(path.size()-1);
        }
    }
}
