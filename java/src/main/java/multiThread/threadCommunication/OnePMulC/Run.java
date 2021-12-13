package multiThread.threadCommunication.OnePMulC;

public class Run {
    public static void main(String[] args) {
        SingleElemStack stack = new SingleElemStack();
        Producer producer = new Producer(stack);
        Consumer consumer = new Consumer(stack);

        Thread producerThread = new Thread(new ProducerThread(producer));
        producerThread.setName("生产者1");
        producerThread.start();

        Thread consumerThread1 = new Thread(new ConsumerThread(consumer));
        consumerThread1.setName("消费者1");
        consumerThread1.start();

        Thread consumerThread2 = new Thread(new ConsumerThread(consumer));
        consumerThread2.setName("消费者2");
        consumerThread2.start();
    }
}
