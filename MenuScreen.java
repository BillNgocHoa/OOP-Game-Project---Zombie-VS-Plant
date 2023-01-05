import java.awt.*;
import javax.swing.*;

public class MenuScreen extends JPanel {

    Image menu;

    public MenuScreen() {
        initComponents();
        setSize(1024, 768);
        menu = new ImageIcon(this.getClass().getResource("images/menu.JPG")).getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menu, 0, 0, 1024, 768, null);

    }

    private void initComponents() {

        jp = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        jp.setOpaque(false);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evnt) {
                jpMouseClicked(evnt);
            }
        });

        javax.swing.GroupLayout jpLayout = new javax.swing.GroupLayout(jp);
        jp.setLayout(jpLayout);
        jpLayout.setHorizontalGroup(
                jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE));
        jpLayout.setVerticalGroup(
                jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 116, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(523, Short.MAX_VALUE)
                                .addComponent(jp, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(jp, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(547, Short.MAX_VALUE)));
    }

    private void jpMouseClicked(java.awt.event.MouseEvent evnt) {
        Window.begin();
    }

    private javax.swing.JPanel jp;
}
