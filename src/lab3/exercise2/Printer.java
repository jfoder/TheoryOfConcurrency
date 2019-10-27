package lab3.exercise2;

public class Printer {

    private static int printerCounter = 0;

    private int printerNumber;

    public Printer() {
        this.printerNumber = printerCounter++;
    }

    public int getPrinterNumber() {
        return printerNumber;
    }
}
