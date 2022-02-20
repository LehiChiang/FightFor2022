package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class findCircleNumOffer2Solution {
    class UnionFind {
        private int count;
        private int[] parent;
        public UnionFind(int count) {
            this.count = count;
            parent = new int[count];
            for (int i = 0; i < parent.length; i++)
                parent[i] = i;
        }

        public void union(int p, int q) {
            int rootP = findParent(p);
            int rootQ = findParent(q);
            if (rootP == rootQ)
                return;
            parent[rootP] = rootQ;
            count--;
        }

        private int findParent(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public int count() {
            return count;
        }
    }
    public int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1)
                    unionFind.union(i, j);
            }
        }
        return unionFind.count();
    }

    public static void main(String[] args) {
        findCircleNumOffer2Solution solution = new findCircleNumOffer2Solution();
        System.out.println(solution.findCircleNum(new int[][]{{1,0,0}, {0,1,0}, {0,0,1}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
