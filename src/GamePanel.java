import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}

	}

	public void startGame() {
		alienspawn = new Timer(1000, objectmanage);
		alienspawn.start();
	}

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Font titleFont;
	Font subtitleFont;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	Timer alienspawn;
	int currentState = MENU;
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	Timer frameDraw;
	ObjectManagement objectmanage = new ObjectManagement(rocket);

	GamePanel() {
		titleFont = new Font("TimesNewRoman", Font.BOLD, 48);
		subtitleFont = new Font("TimesNewRoman", Font.ITALIC, 30);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}
	}

	void updateMenuState() {
	}

	public void updateGameState() {
		objectmanage.update();
		if (rocket.isActive == false) {
			currentState = END;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 100);
		g.setFont(subtitleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER - Start Game", 30, 500);
		g.drawString("Press SPACE - Instructions", 30, 600);

	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}

		objectmanage.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 100, 100);
		int realscore = objectmanage.getScore();
		g.setFont(subtitleFont);
		g.drawString("You scored " + realscore + " point(s)", 90, 500);

		g.drawString("Try Again", 110, 550);
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {

			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}

		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
			} else {
				currentState++;
				if (currentState == GAME) {
					startGame();
				}

			}
			if (currentState == END) {
				alienspawn.stop();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			rocket.UP = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			rocket.DOWN = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			rocket.RIGHT = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			rocket.LEFT = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == GAME) {
				objectmanage.addProjectile(rocket.getProjectile());
			}
			if (currentState == MENU) {
				JOptionPane.showMessageDialog(null, "SPACE - Fire Projectiles | WASD - Movement");

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			
				rocket.UP = false;
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			
				rocket.DOWN = false;
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			
				rocket.RIGHT = false;
			
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			
				rocket.LEFT = false;
			

		}
	}

}
