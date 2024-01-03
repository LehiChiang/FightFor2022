package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class RandomizedCollection {

    private List<Integer> list;
    private Map<Integer, Set<Integer>> map;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public boolean insert(int val) {
        list.add(val);
        Set<Integer> deque = map.getOrDefault(val, new HashSet<Integer>());
        deque.add(list.size() - 1);
        map.put(val, deque);
        return deque.size() == 1;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        Iterator<Integer> it = map.get(val).iterator();
        int i = it.next();
        int lastNum = list.get(list.size() - 1);
        list.set(i, lastNum);
        map.get(val).remove(i);
        map.get(lastNum).remove(list.size() - 1);
        if (i < list.size() - 1)
            map.get(lastNum).add(i);
        if (map.get(val).size() == 0)
            map.remove(val);
        list.remove(list.size() - 1);
        return false;
    }
    
    public int getRandom() {
        return list.get((int) (Math.random() * list.size()));
    }

    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();
        boolean param_1 = obj.insert(4);
        boolean param_2 = obj.insert(3);
        boolean param_3 = obj.insert(4);
        boolean param_4 = obj.insert(2);
        boolean param_5 = obj.insert(4);
        obj.remove(4);
        obj.remove(3);
        obj.remove(4);
        obj.remove(4);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)
