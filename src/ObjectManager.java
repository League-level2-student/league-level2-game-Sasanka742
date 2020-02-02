import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	soldier player;
	ArrayList<bullet> bullets = new ArrayList<bullet>(); 
	ArrayList<enemy> enemies = new ArrayList<enemy>();
	Random random = new Random();
	public ObjectManager(soldier soldier) {
		player = soldier;
	}
	
	void addBullet(bullet b){
		bullets.add(b);
	}
	void addEnemy() {
		enemies.add(new enemy((random.nextInt(MW3.HEIGHT-400+1)+400),MW3.WIDTH,50,50));
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

