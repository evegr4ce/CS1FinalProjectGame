import java.awt.*; 

public class Item extends Sprite{
	private double value; 
	
	public Item(String path,int x, int y, double value ) { 
		super(path, x, y); 
		this.value = _value; 
	}
	public double getValue() { 
		return this.value; 
	}

}
