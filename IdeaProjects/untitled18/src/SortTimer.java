import java.util.*;
import java.util.Scanner;
public class SortTimer {

    public static int x = 1000000;

    public static void main(String[] args) {

        //ask user for number of experiment to run
        System.out.println("Time of sorting algorithms");
        //asking user
        System.out.println("How many times would you like to run the test?");
        Scanner scan = new Scanner(System.in);
        int z = scan.nextInt();
        System.out.println("--------------------------------");
        for (int j = 0; j < z; j++) {

            //creates the array for the randomised numbers for the sorts
            // selectionsort
            int[] array1 = new int[x];
            //insertionsort
            int[] array2 = new int[x];
            //mergesort
            int[] array3 = new int[x];

            //creates 10000000 random numbers for each array
            for (int i = 0; i < array1.length; i++) {
                int k = (int) (Math.random() * 10000000);
                array1[i] = k;
                array2[i] = k;
                array3[i] = k;
            }


            //print how long it took for each sort to finish

            System.out.println("Try: " + (j + 1));
            System.out.println("Times for 3 sortings");
            System.out.println(" ");
            long start1 = System.currentTimeMillis(); //start timer
            SelectionSort(array1);
            long end1 = System.currentTimeMillis(); //end timer
            System.out.println("Selection Sort took:");
            System.out.println((end1 - start1) + " milliseconds.");


            System.out.println("");
            long start2 = System.currentTimeMillis(); //start timer
            insertionSort(array2);
            long end2 = System.currentTimeMillis(); //end timer
            System.out.println("Insertion Sort took:");
            System.out.println((end2 - start2) + " milliseconds.");


            System.out.println("");
            long start3 = System.currentTimeMillis(); //start timer
            MergeSort.sort(array3, 0, array3.length - 1);
            long end3 = System.currentTimeMillis(); //end timer
            System.out.println("Merge Sort took:");
            System.out.println((end3 - start3) + " milliseconds.");
            System.out.println("--------------------------------");
            System.out.println("");

        }
    }

    /*Selection sort
    takes the next number in array and read through rest of the array
    to find a lowest value, and then switch that next number with the lowest value.
    keep going until the array hit to the last number and it is done
     */

    public static void SelectionSort(int[] selSort) {
        int key, temp;
        for (int index = 0; index < selSort.length - 1; index++) {
            key = index;
            for (int position = index + 1; position < selSort.length; position++)//caompare the vals
            {
                if (selSort[position] < selSort[key])
                    key = position;
            }

            temp = selSort[key];//change values
            selSort[key] = selSort[index];
            selSort[index] = temp;
        }
    }

    /*Insertion sort
    go through each number in the array and switch the number with the next number with a lower value
    continue until the last value
     */
    public static void insertionSort(int[] insSort) {
        for (int index = 1; index < insSort.length; index++) {
            int key = insSort[index];
            int position = index;
            while (position > 0 && insSort[position - 1] > key)//compare vals
            {
                insSort[position] = insSort[position - 1];
                position--;
            }

            insSort[position] = key; //change location of number
        }
    }
}