import javax.swing.*;
import java.awt.*;

public class walkingObj extends gameObj {

    protected boolean standingRight;
    protected ImageIcon standingRightImg;
    protected ImageIcon standingLeftImg;

    protected ImageIcon throwRightImg;

    protected boolean movingRight = false;
    protected int rightAnimationCount = 0;
    protected ImageIcon[] runRightImgs;


    protected boolean movingLeft = false;
    protected int leftAnimationCount = 0;
    protected ImageIcon[] runLeftImgs;

    protected boolean crouching = false;
    protected ImageIcon crouchingImgRight;
    protected ImageIcon crouchingImgLeft;


    public walkingObj(int init_x,int init_y,int p){
        super(init_x,init_y,p);
    }

    public void move(){
        x+=dx;


        if (movingRight){
            rightAnimation(rightAnimationCount);
            rightAnimationCount++;
            if(rightAnimationCount == 20){
                rightAnimationCount = -1;
            }
        }

        if (movingLeft){
            leftAnimation(leftAnimationCount);
            leftAnimationCount++;
            if(leftAnimationCount == 20){
                leftAnimationCount = -1;
            }
        }

        centerX = x+width;
        System.out.println(centerX);
    }

    public void stand(){
        y -= 20;
        crouching = false;
        currImg = standingRightImg;
    }

    public void crouch(){
        y += 20;
        crouching = true;
        currImg = crouchingImgRight;
    }

    public void moveRight(){
        standingRight = true;
        if (!movingLeft) {
            dx = 4;
            movingRight = true;
        }
    }

    public void stopRight(){
        dx=0;
        movingRight = false;
        standingRight = true;
        currImg = standingRightImg;
    }

    public void moveLeft(){
        standingRight = false;
        if(!movingRight) {
            dx = -4;
            movingLeft = true;
        }
    }

    public void stopLeft(){
        dx = 0;
        movingLeft = false;
        standingRight = false;
        currImg = standingLeftImg;
    }

    protected void rightAnimation(int step){
        if(step == 0){
            currImg = runRightImgs[0];
        }else if(step == 10) {
            currImg = runRightImgs[1];
        }
    }

    protected void leftAnimation(int step){
        if(step == 0){
            currImg = runLeftImgs[0];
        }else if(step == 10) {
            currImg = runLeftImgs[1];
        }
    }



    public boolean getCrouching(){return crouching;}

}
