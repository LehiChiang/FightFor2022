package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class MapSumOffer2 {

    class Trie {
        Trie[] children;
        int sum;

        public Trie() {
            children = new Trie[26];
            sum = 0;
        }

        public void insert(String key, int val) {
            Trie node = this;
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                if (node.children[ch - 'a'] == null)
                    node.children[ch - 'a'] = new Trie();
                node = node.children[ch - 'a'];
                node.sum += val;
            }
        }

        public int getSum(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (node.children[ch - 'a'] == null)
                    return 0;
                node = node.children[ch - 'a'];
            }
            return node.sum;
        }
    }

    private Trie trie;
    private Map<String, Integer> map;
    public MapSumOffer2() {
        trie = new Trie();
        map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        trie.insert(key, delta);
    }
    
    public int sum(String prefix) {
        return trie.getSum(prefix);
    }

    public static void main(String[] args) {
        MapSumOffer2 mapSum = new MapSumOffer2();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("apple"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
