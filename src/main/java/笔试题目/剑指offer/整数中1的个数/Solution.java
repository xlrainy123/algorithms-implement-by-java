package 笔试题目.剑指offer.整数中1的个数;

public class Solution {

    public int NumberOf1Between1AndN_Solution(int n) {

        if (n <= 0) return 0;
        if (n < 10) return 1;
        /**
         * 当前位 == 0， 更高位的数字 * 当前位的权重
         * 当前位 == 1， 更高位的数字 * 当前位的权重 + 低位数字 + 1
         * 当前位 > 1, （更高位的数字+1) * 当前位的权重
         */
        // n = 234
        long currentWeight = 1;
        long cnt = 0;
        for ( ; currentWeight <= n; currentWeight = currentWeight*10){
            long low = n % currentWeight;
            long t = n / currentWeight;
            long current = t % 10;
            long high = t / 10;
            System.out.println("current:"+current+", low:"+low+", high:"+high+",currentWeight:"+currentWeight);
            if (current == 0){
                cnt += high*currentWeight;
            }else if (current == 1){
                cnt += high*currentWeight;
                cnt += (low + 1);
            }else {
                cnt += (high+1) * currentWeight;
            }
        }
        return (int)cnt;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.NumberOf1Between1AndN_Solution(10));
    }
}
