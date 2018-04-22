package testerClass;
public class testerClass {

    public static void main(String[] args) {

        int maxSize = 1000000;
        int count = 0;

        bubbleSort arr;
        arr = new bubbleSort(maxSize);

        insertSort arr2;
        arr2 = new insertSort(maxSize);

        selectSort arr3;
        arr3 = new selectSort(maxSize);

        for(int i=0;i<maxSize;i++){
            int random = (int)(Math.random() * 100);
            //arr.insert(random);
            arr.insert(random);
            arr3.insert(random);
            count++;
        }
        System.out.println("bubble sort");

        long startTime = System.currentTimeMillis();
        arr.doSort();
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println(totalTime);


        arr2.doSort();
        arr.display();

        System.out.println("insert sort");
        arr2.display();
        arr2.doSort();
        arr2.display();

        System.out.println("select sort");
        arr3.display();
        arr3.doSort();
        arr3.display();

        System.out.println("total numbers: " + count);

    }
}