import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Sprite {
	private int x = 0;
	private int y = 0;
	private BufferedImage image = null;
	
	public Sprite(String path, int x, int y) {
		this.x = x;
		this.y = y;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		
	}
	
	public void setX(int new_x) {
		x = new_x;
	}
	
	public void setY(int new_y) {
		y = new_y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void updateX(int delta) {
		x += delta;
	}
	
	public void updateY(int delta) {
		y += delta;
	}
	
	public boolean overlaps(Sprite otherSprite) {
		//Capture heros boundaries
		Rectangle ourBounds = new Rectangle();
		ourBounds.setSize(getWidth(), getHeight());
		ourBounds.setLocation(x,y);
		
		//Capture other sprite's boundaries
		Rectangle otherBounds = new Rectangle();
		otherBounds.setSize(otherSprite.getWidth(), otherSprite.getHeight());
		otherBounds.setLocation(otherSprite.getX(), otherSprite.getY());
		
		return ourBounds.intersects(otherBounds);
	}
	
	public abstract boolean collidedWith(Sprite other);
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	void draw(Graphics game) {
		game.setColor(Color.BLACK);
		game.drawImage(image, x, y, null);
	}
}
