package lab2.exercise2;

import java.util.Random;

public class Client {

    private Shop shop;
    private Basket basket;
    private int clientNumber;

    public Client(Shop shop, int clientNumber) {
        this.shop = shop;
        this.clientNumber = clientNumber;
    }

    public void goShopping() {
        shop.getSemaphore().semaphoreWait();

        this.basket = shop.getBasket();
        System.out.println("[" + clientNumber + "] gets basket");
        try {
            Thread.sleep((new Random().nextInt() % 1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shop.returnBasket(basket);
        System.out.println("[" + clientNumber + "] returns basket");
        basket = null;

        shop.getSemaphore().semaphoreRelease();
    }
}
