package lab3.exercise3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {

    private Lock lock = new ReentrantLock();

    private int[] requests;

    private Condition[] bothPeopleFromPairWantsTable;

    private Condition isTableFree = lock.newCondition();

    private int numberOfClients = 0;

    public Waiter(int numberOfPairs) {
        this.requests = new int[numberOfPairs];
        this.bothPeopleFromPairWantsTable = new Condition[numberOfPairs];

        for (int i = 0; i < numberOfPairs; i++) {
            requests[i] = 0;
            bothPeopleFromPairWantsTable[i] = lock.newCondition();
        }
    }

    public void sit(Client client) {
        lock.lock();
        try {
            requests[client.getPairId()]++;

            if (requests[client.getPairId()] < 2) {
                bothPeopleFromPairWantsTable[client.getPairId()].await();
            } else {
                while (numberOfClients > 0) {
                    isTableFree.await();
                }
                numberOfClients = 2;
                requests[client.getPairId()] -= 2;
                System.out.println("Both people from pair [" + client.getPairId() + "] sat down");
                bothPeopleFromPairWantsTable[client.getPairId()].signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void getUp(Client client) {
        lock.lock();
        try {
            numberOfClients--;
            System.out.println("Client [" + client.getPersonId() + "] from pair [" + client.getPairId() + "] has left the table ");
            if (numberOfClients == 0)
                isTableFree.signal();
        } finally {
            lock.unlock();
        }
    }
}
