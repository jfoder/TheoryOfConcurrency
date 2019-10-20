package lab2.exercise2;

import java.util.ArrayList;
import java.util.List;

public class ShopApplication {

    private static final int NUMBER_OF_BASKETS = 30;
    private static final int NUMBER_OF_CLIENTS = 120;

    public static void main(String[] args) throws InterruptedException {
        List<Basket> basketList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BASKETS; i++) {
            basketList.add(new Basket());
        }
        Shop shop = new Shop(basketList);


        List<Thread> clientThreads = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.add(new Thread(new Client(shop, i)));
        }

        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.get(i).start();
        }

        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.get(i).join();
        }
    }
}
