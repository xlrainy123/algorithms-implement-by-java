package sort;


public class SortImp implements Sortable{

    /**
     * 快速排序
     * @param arr
     */
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

    /**
     * 直接插入排序
     * @param arr
     */
    @Override
    public void directInsertSort(int[] arr){
        int len = arr.length;
        for (int i = 1; i < len; ++i){
            if (arr[i] < arr[i-1]){
                int tmp = arr[i];
                int j = i-1;
                for ( ;j >= 0 && tmp < arr[j]; --j){
                    arr[j+1] = arr[j];
                }
                arr[j+1] = tmp;
            }
        }
    }

    /**
     * 冒泡排序
     * @param arr
     */
    public void bubbleSort(int[] arr){
        int len = arr.length;
        for (int i = 0; i < len-1; i++){
            boolean flag = false;
            for (int j = len-1; j > i; j--){
                if (arr[j] < arr[j-1]){
                    swap(arr, j, j-1);
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }

    private void swap(int[] arr, int i, int j){

        if (i < 0 || i > arr.length-1 || j < 0 || j > arr.length-1){
            throw new IndexOutOfBoundsException("输入参数不合法");
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

    }

    /**
     * 折半插入排序
     * @param arr
     */
    public void binaryInsertSort(int[] arr){
        int len = arr.length;
        for (int i = 1; i < len; i++){
            if (arr[i] < arr[i-1]){
                int tmp = arr[i];
                int low = 0, high = i-1;
                while (low <= high){
                    int mid = (low + high) / 2;
                    if (arr[mid] <= tmp){
                        low = mid + 1;
                    }else{
                        high = mid - 1;
                    }
                }
                for (int j = i-1; j >= high+1; j--){
                    arr[j+1] = arr[j];
                }
                arr[high+1] = tmp;
            }
        }
    }

    /**
     * 希尔排序
     * 直接插入排序元素个数比较少，并且基本有序的时候，效率比较高。随着元素个数的增加，
     * 并且元素内部之间的的顺序比较混乱的情况下，直接插入排序的性能将会变得很差
     * @param arr
     */
    public void shellSort(int[] arr){
        int len = arr.length;
        for (int dk = len/2; dk >= 1; dk = dk/2){  //只是比直接插入排序多了这层循环，分不同的步长
            for (int i = dk; i < len; i++) {
                if (arr[i] < arr[i-dk]){
                    int tmp = arr[i];
                    int j = i-dk;
                    for ( ;j >= 0 && tmp < arr[j]; j = j-dk){
                        arr[j+dk] = arr[j];
                    }
                    arr[j+dk] = tmp;
                }
            }
        }
    }

    public void countSort(int[] arr){

    }

    /**
     * 归并排序
     * @param arr
     */
    public void mergeSort(int[] arr){
        int low = 0, high = arr.length - 1;
        mergeSortHandler(arr, low, high);
    }

    private void mergeSortHandler(int[] arr, int low, int high){
        if (low < high){
            int mid = (low + high) / 2;
            mergeSortHandler(arr, low, mid);
            mergeSortHandler(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }

    /**
     * 两个有序数组
     * 数组一： [low, mid]
     * 数组二： [mid+1, high]
     *
     * @param arr
     * @param low
     * @param mid
     * @param high
     */
    private void merge(int[] arr, int low, int mid, int high){

        int len_1 = mid - low + 1;
        int len_2 = high - mid;

        int[] tmp_1 = new int[len_1+1];
        int[] tmp_2 = new int[len_2+1];

        for (int i = low; i <= mid; i++){
            tmp_1[i-low] = arr[i];
        }

        for (int i = mid+1; i <= high; i++){
            tmp_2[i-mid-1] = arr[i];
        }

        tmp_1[len_1] = Integer.MAX_VALUE;
        tmp_2[len_2] = Integer.MAX_VALUE;

        int index_1 = 0, index_2 = 0;

        for (int index = low; index <= high; index++ ){
            if (tmp_1[index_1] < tmp_2[index_2]){
                arr[index] = tmp_1[index_1++];
            }else{
                arr[index] = tmp_2[index_2++];
            }
        }
    }

    /**
     * 堆排序
     * @param arr
     */
    public void heapSort(int[] arr) {
        //建立一个最大堆
        buildMaxHeap(arr);
        int len = arr.length;
        for (int i = 0; i < len; i++){
            swap(arr, 0, len-i-1);
            adjustDown(arr, 0, len-i-1);
        }
    }

    private void buildMaxHeap(int[] arr){
        int len = arr.length;
        for (int i = len/2 - 1; i >= 0; i--){
            adjustDown(arr, i, len);
        }
    }

    private void adjustDown(int[] arr, int parent, int end){
        int tmp = arr[parent];
        for (int child = 2*parent+1; child < end; child = 2*child+1){
            if (child < end-1 && arr[child] < arr[child+1]){
                child = child + 1;
            }
            if (tmp >= arr[child]){
                break;
            }else {
                swap(arr, parent, child);
                parent = child;
            }
        }
        arr[parent] = tmp;
    }
}
