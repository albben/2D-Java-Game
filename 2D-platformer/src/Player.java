import javax.swing.*;
import java.awt.*;

public class Player extends walkingObj {
    private holdableObj objHolding;

    private fillBar powerBar;

    int health = 3;

    ImageIcon heart;
    ImageIcon healthTxt;


    public Player(int x_init,int y_init,int p){
        super(x_init,y_init,p);

        height = 83;
        width = 49;

        standingRight = true;
        standingRightImg = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\player\\standRight.png");
        standingLeftImg = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\player\\standLeft.png");

        throwRightImg = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\player\\throwRight.png");

        runRightImgs = new ImageIcon[2];
        runRightImgs[0] = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\player\\runRight1.png");
        runRightImgs[1] = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\player\\runRight2.png");

        runLeftImgs = new ImageIcon[2];
        runLeftImgs[0] = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\player\\runLeft1.png");
        runLeftImgs[1] = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\player\\runLeft2.png");

        crouchingImgRight = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\player\\crouchingRight.png");

        heart = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\heart\\default.png");

        healthTxt = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\text\\Health.png");

        currImg = standingRightImg;

        objHolding = null;

        powerBar = new fillBar(x,y-15,0);
    }

    public void move(){


        x+=dx;
        powerBar.setX(x);

        if (movingRight){
            if(objHolding!=null){setHoldableRight();}
            rightAnimation(rightAnimationCount);
            rightAnimationCount++;
            if(rightAnimationCount == 20){
                rightAnimationCount = -1;
            }
        }

        if (movingLeft){
            if(objHolding != null){setHoldableLeft();}
            leftAnimation(leftAnimationCount);
            leftAnimationCount++;
            if(leftAnimationCount == 20){
                leftAnimationCount = -1;
            }
        }

        if(objHolding != null){
            objHolding.setDX(dx);
            objHolding.setPane(this.getPane());
        }

        centerX = x+(width/2);
        centerY = y+(height/2);

    }


    public void pickUp(holdableObj obj){
        obj.setHolder(this);
        objHolding = obj;
        objHolding.setSpin(50);
        objHolding.setX(x+28);
        objHolding.setY(240);
        crouching = false;
    }

    public void dropObj(){
        objHolding.setDX(0);
        objHolding.setSpin(objHolding.restSpin);
        objHolding.setY(300 - objHolding.height/2);
        objHolding = null;
        crouching = false;
    }

    public void startThrowing(){
        currImg = throwRightImg;
    }

    public void throwObj(double strength){
        strength = strength / 10;
        if(strength > 10){strength = 10;}
        int str = (int) (strength + 0.5);
        objHolding.setY(220);
        objHolding.setOnGround(false);
        objHolding.setRightFacing(standingRight);
        if(standingRight) {
            currImg = standingRightImg;
            objHolding.setDX(str);
        }else{
            currImg = standingLeftImg;
            objHolding.setDX(-str);
        }
        objHolding.setDY(-3);
        objHolding = null;

    }

    public void setHoldableRight(){
        objHolding.setSpin(50);
        objHolding.setX(x+28);
    }

    public void setHoldableLeft(){
        objHolding.setSpin(-50);
        objHolding.setX(x-20);
    }

    public void drawHealth(Pane p,Graphics2D g2d){
        int x_heart = 10;
        healthTxt.paintIcon(p,g2d,15,5);
        for(int i =0;i<health;i++){
            heart.paintIcon(p,g2d,x_heart,25);
            x_heart += 20;
        }


    }

    public void gainHealth(){
        health += 1;
    }

    public void loseHealth(){
        health -= 1;
    }

    public boolean intersectsWith(gameObj obj){
        return x < obj.getX() + obj.getWidth() && x + width > obj.getX() && y < obj.getY() + obj.getHeight() && y + height > obj.getY() && pane == obj.getPane();
    }

    public holdableObj getObjHolding(){return objHolding;}

    public fillBar getPowerBar() {
        return powerBar;
    }
}
