package pl.buliasz;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int[] arr = {7,1,5,8,3,6};
        int end = arr.length-1,
        numberOfSimulations = 2,
        maxThreadCap=4;
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.setParallelism(maxThreadCap);



        for(int i=0;i<numberOfSimulations;i++) {
            int[] arrCopy = arr.clone();
            pool.invoke(new QuickSort(0,end,arrCopy));
            for (int j=0;j<=end;j++){
                System.out.println(arrCopy[j] + " ");
            }
        }
    }
}