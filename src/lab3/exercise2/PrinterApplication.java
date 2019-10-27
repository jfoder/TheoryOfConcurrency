package lab3.exercise2;

import java.util.ArrayList;
import java.util.List;

public class PrinterApplication {

    private static final int NUMBER_OF_CLIENTS = 23;
    private static final int NUMBER_OF_PRINTERS = 7;
    private static final int DOCUMENTS_PER_CLIENT = 17;

    public static void main(String[] args) throws InterruptedException {
        PrintingHouse printingHouse = new PrintingHouse(NUMBER_OF_PRINTERS);

        List<Thread> clientThreads = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.add(new Thread(new Client(printingHouse, DOCUMENTS_PER_CLIENT)));
        }

        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.get(i).start();
        }

        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clientThreads.get(i).join();
        }
    }
}
