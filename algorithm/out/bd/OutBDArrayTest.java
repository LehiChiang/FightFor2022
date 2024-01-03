package out.bd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class OutBDArrayTest {

    int[] nums = new int[]{4,2,5,1,6,3};
    OutBDArray outBDArray = new OutBDArray(nums);
    @Test
    void quickSelect() {
        int num = outBDArray.QuickSelect(2);
        assert num == 5;
    }

    @Test
    void quickSort() {
        outBDArray.QuickSort();
        System.out.println(Arrays.toString(Arrays.stream(nums).toArray()));
    }
}