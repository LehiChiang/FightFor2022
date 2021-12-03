package common.sort;

import datastructure.Heap;

/**
 * 堆排序算法实现
 */
public class HeapSort extends Sort{

    @Override
    public void sort(int[] list) {
        super.sort(list);
        Heap<Integer> heap = new Heap<>();
        for (int num : list) {
            heap.add(num);
        }
        for (int i = 0; i < list.length; i++) {
            list[i] = heap.remove();
        }
    }
}
