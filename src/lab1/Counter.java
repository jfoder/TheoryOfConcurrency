package lab1;

public class Counter {

    private int counter;

    public Counter() {
        this.counter = 0;
    }

    public synchronized void increment() {
        counter++;
    }

    public synchronized void decrement() {
        counter--;
    }

    public synchronized int getCounter() {
        return counter;
    }
}
