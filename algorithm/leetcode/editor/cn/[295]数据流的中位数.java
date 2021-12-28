package leetcode.editor.cn;

//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
// 例如，
// [2,3,4] 的中位数是 3
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5
// 设计一个支持以下两种操作的数据结构：
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。
// 示例：
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2
// 进阶:
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 593 👎 0

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {

    private Queue<Integer> leftMaxQueue;
    private Queue<Integer> rightMinQueue;
    public MedianFinder() {
        leftMaxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        rightMinQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);
    }
    
    public void addNum(int num) {
        if (leftMaxQueue.isEmpty() || num <= leftMaxQueue.peek()) {
            leftMaxQueue.offer(num);
            if (leftMaxQueue.size() - rightMinQueue.size() > 1) {
                rightMinQueue.offer(leftMaxQueue.poll());
            }
        } else {
            rightMinQueue.offer(num);
            if (rightMinQueue.size() > leftMaxQueue.size()) {
                leftMaxQueue.offer(rightMinQueue.poll());
            }
        }
    }
    
    public double findMedian() {
        if (leftMaxQueue.size() - rightMinQueue.size() == 1) {
            return leftMaxQueue.peek();
        } else {
            return (leftMaxQueue.peek() + rightMinQueue.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(2);
        obj.addNum(3);
        obj.addNum(5);
        obj.addNum(8);
        obj.addNum(1);
        obj.addNum(7);
        System.out.println(obj.findMedian());
        obj.addNum(4);
        obj.addNum(6);
        System.out.println(obj.findMedian());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
