import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class soldier extends GameObject  {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	boolean right = false;
	boolean left = false;
	boolean up = false;
	boolean fall = false;
	String imageName;
	public soldier(int xVal, int yVal, int Width, int Height, String name) {
		super(xVal, yVal, Width, Height);
		// TODO Auto-generated constructor stub 
		 speed = 1;
		loadImage (name);
		imageName = name;
	}
	String getStringName() {
		return imageName;
	}
	
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x,y, 60, 60, null);
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
		y-=speed;
		
	}
	void fall() {
		y+=speed;
	}
	void update() {
		super.update();
		if(left==true) {
			left();
		}
		if(right==true) {
			right();
		}
		if(up==true) {
			jump();
		}
		if(fall==true) {
			fall();
		}
	}
	
	public bullet getProjectile() {
        return new bullet((x+width/2)+10, y-24, 10, 10);
	}
	
	void loadImage(String imageFile) {
	   
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	}
}
