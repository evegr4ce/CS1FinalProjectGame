import java.util.Random;

/**
 * Enemy is a child class of Character and Sprite representing the boss
 * that the user controls
 */
public class Enemy extends Character {
 
	private int strength; //Strength of the enemy
	private boolean active = false; //Whether the enemy is active and moving
	private int direction = 1; //Current movement direction (1 = right, -1 = left)
	private Random rand = new Random(); //Random number generator used for random movement
	
	/**
	 * Constructor for enemy
	 * @param path - path to image file for enemy
	 * @param x - initial x coordinate of enemy
	 * @param y - initial y coordinate of enemy
	 * @param speed - speed of the enemy
	 * @param strength - strength of enemy
	 */
	public Enemy(String path, int x, int y, int speed, int strength) { 
		super(path, x, y, speed); 
		this.strength = strength; 
	}
	
	/**
	 * Returns strength of the enemy
	 * @return strength as an int
	 */
	public int getStrength() { 
		return strength; 
	}
	
	/**
	 * Allows the enemy to start moving
	 */
	public void activate() {
		active = true;
	}
	
	/**
	 * Update enemy's random movement for direction and position
	 */
	public void update() {
		if (!active) {
			return;
		}
		
		if (rand.nextInt(100)<3) {
			direction *= -1;
		}
		
		updateX(direction * getSpeed());
		
	}

	/**
	 * Collision detection between sprite Enemy and sprite Hero
	 */
	@Override
	public boolean collidedWith(Sprite other) {
		if (other instanceof Hero hero && hero.isAttacking()) {
			//hero.resetAttack();
			return true;
		}
		return false;
	}
	
	
	
}
