import java.util.Random;

public class Enemy extends Character {
 
	private int strength; 
	private boolean active = false;
	private int direction = 1;
	private Random rand = new Random();
	
	
	public Enemy(String path, int x, int y, int speed, int strength) { 
		super(path, x, y, speed); 
		this.strength = strength; 
	}
	
	public int getStrength() { 
		return strength; 
	}
	
	public void activate() {
		active = true;
	}
	
	public void update() {
		if (!active) {
			return;
		}
		
		if (rand.nextInt(100)<3) {
			direction *= -1;
		}
		
		updateX(direction * getSpeed());
		
		if (getX() < 0 || getX() > Game.getWindowWidth() - getWidth()) {
			direction *= -1;
		}
	}

	@Override
	public boolean collidedWith(Sprite other) {
		if (other instanceof Hero hero && hero.isAttacking()) {
			hero.resetAttack();
			return true;
		}
		return false;
	}
	
	
	
}
