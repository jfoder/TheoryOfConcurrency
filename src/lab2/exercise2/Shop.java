package lab2.exercise2;

import java.util.List;

public class Shop {

    private List<Basket> basketList;
    private Semaphore semaphore;

    public Shop(List<Basket> basketList) {
        this.basketList = basketList;
        this.semaphore = new Semaphore(basketList.size());
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public Basket getBasket() {
        if (basketList.size() > 0)
            return basketList.remove(0);

        else
            throw new RuntimeException("Client was trying to get basket while no basket was available");
    }

    public void returnBasket(Basket basket) {
        basketList.add(basket);
    }
}
