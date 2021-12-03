package common.sort;

import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        Sort sort = new HeapSort();
        int[] list = new int[]{9,8,7,6,5,4};
        sort.sort(list);
        System.out.println(Arrays.toString(list));
    }
}
