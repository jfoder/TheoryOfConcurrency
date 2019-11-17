package lab4.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BufferApplication {

    private static final int BUFFER_SIZE = 100;

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(BUFFER_SIZE);

        Semaphore producerSemaphore = new Semaphore(0);
        Thread producerThread = new Thread(new Producer(buffer, producerSemaphore));

        Semaphore consumerSemaphore = new Semaphore(0);
        Thread consumerThread = new Thread(new Consumer(buffer, consumerSemaphore));

        Semaphore[] semaphores = new Semaphore[6];
        semaphores[0] = consumerSemaphore;
        semaphores[5] = producerSemaphore;
        for (int i = 1; i < 5; i++) {
            semaphores[i] = new Semaphore(0);
        }

        List<Thread> processorThreads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            processorThreads.add(new Thread(new CellProcessor(semaphores[i], semaphores[i + 1], buffer, 1000)));
        }

        producerThread.start();
        consumerThread.start();
        for (int i = 0; i < 5; i++) {
            processorThreads.get(i).start();
        }

        producerThread.join();
        consumerThread.join();
        for (int i = 0; i < 5; i++) {
            processorThreads.get(i).join();
        }
    }
}
