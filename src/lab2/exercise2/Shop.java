package lab2.exercise2;

import lab2.exercise1.BinarySemaphore;

import java.util.List;

public class Shop {

    private List<Basket> basketList;
    private Semaphore semaphore;
    private BinarySemaphore binarySemaphore;

    public Shop(List<Basket> basketList) {
        this.basketList = basketList;
        this.semaphore = new Semaphore(basketList.size());
        this.binarySemaphore = new BinarySemaphore(true);
    }

    public Basket getBasket() {
        semaphore.semaphoreWait();
        if (basketList.size() > 0) {
            binarySemaphore.semaphoreWait();
            Basket result = basketList.remove(0);
            binarySemaphore.semaphoreRelease();
            return result;
        }
        else
            throw new RuntimeException("Client was trying to get basket while no basket was available");
    }

    public void returnBasket(Basket basket) {
        binarySemaphore.semaphoreWait();
        basketList.add(basket);
        binarySemaphore.semaphoreRelease();
        semaphore.semaphoreRelease();
    }
}
