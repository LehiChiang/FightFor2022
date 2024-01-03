package out.bd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class OutBDArrayTest {

    int[] nums = new int[]{4, 5, 0, -2, -3, 1};
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

    @Test
    void subarraysDivByK() {
        int res = outBDArray.SubarraysDivByK(5);
        System.out.println(res);
    }
}