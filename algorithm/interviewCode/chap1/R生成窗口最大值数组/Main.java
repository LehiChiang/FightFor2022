package interviewCode.chap1.R生成窗口最大值数组;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static int[] getMaxWindow(int[] array, int w) {
        if (array == null || w < 1 || array.length < w)
            return null;
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[array.length - w + 1];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            while (!deque.isEmpty() && deque.peekLast() <= array[i]) {
                deque.pollLast();
            }
            deque.offerLast(array[i]);
            if (i >= w && deque.peekFirst() == array[i - w]) {
                deque.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = deque.peekFirst();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        scanner.nextLine();
        String[] nums = scanner.nextLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(nums[i]);
        }
        int[] res = Main.getMaxWindow(array, w);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : res) {
            stringBuilder.append(i).append(" ");
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
