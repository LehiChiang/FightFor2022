package leetcode.editor.cn;

//从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
// 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，
//可以看成任意数字。A 不能视为 14。 
//
// 
//
// 示例 1: 
//
// 
//输入: [1,2,3,4,5]
//输出: True 
//
// 
//
// 示例 2: 
//
// 
//输入: [0,0,1,2,5]
//输出: True 
//
// 
//
// 限制： 
//
// 数组长度为 5 
//
// 数组的数取值为 [0, 13] . 
// Related Topics 数组 排序 👍 187 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class isStraightSolution {
    public static void main(String[] args) {
        isStraightSolution solution = new isStraightSolution();
        System.out.println(solution.isStraight(new int[]{0, 4, 5, 7, 6}));
    }

    public boolean isStraight(int[] nums) {
        int unknown = 0;
        Arrays.sort(nums); // 数组排序
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) unknown++; // 统计未知朝代数量
            else if (nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
        }
        return nums[4] - nums[unknown] < 5; // 最大编号朝代 - 最小编号朝代 < 5 则连续
    }
}
//leetcode submit region end(Prohibit modification and deletion)
