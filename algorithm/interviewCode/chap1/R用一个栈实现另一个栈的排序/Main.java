package interviewCode.chap1.R用一个栈实现另一个栈的排序;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void sortStack(Stack<Integer> stack) {
        Stack<Integer> sortStack = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!sortStack.isEmpty() && cur > sortStack.peek()) {
                stack.push(sortStack.pop());
            }
            sortStack.push(cur);
        }
        while (!sortStack.isEmpty()) {
            stack.push(sortStack.pop());
        }
    }
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
        String[] nums = scanner.nextLine().split(" ");
        for (int i = 0; i < count; i++) {
            stack.add(Integer.parseInt(nums[i]));
        }
        Main.sortStack(stack);
        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
