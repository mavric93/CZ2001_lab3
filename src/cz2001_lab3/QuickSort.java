package cz2001_lab3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuickSort {

    private static int numComparision = 0;

    public static void main(String[] args) {
        testQuickSort("random2000.txt");
        testQuickSort("random4000.txt");
        testQuickSort("random6000.txt");
        testQuickSort("random8000.txt");
        testQuickSort("random10000.txt");
        
        testQuickSort("asc2000.txt");
        testQuickSort("asc4000.txt");
        testQuickSort("asc6000.txt");
        testQuickSort("asc8000.txt");
        testQuickSort("asc10000.txt");
        
        testQuickSort("dsc2000.txt");
        testQuickSort("dsc4000.txt");
        testQuickSort("dsc6000.txt");
        testQuickSort("dsc8000.txt");
        testQuickSort("dsc10000.txt");
    }

    public static void quickSort(Comparable[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        // pick the pivot
        int middle = low + (high - low) / 2;
        Comparable pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i].compareTo(pivot) < 0) {
                numComparision++;
                i++;
            }

            while (arr[j].compareTo(pivot) > 0) {
                numComparision++;
                j--;
            }

            if (i <= j) {
                Comparable temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j) {
            quickSort(arr, low, j);
        }

        if (high > i) {
            quickSort(arr, i, high);
        }
    }

    private static void testQuickSort(String filename) {
        ArrayList<Integer> sampleData = new ArrayList();
        try {
            Scanner sc = new Scanner(new FileReader(filename));

            String s = new String();
            while (sc.hasNextLine()) {
                //System.out.println(s);
                s = sc.nextLine();
                sampleData.add(Integer.parseInt(s));

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(cz2001_lab3.MergeSort.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        Integer[] a = sampleData.toArray(new Integer[sampleData.size()]);

        System.out.println("Quick Sorting " + filename);
        long now = System.nanoTime();
        quickSort(a, 0, a.length - 1);
        long timetaken = System.nanoTime() - now;
        
        System.out.println("Number of comparision: " + numComparision);
        System.out.println(timetaken);
        
        //System.out.println(Arrays.toString(a));
        System.out.println("");
        System.out.println("");
        numComparision = 0;
    }
}
