package lab4.exercise1;

import java.util.concurrent.Semaphore;

public class CellProcessor implements Runnable {

    private Semaphore nextProcessorSemaphore;

    private Semaphore previousProcessorSemaphore;

    private Buffer buffer;

    private int delayInMilliseconds;

    public CellProcessor(Semaphore nextProcessorSemaphore, Semaphore previousProcessorSemaphore, Buffer buffer, int delayInMilliseconds) {
        this.nextProcessorSemaphore = nextProcessorSemaphore;
        this.previousProcessorSemaphore = previousProcessorSemaphore;
        this.buffer = buffer;
        this.delayInMilliseconds = delayInMilliseconds;
    }

    @Override
    public void run() {
        for(int i = 0; i < this.buffer.getData().length; i++) {
            try {
                previousProcessorSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.getData()[i] += 1;
            try {
                Thread.sleep(delayInMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nextProcessorSemaphore.release();
        }
    }
}
