package lab3.exercise3;

import java.util.Random;

public class Client implements Runnable {

    private static int peopleCounter = 0;

    private int personId;

    private int pairId;

    private int numberOfReservations;

    private Waiter waiter;

    public Client(int pairId, int numberOfReservations, Waiter waiter) {
        this.pairId = pairId;
        this.personId = peopleCounter++;
        this.numberOfReservations = numberOfReservations;
        this.waiter = waiter;
    }

    public int getPersonId() {
        return personId;
    }

    public int getPairId() {
        return pairId;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfReservations; i++) {
            waiter.sit(this);
            try {
                Thread.sleep((new Random().nextInt() % 500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waiter.getUp(this);
        }
    }
}
