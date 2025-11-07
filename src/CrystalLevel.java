import java.awt.*;

import javax.swing.*;

public class CrystalLevel extends Game {

	public CrystalLevel() {
		super();
	}
	
	@Override
	public void setBackground() {
		BackgroundPanel backgroundPanel = new BackgroundPanel("titlescreen.jpg");
	    setContentPane(backgroundPanel);
	}
}
