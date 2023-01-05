import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Panel extends JLayeredPane implements MouseMotionListener {

    Image background;
    Image peashooter;
    Image pea;
    Image frozenPeashooter;
    Image frozenPea;
    Image wallnut;
    Image sunflower;
    Image cherryBomb;

    Image normalZombie;
    Image coneHeadZombie;
    Collisions[] collisions;    // collision in an array to store many collisions

    ArrayList<ArrayList<Zombie>> ZombiesLane;   // 2D array
    ArrayList<ArrayList<Pea>> PeasLane;
    ArrayList<Sun> activeSuns;

    Timer redrawTimer;
    Timer advancerTimer;
    Timer sunAppear;
    Timer zombieAppear;
    JLabel sunNumber;

    Window.Plants activePlantingBrush = Window.Plants.None;

    int mouseX, mouseY;

    private int sunScore;

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunNumber.setText(String.valueOf(sunScore));
    }

    public Panel(JLabel sunNumber) {
        setSize(1000, 752);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunNumber = sunNumber;
        setSunScore(150); // pool avalie

        background = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();

        peashooter = new ImageIcon(this.getClass().getResource("images/plants/peashooter.gif")).getImage();
        frozenPeashooter = new ImageIcon(this.getClass().getResource("images/plants/snowpea.gif")).getImage();
        sunflower = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();
        wallnut = new ImageIcon(this.getClass().getResource("images/plants/wallnut.gif")).getImage();
        cherryBomb = new ImageIcon(this.getClass().getResource("images/plants/cherry-bomb-powie.gif")).getImage();
        pea = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
        frozenPea = new ImageIcon(this.getClass().getResource("images/freezepea.png")).getImage();

        normalZombie = new ImageIcon(this.getClass().getResource("images/zombies/zombie1.png")).getImage();
        coneHeadZombie = new ImageIcon(this.getClass().getResource("images/zombies/zombie2.png")).getImage();

        ZombiesLane = new ArrayList<>();
        ZombiesLane.add(new ArrayList<>()); // line 1       //to locate zombie entities
        ZombiesLane.add(new ArrayList<>()); // line 2
        ZombiesLane.add(new ArrayList<>()); // line 3
        ZombiesLane.add(new ArrayList<>()); // line 4
        ZombiesLane.add(new ArrayList<>()); // line 5

        PeasLane = new ArrayList<>();
        PeasLane.add(new ArrayList<>()); // line 1      // to locate peas entities
        PeasLane.add(new ArrayList<>()); // line 2
        PeasLane.add(new ArrayList<>()); // line 3
        PeasLane.add(new ArrayList<>()); // line 4
        PeasLane.add(new ArrayList<>()); // line 5

        collisions = new Collisions[45];
        for (int i = 0; i < 45; i++) {
            Collisions a = new Collisions();
            a.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            a.setAction(new PlantActionListener((i % 9), (i / 9)));
            collisions[i] = a;
            add(a, 0);
        }

        // collisions[0].setPlant(new frozenPeashooter(this,0,0));
        /*
         * collisions[9].setPlant(new Peashooter(this,0,1));
         * ZombiesLane.get(1).add(new NormalZombie(this,1));
         */

        activeSuns = new ArrayList<>();

        redrawTimer = new Timer(25, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

        advancerTimer = new Timer(60, (ActionEvent e) -> advance());
        advancerTimer.start();

        sunAppear = new Timer(5000, (ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(this, rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200);
            activeSuns.add(sta);
            add(sta, 1);
        });
        sunAppear.start();

        zombieAppear = new Timer(7000, (ActionEvent e) -> {
            Random rnd = new Random();
            String[] Level = DataLevel.Level[Integer.parseInt(DataLevel.lvl) - 1];
            int[][] LevelValue = DataLevel.LevelValue[Integer.parseInt(DataLevel.lvl) - 1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie z = null;
            for (int i = 0; i < LevelValue.length; i++) {
                if (t >= LevelValue[i][0] && t <= LevelValue[i][1]) {
                    z = Zombie.getZombie(Level[i], Panel.this, l);
                }
            }
            ZombiesLane.get(l).add(z);
        });
        zombieAppear.start();

    }

    private void advance() {
        for (int i = 0; i < 5; i++) {
            for (Zombie z : ZombiesLane.get(i)) {
                z.advance();
            }

            for (int j = 0; j < PeasLane.get(i).size(); j++) {
                Pea p = PeasLane.get(i).get(j);
                p.advance();
            }

        }

        for (int i = 0; i < activeSuns.size(); i++) {
            activeSuns.get(i).advance();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        // Draw Plants
        for (int i = 0; i < 45; i++) {
            Collisions c = collisions[i];
            if (c.markedPlant != null) {
                Plant p = c.markedPlant;
                if (p instanceof Peashooter) {
                    g.drawImage(peashooter, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof FreezePeashooter) {
                    g.drawImage(frozenPeashooter, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof Sunflower) {
                    g.drawImage(sunflower, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
            }
        }

        // Draw zombies
        for (int i = 0; i < 5; i++) {
            for (Zombie z : ZombiesLane.get(i)) {
                if (z instanceof NormalZombie) {
                    g.drawImage(normalZombie, z.posX, 109 + (i * 120), null);
                } else if (z instanceof ConeHeadZombie) {
                    g.drawImage(coneHeadZombie, z.posX, 109 + (i * 120), null);
                }
            }

            // Draw pea bullets
            for (int j = 0; j < PeasLane.get(i).size(); j++) {
                Pea p = PeasLane.get(i).get(j);
                if (p instanceof FreezePea) {
                    g.drawImage(frozenPea, p.posX, 130 + (i * 120), null);
                } else {
                    g.drawImage(pea, p.posX, 130 + (i * 120), null);
                }
            }

        }

        // if(!"".equals(activePlantingBrush)){
        // System.out.println(activePlantingBrush);
        /*
         * if(activePlantingBrush == Window.Plants.Sunflower) {
         * g.drawImage(sunflower,mouseX,mouseY,null);
         * }
         */

        // }

    }

    class PlantActionListener implements ActionListener {

        int x, y;

        public PlantActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (activePlantingBrush == Window.Plants.Sunflower) {
                if (getSunScore() >= 50) {
                    collisions[x + y * 9].setPlant(new Sunflower(Panel.this, x, y));
                    setSunScore(getSunScore() - 50);
                }
            }
            if (activePlantingBrush == Window.Plants.Peashooter) {
                if (getSunScore() >= 100) {
                    collisions[x + y * 9].setPlant(new Peashooter(Panel.this, x, y));
                    setSunScore(getSunScore() - 100);
                }
            }

            if (activePlantingBrush == Window.Plants.FrozenPeashooter) {
                if (getSunScore() >= 175) {
                    collisions[x + y * 9].setPlant(new FreezePeashooter(Panel.this, x, y));
                    setSunScore(getSunScore() - 175);
                }
            }
            activePlantingBrush = Window.Plants.None;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    static int progress = 0;

    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        if (progress >= 150) {
            if ("1".equals(DataLevel.lvl)) {
                JOptionPane.showMessageDialog(null, "LEVEL Completed !!!" + '\n' + "Starting next level!");
                Window.w.dispose();
                DataLevel.write("2");
                Window.w = new Window();
            } else {
                JOptionPane.showMessageDialog(null,
                        "LEVEL Completed !!!" + '\n' + "More levels will come soon !!!" + '\n' + "Resetting data");
                DataLevel.write("1");
                System.exit(0);
            }
            progress = 0;
        }
    }
}
