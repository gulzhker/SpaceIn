package testerClass;

public class bubbleSort {
    private long[] lArray;
    private int nElems;

    public bubbleSort(int max) {
        lArray = new long[max];
        nElems = 0;
    }

    //--------------------------------------------------------------
    public void insert(long value) {
        lArray[nElems] = value;
        nElems++;
    }

    //--------------------------------------------------------------
    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(lArray[j] + " ");
        }
        System.out.println("");
    }

    //--------------------------------------------------------------
    public void doSort() {
        int out, in;
        for (out = nElems - 1; out > 1; out--) {
            for (in = 0; in < out; in++) {
                if (lArray[in] > lArray[in + 1]) {
                    swap(in, in + 1);
                }
            }
        }
    }

    //--------------------------------------------------------------
    private void swap(int one, int two) {
        long temp = lArray[one];
        lArray[one] = lArray[two];
        lArray[two] = temp;
    }
}
