package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class relativeSortArrayOffer2Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        int[] unseen = new int[1001];
        for (int num : arr2)
            map.put(num, 0);
        for (int num : arr1) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                unseen[num]++;
        }
        int[] res = new int[arr1.length];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int j = 0; j < entry.getValue(); j++) {
                res[index++] = entry.getKey();
            }
        }
        for (int j = 0; j < unseen.length; j++) {
            while (unseen[j] != 0) {
                res[index++] = j;
                unseen[j]--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        relativeSortArrayOffer2Solution solution = new relativeSortArrayOffer2Solution();
        System.out.println(Arrays.toString(solution.relativeSortArray(new int[]{2,3,1,3,2,4,6,7,9,2,19}, new int[]{2,1,4,3,9,6})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
