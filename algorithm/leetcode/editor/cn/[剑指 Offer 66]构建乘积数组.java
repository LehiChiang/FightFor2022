package leetcode.editor.cn;

//给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[
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
    public static void main(String[] args) {
        constructArrSolution solution = new constructArrSolution();
        System.out.println(Arrays.toString(solution.constructArr(new int[]{1, 2, 3, 4, 5})));
    }

//    public int[] constructArr(int[] a) {
//        int n = a.length;
//        int[] leftRes = new int[n];
//        leftRes[0] = a[0];
//        int[] rightRes = new int[n];
//        rightRes[n - 1] = a[n - 1];
//        for (int i = 1; i < n; i++) {
//            leftRes[i] = a[i] * leftRes[i - 1];
//            rightRes[n - i - 1] = a[n - i - 1] * rightRes[n - i];
//        }
//        int[] res = new int[n];
//        res[0] = rightRes[1];
//        res[n - 1] = leftRes[n - 2];
//        for (int i = 1; i < n - 1; i++) {
//            res[i] = leftRes[i - 1] * rightRes[i + 1];
//        }
//        return res;
//    }

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
}
//leetcode submit region end(Prohibit modification and deletion)
