package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class minimumLengthEncodingOffer2Solution {


    public static void main(String[] args) {
        minimumLengthEncodingOffer2Solution solution = new minimumLengthEncodingOffer2Solution();
        System.out.println(solution.minimumLengthEncoding(new String[]{"t"}));
    }

    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        int res = 0;
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        for (int i = 0; i < words.length; i++) {
            res += trie.insert(words[i]);
        }
        return res;
    }

    class Trie {
        Trie[] children;
        int count;

        public Trie() {
            children = new Trie[26];
            count = 0;
        }

        public int insert(String word) {
            Trie node = this;
            boolean isNewWord = false;
            for (int i = word.length() - 1; i >= 0; i--) {
                char ch = word.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    isNewWord = true;
                    node.children[ch - 'a'] = new Trie();
                }
                node = node.children[ch - 'a'];
            }
            return isNewWord ? word.length() + 1 : 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
