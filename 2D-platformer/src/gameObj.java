import javax.swing.*;
import java.awt.*;

public class gameObj {
    protected int x;
    protected int y;

    protected int centerX;
    protected int centerY;

    protected int height;
    protected int width;

    protected int dx = 0;
    protected double dy = 0;

    protected ImageIcon currImg;

    protected boolean onGround;

    protected boolean rightFacing;

    protected int pane;

    protected int degSpin;

    public gameObj(int x_init,int y_init,int p){
        onGround = true;
        x = x_init;
        y = y_init;
        pane = p;
    }





    public int getX(){return x;}
    public void setX(int x) { this.x = x; }
    public int getY(){return y;}
    public void setY(int newY){y = newY;}
    public void setDX(int newDX){dx = newDX;}
    public int getDx(){return dx;}
    public void setDY(int newDY){dy = newDY;}
    public boolean getOnGround(){return onGround;}
    public void setOnGround(boolean grounded){onGround = grounded;}
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public ImageIcon getCurrImg(){return currImg;}
    public int getPane(){return pane;}
    public void setPane(int newPane){pane = newPane;}
    public int getDeg(){return degSpin;}
    public void setRightFacing(boolean right){rightFacing = right;}

}
