package lab2.exercise2;

import java.util.Random;

public class Client {

    private Shop shop;

    public Client(Shop shop) {
        this.shop = shop;
    }

    public void goShopping() {
        shop.getSemaphore().semaphoreWait();

        try {
            Thread.sleep((new Random().nextInt() % 1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        shop.getSemaphore().semaphoreRelease();
    }
}
