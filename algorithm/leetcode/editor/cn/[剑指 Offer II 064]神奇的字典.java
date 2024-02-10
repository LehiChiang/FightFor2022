package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MagicDictionaryOffer2 {


    private Trie trie;

    public MagicDictionaryOffer2() {
        trie = new Trie();
    }

    public static void main(String[] args) {
        MagicDictionaryOffer2 magicDictionary = new MagicDictionaryOffer2();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(magicDictionary.search("hello"));
        System.out.println(magicDictionary.search("hhllo"));
        System.out.println(magicDictionary.search("hell"));
        System.out.println(magicDictionary.search("helle"));
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary)
            insert(trie, word);
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

    public boolean search(String searchWord) {
        Trie node = trie;
        int count = 1;
        start:
        for (int i = 0; i < searchWord.length(); i++) {
            char ch = searchWord.charAt(i);
            if (count <= 0 && node.children[ch - 'a'] == null)
                return false;
            if (count > 0 && node.children[ch - 'a'] == null) {
                for (int j = 0; j < 26; j++) {
                    if (node.children[j] != null) {
                        count--;
                        node = node.children[j];
                        continue start;
                    }
                }
            }
            node = node.children[ch - 'a'];
            if (node == null)
                return false;
        }
        return node.isEnd && count == 0;
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
