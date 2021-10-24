package interviewCode.chap1.设计一个有getMin功能的栈;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    private final Stack<Integer> stackData;
    private final Stack<Integer> stackMin;

    public Main() {
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int number) {
        if (stackMin.isEmpty()) {
            stackMin.push(number);
            stackData.push(number);
        }
        else {
            stackData.push(number);
            stackMin.push(Math.min(number, getMin()));
        }
    }

    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        stackMin.pop();
        return stackData.pop();
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stackMin.peek();
    }

    public static void main(String[] args) {
        Main solution = new Main();
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            String operation = scanner.nextLine();
            if (operation.equals("push")) {
                int number = scanner.nextInt();
                solution.push(number);
            } else if (operation.equals("pop")) {
                System.out.println(solution.pop());
            } else if (operation.equals("getMin")) {
                System.out.println(solution.getMin());
            } else if (operation.equals("quit")) {
                break;
            } else {
                i -= 1;
            }
        }
    }
}
