package lab3.exercise3;

public class Person {

    private static int peopleCounter = 0;

    private int personId;

    private int pairId;

    public Person(int pairId) {
        this.pairId = pairId;
        this.personId = peopleCounter++;
    }

    public int getPersonId() {
        return personId;
    }

    public int getPairId() {
        return pairId;
    }
}
