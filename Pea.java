import java.awt.*;

public class Pea {

    public int posX;
    protected Panel p;
    public int myLane;

    public Pea(Panel parent, int lane, int startX) {
        this.p = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void advance() {     // bullet is contained in a rectangle
        Rectangle pRect = new Rectangle(posX, 130 + myLane * 120, 28, 28);  // position of bullet
        for (int i = 0; i < p.ZombiesLane.get(myLane).size(); i++) {
            Zombie z = p.ZombiesLane.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX, 109 + myLane * 120, 400, 120);

            if (pRect.intersects(zRect)) {  // when bullet intersect with zombie (another rectangle)
                z.health -= 300;    // zombie health decreased
                boolean exit = false;
                if (z.health < 0) {     // if z.health < 0
                    System.out.println("ZOMBIE DIE");

                    p.ZombiesLane.get(myLane).remove(i);    //remove zombie
                    Panel.setProgress(10);
                    exit = true;
                }
                p.PeasLane.get(myLane).remove(this);
                if (exit)
                    break;
            }
        }
        /*
         * if(posX > 2000){
         * p.lanePeas.get(myLane).remove(this);
         * }
         */
        posX += 15;
    }

}