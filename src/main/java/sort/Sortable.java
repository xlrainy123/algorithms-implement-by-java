package sort;

public interface Sortable<T> {

    void quickSort(T[] arr);
    void mergeSort(T[] arr);
    void heapSort(T[] arr);

}
