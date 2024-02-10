package leetcode.editor.cn;


//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
// 假设输入的数组的任意两个数字都互不相同。
// 参考以下这颗二叉搜索树： 
//
//      5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1：
// 输入: [1,6,3,2,5]
//输出: false
// 示例 2：
// 输入: [1,3,2,6,5]
//输出: true
// 提示：
// 数组长度 <= 1000 
// 
// Related Topics 栈 树 二叉搜索树 递归 二叉树 单调栈 👍 395 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class verifyPostorderSolution {
    public static void main(String[] args) {
        verifyPostorderSolution solution = new verifyPostorderSolution();
        System.out.println(solution.verifyPostorder(new int[]{5, 6, 4}));
    }

    public boolean verifyPostorder(int[] postorder) {
        Deque<Integer> stack = new LinkedList<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root)
                return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.push(postorder[i]);
        }
        return true;
    }

    private boolean dfs(int[] postorder, int start, int end) {
        if (start >= end)
            return true;
        int m = start;
        while (postorder[m] < postorder[end]) m++;
        int n = m;
        while (postorder[n] > postorder[end]) n++;
        return n == end && dfs(postorder, start, m - 1) && dfs(postorder, m, n - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
