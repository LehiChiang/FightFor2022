package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class lexicalOrderSolution {

    private List<Integer> res;
    public List<Integer> lexicalOrder(int n) {
        res = new ArrayList<>();
        dfs(0, 1, n);
        return res;
    }

    private void dfs(int base, int start, int n) {
        if (base > n)
            return;
        for (int i = start; i < 10; i++) {
            int num = base + i;
            if (num <= n) {
                res.add(num);
                dfs(num * 10, 0, n);
            }
        }
    }

    public static void main(String[] args) {
        lexicalOrderSolution solution = new lexicalOrderSolution();
        System.out.println(solution.lexicalOrder(24));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
