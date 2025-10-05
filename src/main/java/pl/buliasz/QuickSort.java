package pl.buliasz;

import java.util.concurrent.RecursiveTask;

public class QuickSort extends RecursiveTask<Integer> {
    private int[] arr;
    private int start, end;

    private int partition(int start, int end, int[] arr)
    {

        int i = start, j = end;

        // make pivot in center
        int pivot = (j + i) / 2;

        // swap the pivot with end element of array;
        int t = arr[j];
        arr[j] = arr[pivot];
        arr[pivot] = t;
        j--;

        // start partitioning
        while (i <= j) {

            if (arr[i] <= arr[end]) {
                i++;
                continue;
            }

            if (arr[j] >= arr[end]) {
                j--;
                continue;
            }

            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j--;
            i++;
        }

        // swap pivot to its correct position
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }

    QuickSort(int start, int end, int[] arr ){
        this.arr = arr;
        this.end = end;
        this.start = start;
    }

    @Override
    protected Integer compute() {
        // means that this chunk is done
        if(start >= end) return null;

        int p = partition(start,end,arr);

        QuickSort left = new QuickSort(start, p - 1, arr);

        QuickSort right = new QuickSort(p + 1,end, arr);

        // left problem for another thread
        left.fork();
        right.compute();

        // wait for left thread to finish
        left.join();

        return null;
    }
}
