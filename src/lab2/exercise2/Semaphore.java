package lab2.exercise2;

public class Semaphore {

    private int counter;

    public Semaphore(int counter) {
        this.counter = counter;
    }

    public synchronized void semaphoreWait() {
        while (this.counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.counter++;
    }

    public synchronized void semaphoreRelease() {
        this.counter--;
        notifyAll();
    }
}