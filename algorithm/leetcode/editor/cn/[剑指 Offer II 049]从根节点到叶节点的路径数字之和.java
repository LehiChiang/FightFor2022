package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class sumNumbersOffer2Solution {
    public int sumNumbers(TreeNode root) {
         return getSum(root, 0);
    }

    private int getSum(TreeNode root, int preSum) {
        if (root == null)
            return 0;
        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null)
            return sum;
        else
            return getSum(root.left, sum) + getSum(root.right, sum);
    }

    public static void main(String[] args) {
        sumNumbersOffer2Solution solution = new sumNumbersOffer2Solution();
        System.out.println(solution.sumNumbers(TreeNode.deserialize("1,2,3")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
