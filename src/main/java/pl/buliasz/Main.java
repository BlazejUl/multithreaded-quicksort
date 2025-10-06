package pl.buliasz;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import static pl.buliasz.IsNumeric.isNumeric;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfSimulations = 1, maxThreadCap = 4, arrLength = 0;
        boolean isOn = true, writeArrayOutput = false, acceptableUserOutput = false;

        System.out.println("""
                Aplikacja wyczyści pliki jeżeli
                 nie chcesz stracić danych skopiuj je do innych plików
                (naciśnij enter)""");
        scanner.nextLine();

        System.out.println("""
                    
                     Aby zobaczyć w plikach nie posortowaną oraz posortowaną listę
                     liczba symulacji nie może przekraczać 10 a długość listy 100
                     
                     """);

        while (acceptableUserOutput == false){
            System.out.println("Podaj ilośc symulacji do wykonania");
            String userOutput = scanner.nextLine();
            if (isNumeric(userOutput)){
                numberOfSimulations = Integer.parseInt(userOutput);
                acceptableUserOutput = true;}
            else {System.out.println("Podano nieprawidłową wartość");}
        }

        acceptableUserOutput = false;
        while (acceptableUserOutput == false){
            System.out.println("Podaj długość tablicy do posortowania");
            String userOutput = scanner.nextLine();
            if (isNumeric(userOutput)){
                arrLength = Integer.parseInt(userOutput);
                acceptableUserOutput = true;}
            else {System.out.println("Podano nieprawidłową wartość");}
        }
        int[] arr = new int[arrLength];
        int end = arr.length-1;

        ArrRandomiser.fillArrayRandom(arr);  //fill array with random numbers from 0 to 999_999

        if(arrLength <= 100 && numberOfSimulations <= 10) writeArrayOutput = true;

        acceptableUserOutput = false;
        while (acceptableUserOutput == false){
            System.out.println("Podaj maksymalną ilość wątków");
            String userOutput = scanner.nextLine();
            if (isNumeric(userOutput)){
                maxThreadCap = Integer.parseInt(userOutput);
                acceptableUserOutput = true;}
            else {System.out.println("Podano nieprawidłową wartość");}
        }

        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.setParallelism(maxThreadCap);

        for(int i=0;i<numberOfSimulations;i++) {
            int[] arrCopy = arr.clone();

            long startTime = System.nanoTime();
            pool.invoke(new QuickSort(0,end,arrCopy));      //measuring time for each iteration
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);

            for (int j=0;j<=end;j++){
                System.out.println(arrCopy[j] + " ");
            }
        }
    }
}