package leetcode.editor.cn;
//给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
// 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
// 示例 1:
// 输入: [[1,1],2,[1,1]]
//输出: [1,1,2,1,1]
//解释: 通过重复调用next 直到hasNext 返回 false，next返回的元素的顺序应该是: [1,1,2,1,1]。
// 示例 2:
// 输入: [1,[4,[6]]]
//输出: [1,4,6]
//解释: 通过重复调用next直到hasNext 返回 false，next返回的元素的顺序应该是: [1,4,6]。
// Related Topics 栈 树 深度优先搜索 设计 队列 迭代器 
// 👍 346 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import leetcode.editor.datastructure.NestedInteger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class NestedIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    public NestedIterator(List<NestedInteger> nestedList) {
        LinkedList<Integer> res = new LinkedList<>();
        for (NestedInteger integer : nestedList) {
            traverse(integer, res);
        }
        iterator = res.iterator();
    }

    private void traverse(NestedInteger integer, LinkedList<Integer> res) {
        if (integer.isInteger()) {
            res.add(integer.getInteger());
            return;
        }
        for (NestedInteger nestedInteger : integer.getList()) {
            traverse(nestedInteger, res);
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
//leetcode submit region end(Prohibit modification and deletion)
