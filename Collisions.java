import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Collisions extends JPanel implements MouseListener {

    ActionListener al;

    public Collisions() {
        // setBorder(new LineBorder(Color.RED));
        setOpaque(false);
        addMouseListener(this);
        // setBackground(Color.green);
        setSize(100, 120);
    }

    public Plant markedPlant;

    public void setPlant(Plant p) {
        markedPlant = p;
    }

    public void removePlant() {
        markedPlant.stop();
        markedPlant = null;
    }

    public boolean isInsideCollider(int tx) {
        return (tx > getLocation().x) && (tx < getLocation().x + 100);
    }

    public void setAction(ActionListener al) {
        this.al = al;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (al != null) {
            al.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
