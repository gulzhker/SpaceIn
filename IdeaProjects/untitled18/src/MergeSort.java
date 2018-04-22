public class MergeSort
    {
        public static void sort (int [] numbers, int start, int end)
        {
            if (start < end)
            {
                int middle = (start + end) / 2;
                sort (numbers, start, middle);
                sort (numbers, middle + 1, end);
                merge (numbers, start, middle, end);
            }
        }
        public static void merge (int[] numbers, int start, int middle, int end)
        {
            int[] tmp = new int[end - start + 1];

            int indexLeft = start;
            int indexRight = middle + 1;
            int indexTmp = 0;

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
