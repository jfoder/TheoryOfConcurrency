package lab2.exercise2;

import java.util.Random;

public class Client implements Runnable {

    private Shop shop;
    private int clientNumber;

    public Client(Shop shop, int clientNumber) {
        this.shop = shop;
        this.clientNumber = clientNumber;
    }

    public void goShopping() {
        Basket basket = shop.getBasket();
        System.out.println("[" + clientNumber + "] gets basket");
        try {
            Thread.sleep((new Random().nextInt() % 1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shop.returnBasket(basket);
        System.out.println("[" + clientNumber + "] returns basket");
    }

    @Override
    public void run() {
        goShopping();
    }
}
