
public class Enemy extends GameCharcter {
 
	private int strength; 
	
	
	public Enemy(String path, int x, int y, int speedField, int strength) { 
		super(path, x, y, speed); 
		this.strength = strength; 
	}
	
	public int setStrength() { 
		return strength; 
		
	}
	
	
	
}
