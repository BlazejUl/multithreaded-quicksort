package pl.buliasz;

import java.util.Random;

public class ArrRandomiser {
    public static int[] CreateRandomArray(int length){
        int[] arr = new int[length];
        for(int i=0;i<length;i++){
            arr[i] = new Random().nextInt(1_000_000);
        }
        return arr;
    }
}
