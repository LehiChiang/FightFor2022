package datastructure;

/**
 * 并查集实现
 *
 * @author LehiChiang
 */
public class UnionFind {
    private int[] parents;
    // 数据个数
    private int count;
    private int[] rank;   // rank[i]表示以i为根的集合所表示的树的层数

    public UnionFind(int totalNodes) {
        count = totalNodes;
        parents = new int[totalNodes];
        rank = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        if (rank[pRoot] < rank[qRoot]) {
            parents[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parents[qRoot] = pRoot;
        } else { // rank[pRoot] == rank[qRoot]
            parents[pRoot] = qRoot;
            rank[qRoot] += 1;   // 维护rank的值
        }
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    int find(int node) {
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[node] == node
        while (parents[node] != node) {
            // 当前节点的父节点 指向父节点的父节点.
            // 保证一个连通区域最终的parents只有一个.
            parents[node] = parents[parents[node]];
            node = parents[node];  // 路径压缩
        }

        return node;
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }
}
