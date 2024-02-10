package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Trie {

    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

    public void insert(String word) {
//        Trie node = this;
//        for (int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//            if (node.children[c - 'a'] == null) {
//                node.children[c - 'a'] = new Trie();
//            }
//            node = node.children[c - 'a'];
//        }
//        node.isEnd = true;
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new Trie();
            }
            node = node.children[c - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
//        Trie node = this;
//        for (int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//            if (node.children[c - 'a'] == null)
//                return false;
//            node = node.children[c - 'a'];
//        }
//        return node.isEnd == true;
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return node.isEnd == true;
    }

    public boolean startsWith(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
