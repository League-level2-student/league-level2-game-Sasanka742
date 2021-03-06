import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

public class bullet extends GameObject {
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public bullet(int xVal, int yVal, int Width, int Height) {
		super(xVal, yVal, Width, Height);
		// TODO Auto-generated constructor stub
		speed = 4;
		
		if (needImage) {
		    loadImage ("bullet.png");
		}
	}
	void draw(Graphics g) {
		
		if (gotImage) {
			g.drawImage(image, x, y+55, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
	void enemyUpdate() {
		speed = 5;
		x-=speed;
		super.update();
		collisionBox.setBounds(x, y+55, width, height);
	}
	void reflect() {
		x+=speed;
		super.update();
		collisionBox.setBounds(x, y+55, width, height);
	}
	void reflectUp() {
		speed = 5;
		x+=speed;
		y+=1;
		super.update();
		collisionBox.setBounds(x, y+55, width, height);
	}
	void reflectDown() {
		speed = 5;
		x+=speed;
		y-=1;
		super.update();
		collisionBox.setBounds(x, y+55, width, height);
	}
	void update() {
		x+=speed;
		super.update();
		collisionBox.setBounds(x, y+55, width, height);
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
