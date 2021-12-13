package multiThread.threadCommunication.MulPMulC;

class ProducerThread implements Runnable {
    private Producer producer;

    public ProducerThread(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            producer.pushService();
        }
    }
}
