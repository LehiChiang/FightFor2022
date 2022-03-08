package multiThread.threadCommunication.MulPOneC;

class ProducerThread implements Runnable {
    private Producer producer;

    public ProducerThread(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            producer.pushService();
        }
    }
}
