package lab3.exercise1;

import java.util.ArrayList;
import java.util.List;

public class BufferApplication {
    private static final int PRODUCERS_AND_CONSUMERS_NUMBER = 200;

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();

        List<Thread> producerThreads = new ArrayList<>();
        List<Thread> consumerThreads = new ArrayList<>();

        for (int i = 0; i < PRODUCERS_AND_CONSUMERS_NUMBER; i++) {
            producerThreads.add(new Thread(new Producer(buffer)));
            consumerThreads.add(new Thread(new Consumer(buffer)));
        }

        for (int i = 0; i < PRODUCERS_AND_CONSUMERS_NUMBER; i++) {
            producerThreads.get(i).start();
            consumerThreads.get(i).start();
        }

        for (int i = 0; i < PRODUCERS_AND_CONSUMERS_NUMBER; i++) {
            producerThreads.get(i).join();
            consumerThreads.get(i).join();
        }
    }
}
