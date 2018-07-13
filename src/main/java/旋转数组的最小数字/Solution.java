package 旋转数组的最小数字;

public class Solution {

    public int minNumberInRotateArray(int [] array) {

        if (array.length < 2){
            return array.length;
        }
        if (array.length == 2){
            return array[0] < array[1] ? array[0] : array[1];
        }

        int low = 0, high = array.length - 1;
        while (high - low > 1){
            int mid = (low + high) / 2;
            if (array[mid] < array[high]){
                high = mid;
            }else{
                low = mid;
            }
        }
        return array[low] < array[high] ? array[low] : array[high];
    }
}
