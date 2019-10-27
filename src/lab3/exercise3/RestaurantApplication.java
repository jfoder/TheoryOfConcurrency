package lab3.exercise3;

import java.util.ArrayList;
import java.util.List;

public class RestaurantApplication {

    private static final int NUMBER_OF_RESERVATIONS = 17;
    private static final int NUMBER_OF_CLIENTS = 8;

    public static void main(String[] args) throws InterruptedException {
        Waiter waiter = new Waiter(NUMBER_OF_CLIENTS / 2);

        List<Thread> clientThreads = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.add(new Thread(new Client(i / 2, NUMBER_OF_RESERVATIONS, waiter)));
        }

        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.get(i).start();
        }

        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.get(i).join();
        }
    }
}
