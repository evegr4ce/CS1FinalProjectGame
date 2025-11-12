/**
 * Hero is a child class of Character and Sprite representing the main hero
 * that the user controls
 */
public class Hero extends Character{

	private String heroName; //Name of hero
	private boolean isJumping = false; //Whether the hero is jumping
	private double velocityY = 0; //Velocity of hero's vertical movement
	private final double gravity = 0.8; //Gravity affecting the hero's jumps
	private final double jumpStrength = -10; //Strength (height) of hero's jumps
	private boolean isAttacking = false; //Whether the hero is attacking enemy
	private int groundY; //ground level position of the hero to return to from jumping
	
	/**
	 * Constructor for hero
	 * @param path - path to image file for the Hero
	 * @param x - initial x coordinate of the Hero
	 * @param y - initial y coordinate of the Hero
	 * @param speed - movement speed of Hero
	 * @param heroName - name of the Hero
	 */
	public Hero(String path, int x, int y, int speed, String heroName) {
		super(path, x, y, speed);
		this.heroName = heroName;
		groundY = y;
	}
	
	/**
	 * Updates the hero when jumping and returns the hero back to the "ground"
	 */
	public void update() {
	    int groundLevel = groundY;

	    if (isJumping) {
	        velocityY += gravity;
	        setY((int) (getY() + velocityY));

	        if (getY() >= groundLevel) {
	            setY(groundLevel);
	            velocityY = 0;
	            isJumping = false;
	        }
	    } else {
	        if (getY() > groundLevel) {
	            setY(groundLevel);
	        }
	    }
	}


	/**
	 * Allows the hero to move upward in maze levels and jump in boss level fights
	 */
	public void moveUp() {
	    if (!isJumping && (MushroomLevelBossFight.getCurrentLevel() instanceof MushroomLevelBossFight 
	    		||IceLevelBossFight.getCurrentLevel() instanceof IceLevelBossFight 
	    		||CrystalLevelBossFight.getCurrentLevel() instanceof CrystalLevelBossFight)) {
	        isJumping = true;
	        velocityY = jumpStrength;
	    } else {
	        updateY(-getSpeed());
	    }
	}
	
	/**
	 * Allows the hero to move down only in maze levels 
	 */
	public void moveDown() {
		if (!(MushroomLevelBossFight.getCurrentLevel() instanceof MushroomLevelBossFight
				|| IceLevelBossFight.getCurrentLevel() instanceof IceLevelBossFight 
	    		||CrystalLevelBossFight.getCurrentLevel() instanceof CrystalLevelBossFight)) {
            updateY(getSpeed());
        }
	}
	
	/**
	 * Moves the hero left
	 */
	public void moveLeft() {
		updateX(- getSpeed());
	}
	
	/**
	 * Moves the hero right
	 */
	public void moveRight() {
		updateX(getSpeed());
	}
	
	/**
	 * State of hero when attacking
	 */
	public void attack() {
		isAttacking = true;
	}
	
	/**
	 * Checks if the hero is attacking
	 * @return - true if attacking, false if not
	 */
	public boolean isAttacking() {
		return isAttacking;
	}
	
	/**
	 * State of hero when not attacking
	 */
	public void resetAttack() {
		isAttacking = false;
	}
	
	/**
	 * Collision detection between sprite Hero and sprite Item
	 */
	public boolean collidedWith(Sprite other) {
		if (other instanceof Item) {
			return true;
		}
		return false;
	}
	
}
