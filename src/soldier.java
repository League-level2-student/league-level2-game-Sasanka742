import java.awt.Color;
import java.awt.Graphics;

public class soldier extends GameObject{

	public soldier(int xVal, int yVal, int Width, int Height) {
		super(xVal, yVal, Width, Height);
		// TODO Auto-generated constructor stub
	}
	
	void draw(Graphics g) {
		   g.setColor(Color.BLUE);
	       g.fillRect(x, y, width, height);
	}
}
