package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class expandSoltion {

    private List<String> chars;
    private List<String> res;
    public List<String> expand(String s) {
        chars = new ArrayList<>();
        res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                StringBuilder sb = new StringBuilder();
                while (i + 1 < s.length() && s.charAt(++i) != '}') {
                    if (s.charAt(i) == ',')
                        continue;
                    sb.append(s.charAt(i));
                }
                chars.add(sb.toString());
            } else {
                chars.add(String.valueOf(s.charAt(i)));
            }
        }
        dfs(0, new StringBuilder());
        return res;
    }

    private void dfs(int index, StringBuilder stringBuilder) {
        if (index == chars.size()) {
            res.add(stringBuilder.toString());
            return;
        }
        String curString = chars.get(index);
        for (int i = 0; i < curString.length(); i++) {
            stringBuilder.append(curString.charAt(i));
            dfs(index + 1, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    public static void main(String[] args) {
        expandSoltion soltion = new expandSoltion();
        System.out.println(soltion.expand("{a,b}c{d,e}f"));
    }
}
