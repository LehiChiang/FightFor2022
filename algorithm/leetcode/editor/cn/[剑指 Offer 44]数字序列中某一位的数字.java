package leetcode.editor.cn;//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
//等等。 
//
// 请写一个函数，求任意第n位对应的数字。 
//
// 
//
// 示例 1： 
//
// 输入：n = 3
//输出：3
// 
//
// 示例 2： 
//
// 输入：n = 11
//输出：0 
//
// 
//
// 限制： 
//
// 
// 0 <= n < 2^31 
// 
//
// 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/ 
// Related Topics 数学 二分查找 👍 188 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class findNthDigitSolution {
    public int findNthDigit(int n) {
        int digit = 1;
        int numDigit = 9;
        // 开始求是几位数字
        while (n > digit * numDigit) {
            n -= numDigit * digit++;
            numDigit *= 10;
            if(Integer.MAX_VALUE / numDigit < digit){
                break;
            }
        }
        // 开始求是哪个数字num
        int num = (int) (Math.pow(10, digit - 1)) + (n - 1) / digit;
        // 开始求第几位iDigit
        int iDigit = (n - 1) % digit;
        // 那就是num的第iDigit位置(从高位计算)
        return num / (int) (Math.pow(10, digit - iDigit - 1)) % 10;
    }

    public static void main(String[] args) {
        findNthDigitSolution solution = new findNthDigitSolution();
        System.out.println(solution.findNthDigit(2147483647));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
