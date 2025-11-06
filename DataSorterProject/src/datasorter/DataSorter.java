package datasorter;

import java.util.*;

public class DataSorter {
    static Scanner sc = new Scanner(System.in);
    static int[] data;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random numbers");
            System.out.println("3. Perform Bubble Sort");
            System.out.println("4. Perform Merge Sort");
            System.out.println("5. Perform Quick Sort");
            System.out.println("6. Compare all algorithms (table)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> enterNumbers();
                case 2 -> generateNumbers();
                case 3 -> runBubble();
                case 4 -> runMerge();
                case 5 -> runQuick();
                case 6 -> compareAll();
                case 7 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void enterNumbers() {
        System.out.print("How many numbers? ");
        int n = sc.nextInt();
        data = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            data[i] = sc.nextInt();
        }
    }

    static void generateNumbers() {
        System.out.print("Enter how many random numbers: ");
        int n = sc.nextInt();
        Random r = new Random();
        data = new int[n];
        for (int i = 0; i < n; i++) data[i] = r.nextInt(100);
        System.out.println("Random numbers: " + Arrays.toString(data));
    }

    static void runBubble() {
        if (data == null) { System.out.println("No data!"); return; }
        int[] copy = Arrays.copyOf(data, data.length);
        long start = System.nanoTime();
        int steps = BubbleSort.bubbleSort(copy);
        long end = System.nanoTime();
        System.out.printf("Bubble Sort → Steps:%d | Time:%.3f ms%n", steps, (end - start)/1e6);
    }

    static void runMerge() {
        if (data == null) { System.out.println("No data!"); return; }
        int[] copy = Arrays.copyOf(data, data.length);
        long start = System.nanoTime();
        MergeSort.mergeSort(copy, 0, copy.length - 1);
        long end = System.nanoTime();
        System.out.printf("Merge Sort → Steps:%d | Time:%.3f ms%n",
                MergeSort.getSteps(), (end - start)/1e6);
    }

    static void runQuick() {
        if (data == null) { System.out.println("No data!"); return; }
        int[] copy = Arrays.copyOf(data, data.length);
        long start = System.nanoTime();
        QuickSort.quickSort(copy, 0, copy.length - 1);
        long end = System.nanoTime();
        System.out.printf("Quick Sort → Steps:%d | Time:%.3f ms%n",
                QuickSort.getSteps(), (end - start)/1e6);
    }

    static void compareAll() {
        if (data == null) { System.out.println("No data!"); return; }
        int[] d1 = Arrays.copyOf(data, data.length);
        int[] d2 = Arrays.copyOf(data, data.length);
        int[] d3 = Arrays.copyOf(data, data.length);

        long t1s = System.nanoTime(); int s1 = BubbleSort.bubbleSort(d1); long t1e = System.nanoTime();
        long t2s = System.nanoTime(); MergeSort.mergeSort(d2, 0, d2.length - 1); long t2e = System.nanoTime();
        long t3s = System.nanoTime(); QuickSort.quickSort(d3, 0, d3.length - 1); long t3e = System.nanoTime();

        System.out.println("\n--- Comparison Table ---");
        System.out.printf("%-10s %-10s %-10s%n", "Algorithm", "Steps", "Time(ms)");
        System.out.printf("%-10s %-10d %-10.3f%n", "Bubble", s1, (t1e - t1s)/1e6);
        System.out.printf("%-10s %-10d %-10.3f%n", "Merge", MergeSort.getSteps(), (t2e - t2s)/1e6);
        System.out.printf("%-10s %-10d %-10.3f%n", "Quick", QuickSort.getSteps(), (t3e - t3s)/1e6);
    }
}
