package out.bd;

import org.junit.jupiter.api.Test;
import utils.ArrayUtils;

import java.util.Arrays;
import java.util.List;

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

    @Test
    void searchInRotatedSortedArray() {
        int[] array = new int[]{9, 12, 3, 5, 7};
        int res = outBDArray.SearchInRotatedSortedArray(array, 1);
        System.out.println(res);
    }

    @Test
    void findMinimumInRotatedSortedArray() {
        int[] array = new int[]{2, 3, 4, 5, 6};
        int res = outBDArray.FindMinimumInRotatedSortedArray(array);
        System.out.println(res);
    }

    @Test
    void NQueens() {
        for (List<String> queen : outBDArray.NQueens(12)) {
            System.out.println(queen);
        }
    }

    @Test
    void shortestUnsortedContinuousSubarray() {
        int[] array = new int[]{2, 6, 4, 8, 10, 9, 15};
        int res = outBDArray.ShortestUnsortedContinuousSubarray(array);
        System.out.println(res);
    }

    @Test
    void largestRectangleArea() {
        int[] array = new int[]{2, 1, 5, 6, 2, 3};
        int res = outBDArray.LargestRectangleArea(array);
        System.out.println(res);
    }

    @Test
    void trap() {
        int[] array = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int res = outBDArray.Trap(array);
        System.out.println(res);
    }
}