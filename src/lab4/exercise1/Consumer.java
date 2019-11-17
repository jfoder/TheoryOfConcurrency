package lab4.exercise1;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

    private Semaphore semaphore;

    private Buffer buffer;

    public Consumer(Buffer buffer, Semaphore semaphore) {
        this.buffer = buffer;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.buffer.getData().length; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(buffer.take());
        }
    }
}
