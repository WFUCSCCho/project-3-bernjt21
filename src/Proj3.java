/********************************************************************
 * @file: DogData.java
 * @description: This program implements the Proj3 class which includes the algorithms to run bubble sort, heap sort, merge sort, quick sort, and off-even transposition. It runs each sorting method for sorted, shuffled, and reversed arraylists and counts the comparisons for bubble and odd even transposition and the running time in nano-seconds for bubble, merge, heap, and quick sort
 * @author: June Bernstein
 * @date: November 14, 2024
 ******************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort method with recursive calls and calls to the merge method
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left<right){
            int mid = (left+right)/2;
            mergeSort(a, left, mid);
            mergeSort(a, mid+1, right);
            merge(a, left, mid, right);
        }
    }
    //merge method that runs the algorithm
    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
        ArrayList<T> temp = new ArrayList<>(a);
        int i = left;
        int j = mid+1;
        int k = left;
        while (i <= mid && j <= right) {
            if(a.get(i).compareTo(a.get(j)) <= 0){
                temp.set(k++, a.get(i++));
            }
            else{
                temp.set(k++, a.get(j++));
            }
        }
        while (i <= mid) temp.set(k++, a.get(i++));
        while (j <= right) temp.set(k++, a.get(j++));

        for(k=left; k<= right; k++){
            a.set(k, temp.get(k));
        }
    }

    // Quick Sort method which calls to the partition method for the algorithm from the pivots which are recursively used in quickSort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left<right) {
            int pivot = partition(a, left, right);
            quickSort(a, left, pivot-1);
            quickSort(a, pivot+1, right);
        }
    }

    //runs the algorithm and finds pivots for quickSort
    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me
        T pivot = a.get(right);
        int i = left-1;
        for(int j=left; j<right; j++) {
            if (a.get(j).compareTo(pivot) <= 0) {

                i++;
                swap(a, i, j);
            }
        }
        swap (a, i +1, right);
        return i+1;
    }

    //swaps values in the ArrayList
    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort method that calls heapify to run algorithm
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        int size = right-left+1;

        for(int i = left + size / 2 -  1; i >=left; i--){
            heapify(a, left, right, i);
        }
        for (int i = right; i >left; i--){
            swap(a, left, i);
            heapify(a, left, i-1, left);
        }
    }
    //method with algorithm for heaps
    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right, int i) {// does adding int i here work
        // Finish Me
        int largest = i;
        int leftChild = 2 * (i-left) + 1 + left;
        int rightChild = 2 * (i-left) + 2 + left;
        if (leftChild <= right && a.get(leftChild).compareTo(a.get(largest)) > 0) {
            largest = leftChild;
        }
        if (rightChild <= right && a.get(rightChild).compareTo(a.get(largest)) > 0) {
            largest = rightChild;
        }
        if (largest != i){
            swap(a, i, largest);
            heapify(a, left, right, largest);
        }
    }

    // Bubble Sort method that performs the sorting and counts the number of comparisons
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me
        int count = 0;
        boolean swapped;
        for(int i = 0; i < size-1; i++){
            swapped = false;
            for(int j = 0; j < size-i-1; j++){
                count++;
                if (a.get(j).compareTo(a.get(j+1)) > 0) {
                    swap(a, j, j+1);
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
        return count;
    }

    // Odd-Even Transposition Sort method that runs the algorithm and counts the number of comparisons performed
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
        int count = 0;
        boolean sorted = false;
        while (!sorted){
            sorted = true;
            for (int i = 1; i < size-1; i+=2){
                count++;
                if(a.get(i).compareTo(a.get(i+1))>0){
                    swap(a, i, i+1);
                    sorted = false;
                }
            }
            for (int i=0; i<size-1; i+= 2){
                count++;
                if(a.get(i).compareTo(a.get(i+1))>0){
                    swap(a, i, i+1);
                    sorted = false;
                }
            }
        }
        return count;
    }
    //The Main method takes in the input file, creates an arraylist for DogData, creates an output file, and runs the sorting methods for the sorted, shuffled, and the reversed sorted list
    public static void main(String [] args)  throws IOException {
        //...
        // Finish Me
        //...
        // Use command line arguments to specify the input file

        if (args.length != 3) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String sortType = args[1];
        int numLines = Integer.parseInt(args[2]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME
        //implements arraylist and adds all the data for each dog data line
        ArrayList<DogData> dogArrayList = new ArrayList<>();
        Scanner scnr = new Scanner(new File("src/dogbreeds.csv"));
        Scanner inputscnr = new Scanner(inputFileNameStream);
        //remove header in file
        if (scnr.hasNext()) {
            scnr.nextLine();
        }
        while (scnr.hasNext() && dogArrayList.size()<numLines) {
            String line = scnr.nextLine();
            String[] parts = line.split(","); //split the string into multiple parts
            //Check if the dogs match
            //if (parts[0].equals("Breed")) {
            //Create new DogData object
            DogData data = new DogData(
                    parts[0], //breed
                    parts[1], //originCountry
                    parts[2], //furColor
                    parts[3], //height
                    parts[4], //eyeColor
                    parts[5]  //longevity
            );
            dogArrayList.add(data);//add the data onto the ArrayList
            //}
        }
        //creating output files for the times and counts as well as the sorted arraylists
        File file = new File("./analysis.csv");
        File sortedFile = new File("./sorted.txt");
        if (sortedFile.delete()) {
            sortedFile.createNewFile();
        }
        writeToFile("\nSort Algorithm, List Sort Type, Number of Lines, Runtime, Comparisons", "./analysis.csv");
        //performs collections.sort on arraylist
        Collections.sort(dogArrayList);
        doSortingAlgorithm(dogArrayList, sortType, numLines,"sorted");
        writeToFile("Sorted list: " + sortType + "\n" + dogArrayList + "\n", "./sorted.txt");

        //performs collections.shuffle on arraylist
        Collections.shuffle(dogArrayList);
        doSortingAlgorithm(dogArrayList,sortType, numLines,"shuffled");
        writeToFile("Shuffled list: " + sortType + "\n" + dogArrayList + "\n", "./sorted.txt");

        //performs collections sort in the reverse order on arraylist
        Collections.sort(dogArrayList, Collections.reverseOrder());
        doSortingAlgorithm(dogArrayList,sortType, numLines, "reversed");
        writeToFile("Reversed sorted list: " + sortType + "\n" + dogArrayList+ "\n", "./sorted.txt");
    }

    //depending on the input for the configuration, runs the specified sorting algorithm or displays error
    private static ArrayList<DogData> doSortingAlgorithm(ArrayList<DogData> dogArrayList, String sortType, int numLines, String inputSortType){
        long startTime;
        long endTime;
        switch (sortType) {
            case "bubble":
                startTime = System.nanoTime();
                int bubbleCount = bubbleSort(dogArrayList, numLines);
                endTime = System.nanoTime();
                writeToFile("Bubble Sort," + inputSortType + "," + numLines + "," + (endTime - startTime) + "," + bubbleCount, "./analysis.csv");
                System.out.println(inputSortType + " list sorted using bubble sort, run time: " +  (endTime - startTime) + "ns, number of comparisons: " + bubbleCount);
                break;
            case "quick":
                startTime = System.nanoTime();
                quickSort(dogArrayList, 0, numLines-1);
                endTime = System.nanoTime();
                writeToFile("Quick Sort," + inputSortType + "," + numLines + "," + (endTime - startTime) + ",null", "./analysis.csv");
                System.out.println(inputSortType + " list sorted using quick sort, run time: " +  (endTime - startTime) + "ns");
                break;
            case "merge":
                startTime = System.nanoTime();
                mergeSort(dogArrayList, 0, numLines-1);
                endTime = System.nanoTime();
                writeToFile("Merge Sort," + inputSortType + "," + numLines + "," + (endTime - startTime) + ",null", "./analysis.csv");
                System.out.println(inputSortType + " list sorted using merge sort, run time: " +  (endTime - startTime) + "ns");
                break;
            case "heap":
                startTime = System.nanoTime();
                heapSort(dogArrayList, 0, numLines-1);
                endTime = System.nanoTime();
                writeToFile("Heap Sort," + inputSortType + "," + numLines + "," + (endTime - startTime) + ",null", "./analysis.csv");
                System.out.println(inputSortType + " list sorted using heap sort, run time: " +  (endTime - startTime) + "ns");
                break;
            case "transposition":
                int transpositionCount = transpositionSort(dogArrayList, numLines);
                writeToFile("Odd-Even Transposition," + inputSortType + "," + numLines  + ",null" +","+transpositionCount, "./analysis.csv");
                System.out.println(inputSortType + " list sorted using odd-even transposition sort, number of comparisons: " + transpositionCount);
                break;
            default:
                System.err.println("Invalid sort type: " + sortType);
        }
        return dogArrayList;
    }

    //writeToFile is used to write the nanoseconds onto the output file
    public static void writeToFile(String content, String filePath) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            boolean emptyFile = new File(filePath).length() == 0;
            if (!emptyFile) {
                bufferedWriter.newLine();
            }
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}