package cz2001_lab3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MergeSort {
    private static int numComparision = 0;

    public static void main(String[] args) {
        testMergeSort("random2000.txt");
        testMergeSort("random4000.txt");
        testMergeSort("random6000.txt");
        testMergeSort("random8000.txt");
        testMergeSort("random10000.txt");
        
        testMergeSort("asc2000.txt");
        testMergeSort("asc4000.txt");
        testMergeSort("asc6000.txt");
        testMergeSort("asc8000.txt");
        testMergeSort("asc10000.txt");
        
        testMergeSort("dsc2000.txt");
        testMergeSort("dsc4000.txt");
        testMergeSort("dsc6000.txt");
        testMergeSort("dsc8000.txt");
        testMergeSort("Dsc10000.txt");
        
    }

    public static void mergeSort(Comparable[] a) {
        Comparable[] tmp = new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    private static void mergeSort(Comparable[] a, Comparable[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    private static void merge(Comparable[] a, Comparable[] tmp, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {
            if (a[left].compareTo(a[right]) <= 0) {
                numComparision++;
                tmp[k++] = a[left++];
            } else {
                numComparision++;
                tmp[k++] = a[right++];
            }
        }

        while (left <= leftEnd) // Copy rest of first half
        {
            tmp[k++] = a[left++];
        }

        while (right <= rightEnd) // Copy rest of right half
        {
            tmp[k++] = a[right++];
        }

        // Copy tmp back
        for (int i = 0; i < num; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }

    private static void testMergeSort(String filename) {
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

        System.out.println("Merge Sorting " + filename);
        long now = System.nanoTime();
        mergeSort(a);
        long timetaken = System.nanoTime() - now;
        //System.out.println(Arrays.toString(a));
        System.out.println("Number of comparision: " + numComparision);
        System.out.println("Time taken in nanoseconds :" + timetaken);
        System.out.println("");
        System.out.println("");
        numComparision = 0;
    }

}

