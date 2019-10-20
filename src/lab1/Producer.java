package lab1;

public class Producer implements Runnable {

    private static final int ITERATION_NUMBER = 1000;

    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < ITERATION_NUMBER; i++) {
            buffer.put("message " + i);
        }
    }
}