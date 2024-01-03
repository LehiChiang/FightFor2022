package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class RangeFreqQuery {

    Map<Integer, List<Integer>> map;
    public RangeFreqQuery(int[] arr) {
       map = new HashMap<>();
       for (int i = 0; i < arr.length; i++) {
           List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
           list.add(i);
           map.put(arr[i], list);
       }
    }
    
    public int query(int left, int right, int value) {
        if (!map.containsKey(value))
            return 0;
        List<Integer> idxList = map.get(value);
        int l = binarySearch(idxList, left, true, false);
        int r = binarySearch(idxList, right, false, true);
        return r - l;
    }

    private int binarySearch(List<Integer> nums, int num, boolean fl, boolean fr) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) > num)
                right = mid;
            else if (nums.get(mid) < num)
                left = mid + 1;
            else {
                if (fl)
                    right = mid;
                if (fr)
                    left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        RangeFreqQuery solution = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        System.out.println(solution.query(1, 2, 4));
        System.out.println(solution.query(0, 11, 33));
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
