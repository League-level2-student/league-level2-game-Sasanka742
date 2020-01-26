import java.awt.Color;
import java.awt.Graphics;

public class soldier extends GameObject{

	public soldier(int xVal, int yVal, int Width, int Height) {
		super(xVal, yVal, Width, Height);
		// TODO Auto-generated constructor stub
		 speed = 10;
	}
	
	void draw(Graphics g) {
		   g.setColor(Color.BLUE);
	       g.fillRect(x, y, width, height);
	       
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
}
