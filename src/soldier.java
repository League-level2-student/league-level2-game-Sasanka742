import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class soldier extends GameObject{

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	public soldier(int xVal, int yVal, int Width, int Height) {
		super(xVal, yVal, Width, Height);
		// TODO Auto-generated constructor stub
		 speed = 10;
		 if (needImage) {
			    loadImage ("Gunner.png");
		}
	}
	
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x,y, 70, 100, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
	void right() {
		x+=speed;
	}
	void  left() {
		x-=speed;
	}
	void jump() {
		y-=speed +2*(4.8)*(speed) ;
		
	}
	void fall() {
		y+=speed +2*(4.8)*(speed) ;
	}
	void crouch(Graphics g) {
		 g.setColor(Color.RED);
	     g.fillRect(x, y, width, height);
	}
	
	public bullet getProjectile() {
        return new bullet(x+width/2, y, 10, 10);
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
