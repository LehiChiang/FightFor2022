package out.bd;

import org.junit.jupiter.api.Test;
import utils.ArrayUtils;

import java.util.Arrays;

class OutBDArrayTest {

    int[] nums = new int[]{1, 2};
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

    @Test
    void threeSum() {
        ArrayUtils.print(outBDArray.ThreeSum().toArray());
    }

    @Test
    void threeSumClosest() {
        System.out.println(outBDArray.ThreeSumClosest(-2));
    }

    @Test
    void nextPermutation() {
        outBDArray.NextPermutation();
        System.out.println(Arrays.toString(outBDArray.getNums()));
    }

    @Test
    void rob() {
        System.out.println(outBDArray.Rob());
    }

    @Test
    void sortColors() {
        int[] array = new int[]{1, 2, 0, 2, 1, 1, 0, 0};
        outBDArray.sortColors(array);
        System.out.println(Arrays.toString(array));
    }
}