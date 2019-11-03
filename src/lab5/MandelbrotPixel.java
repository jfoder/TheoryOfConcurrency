package lab5;

public class MandelbrotPixel {

    private int x;
    private int y;

    private int rgb;

    public MandelbrotPixel(int x, int y, int rgb) {
        this.x = x;
        this.y = y;
        this.rgb = rgb;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRgb() {
        return rgb;
    }
}
