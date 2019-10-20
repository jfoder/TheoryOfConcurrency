package lab2.exercise1;

public class Counter {

    private int counter;
    private BinarySemaphore binarySemaphore;

    public Counter() {
        this.counter = 0;
        this.binarySemaphore = new BinarySemaphore(true);
    }

    public void increment() {
        binarySemaphore.semaphoreWait();
        counter++;
        binarySemaphore.semaphoreRelease();
    }

    public void decrement() {
        binarySemaphore.semaphoreWait();
        counter--;
        binarySemaphore.semaphoreRelease();
    }

    public synchronized int getCounter() {
        return counter;
    }
}
