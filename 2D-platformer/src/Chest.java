import javax.swing.*;
import java.util.ArrayList;

public class Chest extends gameObj {
    protected ImageIcon closedImg;
    protected ImageIcon openedImg;

    protected ArrayList<holdableObj> contained;

    private boolean open;
    public Chest(int x_init,int y_init,int p){
        super(x_init,y_init,p);
        width = 105;
        height = 88;
        closedImg = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\chest\\chest.png");
        openedImg = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\chest\\openChest.png");
        currImg = closedImg;

        contained = new ArrayList<>();
        String swordPath = "D:\\JavaAlgoProjects\\2D-platformer\\assets\\sword";
        String heartPath = "D:\\JavaAlgoProjects\\2D-platformer\\assets\\heart";
        holdableObj sword = new holdableObj(this.x,this.y,this.pane,41,77,90,swordPath);
        holdableObj heart = new PowerUp(this.x,this.y,this.pane,48,49,"Health",heartPath);
        contained.add(sword);
        contained.add(heart);

        open = false;
    }


    public void open(Pane p){
        open = true;
        currImg = openedImg;
        int DY = -2;
        int DX = 3;
        boolean right = true;

        for(holdableObj obj : contained){
            if(!right){
                DX = -DX;
            }
            obj.setRightFacing(right);
            obj.setDY(DY);
            obj.setDX(DX);
            obj.setY(239);
            obj.setOnGround(false);
            p.addHoldableObj(obj);
            right = !right;
        }

    }

    public boolean isOpen() {
        return open;
    }
}
