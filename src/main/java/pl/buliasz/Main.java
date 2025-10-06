package pl.buliasz;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = {7,1,5,8,3,6};
        int end = arr.length-1,
        numberOfSimulations = 2,
        maxThreadCap=4;
        boolean isOn = true, arrayOutput = true, acceptableUserOutput = false;

        System.out.println("""
                Aplikacja wyczyści pliki jeżeli
                 nie chcesz stracić danych skopiuj je do innych plików
                (naciśnij enter)""");
        scanner.nextLine();

        System.out.println("""
                    
                     Aby zobaczyć w plikach nie posortowaną oraz posortowaną listę
                     liczba symulacji nie może przekraczać 10 a długość listy 100
                     
                     """);
        while (acceptableUserOutput = false){
            System.out.println("Podaj ilośc symulacji do wykonania");
            String userOutput = scanner.nextLine();
            if ()
        }



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