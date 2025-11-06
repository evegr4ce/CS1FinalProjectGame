
public class Hero extends Character{

	private String heroName;
	
	public Hero(String path, int x, int y, int speed, String heroName) {
		super(path, x, y, speed);
		this.heroName = heroName;
	}
	
	public void update() {
		
	}
	
	public void moveUp() {
		updateY(- getSpeed());
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
	
	public boolean collidedWith(Sprite other) {
		if (other instanceof Item) {
			return true;
		}
		return false;
	}
	
}
