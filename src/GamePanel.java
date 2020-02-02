import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
	
	int currentState = MENU;
	
	Font titleFont;
	Timer frameDraw;
	Timer enemySpawn;
	
	soldier player = new soldier(100,300,50,50);
	
	ObjectManager manager = new ObjectManager(player);
	
	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		if (needImage) {
		    loadImage ("map.png");
		}
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	void startGame() {
		enemySpawn = new Timer(1000 , manager);
	    enemySpawn.start();
	}
	void updateMenuState() {
		
	}
	void updateGameState() {
		manager.update();
	}
	void updateEndState() {
		
	}
	////////////
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, MW3.WIDTH, MW3.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("MW3",350,150);
		
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("Press Enter to Start",200,250);
	}
	void drawGameState(Graphics g){
		if (gotImage) {
			g.drawImage(image, 0, 0, MW3.WIDTH, MW3.HEIGHT, null);
		}else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		manager.draw(g);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, MW3.WIDTH, MW3.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("GAME OVER",250,150);
		
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("Press Enter to Start",200,250);
	}
	////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Action");
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		        startGame();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    System.out.println("JUMP"); 
		   player.jump();
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    System.out.println("CROUCH");
		    if(player.y<MW3.HEIGHT-player.height) {
				//System.out.println("DOWN");
		    	
			}
		    
		}if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    System.out.println("RIGHT");
		    if(player.x<=MW3.WIDTH-(player.width)-player.speed) {
				//System.out.println("RIGHT");
				player.right();
			}
		}if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    System.out.println("LEFT");
		    if(player.x>=player.speed) {
				player.left();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    System.out.println("JUMP");
		    player.fall();
		    
		}
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