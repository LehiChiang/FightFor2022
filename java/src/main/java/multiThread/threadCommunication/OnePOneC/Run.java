package multiThread.threadCommunication.OnePOneC;

public class Run {
    public static void main(String[] args) {
        SingleElemStack stack = new SingleElemStack();
        Producer producer = new Producer(stack);
        Consumer consumer = new Consumer(stack);

        Thread producerThread = new Thread(new ProducerThread(producer));
        producerThread.setName("生产者线程1");
        producerThread.start();

        Thread consumerThread = new Thread(new ConsumerThread(consumer));
        consumerThread.setName("消费者线程1");
        consumerThread.start();
    }
}
