package pl.buliasz;

import java.util.Random;

public class ArrRandomiser {
    public static int[] fillArrayRandom(int[] arr){
        for(int i=0;i <= arr.length-1;i++){
            arr[i] = new Random().nextInt(1_000_000);
        }
        return arr;
    }
}
