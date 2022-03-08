package multiThread.threadCommunication.OnePMulC;

import java.util.LinkedList;
import java.util.List;

class SingleElemStack {
    private List<Integer> list = new LinkedList<>();

    synchronized public void push() {
        try {
            while (list.size() == 1) {
                System.out.println(Thread.currentThread().getName() + "---" + "list已满，push操作wait中！");
                this.wait();
            }
            int num = (int) (Math.random() * 10);
            list.add(num);
            System.out.println(Thread.currentThread().getName() + "---" + "push " + num + "!");
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void pop() {
        try {
            while (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + "---" + "list已空，pop操作wait中！");
                this.wait();
            }
            int num = list.get(0);
            list.remove(0);
            System.out.println(Thread.currentThread().getName() + "---" + "pop " + num + "!");
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
