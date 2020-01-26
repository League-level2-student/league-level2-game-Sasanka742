import java.awt.Color;
import java.awt.Graphics;

public class enemy extends GameObject {

	public enemy(int xVal, int yVal, int Width, int Height) {
		super(xVal, yVal, Width, Height);
		// TODO Auto-generated constructor stub
		speed = 1;
	}
	
	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
	}
	void update() {
		x-=speed;
	}

}
