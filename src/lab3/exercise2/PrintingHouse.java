package lab3.exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintingHouse {

    private List<Printer> printers = new ArrayList<>();

    private Lock lock = new ReentrantLock();

    private Condition anyPrinterAvailable = lock.newCondition();

    public PrintingHouse(int numberOfPrinters) {
        for (int i = 0; i < numberOfPrinters; i++) {
            printers.add(new Printer());
        }

    }

    public Printer takePrinter() {
        lock.lock();
        try {
            while (printers.size() <= 0) {
                anyPrinterAvailable.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Printer result = printers.remove(0);
        lock.unlock();
        return result;
    }

    public void returnPrinter(Printer printer) {
        lock.lock();
        printers.add(printer);
        anyPrinterAvailable.signal();
        lock.unlock();
    }
}
