package lab5;

import java.util.concurrent.Callable;

import static lab5.Constants.*;

public class MandelbrotPixelProcessor implements Callable<MandelbrotPixel> {

    private int x;
    private int y;

    public MandelbrotPixelProcessor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public MandelbrotPixel call() throws Exception {
        double zx = 0;
        double zy = 0;
        double cX = (x - WIDTH / 2) / ZOOM;
        double cY = (y - HEIGHT / 2) / ZOOM;
        int iter = MAX_ITER;

        while (zx * zx + zy * zy < 4 && iter > 0) {
            double tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iter--;
        }

        return new MandelbrotPixel(x, y, iter | (iter << 8));
    }
}
