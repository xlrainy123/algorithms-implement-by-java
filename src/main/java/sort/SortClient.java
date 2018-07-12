package sort;

import utils.Utils;

import java.util.Arrays;
import java.util.Collections;

public class SortClient {
    static int[] arr = {7,6,3,1,5};
    public static void main(String[] args){
        SortImp sortImp = new SortImp();
        sortImp.mergeSort(arr);
        for (int a : arr){
            Utils.print(a+",");
        }
    }
}
