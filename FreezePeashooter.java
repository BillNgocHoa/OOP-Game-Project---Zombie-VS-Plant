import java.awt.event.ActionEvent;
import javax.swing.*;

public class FreezePeashooter extends Plant {

    public Timer shootTimer;

    public FreezePeashooter(Panel parent, int x, int y) {
        super(parent, x, y);
        shootTimer = new Timer(1000, (ActionEvent e) -> {   //delay bwt shooting, delay 2s
            // System.out.println("SHOOT");
            if (p.ZombiesLane.get(y).size() > 0) {      //check if the lane has zombie
                p.PeasLane.get(y).add(new FreezePea(p, y, 103 + this.x * 100)); //tọa độ di chuyển trái sang phải
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop() { // override from Plant
        shootTimer.stop();
    }

}