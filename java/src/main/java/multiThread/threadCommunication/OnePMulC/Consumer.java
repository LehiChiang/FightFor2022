package multiThread.threadCommunication.OnePMulC;

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