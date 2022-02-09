package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class restoreIpAddressesOffer2Solution {

    private List<String> res;
    private int[] segments;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12)
            return res;
        segments = new int[4];
        dfs(s, 0, 0);
        return res;
    }

    private void dfs(String s, int segStart, int segId) {
        if (segId == 4) {
            if (segStart == s.length()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    stringBuilder.append(segments[i]).append(".");
                }
                res.add(stringBuilder.append(segments[3]).toString());
            }
            return;
        }
        if (segStart == s.length())
            return;
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segStart + 1, segId + 1);
        }
        int ip = 0;
        for (int i = segStart; i < s.length(); i++) {
            ip = ip * 10 + s.charAt(i) - '0';
            segments[segId] = ip;
            if (ip > 0 && ip <= 0xFF) {
                dfs(s, i + 1, segId + 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        restoreIpAddressesOffer2Solution solution = new restoreIpAddressesOffer2Solution();
        System.out.println(solution.restoreIpAddresses("25525511135"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
