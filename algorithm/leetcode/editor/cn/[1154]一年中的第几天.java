package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class dayOfYearSolution {
    public int dayOfYear(String date) {
        int[] days = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] dates = date.split("-");
        int dayTh = 0;
        int year = Integer.parseInt(dates[0]), month = Integer.parseInt(dates[1]), day = Integer.parseInt(dates[2]);
        for (int i = 0; i < month; i++) {
            dayTh += days[i];
        }
        dayTh += day;
        if (((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) && month > 2)
            dayTh += 1;
        return dayTh;
    }

    public static void main(String[] args) {
        dayOfYearSolution solution = new dayOfYearSolution();
        System.out.println(solution.dayOfYear("1900-05-02"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
