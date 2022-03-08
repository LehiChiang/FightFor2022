package multiThread.threadCommunication.OnePMulC;

class ProducerThread implements Runnable {
    private Producer producer;

    public ProducerThread(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            producer.pushService();
        }
    }
}
