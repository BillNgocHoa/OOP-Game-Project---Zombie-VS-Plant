import javax.swing.*;
import java.awt.event.ActionEvent;

// created by Ngoc Hoa

public class Sunflower extends Plant {

    Timer sunProduceTimer;

    public Sunflower(Panel parent, int x, int y) {
        super(parent, x, y);
        sunProduceTimer = new Timer(15000, (ActionEvent e) -> {     // every 15s, sun appears
            Sun sta = new Sun(p, 60 + x * 100, 110 + y * 120, 130 + y * 120);
            p.activeSuns.add(sta);
            p.add(sta, Integer.valueOf(1));
        });
        sunProduceTimer.start();
    }

}