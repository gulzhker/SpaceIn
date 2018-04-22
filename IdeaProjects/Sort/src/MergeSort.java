public class MergeSort{

    //Like QuickSort, Merge Sort is a Divide and Conquer algorithm.
    // It divides input array in two halves, calls itself for the two halves and then merges the two sorted halves.
    // The merge() function is used for merging two halves

    public static void sort (int [] numbers, int start, int end) {// creating sort method than i added array num
        if (start < end) {
            int middle = (start + end) / 2;
            sort (numbers, start, middle);
            sort (numbers, middle + 1, end);
            merge (numbers, start, middle, end);
        }
    }


    public static void merge (int[] numbers, int start, int middle, int end) {

        int[] tmp = new int[end - start + 1];
        int indexLeft = start;
        int indexRight = middle + 1;
        int indexTmp = 0;

        // creating there while loop and comparing left num and right num
        while (indexLeft <= middle && indexRight <= end)
        {
            if (numbers[indexLeft] < numbers[indexRight])
            {
                tmp[indexTmp] = numbers[indexLeft];
                indexLeft++;
            }
            else
            {
                tmp[indexTmp] = numbers[indexRight];
                indexRight++;
            }

            indexTmp++;
        }

        while (indexLeft <= middle)
        {
            tmp[indexTmp] = numbers[indexLeft];
            indexLeft++;
            indexTmp++;
        }
        while (indexRight <= end)
        {
            tmp[indexTmp] = numbers[indexRight];
            indexRight++;
            indexTmp++;
        }

        for (int i = 0; i < tmp.length; i++)
        {
            numbers[start + i] = tmp[i];
        }
    }
}