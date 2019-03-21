package pdd;
import java.util.*;

public class 最大乘积 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        List<Long> nums = new ArrayList<>();
        for (int i = 0; i < len; i++){
            nums.add(in.nextLong());
        }
        Long max1 = Long.MIN_VALUE;
        Long max2 = Long.MIN_VALUE;
        Long max3 = Long.MIN_VALUE;
        Long min1 = Long.MAX_VALUE;
        Long min2 = Long.MAX_VALUE;
        for (Long a : nums){
            if (a > max3){
                max1 = max2;
                max2 = max3;
                max3 = a;
            } else if (a > max2){
                max1 = max2;
                max2 = a;
            } else if(a > max1){
                max1 = a;
            }else{

            }
            if (a < min1){
                min2 = min1;
                min1 = a;
            }else if (a < min2){
                min2 = a;
            }
        }
        System.out.println(max1*max2*max3 > max3*min1*min2 ?
                max1*max2*max3 : max3*min1*min2);
    }
}
