package lab3.exercise1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private String storedData;

    private Lock lock = new ReentrantLock();

    private boolean waitingForProducer = true;

    private Condition canPut = lock.newCondition();

    private Condition canTake = lock.newCondition();

    public void put(String data) {
        lock.lock();
        try {
            while (!waitingForProducer) {
                try {
                    canPut.await();
                } catch (InterruptedException ignored) {
                }
            }
            waitingForProducer = false;
            this.storedData = data;
            canTake.signal();
        } finally {
            lock.unlock();
        }
    }

    public String take() {
        lock.lock();
        try {
            while (waitingForProducer) {
                try {
                    canTake.await();
                } catch (InterruptedException ignored) {
                }
            }
            waitingForProducer = true;
            String result = storedData;
            this.storedData = null;
            canPut.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
}
