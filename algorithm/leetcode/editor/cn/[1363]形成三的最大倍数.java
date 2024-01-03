package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class largestMultipleOfThreeSolution {
    public String largestMultipleOfThree(int[] digits) {
        int[] digitCnt = new int[10];
        int[] modCnt = new int[3];
        int totalSum = 0;
        for (int digit : digits) {
            totalSum += digit;
            digitCnt[digit]++;
            modCnt[digit % 3]++;
        }
        int delMod = 0, delCnt = 0;
        if (totalSum % 3 == 1) {
            if (modCnt[1] >= 1) {
                delMod = 1;
                delCnt = 1;
            } else {
                delMod = 2;
                delCnt = 2;
            }
        } else if (totalSum % 3 == 2) {
            if (modCnt[2] >= 1) {
                delMod = 2;
                delCnt = 1;
            } else {
                delMod = 1;
                delCnt = 2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < digitCnt[i]; j++) {
                if (delCnt > 0 && i % 3 == delMod)
                    delCnt--;
                else sb.append((char) (i + '0'));
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0')
            return "0";
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        largestMultipleOfThreeSolution solution = new largestMultipleOfThreeSolution();
        System.out.println(solution.largestMultipleOfThree(new int[]{8,1,9}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
