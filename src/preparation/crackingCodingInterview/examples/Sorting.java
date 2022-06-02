package preparation.crackingCodingInterview.examples;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
        int[] array = new int[]{-1, 3, 64, 2, 0, 1, 9, 7, 2, 1, 1};
        MergeSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static class MergeSort {

        public static void sort(int[] a) {

            int n = a.length;
            if (n < 2) {
                return;
            }

            int mid = n / 2;
            int[] left = new int[mid];
            int[] right = new int[n - mid];

            for (int i = 0; i < mid; i++) {
                left[i] = a[i];
            }

            for (int i = mid; i < n; i++) {
                right[i - mid] = a[i];
            }

            sort(left);
            sort(right);

            merge(a, left, right);
        }

        private static void merge(int[] a, int[] left, int[] right) {
            int leftLength = left.length;
            int rightLength = right.length;

            int i = 0;
            int j = 0;
            int k = 0;

            while (i < leftLength && j < rightLength) {
                if (left[i] <= right[j]) {
                    a[k++] = left[i++];
                } else {
                    a[k++] = right[j++];
                }
            }

            while (i < leftLength) {
                a[k++] = left[i++];
            }

            while (j < rightLength) {
                a[k++] = right[j++];
            }

        }
    }

}
