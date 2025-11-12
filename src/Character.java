/**
 * Child class of sprite representing a character in our game
 */
public abstract class Character extends Sprite{
    
	private int speed; //Speed of character
	
	/**
	 * Character constructor
	 * @param path - Path to image file for character
	 * @param x - Initial x coordinate of character
	 * @param y - Initial y coordinate of character
	 * @param speed - speed of character
	 */
	public Character(String path, int x, int y, int speed) {
		super(path, x, y);
		this.speed = speed;
	}
	
	/**
	 * Gets the speed of character
	 * @return - returns the speed of character
	 */
	public int getSpeed() {
		return speed;
	}
	
}