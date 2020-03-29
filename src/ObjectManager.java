import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	soldier player;
	ArrayList<bullet> bullets = new ArrayList<bullet>(); 
	ArrayList<enemy> enemies = new ArrayList<enemy>();
	Random random = new Random();
	int kills;
	int speedValue;
	public ObjectManager(soldier soldier) {
		player = soldier;
	}
	void setPlayer(soldier soldier) {
		player = soldier;
	}
	void changeEnemySpeed() {
		speedValue++;
	}
	int getEnemySpeed() {
			return enemies.get(0).getSpeed();
	}
	void addBullet(bullet b){
		bullets.add(b);
		
	}
	void addEnemy() {
		enemies.add(new enemy(MW3.WIDTH+100,(random.nextInt(MW3.HEIGHT-450+1)+310),50,50,3));
	}
	
	void update(){
		for(enemy enemy: enemies) {
			enemy.update();
			if(enemy.x<0) {
				
				enemy.isActive = false;
				player.isActive = false;
			}
		}
		
		for(bullet bullet: bullets) {
			bullet.update();
			if(bullet.x>MW3.WIDTH) {
				bullet.isActive = false;
			}
		}
		player.update();
		if(player.isActive==true) {
			checkCollision();
			purgeObjects();
		}
	}
	void draw(Graphics g) {
		player.draw(g);
		for(enemy enemy: enemies) {
			enemy.draw(g);
		}
		for(bullet bullet: bullets) {
			bullet.draw(g);
		}
		
	}
	int getScore() {
		return kills;
	}
	void setScore(int score) {
		kills=score;
	}
	void checkCollision() {
		for(int i=0;i<enemies.size();i++) {
			if(player.collisionBox.intersects(enemies.get(i).collisionBox)&&(player.getStringName().equals("defend.png")==false)) {
				player.isActive = false;
				enemies.get(i).isActive = false;
			}
		}
		for(int i=0;i<enemies.size();i++) {
			for(int k=0;k<bullets.size();k++) {
				if(bullets.get(k).collisionBox.intersects(enemies.get(i).collisionBox)) {
					enemies.get(i).isActive = false;
					bullets.get(k).isActive = false;
					kills++;
				}
			}
		}
	}
	void purgeObjects() {
		for(int i=0;i<bullets.size();i++) {
			if(bullets.get(i).isActive==false) {
				bullets.remove(i);
			}
		}
		for(int i=0;i<enemies.size();i++) {
			if(enemies.get(i).isActive==false) {
				enemies.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addEnemy();
	}
}

