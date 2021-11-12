import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public boolean UP = false;
	public boolean DOWN = false;
	public boolean RIGHT = false;
	public boolean LEFT = false;

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		if (needImage) {
			loadImage("rocket.png");
		}
	}

	public Projectile getProjectile() {
		return new Projectile(x + width / 2, y, 10, 10);
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

	void draw(Graphics g) {

		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);

		}
		// g.setColor(Color.red);
		// g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width,
		// collisionBox.height);

	}

	public void update() {

		super.update();

		if (UP == true) {
			y -= speed;

		}

		if (DOWN == true) {
			y += speed;

		}

		if (RIGHT == true) {
			x += speed;

		}

		if (LEFT == true) {
			x -= speed;

		}

		
		if (y < 0) {
			y = 0;

		}
		if (y + height  > LeagueInvaders.HEIGHT) {
			y = LeagueInvaders.HEIGHT - height ;

		}
		if (x  + width > LeagueInvaders.WIDTH) {

			x = LeagueInvaders.WIDTH - width;
		}
		if (x  < 0) {
			x = 0;

		}
	}
}
