
public class Hero extends Character{

	private String heroName;
	private boolean isJumping = false;
	private double velocityY = 0;
	private final double gravity = 0.8;
	private final double jumpStrength = -10;
	private boolean isAttacking = false;
	private int groundY;
	
	public Hero(String path, int x, int y, int speed, String heroName) {
		super(path, x, y, speed);
		this.heroName = heroName;
		groundY = y;
	}
	
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


	
	public void moveUp() {
	    if (!isJumping && MushroomLevelBossFight.getCurrentLevel() instanceof MushroomLevelBossFight) {
	        isJumping = true;
	        velocityY = jumpStrength;
	    } else {
	        updateY(-getSpeed());
	    }
	}
	
	public void moveDown() {
		if (!(MushroomLevelBossFight.getCurrentLevel() instanceof MushroomLevelBossFight)) {
            updateY(getSpeed());
        }
	}
	
	public void moveLeft() {
		updateX(- getSpeed());
	}
	
	public void moveRight() {
		updateX(getSpeed());
	}
	
	public void attack() {
		isAttacking = true;
	}
	
	public boolean isAttacking() {
		return isAttacking;
	}
	
	public void resetAttack() {
		isAttacking = false;
	}
	
	public boolean collidedWith(Sprite other) {
		if (other instanceof Item) {
			return true;
		}
		return false;
	}
	
}
