import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Alien extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
		if (needImage) {
		    loadImage ("alien.png");
		}
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
	public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	
        }
      //  g.setColor(Color.red);
       // g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}
	public void update() {
		y+=speed;
		super.update();
	}

}
