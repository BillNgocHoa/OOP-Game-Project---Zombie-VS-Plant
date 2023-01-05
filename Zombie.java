import javax.swing.*;

public class Zombie {

    public int health = 1000;
    public int speed = 1;

    private Panel p;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;

    public Zombie(Panel parent, int lane) {
        this.p = parent;
        myLane = lane;
    }

    public void advance() {
        if (isMoving) {
            boolean isCollides = false;
            Collisions collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (p.collisions[i].markedPlant != null && p.collisions[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = p.collisions[i];
                }
            }
            if (!isCollides) {
                if (slowInt > 0) {
                    if (slowInt % 2 == 0) {
                        posX--;
                    }
                    slowInt--;
                } else {
                    posX -= 1;
                }
            } else {
                collided.markedPlant.health -= 10;      // zombies collide plants
                if (collided.markedPlant.health < 0) {
                    collided.removePlant();
                }
            }
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(p, "ZOMBIES HAVE ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                Window.w.dispose();
                Window.w = new Window();
            }
        }
    }

    int slowInt = 0;

    public void slow() {
        slowInt = 1000;
    }

    public static Zombie getZombie(String type, Panel parent, int lane) {
        Zombie z = new Zombie(parent, lane);
        switch (type) {
            case "NormalZombie":
                z = new NormalZombie(parent, lane);
                break;
            case "ConeHeadZombie":
                z = new ConeHeadZombie(parent, lane);
                break;
        }
        return z;
    }
}
