package pl.buliasz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

import static pl.buliasz.IsNumeric.isNumeric;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numberOfSimulations = 1, maxThreadCap = 4, arrLength = 0;
        boolean isOn = true, acceptableUserOutput = false, sortedArrayIn = false;
        Path toSimTime = Paths.get("output/simulationTime.txt");
        Path toArrayOutput = Paths.get("output/arrayOutput.txt");
        List<String> listOfArrays = new ArrayList<>();
        List<String> listOfSimulationTimesWithText = new ArrayList<>();
        List<Long> listOfSimulationTimes = new ArrayList<>();
        /*//for multithreaded-quicksort-needs-jre.exe
        System.out.println("""
                Aplikacja wyczyści pliki arrayOutput.txt i simulationTime.txt
                jeżeli nie chcesz stracić danych skopiuj je do innych plików
                (naciśnij enter)""");
        scanner.nextLine();
         //*/


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


        listOfArrays.add("nie posortowana lista: " + Arrays.toString(arr));

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

            long duration = (endTime - startTime)/1_000_000;    //in ms
            listOfSimulationTimes.add(duration);
            listOfSimulationTimesWithText.add("czas symulacji nr" + (i + 1) + ": " + duration + " ms");
            if(!sortedArrayIn){
                listOfArrays.add("posortowana lista "+Arrays.toString(arrCopy));
                sortedArrayIn = true;}
        }
        double average = 0;
        for(Long time: listOfSimulationTimes){
            average += time;
        }
        average = average / (double) listOfSimulationTimes.size();

        /*//for multithreaded-quicksort-needs-jre.exe
        listOfSimulationTimesWithText.add("średni czas symulacji : " + average + " ms");
        Files.write(toSimTime,listOfSimulationTimesWithText,StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(toArrayOutput,listOfArrays, StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);

        System.out.println("Wyniki są w folderze output");
        System.out.println("(wciśnij enter)");
        scanner.nextLine();
         //*/
        //for multithreaded-quicksort.msi
        System.out.println("wciśnij enter aby wyświetlić czasy symulacji");
        scanner.nextLine();
        for(String line:listOfSimulationTimesWithText){
            System.out.println(line);
        }
        System.out.println("wciśnij enter aby wyświetlić nie posortowaną i posortowaną tablice");
        scanner.nextLine();
        for(String line:listOfArrays){
            System.out.println(line);
        }
        System.out.println("Aby zamknąć aplikacje");
        System.out.println("(wciśnij enter)");
        scanner.nextLine();
        //*/
    }
}