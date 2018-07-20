package 数组中连续最大子数组;

public class Solution {

    public int FindGreatestSumOfSubArray(int[] array) {

        if (array == null || array.length == 0)
            return 0;
        int n = array.length;
        int[] dp = new int[n+1];
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < n+1; i++){
            dp[i] = Math.max(dp[i-1]+array[i-1], array[i-1]);
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args){
        //1,-2,3,10,-4,7,2,-5
        int[] a = {1,-2,3,10,-4,7,2,-5};
        Solution solution = new Solution();

        System.out.println(solution.FindGreatestSumOfSubArray(a));
    }
}
