import java.util.Scanner;
    public class SortTimer {
        
        public static int x = 100000;


        public static void main(String[] args) {


            //ask user for number of experiment to run


            System.out.println("Timing Sort Lab");
            System.out.println("How many times would you like to run the test?");
            Scanner scan = new Scanner(System.in);
            int z = scan.nextInt();
            System.out.println("--------------------------------");
            for (int j = 0; j < z; j++) {


                //creates the array for the randomised numbers for the sorts

                //selectionSort
                int[] array1 = new int[x];
                //insertionSort
                int[] array2 = new int[x];
                //mergeSort
                int[] array3 = new int[x];
                //quickSort
                int[] array4 = new int[x];
                //bubbleSort
                int[] array5 = new int[x];
                //shellSort
                int[] array6 = new int[x];



                //creates 10000000 random numbers for each array
                for (int i = 0; i < array1.length; i++) {
                    int k = (int) (Math.random() * 10000000);
                    array1[i] = k;
                    array2[i] = k;
                    array3[i] = k;
                    array4[i] = k;
                    array5[i] = k;
                    array6[i] = k;
                }

                //print how long it took for each sort to finish

                System.out.println("Try: " + (j + 1));
                System.out.println("Times for 3 sortings");
                System.out.println(" ");

                //selection
                long start1 = System.currentTimeMillis(); //start timer
                SelectionSort(array1);
                long end1 = System.currentTimeMillis(); //end timer
                System.out.println("Selection Sort took:");
                System.out.println((end1 - start1) + " milliseconds.");

                //insertion
                System.out.println(" ");
                long start2 = System.currentTimeMillis(); //start timer
                insertionSort(array2);
                long end2 = System.currentTimeMillis(); //end timer
                System.out.println("Insertion Sort took:");
                System.out.println((end2 - start2) + " milliseconds.");

                //merge
                System.out.println(" ");
                long start3 = System.currentTimeMillis(); //start timer
                MergeSort.sort(array3, 0, array3.length - 1);
                long end3 = System.currentTimeMillis(); //end timer
                System.out.println("Merge Sort took:");
                System.out.println((end3 - start3) + " milliseconds.");


                //quick sort
                System.out.println(" ");
                long start4 = System.currentTimeMillis(); //start timer
                quickSort(array4);
                long end4 = System.currentTimeMillis(); //end timer
                System.out.println("Quick Sort took:");
                System.out.println((end4 - start4) + " milliseconds.");


                //bubble sort

                System.out.println(" ");
                long start5 = System.currentTimeMillis(); //start timer
                bubbleSort(array5);
                long end5 = System.currentTimeMillis(); //end timer
                System.out.println("Bubble Sort took:");
                System.out.println((end5 - start5) + " milliseconds. ");

                //shell sort

                System.out.println(" ");
                long start6 = System.currentTimeMillis();
                shellSort(array6);
                long end6 = System.currentTimeMillis();
                System.out.println("Shell Sort took:");
                System.out.println((end6 - start6) + " milliseconds. ");

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

                temp = selSort[key]; //change values
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


        // All elements which are smaller than the pivot element are placed in one array
        // and all elements which are larger are placed in another array.

        public static void quickSort(int array[]) {
            quickSort(array, 0, array.length - 1);

        }

        public static void quickSort(int array[], int start, int end) {
            int i = start;
            int k = end;



            //// If we have found a value in the left list which is larger than
            //            // the pivot element and if we have found a value in the right list
            //            // which is smaller than the pivot element then we exchange the
            //            // values.
            //            // As we are done we can increase i and j

            if (end - start >= 1) {
                int pivot = array[start];
                while (k > i) {
                    while (array[i] <= pivot && i <= end && k > i) {
                        i++;
                    }
                    while (array[k] > pivot && k >= start && k >= i) {
                        k--;
                    }
                    if (k > i) {
                        swap(array, i, k);
                    }
                }
                swap(array, start, k);

                quickSort(array, start, k - 1);
                quickSort(array, k + 1, end);
            } else {
                return;
            }
        }

        public static void swap(int array[], int index1, int index2) {
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }

        public static void bubbleSort(int[] list) {
            for (int i = 0; i < list.length; i++) {
                for (int j = i + 1; j < list.length; j++) {
                    if (list[i] > list[j]) {
                        int temp = list[i];
                        list[i] = list[j];
                        list[j] = temp;
                    }
                }

            }
        }

        //Shellsort, also known as Shell sort or Shell's method, is an in-place comparison sort.
// It can be seen as either a generalization of sorting by exchange (bubble sort) or sorting by insertion (insertion sort).
// The method starts by sorting elements far apart from each other and progressively reducing the gap between them.
        public static void shellSort(int[] array) {
            int inner, outer;
            int temp;

            int h = 1;
            while (h <= array.length / 3) {
                h = h * 3 + 1;
            }

            while (h > 0) {
                for (outer = h; outer < array.length; outer++) {
                    temp = array[outer];
                    inner = outer;

                    while (inner > h - 1 && array[inner - h] >= temp) {
                        array[inner] = array[inner - h];
                        inner -= h;
                    }
                    array[inner] = temp;
                }
                h = (h - 1) / 3;
            }
        }
    }