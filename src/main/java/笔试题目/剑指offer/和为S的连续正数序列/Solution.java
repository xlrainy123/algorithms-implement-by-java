package 笔试题目.剑指offer.和为S的连续正数序列;

import java.util.*;


public class Solution {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer> > result = new ArrayList<>();
        if (sum < 3)
            return result;
        int low = 1;
        int high = 2;

        while (low < (sum+1)/2 && low < high){
            ArrayList<Integer> list = new ArrayList<>();
            int current = getSum(low, high);
            if (current == sum){
                put2list(low, high, list);
                result.add(list);
                low++;
            }else if (current > sum){
                low++;
            }else{
                high++;
            }
        }
        return result;
    }

    public void put2list(int low, int high, List<Integer> list){
        for (int i = low; i <= high; i++){
            list.add(i);
        }
    }

    public int getSum(int low, int high){
        int sum = 0;
        for (int i = low; i < high+1; i++){
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.FindContinuousSequence(40));

        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.print(i == j); //false
    }
}