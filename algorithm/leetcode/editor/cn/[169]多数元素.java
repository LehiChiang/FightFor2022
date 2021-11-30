package leetcode.editor.cn;//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//
// 进阶： 
//
// 
// 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// 
// Related Topics 数组 哈希表 分治 计数 排序 👍 1216 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class majorityElementSolution {
    public int majorityElement(int[] nums) {
        return getByMoleVoting(nums);
    }

    private int getByMoleVoting(int[] nums) {
        int count = 0;
        int res = nums[0];
        for (int num : nums) {
            if (num == res)
                count++;
            else {
                count--;
            }
            if (count <= 0) {
                res = num;
                count = 1;
            }
        }
        return res;
    }

    private int getByMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0, len = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len / 2) {
                res = entry.getKey();
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        majorityElementSolution solution = new majorityElementSolution();
        System.out.println(solution.majorityElement(new int[]{2,2,1,1,1,2,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
