package leetcode.editor.cn;

import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class MyCalendarOffer2 {

    private TreeMap<Integer, Integer> map;

    public MyCalendarOffer2() {
        map = new TreeMap<>();
    }

    public static void main(String[] args) {
        MyCalendarOffer2 calendar = new MyCalendarOffer2();
        System.out.println(calendar.book(37, 50));
        System.out.println(calendar.book(33, 50));
        System.out.println(calendar.book(4, 17));
        System.out.println(calendar.book(35, 48));
        System.out.println(calendar.book(8, 25));
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> l = map.floorEntry(start);
        Map.Entry<Integer, Integer> r = map.ceilingEntry(start);
        if ((l == null || l.getValue() <= start) &&
                (r == null || r.getKey() >= end)) {
            map.put(start, end);
            return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
