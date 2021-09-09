import javax.swing.*;
import java.awt.*;

public class holdableObj extends gameObj {

    private boolean highlighted;

    ImageIcon stdImg;
    ImageIcon highlightedImg;


    private Player holder;

    protected boolean spinner;

    protected int restSpin;


    public holdableObj(int x_init,int y_init,int p,int w,int h,int rSpin,String assetPath){
        super(x_init,y_init,p);
        width = w;
        height = h;
        //D:\JavaAlgoProjects\2D-platformer\sign.png
        stdImg = new ImageIcon(assetPath + "\\default.png");
        highlightedImg = new ImageIcon(assetPath + "\\highlighted.png");
        currImg = stdImg;
        degSpin = 0;

        spinner = true;

        restSpin = rSpin;

    }

    public void highlight(){
        highlighted = true;
        currImg = highlightedImg;
    }

    public void unhighlight(){
        highlighted = false;
        currImg = stdImg;
    }

    private int counter = 0;
    public void fall(double gravity){
        dy += gravity;
        double dec = (dy - ((int) dy)) * -1;
        int smoother = 0;
        if(dec < 0.33 && counter >= 3){
            counter = 0;
            smoother = 1;
        }else if(dec >= 0.33 && dec <= 0.66 && counter >= 2) {
            counter = 0;
            smoother = 1;
        }else if(dec > 0.66 && counter >= 1){
            counter = 0;
            smoother = 1;
        }
        if (dy < 0){ smoother = smoother * -1;}
        counter++;

        y += dy + smoother;

        if(spinner){
            spin();
        }

        if(y + (height/2)> 300){
            stopMoving();
        }

    }

    protected void spin(){
        if(rightFacing) {
            degSpin += 2;
            if(degSpin >= 360){
                degSpin = 0;
            }
        }else{
            degSpin -= 2;
            if(degSpin <= -360){
                degSpin = 0;
            }
        }
    }


    public void move(){
        x += dx;
        if(!onGround){
            this.fall(0.1);
        }

        if(x < 0 || x > 830) {
            dx = -(dx);
            rightFacing = !rightFacing;

        }

        centerX = x + width/2;
        centerY = y + height/2;
    }

    public void stopMoving(){
        onGround = true;
        dx =0;
        dy =0;
    }


    public void setHolder(Player p){holder = p;}
    public boolean getHighlighted(){return highlighted;}
    public void setSpin(int s){
        degSpin = s;
    }
    public void setRightFacing(boolean face){rightFacing = face;}
}
