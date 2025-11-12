import java.awt.*;

import javax.swing.*;

public class IceLevel extends Game {

	public IceLevel() {
		super();
	}

	@Override
	public void setBackground() {
		BackgroundPanel backgroundPanel = new BackgroundPanel("islandscreen.jpg");
        setContentPane(backgroundPanel);
	}
	
	
}
