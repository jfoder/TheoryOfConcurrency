package lab3.exercise2;

import java.util.Random;

public class Client implements Runnable {

    private PrintingHouse printingHouse;

    private static int clientCounter = 0;

    private int clientNumber;

    private int documentsToPrintNumber;

    public Client(PrintingHouse printingHouse, int documentsToPrintNumber) {
        this.clientNumber = clientCounter++;
        this.printingHouse = printingHouse;
        this.documentsToPrintNumber = documentsToPrintNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < documentsToPrintNumber; i++) {
            Printer reservedPrinter = printingHouse.takePrinter();
            System.out.println("Client [" + clientNumber + "] is printing document [" + i + "] using printer [" + reservedPrinter.getPrinterNumber() + "]");
            try {
                Thread.sleep((new Random().nextInt() % 500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printingHouse.returnPrinter(reservedPrinter);
            System.out.println("Client [" + clientNumber + "] printed document [" + i + "] and returned printer [" + reservedPrinter.getPrinterNumber() + "]");
        }
    }
}
