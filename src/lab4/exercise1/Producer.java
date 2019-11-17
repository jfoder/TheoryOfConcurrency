package lab4.exercise1;

import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

    private Semaphore semaphore;

    private Buffer buffer;

    public Producer(Buffer buffer, Semaphore semaphore) {
        this.buffer = buffer;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.buffer.getData().length; i++) {
            buffer.put(i);
            semaphore.release();
        }
    }
}