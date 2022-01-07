package leetcode.editor.cn;//将非负整数 num 转换为其对应的英文表示。
//
// 
//
// 示例 1： 
//
// 
//输入：num = 123
//输出："One Hundred Twenty Three"
// 
//
// 示例 2： 
//
// 
//输入：num = 12345
//输出："Twelve Thousand Three Hundred Forty Five"
// 
//
// 示例 3： 
//
// 
//输入：num = 1234567
//输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
// 
//
// 示例 4： 
//
// 
//输入：num = 1234567891
//输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven 
//Thousand Eight Hundred Ninety One"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= num <= 2³¹ - 1 
// 
// Related Topics 递归 数学 字符串 👍 247 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class numberToWordsSolution {

    private String[] thousands;
    private String[] digits;
    private String[] tens;
    private String[] tys;

    public numberToWordsSolution() {
        thousands = new String[]{"", "Thousand", "Million", "Billion"};
        digits = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        tens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        tys = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    }

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 3, unit = 1_000_000_000; i >= 0; i--, unit /= 1_000) {
            int curNum = num / unit;
            if (curNum != 0) {
                String name = getHundredName(curNum);
                stringBuilder.append(name).append(" ").append(thousands[i]).append(" ");
                num %= unit;
            }
        }
        return stringBuilder.toString().trim();
    }

    private String getHundredName(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(digits[num / 100]).append(" Hundred").append(" ");
            num %= 100;
        }
        if (num >= 20 && num < 100) {
            sb.append(tys[num / 10]).append(" ").append(digits[num % 10]).append(" ");
        }
        if (num >= 10 && num < 20) {
            sb.append(tens[num % 10]).append(" ");
        }
        if (num > 0 && num < 10){
            sb.append(digits[num]).append(" ");
        }
        if (num == 0){
            sb.append(digits[num]);
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        numberToWordsSolution solution = new numberToWordsSolution();
        System.out.println(solution.numberToWords(123));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
