package lab2.exercise1;

import java.util.stream.IntStream;

public class CounterApplication {

    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();

        Thread threadIncrement = new Thread(() -> IntStream.range(0, 100000000).forEach(i -> counter.increment()));
        Thread threadDecrement = new Thread(() -> IntStream.range(0, 100000000).forEach(i -> counter.decrement()));

        threadIncrement.start();
        threadDecrement.start();

        threadIncrement.join();
        threadDecrement.join();

        System.out.println(counter.getCounter());
    }
}
