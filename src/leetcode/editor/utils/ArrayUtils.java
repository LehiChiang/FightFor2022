package leetcode.editor.utils;

import java.util.ArrayList;

public class ArrayUtils {

    public static void printIntArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int num :
                array) {
            sb.append(num).append(',');
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }

    public static int[] newIntArray(int... arrays) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : arrays)
            list.add(num);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i);
        return res;
    }
}
