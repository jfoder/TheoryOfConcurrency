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

    public void sit(Person person) {
        lock.lock();
        try {
            System.out.println("Person [" + person.getPersonId() + "] from pair [" + person.getPairId() + "] is waiting for a table");
            requests[person.getPairId()]++;

            if (requests[person.getPairId()] < 2) {
                bothPeopleFromPairWantsTable[person.getPairId()].await();
            } else {
                while (numberOfClients > 0) {
                    isTableFree.await();
                }
                numberOfClients = 2;
                System.out.println("Both people from pair [" + person.getPairId() + "] sat down");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void getUp(Person person) {
        lock.lock();
        try {
            numberOfClients--;
            System.out.println("Person [" + person.getPersonId() + "] from pair [" + person.getPairId() + "] has left the table");
            isTableFree.signal();
        } finally {
            lock.unlock();
        }
    }
}
