package leetcode.editor.cn;//给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[
//i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。 
//
// 
//
// 示例: 
//
// 
//输入: [1,2,3,4,5]
//输出: [120,60,40,30,24] 
//
// 
//
// 提示： 
//
// 
// 所有元素乘积之和不会溢出 32 位整数 
// a.length <= 100000 
// 
// Related Topics 数组 前缀和 👍 178 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class constructArrSolution {
    public int[] constructArr(int[] a) {
        int[] leftPart = new int[a.length];
        leftPart[0] = 1;
        for (int i = 1; i < leftPart.length; ++i)
            leftPart[i] = leftPart[i - 1] * a[i - 1];
        int tmp = 1;
        for (int i = leftPart.length - 2; i >= 0; --i) {
            tmp *= a[i + 1];
            leftPart[i] = leftPart[i] * tmp;
        }
        return leftPart;
    }

    public static void main(String[] args) {
        constructArrSolution solution = new constructArrSolution();
        System.out.println(Arrays.toString(solution.constructArr(new int[]{1,2,3,4,5})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
