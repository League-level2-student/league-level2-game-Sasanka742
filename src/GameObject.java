import java.awt.Rectangle;

public class GameObject {
	 int x;
	 int y;
	 int width;
	 int height;
	 int speed = 0;
	 boolean isActive = true;
	 Rectangle collisionBox;
		
	 public GameObject (int xVal, int yVal, int Width, int Height) {
			x = xVal;
			y = yVal;
			width = Width;
			height = Height;
			collisionBox = new Rectangle(xVal, yVal, Width, Height);
	 }
	 void update() {
		 collisionBox.setBounds(x, y, width, height);
	 }
}
