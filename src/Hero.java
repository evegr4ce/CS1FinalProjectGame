
public class Hero extends Character{

	private String heroName;
	private boolean isJumping = false;
	private double velocityY = 0;
	private final double gravity = 0.8;
	private final double jumpStrength = -15;
	private boolean isAttacking = false;
	
	public Hero(String path, int x, int y, int speed, String heroName) {
		super(path, x, y, speed);
		this.heroName = heroName;
	}
	
	public void update() {
		if (isJumping) {
			velocityY += gravity;
			updateY((int) velocityY);
			if (getY() >= Game.getWindowHeight() - getHeight() - 50) {
                setY(Game.getWindowHeight() - getHeight() - 50);
                velocityY = 0;
                isJumping = false;
            }
		}
	}
	
	public void moveUp() {
		if (!isJumping) {
			isJumping = true;
			velocityY = jumpStrength;
		}
	}
	
	public void moveDown() {
		updateY(getSpeed());
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
