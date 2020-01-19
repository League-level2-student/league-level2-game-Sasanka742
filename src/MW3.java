import javax.swing.JFrame;

public class MW3 {
	JFrame frame;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	
	GamePanel panel;
	public MW3() {
		frame = new JFrame();
		panel = new GamePanel();
	}
	
	public static void main(String[] args) {	
		MW3 game = new MW3();
		game.setup();
	}
	
	void setup() {
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(WIDTH,HEIGHT);
		frame.addKeyListener(panel);
	}
}
