package leetcode.editor.cn;//从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，
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


//leetcode submit region begin(Prohibit modification and deletion)
class isStraightSolution {
    public boolean isStraight(int[] nums) {
        int[] poker = new int[14];
        for (int num : nums) {
            poker[num]++;
        }
        int zeros = poker[0];
        int dis = 0, lastNum = 0;
        for (int i = 1; i < poker.length; i++) {
            if (poker[i] > 1) return false;
            else if (poker[i] == 1) {
                if (lastNum != 0)
                    dis = Math.max(dis, i - lastNum);
                lastNum = i;
            }
        }
        return dis - 1 <= zeros;
    }

    public static void main(String[] args) {
        isStraightSolution solution = new isStraightSolution();
        System.out.println(solution.isStraight(new int[]{2,4,5,7,9}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
