import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
int savedScore=0;
int spawnRate=1000;
int waveNum = 1;
int number = 1;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
    final int MENU = 0;
    final int GAME = 1;
    final int WAVE = 2;
    final int END = 3;
	Random random = new Random();
	int currentState = MENU;
	
	Font titleFont;
	
	Timer frameDraw;
	Timer enemySpawn;
	
	soldier player = new soldier(100,300,50,50);
	
	ObjectManager manager;
	
	public GamePanel() {
		manager = new ObjectManager(player);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		frameDraw = new Timer(1000/120, this);
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
		}else if(currentState == WAVE){
			drawWaveState(g);
		}
	}
	void startGame() {
		if(manager.getScore()/20==number&&manager.getScore()!=0) {
			spawnRate = spawnRate-100;
		}
		manager.changeEnemySpeed();
		enemySpawn = new Timer(spawnRate , manager);
	    enemySpawn.start();
	}
	void updateMenuState() {
		
	}
	void updateGameState() {
		
		manager.update();
		if(player.isActive==false) {
			currentState = END;
		}
		if(manager.getScore()/20==number&&manager.getScore()!=0) {
			spawnRate = spawnRate-100;
			waveNum++;
			currentState = WAVE;
		}
		
			if(manager.getScore()>200&&manager.getScore()<40000) {
				System.out.println("SYSTEM OVERLOAD!!!");
			}
			if(manager.getScore()>50000) {
				System.out.println("SYSTEM ENTERING GOD MODE");
			}
	}
	void updateEndState() {
		
	}
	void updateWaveState() {
		
	}
	////////////
	void drawMenuState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, MW3.WIDTH, MW3.HEIGHT, null);
		}else {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, MW3.WIDTH, MW3.HEIGHT);
		}
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("Ghost Warfare 3",550,150);
		
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("Press Enter to Start",500,250);
	}
	void drawGameState(Graphics g){
		if (gotImage) {
			g.drawImage(image, 0, 0, MW3.WIDTH, MW3.HEIGHT, null);
			g.setColor(Color.WHITE);
			g.drawString("Score: "+ manager.getScore(), 10, 15);
		}else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		manager.draw(g);
		
	}
	
	void drawEndState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, MW3.WIDTH, MW3.HEIGHT, null);
		}else {
		g.setColor(Color.RED);
		g.fillRect(0, 0, MW3.WIDTH, MW3.HEIGHT);
		}
		
		if(player.isActive == false) {
			g.setFont(titleFont);
			g.setColor(Color.white);
			g.drawString("Wave " + waveNum,550,150);
			
			g.setFont(titleFont);
			g.setColor(Color.white);
			g.drawString("Press Enter for next Wave",500,250);
		}else {
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("GAME OVER",550,150);
		
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("Press Enter to Start",500,250);
		}
	}
	void drawWaveState(Graphics g) {
		if(player.isActive == true) {
			g.setFont(titleFont);
			g.setColor(Color.white);
			g.drawString("Wave " + waveNum,550,150);
			
			g.setFont(titleFont);
			g.setColor(Color.white);
			g.drawString("Press Enter for next Wave",350,250);
		}
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
		}else if(currentState == WAVE){
		    updateWaveState();
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
		    	player = new soldier(100,300,50,50);
				manager = new ObjectManager(player);
		        currentState = MENU;
		        manager.setScore(0);
		    } else if(currentState==WAVE&&waveNum>1&&manager.getScore()/20==number) {
		    	player = new soldier(100,300,50,50);
		    	savedScore= manager.getScore();
		    	manager = new ObjectManager(player);
				number++;
				manager.setScore(savedScore);
				manager.changeEnemySpeed();
				currentState = GAME;
				startGame();
		    }else {
		    	 currentState++;
			        startGame();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    System.out.println("JUMP"); 
		    if(player.y>MW3.HEIGHT-450){
		    	player.up = true;
		    }
		}
		if(player.y<300) {
    		player.up = false;
    	}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    System.out.println("CROUCH");
		    if(player.y<MW3.HEIGHT-player.height) {
				//System.out.println("DOWN");
		    	player.fall = true;
			}   
		}
		if(player.y>350) {
			player.fall=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    System.out.println("RIGHT");
		    if(player.x<=MW3.WIDTH-(player.width)-player.speed) {
				//System.out.println("RIGHT");
				player.right = true;
			}
		}if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    System.out.println("LEFT");
		    if(player.x>=player.speed) {
				player.left = true;
			}
		}if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			System.out.println("Fire!!");
			if(currentState==GAME) {
				manager.addBullet(player.getProjectile());
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    System.out.println("JUMP");
		   // player.fall();
		    player.up = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.right=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    System.out.println("JUMP");
		   // player.fall();
		    player.fall = false;
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