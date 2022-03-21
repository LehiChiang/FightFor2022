package multiThread.threadCommunication.OnePOneC;

class Consumer {
    private SingleElemStack stack;

    public Consumer(SingleElemStack stack) {
        super();
        this.stack = stack;
    }

    public void popService() {
        stack.pop();
    }
}

class Producer {
    private SingleElemStack stack;

    public Producer(SingleElemStack stack) {
        super();
        this.stack = stack;
    }

    public void pushService() {
        stack.push();
    }
}

public class Run {
    public static void main(String[] args) {
        SingleElemStack stack = new SingleElemStack();
        Producer producer = new Producer(stack);
        Consumer consumer = new Consumer(stack);

        new Thread(() -> {
            for (int i = 0; i < 1; i++) {
                producer.pushService();
            }
        }, "生产者线程1").start();

        new Thread(() -> {
            for (int i = 0; i < 1; i++) {
                consumer.popService();
            }
        }, "消费者线程1").start();
    }
}
