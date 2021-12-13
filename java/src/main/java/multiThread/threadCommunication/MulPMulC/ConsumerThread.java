package multiThread.threadCommunication.MulPMulC;

class ConsumerThread implements Runnable {
    private Consumer consumer;

    public ConsumerThread(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            consumer.popService();
        }
    }
}
