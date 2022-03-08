package multiThread.threadCommunication.MulPOneC;

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
