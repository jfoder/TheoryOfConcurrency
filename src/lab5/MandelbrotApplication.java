package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class MandelbrotApplication extends JFrame {

    private BufferedImage bufferedImage;

    private MandelbrotApplication() throws InterruptedException, ExecutionException {
        super("Mandelbrot Set");
        setBounds(100, 100, Constants.WIDTH, Constants.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        setVisible(true);

        ExecutorService pool = Executors.newFixedThreadPool(1);
        Set<Future<MandelbrotPixel>> set = new HashSet<>();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Callable<MandelbrotPixel> callable = new MandelbrotPixelProcessor(x, y);
                Future<MandelbrotPixel> future = pool.submit(callable);
                set.add(future);
            }
        }
        System.out.println(set.size());

        for (Future<MandelbrotPixel> future : set) {
            MandelbrotPixel mandelbrotPixel = future.get();
            bufferedImage.setRGB(mandelbrotPixel.getX(), mandelbrotPixel.getY(), mandelbrotPixel.getRgb());
        }
        paint(getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, this);
    }

    public static void main(String[] args) throws Exception {
        new MandelbrotApplication();
    }
}