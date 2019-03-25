package 笔试题目.剑指offer.数组中前k小的数;


import java.util.*;

public class Solution {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (input == null || input.length < k){
            return new ArrayList<>();
        }
        buildMinHeap(input);
        int n = input.length;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; ++i){
            result.add(input[0]);
            input[0] = input[--n];
            adjustDown(input, 0, n);
        }
        return result;
    }

    public void buildMinHeap(int[] input){
        int start = input.length / 2 - 1;
        for (int i = start; i >= 0; i--){
            adjustDown(input, i, input.length);
        }
    }

    public void adjustDown(int[] input, int parent, int len){
        int tmp = input[parent];
        for (int child = 2*parent+1; child < len; child = 2*child+1){
            if (child < len-1 && input[child] > input[child+1]){
                child = child+1;
            }
            if (input[child] >= tmp){
                break;
            }else{
                int t = input[child];
                input[child] = input[parent];
                input[parent] = t;
                parent = child;
            }
        }
        input[parent] = tmp;
    }


    public static void main(String[] args){

        Solution solution = new Solution();
        int[] input = {4,5,1,6,2,7,3,8};

        System.out.println(solution.GetLeastNumbers_Solution(input, 4));
    }
}
