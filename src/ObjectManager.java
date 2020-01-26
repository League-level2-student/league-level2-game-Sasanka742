import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
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
	void addEnemy(enemy e) {
		enemies.add(new enemy(400,MW3.HEIGHT-100,50,50));
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
}

