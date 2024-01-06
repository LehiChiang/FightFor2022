package out.bd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class OutBDArrayTest {

    int[] nums = new int[]{10, 9, 2, 5, 3, 7, 100, 18};
    OutBDArray outBDArray = new OutBDArray(nums);

    @Test
    void quickSelect() {
        int num = outBDArray.QuickSelect(2);
        System.out.println(num);
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

    @Test
    void lengthOfLongestIncreasingSubsequence() {
        int res = outBDArray.LIS();
        System.out.println(res);
    }


    @Test
    void LCNRS() {
        assert outBDArray.LCNRS("qwwaew") == 3;
    }
}