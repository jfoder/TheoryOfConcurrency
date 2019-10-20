package lab2.exercise2;

import java.util.List;

public class Shop {

    private List<Basket> basketList;
    private Semaphore semaphore;

    public Shop(List<Basket> basketList) {
        this.basketList = basketList;
        this.semaphore = new Semaphore(basketList.size());
    }

    public List<Basket> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<Basket> basketList) {
        this.basketList = basketList;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
}
