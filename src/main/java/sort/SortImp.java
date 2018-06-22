package sort;

import javafx.beans.binding.ObjectExpression;

public class SortImp implements Sortable{

    public void quickSort(int[] arr){
        int low = 0, high = arr.length - 1;
        quickSortHandler(arr, low, high);
    }

    private void quickSortHandler(int[] arr, int low, int high){
        if (low < high) {
            int pos = partition(arr, low, high);
            quickSortHandler(arr, low, pos-1);
            quickSortHandler(arr, pos+1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while(low < high) {
            while (low < high && arr[high] >= pivot) {
                high --;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[high] = pivot;
        return high;
    }

    public void mergeSort(int[] arr){

    }

    public void heapSort(int[] arr) {

    }
}
