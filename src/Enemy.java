
public class Enemy extends Character {
 
	private int strength; 
	
	
	public Enemy(String path, int x, int y, int speed, int strength) { 
		super(path, x, y, speed); 
		this.strength = strength; 
	}
	
	public int setStrength() { 
		return strength; 
		
	}

	@Override
	public boolean collidedWith(Sprite other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
