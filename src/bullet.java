import java.awt.Color;
import java.awt.Graphics;

public class bullet extends GameObject {

	public bullet(int xVal, int yVal, int Width, int Height) {
		super(xVal, yVal, Width, Height);
		// TODO Auto-generated constructor stub
		speed = 10;
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
	}
	void update() {
		x+=speed;
	}
}
