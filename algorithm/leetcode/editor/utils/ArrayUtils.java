package leetcode.editor.utils;

import leetcode.editor.datastructure.ListNode;

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

    public static void printCharArray(char[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (char c :
                array) {
            sb.append(c).append(',');
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }

    public static void printStringArray(String[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (String c :
                array) {
            sb.append(c).append(',');
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }

    public static void printIntArray2D(int[][] array) {
        for (int i = 0 ; i < array.length ; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int num : array[i]) {
                sb.append(num).append(',');
            }
            sb.replace(sb.length() - 1, sb.length(), "]");
            System.out.println(sb.toString());
        }
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

    public static void printIntLinkedList(ListNode list) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (list != null){
            sb.append(list.val).append(',');
            list = list.next;
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }
}
