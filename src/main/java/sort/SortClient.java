package sort;

import utils.Utils;

import java.util.Arrays;
import java.util.Collections;

public class SortClient {
    static int[] arr = {10,5,8,5,9};
    public static void main(String[] args){
        SortImp sortImp = new SortImp();
        sortImp.quickSort(arr);
        for (int a : arr){
            Utils.print(a+",");
        }
    }
}
