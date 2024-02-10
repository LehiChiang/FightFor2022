package leetcode.editor.cn;


//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 

// 提示: 
//
// 
// 0 < nums.length <= 100 

// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 贪心 字符串 排序 👍 350 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class minNumberSolution {

    private List<String> list;

    public static void main(String[] args) {
        minNumberSolution solution = new minNumberSolution();
        System.out.println(solution.minNumber(new int[]{0, 3, 2, 1}));
    }

    public String minNumber(int[] nums) {
        list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        quicksort(list, 0, list.size() - 1);
        System.out.println(list);
        return list.stream().collect(Collectors.joining());
    }

    private void quicksort(List<String> list, int start, int end) {
        if (start >= end)
            return;
        int index = partition(list, start, end);
        quicksort(list, start, index - 1);
        quicksort(list, index + 1, end);
    }

    private int partition(List<String> list, int start, int end) {
        int i = start, j = end;
        String pivot = list.get(i);
        while (i < j) {
            while (i < j && (list.get(j) + pivot).compareTo(pivot + list.get(j)) >= 0) j--;
            list.set(i, list.get(j));
            while (i < j && (list.get(i) + pivot).compareTo(pivot + list.get(i)) <= 0) i++;
            list.set(j, list.get(i));
        }
        list.set(j, pivot);
        return j;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
