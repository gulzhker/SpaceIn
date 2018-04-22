package testerClass;

public class insertSort {

        private long[] lArray;
        private int nElems;

        //--------------------------------------------------------------
        public insertSort(int max) {
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
            for(int j=0; j<nElems; j++) {
                System.out.print(lArray[j] + " ");
            }
            System.out.println("");
        }
        //--------------------------------------------------------------
        public void doSort() {
            int in, out;

            for(out=1; out<nElems; out++) {
                long temp = lArray[out];
                in = out;
                while(in>0 && lArray[in-1] >= temp) {
                    lArray[in] = lArray[in-1];
                    --in;
                }
                lArray[in] = temp;
            }
        }
        //--------------------------------------------------------------
    }
