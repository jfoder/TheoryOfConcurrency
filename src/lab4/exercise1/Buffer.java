package lab4.exercise1;

public class Buffer {

    private int[] data;

    private int readIterator;

    private int writeIterator;

    private boolean waitingForProducer = true;

    public Buffer(int size) {
        this.data = new int[size];
        this.readIterator = 0;
        this.writeIterator = 0;
    }

    public int[] getData() {
        return this.data;
    }

    public void put(int dataValue) {
        this.data[writeIterator++] = dataValue;
    }

    public int take() {
        int result = data[readIterator];
        data[readIterator++] = -1;
        return result;
    }
//    public synchronized void put(int dataValue) {
//        while (!waitingForProducer) {
//            try {
//                wait();
//            } catch (InterruptedException ignored) {
//            }
//        }
//        waitingForProducer = false;
//        this.data[writeIterator++] = dataValue;
//        notifyAll();
//    }
//
//    public synchronized int take() {
//        while (waitingForProducer) {
//            try {
//                wait();
//            } catch (InterruptedException ignored) {
//            }
//        }
//        waitingForProducer = true;
//        int result = data[readIterator];
//        data[readIterator++] = -1;
//        notifyAll();
//        return result;
//    }
}
