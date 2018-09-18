package sort;

import utils.Utils;

public class SortClient {
    static int[] arr = {7,6,3,1,5};
    public static void main(String[] args){
        System.out.println(3<<1);
        SortImp sortImp = new SortImp();
        sortImp.mergeSort(arr);
        for (int a : arr){
            Utils.print(a+",");
        }


    }
}
