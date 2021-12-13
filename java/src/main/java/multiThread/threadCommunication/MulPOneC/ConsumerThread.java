package multiThread.threadCommunication.MulPOneC;

class ConsumerThread implements Runnable {
    private Consumer consumer;

    public ConsumerThread(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            consumer.popService();
        }
    }
}
