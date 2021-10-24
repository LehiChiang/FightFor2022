package interviewCode.chap1.单调栈结构1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int[][] getNearLessNoRepeat(int[] nums) {
        int[][] res = new int[nums.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                int index = stack.pop();
                res[index][0] = stack.isEmpty() ? -1 : stack.peek();
                res[index][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            res[index][0] = stack.isEmpty() ? -1 : stack.peek();
            res[index][1] = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
        String[] nums = scanner.nextLine().split(" ");
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = Integer.parseInt(nums[i]);
        }
        int[][] res = Main.getNearLessNoRepeat(array);
        for (int[] arr : res) {
            System.out.println(arr[0] + " " + arr[1]);
        }
    }
}
