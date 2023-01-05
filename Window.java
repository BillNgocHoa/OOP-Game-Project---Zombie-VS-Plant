import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Window extends JFrame {
	enum Plants {
		None,
		Sunflower,
		Peashooter,
		FrozenPeashooter,
		CherryBomb,
		Wallnut
	}

	public Window() {
		setSize(1024, 768);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel Sun = new JLabel("SUN");
		Sun.setLocation(37, 80);
		Sun.setSize(60, 20);

		Panel gp = new Panel(Sun);
		gp.setLocation(0, 0);
		getLayeredPane().add(gp, Integer.valueOf(0));

		PlaceCard sunflower = new PlaceCard(
				new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
		sunflower.setLocation(110, 8);
		sunflower.setAction((ActionEvent e) -> {
			gp.activePlantingBrush = Plants.Sunflower;
		});
		getLayeredPane().add(sunflower, Integer.valueOf(3));

		PlaceCard peashooter = new PlaceCard(
				new ImageIcon(this.getClass().getResource("images/cards/card_peashooter.png")).getImage());
		peashooter.setLocation(175, 8);
		peashooter.setAction((ActionEvent e) -> {
			gp.activePlantingBrush = Plants.Peashooter;
		});
		getLayeredPane().add(peashooter, Integer.valueOf(3));

		PlaceCard frozenpeashooter = new PlaceCard(
				new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
		frozenpeashooter.setLocation(240, 8);
		frozenpeashooter.setAction((ActionEvent e) -> {
			gp.activePlantingBrush = Plants.FrozenPeashooter;
		});
		getLayeredPane().add(frozenpeashooter, Integer.valueOf(3));

		PlaceCard cherrybomb = new PlaceCard(
		new
		ImageIcon(this.getClass().getResource("images/cards/card_cherrybomb.png")).getImage());
		cherrybomb.setLocation(305, 8);
		cherrybomb.setAction((ActionEvent e) -> {
		gp.activePlantingBrush = Plants.CherryBomb;
		});
		getLayeredPane().add(cherrybomb, Integer.valueOf(3));

		PlaceCard wallnut = new PlaceCard(
		new
		ImageIcon(this.getClass().getResource("/images/cards/card_wallnut.png")).getImage());
		wallnut.setLocation(370, 8);
		wallnut.setAction((ActionEvent e) -> {
		gp.activePlantingBrush = Plants.Wallnut;
		});
		getLayeredPane().add(wallnut, Integer.valueOf(3));

		getLayeredPane().add(Sun, Integer.valueOf(2));
		setResizable(false);
		setVisible(true);
	}

	public Window(boolean bool) {
		MenuScreen menu = new MenuScreen();
		menu.setLocation(0, 0);
		setSize(1024, 768);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getLayeredPane().add(menu, Integer.valueOf(0));
		menu.repaint();
		setResizable(false);
		setVisible(true);
	}

	static Window w;

	public static void begin() {
		w.dispose();
		w = new Window();
	}

	public static void main(String[] args) {
		w = new Window(true);
	}
}
