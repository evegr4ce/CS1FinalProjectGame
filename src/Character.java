
public abstract class Character extends Sprite{
    
	private int speed;
	
	public Character(String path, int x, int y, int speed) {
		super(path, x, y);
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
}