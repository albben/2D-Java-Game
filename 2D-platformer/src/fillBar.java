public class fillBar {
    private int height;
    private int width;
    private int x;
    private int y;

    int pctFilled;

    public fillBar(int x_init,int y_init,int filled){
        width = 50;
        height = 5;
        x = x_init;
        y = y_init;
        pctFilled = filled;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPixelsFilled() {
        double pct = pctFilled * 0.01;
        int pixels = (int) (width * pct);
        return pixels;
    }

    public void setPctFilled(int pctFilled) {
        this.pctFilled = pctFilled;
    }
}
