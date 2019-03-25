package 笔试题目.剑指offer.将数组中的奇数偶数分离;

public class Solution {

    //这种方法是不稳定的算法
    public void reOrderArray(int[] array) {
        /**
        int low = 0, high = array.length - 1;
        while (low < high){
            while(low < high && array[low] % 2 == 1){
                low++;
            }
            while(low < high && array[high] % 2 == 0){
                high--;
            }
            if (low < high){
                int t = array[low];
                array[low] = array[high];
                array[high] = t;
            }
        }
        **/
        int[] tmp = new int[array.length];
        int i = 0;
        for (int a : array){
            if (a % 2 == 1){
                tmp[i++] = a;
            }
            System.out.println(tmp[i-1]);
        }
        for (int a : array){
            if (a % 2 == 0){
                tmp[i++] = a;
            }
            System.out.println(tmp[i-1]);
        }
        array = tmp;
    }

}
