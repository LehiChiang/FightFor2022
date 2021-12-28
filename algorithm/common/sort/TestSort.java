package common.sort;

import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        Sort sort = new QuickSort();
        int[] list = new int[]{6,5,4,3,2,1};
        sort.sort(list);
        System.out.println(Arrays.toString(list));
    }
}
