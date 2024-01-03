package leetcode.editor.cn;

import com.sun.source.tree.IfTree;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class MyCalendarOffer2 {

    private TreeMap<Integer, Integer> map;

    public MyCalendarOffer2() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> leftSchedule = map.floorEntry(start);
        Map.Entry<Integer, Integer> rightSchedule = map.ceilingEntry(start);
        if ((leftSchedule == null || start >= leftSchedule.getValue()) &&
                (rightSchedule == null || end <= rightSchedule.getKey())) {
            map.put(start, end);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MyCalendarOffer2 calendar = new MyCalendarOffer2();
        System.out.println(calendar.book(37, 50));
        System.out.println(calendar.book(33, 50));
        System.out.println(calendar.book(4, 17));
        System.out.println(calendar.book(35, 48));
        System.out.println(calendar.book(8, 25));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
