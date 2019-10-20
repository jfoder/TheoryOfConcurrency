package lab1;

public class Buffer {

    private String storedData;

    private boolean waitingForProducer = true;

    public synchronized void put(String data) {
        while (!waitingForProducer) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        waitingForProducer = false;
        this.storedData = data;
        notifyAll();
    }

    public synchronized String take() {
        while (waitingForProducer) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        waitingForProducer = true;
        String result = storedData;
        this.storedData = null;
        notifyAll();
        return result;
    }
}
