package lab3.exercise1;

public class Consumer implements Runnable {

    private static final int ITERATION_NUMBER = 1000;

    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < ITERATION_NUMBER; i++) {
            String message = buffer.take();
            if (message == null) {
                System.out.println("Error");
            }
        }
    }
}