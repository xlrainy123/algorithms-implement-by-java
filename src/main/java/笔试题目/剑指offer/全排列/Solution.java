package 笔试题目.剑指offer.全排列;

import java.util.ArrayList;
import java.util.*;


public class Solution {
    /**
     * str中不存在相同的字符
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        if (str == null || "".equals(str)) return new ArrayList<>();
        char[] chs = str.toCharArray();
        boolean[] visited = new boolean[chs.length];
        ArrayList<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(chs, 0, result, sb, visited);
        return result;
    }

    public void dfs(char[] chs, int start, ArrayList<String> result, StringBuilder sb, boolean[] visited){
        if (sb.length() == chs.length){
            result.add(new String(sb));
            return;
        }
        if (sb.length() > chs.length)
            return;
        for (int i = 0; i < chs.length; i++){
            if (visited[i] == true)
                continue;
            if (i > 0 && chs[i] == chs[i-1] && visited[i-1] != true)
                continue;
            visited[i] = true;
            sb.append(chs[i]);
            dfs(chs, start+1, result, sb, visited);
            sb.deleteCharAt(sb.length()-1);
            visited[i] = false;
        }
    }

    /**
     * 包含重复的元素
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {

        Set<List<Integer>> result = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        dfs(result, path, nums, 0, visited);

        return new ArrayList<>(result);
    }

    public void dfs(Set<List<Integer>> result, List<Integer> path, int[] nums, int pos, boolean[] visited){
        if (pos == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (!visited[i]){
                visited[i] = true;
                path.add(nums[i]);
                dfs(result, path, nums, pos+1, visited);
                path.remove(path.size()-1);
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args){
        Solution solution = new Solution();
        List<String> res = solution.Permutation("101");
        System.out.println(res);

        int[] nums = {2,3,4};
        List<Object> list = new ArrayList<>(Arrays.asList(nums));
        System.out.println(list);
    }
}