import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class enemy extends GameObject {
	int step;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	bullet enemyBullet;
	public enemy(int xVal, int yVal, int Width, int Height, int setSpeed) {
		super(xVal, yVal, Width, Height);
		// TODO Auto-generated constructor stub
		
		speed = setSpeed;
		if (needImage) {
		    loadImage ("enemy1.png");
		}
		 enemyBullet = new bullet(x, y, 8, 8);
		 
	}
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, 60, 40, null);
			enemyBullet.draw(g);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
	int getSpeed() {
		return speed;
	}
	void setSpeed(int speed) {
		this.speed = speed;
	}
	void update() {
		x-=speed;
		super.update();
	}
	void bulletReflect() {
		 enemyBullet = new bullet(x, y, 8, 8);
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

	
}
