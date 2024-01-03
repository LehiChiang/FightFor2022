package leetcode.editor.cn;//给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
//
// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下： 
//
// 
// "123" 
// "132" 
// "213" 
// "231" 
// "312" 
// "321" 
// 
//
// 给定 n 和 k，返回第 k 个排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3, k = 3
//输出："213"
// 
//
// 示例 2： 
//
// 
//输入：n = 4, k = 9
//输出："2314"
// 
//
// 示例 3： 
//
// 
//输入：n = 3, k = 1
//输出："123"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 1 <= k <= n! 
// 
// Related Topics 递归 数学 👍 609 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class getPermutationSolution {

    private StringBuilder stringBuilder;
    private boolean[] visited;
    private int[] factorial;
    public String getPermutation(int n, int k) {
        calculateFactorial(n);
        stringBuilder = new StringBuilder();
        visited = new boolean[n + 1];
        dfs(n, k, 0);
        return stringBuilder.toString();
    }

    private void dfs(int n, int k, int index) {
        if (index == n)
            return;

        for (int i = 1; i <= n; i++) {
            if (visited[i])
                continue;
            int fact = factorial[n - index - 1];
            if (fact < k) {
                k -= fact;
                continue;
            }
            visited[i] = true;
            stringBuilder.append(i);
            dfs(n, k, index + 1);
            return;
        }
    }

    private void calculateFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i -1];
        }
    }

    public static void main(String[] args) {
        getPermutationSolution solution = new getPermutationSolution();
        for ( int i = 1; i < 20; i++)
            System.out.println(solution.getPermutation(4, i));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
