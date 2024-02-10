package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class replaceWordsOffer2Solution {

    private Trie trie;

    public static void main(String[] args) {
        replaceWordsOffer2Solution solution = new replaceWordsOffer2Solution();
        System.out.println(
                solution.replaceWords(
                        new ArrayList<>() {{
                            add("a");
                            add("aa");
                            add("aaa");
                            add("aaaa");
                        }},
                        "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"));
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        trie = new Trie();
        String[] words = sentence.split(" ");
        // 构建字典前缀树
        for (String prefix : dictionary) {
            insert(trie, prefix);
        }
        for (int i = 0; i < words.length; i++) {
            if (search(trie, words[i])) {
                words[i] = replace(trie, words[i]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words)
            stringBuilder.append(word).append(" ");
        return stringBuilder.toString().trim();
    }

    private String replace(Trie trie, String word) {
        Trie node = trie;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.isEnd || node.children[ch - 'a'] == null) break;
            stringBuilder.append(ch);
            node = node.children[ch - 'a'];
        }
        return stringBuilder.toString();
    }

    private boolean search(Trie trie, String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.isEnd) return true;
            if (node.children[ch - 'a'] == null) return false;
            node = node.children[ch - 'a'];
        }
        return true;
    }

    private void insert(Trie trie, String prefix) {
        Trie node = trie;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (node.children[ch - 'a'] == null)
                node.children[ch - 'a'] = new Trie();
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
    }

    class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
