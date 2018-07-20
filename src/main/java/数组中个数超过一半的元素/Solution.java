package 数组中个数超过一半的元素;

public class Solution {

    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0){
            return 0;
        }
        int result = 0;
        int cnt = 0;
        for (int a : array){
            if (cnt == 0){
                result = a;
                cnt++;
            }else if (result == a){
                cnt++;
            }else{
                cnt--;
            }
        }

        cnt = 0;
        for (int a : array){
            if (result == a){
                cnt++;
            }
        }
        System.out.println("cnt = "+cnt+", result = "+result);
        return cnt > array.length / 2 ? result : 0;
    }

    public static void main(String[] args){
        int[] array = {1,2,3,2,2,2,5,4,2};
        Solution solution = new Solution();
        int re = solution.MoreThanHalfNum_Solution(array);
        System.out.println(re);
    }
}
