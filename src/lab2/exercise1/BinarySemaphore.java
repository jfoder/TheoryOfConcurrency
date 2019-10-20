package lab2.exercise1;

class BinarySemaphore {

    private boolean available;

    public BinarySemaphore(boolean available) {
        this.available = available;
    }

    public synchronized void semaphoreWait() {
        while (!this.available) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.available = false;
    }

    public synchronized void semaphoreRelease() {
        this.available = true;
        this.notifyAll();
    }
}