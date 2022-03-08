package multiThread.threadCommunication.MulPMulC;

public class Run {
    public static void main(String[] args) {
        SingleElemStack stack = new SingleElemStack();
        Producer producer = new Producer(stack);
        Consumer consumer = new Consumer(stack);

        Thread producerThread1 = new Thread(new ProducerThread(producer));
        producerThread1.setName("生产者1");
        producerThread1.start();

        Thread producerThread2 = new Thread(new ProducerThread(producer));
        producerThread2.setName("生产者2");
        producerThread2.start();

        Thread consumerThread1 = new Thread(new ConsumerThread(consumer));
        consumerThread1.setName("消费者1");
        consumerThread1.start();

        Thread consumerThread2 = new Thread(new ConsumerThread(consumer));
        consumerThread2.setName("消费者2");
        consumerThread2.start();
    }
}
