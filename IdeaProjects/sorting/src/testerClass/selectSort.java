package testerClass;

public class selectSort {

    private long[] lArray;
    private int nElems;
    //--------------------------------------------------------------
    public selectSort(int max) {
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
        for(int j=0; j<nElems; j++){
            System.out.print(lArray[j] + " ");
        }
        System.out.println("");
    }
    //--------------------------------------------------------------
    public void doSort() {
        int out, in, min;

        for(out=0; out<nElems-1; out++) {
            min = out;
            for(in=out+1; in<nElems; in++) {
                if(lArray[in] < lArray[min]) {
                    min = in;
                }
            }
            swap(out, min);
        }
    }
    //--------------------------------------------------------------
    private void swap(int one, int two) {
        long temp = lArray[one];
        lArray[one] = lArray[two];
        lArray[two] = temp;
    }
    //--------------------------------------------------------------
}