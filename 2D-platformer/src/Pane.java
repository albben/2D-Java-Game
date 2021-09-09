
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;



public class Pane extends JPanel implements KeyListener{
    ImageIcon[] background;

    int scene = 0;

    Player p1;

    ArrayList<holdableObj> holdableObjects;
    String signPath = "D:\\JavaAlgoProjects\\2D-platformer\\assets\\sign";
    holdableObj sign = new holdableObj(500,240,0,49,86,0,signPath);

    ArrayList<gameObj> otherObjs;
    Chest chest1 = new Chest(350,250,1);

    Timer gameTick;
    Timer keyHolder;


    private double holdTime;


    public Pane(){
        addKeyListener(this);
        setFocusable(true);

        p1 = new Player(50,240,scene);

        background = new ImageIcon[2];
        background[0] = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\.backgrounds\\background.png");
        background[1] = new ImageIcon("D:\\JavaAlgoProjects\\2D-platformer\\assets\\.backgrounds\\background2.png");

        holdableObjects = new ArrayList<>();
        holdableObjects.add(sign);

        otherObjs = new ArrayList<>();
        otherObjs.add(chest1);

        gameTick = new Timer(10,taskPerformer);
        keyHolder = new Timer(10,keyHoldTimer);
        gameTick.start();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        background[scene].paintIcon(this,g2d,0,0);

        for (holdableObj object : holdableObjects) {
            if (object.getPane() == scene) {
                g2d.rotate(Math.toRadians(object.getDeg()), object.getX() + (object.getWidth() / 2.0), object.getY() + (object.getHeight() / 2.0));
                object.getCurrImg().paintIcon(this, g2d, object.getX(), object.getY());
                g2d.rotate(Math.toRadians(360 - object.getDeg()), object.getX() + (object.getWidth() / 2.0), object.getY() + (object.getHeight() / 2.0));
            }
        }

        for(gameObj obj : otherObjs){
            if(obj.getPane() == scene) {
                obj.getCurrImg().paintIcon(this, g2d, obj.getX(), obj.getY());
            }
        }

        p1.getCurrImg().paintIcon(this,g2d,p1.getX(), p1.getY());
        p1.drawHealth(this,g2d);
        g.drawRect(p1.getPowerBar().getX(), p1.getPowerBar().getY(),p1.getPowerBar().getWidth(),p1.getPowerBar().getHeight());
        g.fillRect(p1.getPowerBar().getX(), p1.getPowerBar().getY(),p1.getPowerBar().getPixelsFilled(),p1.getPowerBar().getHeight());
        g.fillRect(0,320,900,5);
    }


    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {

            p1.move();

            for (holdableObj object : holdableObjects) {
                object.move();
            }

            for(holdableObj object : holdableObjects){
                if(p1.intersectsWith(object)){
                    object.highlight();
                }else if(object.getHighlighted()){
                    object.unhighlight();
                }
            }

            if(p1.getX() > 880){
                scene++;
                p1.setX(0);
                p1.setPane(p1.getPane() + 1);
            }

            if(p1.getX() < 0 && scene != 0){
                scene--;
                p1.setX(880);
                p1.setPane(p1.getPane() -1);
            }


            repaint();
        }
    };

    ActionListener keyHoldTimer = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            holdTime++;
            if(holdTime > 100){
                holdTime = 100;
            }
            p1.getPowerBar().setPctFilled((int)(holdTime));
        }
    };

    public void addObj(gameObj obj){
        otherObjs.add(obj);
    }

    public void addHoldableObj(holdableObj obj){
        holdableObjects.add(obj);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            p1.moveRight();
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            p1.moveLeft();
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN && !p1.getCrouching()){
            p1.crouch();
            for(holdableObj object : holdableObjects){
                if(p1.intersectsWith(object)  && p1.getObjHolding() == null && p1.getCrouching()){
                    p1.pickUp(object);
                }else if(p1.getObjHolding() != null && p1.getCrouching()) {
                    p1.dropObj();
                }
            }

            if(p1.intersectsWith(chest1) && !chest1.isOpen()){
                chest1.open(this);
            }
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE && p1.getObjHolding() != null){
           //  p1.startThrowing();
            keyHolder.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            p1.stopRight();
            repaint();
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            p1.stopLeft();
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            p1.stand();
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE && p1.getObjHolding() != null){
            keyHolder.stop();
            p1.throwObj(holdTime);
            holdTime = 0;
            p1.getPowerBar().setPctFilled((int)(holdTime));
        }

    }
}
